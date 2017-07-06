package com.cxb.mvp_project.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.cxb.mvp_project.R;
import com.cxb.mvp_project.model.GankNewsBean;
import com.cxb.mvp_project.presenter.GankNewsPresenter;
import com.cxb.mvp_project.ui.adapter.GankNewsAdapter;
import com.cxb.mvp_project.ui.adapter.OnListClickListener;
import com.cxb.mvp_project.utils.ToastMaster;
import com.cxb.mvp_project.view.IGankNewsView;

import java.util.ArrayList;
import java.util.List;

/**
 * 干货列表 fragment
 */

public class GankNewsFragment extends Fragment implements IGankNewsView {

    public static final String ARGUMENT = "argument";

    private View mRootView;
    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private LinearLayout mPlaceholder;
    private ProgressBar mProgress;

    private List<GankNewsBean> mGankList;
    private GankNewsAdapter mGankAdapter;

    private boolean mIsFirstPager = false;
    private String mTitle;
    private final int mTotalCount = 10;
    private int mPage = 1;

    private GankNewsPresenter mGankPresenter;

    public static GankNewsFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT, title);
        GankNewsFragment gankNewsFragment = new GankNewsFragment();
        gankNewsFragment.setArguments(bundle);
        return gankNewsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            mTitle = bundle.getString(ARGUMENT);
        }

        mGankPresenter = new GankNewsPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(mTitle, "onCreateView");
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_gank_news, container, false);

            initView();
        }

        mGankPresenter.attachView(this);

        return mRootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (mRootView != null) {
                if (mGankList.size() == 0) {
                    mGankPresenter.getGankNewsData(mTitle, mTotalCount, mPage);
                }
            } else {
                mIsFirstPager = true;
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mIsFirstPager) {
            mIsFirstPager = false;
            if (mGankList.size() == 0) {
                mGankPresenter.getGankNewsData(mTitle, mTotalCount, mPage);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mGankPresenter.detachView();
    }

    private void initView() {
        mRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.srl_refresh);
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.rv_gank_list);
        mPlaceholder = (LinearLayout) mRootView.findViewById(R.id.ll_placeholder);
        mProgress = (ProgressBar) mRootView.findViewById(R.id.pb_progress);

        mRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        mRefreshLayout.setOnRefreshListener(refresh);

        mGankList = new ArrayList<>();
        mGankAdapter = new GankNewsAdapter(getActivity(), mGankList, mTitle);
        mGankAdapter.setOnListClickListener(mListClick);
        final RecyclerView.LayoutManager layoutManager;
        if (getString(R.string.tab_title_welfare).equals(mTitle)) {
            layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        } else {
            layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        }
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mGankAdapter);

    }

    private final SwipeRefreshLayout.OnRefreshListener refresh = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mPage = 1;
            mGankPresenter.getGankNewsData(mTitle, mTotalCount, mPage);
        }
    };

    private final OnListClickListener mListClick = new OnListClickListener() {
        @Override
        public void onItemClick(int position) {
            ToastMaster.toast(mGankList.get(position).getDesc());
        }

        @Override
        public void onTagClick(@ItemView int tag, int position) {

        }
    };

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {
        mRefreshLayout.setRefreshing(false);
        mPlaceholder.setVisibility(View.GONE);
    }

    @Override
    public void setNotDataView(boolean isShow) {
        if (isShow) {

        }
    }

    @Override
    public void updateNewsList(List<GankNewsBean> gankNewsList) {
        if (gankNewsList.size() > 0) {
            if (gankNewsList.size() < mTotalCount) {
                //TODO 设置不能加载更多
            } else {
                mPage++;
            }
            mGankList.addAll(gankNewsList);
            mGankAdapter.notifyDataSetChanged();
        }

        if (mGankList.size() == 0) {
            setNotDataView(true);
        }
    }

    @Override
    public void clearNewsList() {
        mGankList.clear();
    }
}
