package com.cxb.mvp_project.widget.familytree;


import android.view.View;

import com.cxb.mvp_project.model.FamilyBean;

/**
 * 家庭成员选中回调
 */

public interface OnFamilySelectListener {
    void onFamilySelect(View view, FamilyBean family);
}
