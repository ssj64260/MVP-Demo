package com.cxb.mvp_project.config;

/**
 * 网络请求链接
 */

public class API {
    public static final String GANK_BASE_HOST = "http://gank.io/api/";//干货
    private static final String GANK_IMAGE = "?imageView2/0/w/";

    public static String getCankImageURL(String url, int maxWidthPX) {
        return url + GANK_IMAGE + maxWidthPX;
    }

}
