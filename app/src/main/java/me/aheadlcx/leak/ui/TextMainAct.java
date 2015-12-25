package me.aheadlcx.leak.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import me.aheadlcx.leak.R;


/**
 * Description:
 * Creator: aheadlcx
 * Date:15/12/11 下午3:12
 */
public class TextMainAct extends BaseActivity implements View.OnClickListener {

    private TextView txtLoadingAct;
    private TextView txtBigImageAct;
    private TextView txtClickViewAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_test_main);
        this.txtBigImageAct = (TextView) findViewById(R.id.txtBigImageAct);
        this.txtLoadingAct = (TextView) findViewById(R.id.txtLoadingAct);
        txtLoadingAct.setOnClickListener(this);
        txtBigImageAct.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.txtLoadingAct:
                startActivity(new Intent(this, TestLoadingAct.class));
                break;
            case R.id.txtBigImageAct:
                startActivity(new Intent(this, TestBigImageAct.class));
                break;
            default:
                break;
        }
    }
}
