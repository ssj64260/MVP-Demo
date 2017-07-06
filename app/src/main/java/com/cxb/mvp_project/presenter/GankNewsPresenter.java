package com.cxb.mvp_project.presenter;

import com.cxb.mvp_project.model.GankNewsBean;
import com.cxb.mvp_project.model.GankNewsModel;
import com.cxb.mvp_project.model.GankResultBean;
import com.cxb.mvp_project.view.IGankNewsView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * 干货资讯 presenter
 */

public class GankNewsPresenter implements IBasePresenter<IGankNewsView> {

    private IGankNewsView mView;
    private GankNewsModel mModel;

    public GankNewsPresenter() {
        mModel = new GankNewsModel();
    }

    public void getGankNewsData(String type, int totalCount, final int page) {
        mModel.loadGankNewsData(type, totalCount, page)
                .subscribe(new Consumer<GankResultBean>() {
                    @Override
                    public void accept(@NonNull GankResultBean result) throws Exception {
                        if (isActive()) {
                            if (result.isError()) {
                                loadFailure();
                            } else {
                                loadSuccess(result.getResults(), page);
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        if (isActive()) {
                            loadFailure();
                        }
                    }
                });
    }

    private void loadSuccess(List<GankNewsBean> gankNewsList, int page) {
        if (gankNewsList == null) {
            gankNewsList = new ArrayList<>();
        }
        if (page == 1) {
            mView.clearNewsList();
        }
        mView.updateNewsList(gankNewsList);
        mView.hideProgress();
    }

    private void loadFailure() {

    }

    private boolean isActive() {
        return mView != null;
    }

    @Override
    public void attachView(IGankNewsView view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
