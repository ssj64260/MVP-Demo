package com.cxb.mvp_project.model;

import com.cxb.mvp_project.config.ServiceApi;
import com.cxb.mvp_project.service.ServiceClient;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 干货资讯 逻辑处理
 */

public class GankNewsModel {

    public Observable<GankResultBean> loadGankNewsData(String type, int totalCount, int page) {
        ServiceApi api = ServiceClient.getService();

        return api.getGankNews(type, totalCount, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
