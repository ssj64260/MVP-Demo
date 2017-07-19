package com.cxb.mvp_project.ui.main;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.cxb.mvp_project.R;
import com.cxb.mvp_project.app.BaseAppCompatActivity;
import com.cxb.mvp_project.widget.imageloader.ImageLoaderFactory;
import com.cxb.mvp_project.widget.imageloader.ImageLoaderWrapper;

/**
 * 干货大图显示 //TODO 写behavior，研究MD布局用法
 */
public class GankShowImageActivity extends BaseAppCompatActivity {

    public static final String KEY_PICTURE_URL = "key_picture_url";//图片链接

    private Toolbar mToolbar;
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
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    private void setData() {
        mToolbar.setLogo(R.drawable.ic_welfare);
        mToolbar.setSubtitle(R.string.tab_title_welfare);
        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.tab_title_welfare);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mLoader = ImageLoaderFactory.getLoader();

        final String url = getIntent().getStringExtra(KEY_PICTURE_URL);

        mLoader.loadImageFitCenter(this, mImage, url, 0, 0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
