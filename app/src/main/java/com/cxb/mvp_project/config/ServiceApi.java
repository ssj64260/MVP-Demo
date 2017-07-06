package com.cxb.mvp_project.config;

import com.cxb.mvp_project.model.FamilyBean;
import com.cxb.mvp_project.model.GankResultBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 网络请求接口 api
 */

public interface ServiceApi {

    @POST("json/member/getTrees.htm")
    Observable<List<FamilyBean>> getFamilyTreeCall(@Query("id") String id);

    @GET("data/{type}/{totalCount}/{page}")
    Observable<GankResultBean> getGankNews(@Path("type") String type, @Path("totalCount") int totalCount, @Path("page") int page);

}
