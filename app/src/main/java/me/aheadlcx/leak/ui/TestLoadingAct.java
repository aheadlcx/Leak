package me.aheadlcx.leak.ui;

import android.os.Bundle;

import me.aheadlcx.leak.R;
import me.aheadlcx.leak.view.TestLoadingView;

/**
 * Description:
 * Creator: aheadlcx
 * Date:15/12/11 下午3:07
 */
public class TestLoadingAct extends BaseActivity {

    private TestLoadingView loadingView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_test_loading_view_act);
        loadingView = (TestLoadingView) findViewById(R.id.loadingView);
    }

    @Override
    protected void onPause() {
//        if (loadingView != null) {
//            loadingView.stopAnimator();
//        }
        super.onPause();
    }
}
