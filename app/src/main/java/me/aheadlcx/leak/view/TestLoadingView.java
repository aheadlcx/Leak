package me.aheadlcx.leak.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Interpolator;
import android.widget.ImageView;

import java.util.ArrayList;

import me.aheadlcx.leak.R;

public class TestLoadingView extends ImageView {
    private int index = 0;
    int[] images = new int[]{R.color.loading_first, R.color.loading_second, R.color
            .loading_third, R.color.loading_fourth};

    private ArrayList<AnimatorSet> AnimatorSets = new ArrayList<>();
    private boolean showAnimator = true;

    public TestLoadingView(Context context) {
        this(context, null);
    }

    public TestLoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        getAnimatorSet(index).start();
    }

    private Animator.AnimatorListener animationListener = new AnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(Animator animation) {
            if (showAnimator) {
                index++;
                getAnimatorSet(index).start();
            }
        }
    };

    @Override
    protected void onDetachedFromWindow() {
//        stopAnimator();
        super.onDetachedFromWindow();
    }

    /**
     * 获取旋转动画
     *
     * @param index
     * @return
     */
    public AnimatorSet getAnimatorSet(int index) {
        int position = index % images.length;


        setBackgroundResource(images[position]);

        AnimatorSet sets = new AnimatorSet();

        ObjectAnimator rotate = ObjectAnimator.ofPropertyValuesHolder(this, PropertyValuesHolder.ofFloat("rotation", 90 * position, 90 + 90 * position));
        rotate.setInterpolator(new RotateInterpolator());
        rotate.setDuration(700);

        ObjectAnimator alpha = ObjectAnimator.ofPropertyValuesHolder(this, PropertyValuesHolder.ofFloat("alpha", 0, 1));
        alpha.setInterpolator(new AlphaInterpolator());
        alpha.setDuration(700);

        sets.playTogether(rotate, alpha);
        sets.addListener(animationListener);
        if (!AnimatorSets.contains(sets)) {
            AnimatorSets.add(sets);
        }
        return sets;
    }

    /**
     * 自定义旋转差值器
     */
    public class RotateInterpolator implements Interpolator {

        @Override
        public float getInterpolation(float input) {
            if (input < 0.2f) {
                return (15.0f / 90.0f) * (input / 0.2f);
            } else if (input < 0.6f) {
                return (float) (15.0f / 90.0f + (10.0f / 90.0f) * (input - 0.2) / 0.4f);
            } else if (input < 0.8f) {
                return (float) (25.0f / 90.0f + (20.0f / 90.0f) * (input - 0.6) / 0.2f);
            } else {
                return (float) (45.0f / 90.0f + (45.0f / 90.0f) * (input - 0.8) / 0.2f);
            }
        }
    }

    /**
     * 自定义透明度差值器
     */
    public class AlphaInterpolator implements Interpolator {

        @Override
        public float getInterpolation(float input) {
            if (input < 0.2f) {
                return (input / 0.2f);
            } else if (input < 0.8f) {
                return 1.0f;
            } else {
                return (float) (1.0f - (input - 0.8) / 0.2f);
            }
        }
    }

    public void stopAnimator() {
        showAnimator = false;
        for (AnimatorSet set : AnimatorSets) {
            set.end();
        }
        AnimatorSets.clear();
    }

    public void startAnimator() {
        if (showAnimator){
            //already start
            return;
        }
        showAnimator = true;
        index = 0;
        getAnimatorSet(index).start();
    }
}
