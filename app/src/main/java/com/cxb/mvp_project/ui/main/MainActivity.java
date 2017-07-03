package com.cxb.mvp_project.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cxb.mvp_project.R;
import com.cxb.mvp_project.app.BaseActivity;
import com.cxb.mvp_project.ui.familytree.FamilyTreeActivity;
import com.cxb.mvp_project.utils.NoDoubleClick;
import com.cxb.mvp_project.utils.ToastMaster;

public class MainActivity extends BaseActivity {

    private TextView tvNot;
    private TextView tvHave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setData();

    }

    @Override
    public void onBackPressed() {
        if (NoDoubleClick.isExitClick()) {
            finish();
        } else {
            ToastMaster.toast(getString(R.string.tosat_double_click_to_exit));
        }
    }

    private void initView() {
        tvNot = (TextView) findViewById(R.id.tv_not_foster_parent);
        tvHave = (TextView) findViewById(R.id.tv_have_foster_parent);
    }

    private void setData() {
        tvNot.setOnClickListener(click);
        tvHave.setOnClickListener(click);
    }

    //点击监听
    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_not_foster_parent:
                    startActivity(new Intent(MainActivity.this, FamilyTreeActivity.class));
                    break;
                case R.id.tv_have_foster_parent:
                    startActivity(new Intent(MainActivity.this, FamilyTreeActivity.class).putExtra(FamilyTreeActivity.HAVE_FOSTER_PARENT, true));
                    break;
            }
        }
    };
}
