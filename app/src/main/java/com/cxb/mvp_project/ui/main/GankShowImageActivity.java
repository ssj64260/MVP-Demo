package com.cxb.mvp_project.ui.main;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.cxb.mvp_project.R;
import com.cxb.mvp_project.widget.imageloader.ImageLoaderFactory;
import com.cxb.mvp_project.widget.imageloader.ImageLoaderWrapper;

/**
 * 干货大图显示 //TODO 写behavior，研究MD布局用法
 */
public class GankShowImageActivity extends Activity {

    public static final String KEY_PICTURE_URL = "key_picture_url";//图片链接

    private ImageView mImage;
    private ImageLoaderWrapper mLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gank_show_image);

        initView();
        setData();

    }

    private void initView() {
        mImage = (ImageView) findViewById(R.id.iv_picture);
    }

    private void setData() {
        mLoader = ImageLoaderFactory.getLoader();

        final String url = getIntent().getStringExtra(KEY_PICTURE_URL);

        mLoader.loadImageFitCenter(this, mImage, url, 0, 0);
    }
}
