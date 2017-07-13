package com.cxb.mvp_project.config;

import com.cxb.mvp_project.model.GankResultBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 网络请求接口 api
 */

public interface ServiceApi {

    @GET("data/{type}/{totalCount}/{page}")
    Observable<GankResultBean> getGankNews(@Path("type") String type, @Path("totalCount") int totalCount, @Path("page") int page);

}
