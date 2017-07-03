package com.cxb.mvp_project.config;

import com.cxb.mvp_project.model.FamilyBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 网络请求接口 api
 */

public interface ServiceApi {

    @POST("json/member/getTrees.htm")
    Observable<List<FamilyBean>> getFamilyTreeCall(@Query("id") String id);

}
