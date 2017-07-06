package com.cxb.mvp_project.widget.imageloader;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.cxb.mvp_project.R;
import com.cxb.mvp_project.app.APP;

/**
 * 加载图片实现
 */

public class ImageLoader implements ImageLoaderWrapper {

    private final int placeholder = R.drawable.ic_no_image;
    private final int errorImage = R.drawable.ic_no_image;

    @Override
    public void loadWithoutAnimate(ImageView imageView, String url, BitmapTransformation transformation, int placeholder, int errorImage) {
        Glide.with(APP.getInstance())
                .load(url)
                .placeholder(placeholder)
                .error(errorImage)
                .centerCrop()
                .transform(transformation)
                .dontAnimate()
                .into(imageView);
    }

    @Override
    public void loadImage(ImageView imageView, String url) {
        Glide.with(APP.getInstance())
                .load(url)
                .placeholder(placeholder)
                .error(errorImage)
                .centerCrop()
                .dontAnimate()
                .into(imageView);
    }
}
