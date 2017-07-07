package com.cxb.mvp_project.ui.familytree;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.cxb.mvp_project.R;
import com.cxb.mvp_project.app.BaseActivity;
import com.cxb.mvp_project.model.FamilyBean;
import com.cxb.mvp_project.widget.imageloader.GlideCircleTransform;
import com.cxb.mvp_project.widget.imageloader.ImageLoaderFactory;
import com.cxb.mvp_project.widget.imageloader.ImageLoaderWrapper;

/**
 * 展示用户信息
 */

public class UserInfoActivity extends BaseActivity {

    public static final String KEY_USER_INFO = "key_user_info";//用户信息

    private ImageView ivAvatar;
    private TextView tvName;
    private TextView tvCall;

    private FamilyBean family;

    private ImageLoaderWrapper mImageLoader;
    private GlideCircleTransform mTransform;//画圆类

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        initView();
        setData();

    }

    private void initView() {
        ivAvatar = (ImageView) findViewById(R.id.iv_avatar);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvCall = (TextView) findViewById(R.id.tv_call);
    }

    private void setData() {
        family = getIntent().getParcelableExtra(KEY_USER_INFO);
        String url = family.getMemberImg();
        String name = family.getMemberName();
        String call = family.getCall();

        mTransform = new GlideCircleTransform(this);
        mImageLoader = ImageLoaderFactory.getLoader();
        mImageLoader.loadImageCenterCrop(this, ivAvatar, url, 0, 0, mTransform);

        tvName.setText(name);
        tvCall.setText("(" + call + ")");
    }
}
