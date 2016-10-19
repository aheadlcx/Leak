package me.aheadlcx.leak.test;

import android.content.Context;
import android.util.AttributeSet;

import com.wkzf.library.component.player.view.WKVideoView;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/10/19 下午4:24
 */

public class TestView extends WKVideoView {
    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
