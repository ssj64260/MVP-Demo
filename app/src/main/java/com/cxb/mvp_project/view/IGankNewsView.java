package com.cxb.mvp_project.view;

import com.cxb.mvp_project.model.GankNewsBean;

import java.util.List;

/**
 * 干货资讯界面更新
 */

public interface IGankNewsView extends IBaseView {

    void setNotDataView(boolean isShow);

    void updateNewsList(List<GankNewsBean> gankNewsList);

    void clearNewsList();

}
