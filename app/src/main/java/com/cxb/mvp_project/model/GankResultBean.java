package com.cxb.mvp_project.model;

import java.util.List;

/**
 * 干货请求结果
 */

public class GankResultBean {

    private boolean error;
    private List<GankNewsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<GankNewsBean> getResults() {
        return results;
    }

    public void setResults(List<GankNewsBean> results) {
        this.results = results;
    }
}
