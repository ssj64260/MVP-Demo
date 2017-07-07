package com.cxb.mvp_project.ui.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.util.Pair;
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
import com.cxb.mvp_project.ui.main.GankShowImageActivity;
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
    private RecyclerView.LayoutManager mLayoutManager;

    private int mLastVisibleItemPosition = 0;
    private boolean mIsLoadingMore = false;
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
        mRecyclerView.removeOnScrollListener(mListScroll);
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

        if (getString(R.string.tab_title_welfare).equals(mTitle)) {
            mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        } else {
            mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        }
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mGankAdapter);
        mRecyclerView.setOnScrollListener(mListScroll);

    }

    private final SwipeRefreshLayout.OnRefreshListener refresh = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mPage = 1;
            mGankPresenter.getGankNewsData(mTitle, mTotalCount, mPage);
        }
    };

    private final RecyclerView.OnScrollListener mListScroll = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (!mIsLoadingMore && newState == RecyclerView.SCROLL_STATE_IDLE
                    && mLastVisibleItemPosition + 1 == mGankAdapter.getItemCount()) {
                mIsLoadingMore = true;
                mGankPresenter.getGankNewsData(mTitle, mTotalCount, mPage);
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (mLayoutManager instanceof LinearLayoutManager) {
                LinearLayoutManager manager = (LinearLayoutManager) mLayoutManager;
                mLastVisibleItemPosition = manager.findLastCompletelyVisibleItemPosition();
            } else if (mLayoutManager instanceof StaggeredGridLayoutManager) {
                StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) mLayoutManager;
                int[] positions = new int[manager.getSpanCount()];
                manager.findLastCompletelyVisibleItemPositions(positions);
                int maxPosition = 0;
                for (int position : positions) {
                    if (position > maxPosition) {
                        maxPosition = position;
                    }
                }
                mLastVisibleItemPosition = maxPosition;
            }
        }
    };

    private final OnListClickListener mListClick = new OnListClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            final GankNewsBean gankNews = mGankList.get(position);
            switch (view.getId()) {
                case R.id.iv_picture:
                    Pair<View, String> picturePair = Pair.create(view, getString(R.string.large_image_transition_name));
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), picturePair);

                    Intent intent = new Intent();
                    intent.setClass(getActivity(), GankShowImageActivity.class);
                    intent.putExtra(GankShowImageActivity.KEY_PICTURE_URL, gankNews.getUrl());
                    startActivity(intent, options.toBundle());
                    break;
                default:
                    ToastMaster.toast(gankNews.getUrl());
                    break;
            }
        }
    };

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {
        mRefreshLayout.setRefreshing(false);
        mPlaceholder.setVisibility(View.GONE);
        mIsLoadingMore = false;
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
