package com.cxb.mvp_project.presenter;

import com.cxb.mvp_project.view.IBaseView;

/**
 * presenter 基础部分
 */

public interface IBasePresenter<V extends IBaseView> {

    void attachView(V view);//绑定view

    void detachView();//解绑view

}
