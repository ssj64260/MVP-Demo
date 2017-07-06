package com.cxb.mvp_project.ui.adapter;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * RecyclerView Adapter 的点击回调
 */

public interface OnListClickListener {

    @IntDef({TAG0, TAG1, TAG2, TAG3})
    @Retention(RetentionPolicy.SOURCE)
    @interface ItemView {
    }

    int TAG0 = 0;
    int TAG1 = 1;
    int TAG2 = 2;
    int TAG3 = 3;

    //item点击事件
    void onItemClick(int position);

    //可根据tag来区分点击的是item内部哪个控件
    void onTagClick(@ItemView int tag, int position);
}
