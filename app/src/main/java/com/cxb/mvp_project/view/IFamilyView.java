package com.cxb.mvp_project.view;

import com.cxb.mvp_project.model.FamilyBean;

/**
 * 家谱树UI操作接口
 */

public interface IFamilyView extends IBaseView {

    void showFamilyTree(FamilyBean family);

    void showFailureView();

}
