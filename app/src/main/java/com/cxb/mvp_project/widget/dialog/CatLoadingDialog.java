package com.cxb.mvp_project.widget.dialog;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.widget.ImageView;

import com.cxb.mvp_project.R;

/**
 * 猫 loading
 */

public class CatLoadingDialog extends AlertDialog {

    private AnimationDrawable loadingDrawable;

    public CatLoadingDialog(@NonNull Context context) {
        super(context, R.style.LoadingDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_cat_loading);
        setCanceledOnTouchOutside(false);

        ImageView ivCatLoading = (ImageView) findViewById(R.id.iv_cat_loading);

        loadingDrawable = (AnimationDrawable) ivCatLoading.getDrawable();
    }

    @Override
    public void show() {
        super.show();
        loadingDrawable.start();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        loadingDrawable.stop();
    }
}
