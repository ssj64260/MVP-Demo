package com.cxb.mvp_project.ui.familytree;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cxb.mvp_project.R;
import com.cxb.mvp_project.app.BaseActivity;
import com.cxb.mvp_project.model.FamilyBean;
import com.cxb.mvp_project.presenter.FamilyPresenter;
import com.cxb.mvp_project.utils.ToastMaster;
import com.cxb.mvp_project.view.IFamilyView;
import com.cxb.mvp_project.widget.familytree.FamilyTreeView;
import com.cxb.mvp_project.widget.familytree.FamilyTreeView2;
import com.cxb.mvp_project.widget.familytree.OnFamilySelectListener;

/**
 * 家谱树
 */

public class FamilyTreeActivity extends BaseActivity implements IFamilyView {

    public static final String HAVE_FOSTER_PARENT = "have_foster_parent";//是否有养父母

    private TextView tvChangeType;
    private FamilyTreeView ftvTree;//没有养父母
    private FamilyTreeView2 ftvTree2;//有养父母

    private boolean haveFosterParent = false;//是否有养父母

    private Intent mQQMessageIntent;

    private FamilyPresenter mFamilyPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_tree);

        initView();
        initData();

    }

    @Override
    protected void onDestroy() {
        mFamilyPresenter.detachView();
        super.onDestroy();
    }

    private void initView() {
        mFamilyPresenter = new FamilyPresenter();
        mFamilyPresenter.attachView(this);

        tvChangeType = (TextView) findViewById(R.id.tv_change_type);
        ftvTree = (FamilyTreeView) findViewById(R.id.ftv_tree);
        ftvTree2 = (FamilyTreeView2) findViewById(R.id.ftv_tree2);
    }

    private void initData() {
//        mQQMessageIntent = new Intent(this, QQMessageService.class);
//        startService(mQQMessageIntent);

        haveFosterParent = getIntent().getBooleanExtra(HAVE_FOSTER_PARENT, false);
        if (haveFosterParent) {
            tvChangeType.setText(getString(R.string.title_have_foster_parent));
            ftvTree.setVisibility(View.GONE);
            ftvTree2.setVisibility(View.VISIBLE);
            ftvTree2.setOnFamilySelectListener(familySelect);
            mFamilyPresenter.loadFamilyFromRemote("1");
//            mFamilyPresenter.loadFamilyFromLoacl(this, "601");
        } else {
            tvChangeType.setText(getString(R.string.title_not_foster_parent));
            ftvTree.setVisibility(View.VISIBLE);
            ftvTree2.setVisibility(View.GONE);
            ftvTree.setOnFamilySelectListener(familySelect);
            mFamilyPresenter.loadFamilyFromLoacl(this, "601");
        }
    }

    private OnFamilySelectListener familySelect = new OnFamilySelectListener() {
        @Override
        public void onFamilySelect(final FamilyBean family) {
            if (family.isSelect()) {
                ToastMaster.toast(family.getMemberName());
            } else {
                String currentFamilyId = family.getMemberId();
                mFamilyPresenter.getFamilyData(currentFamilyId);
            }
        }
    };

    @Override
    public void showProgress() {
        showLoading();
    }

    @Override
    public void hideProgress() {
        hideLoading();
    }

    @Override
    public void showFamilyTree(FamilyBean family) {
        if (family != null) {
            if (haveFosterParent) {
                ftvTree2.setFamilyMember(family);
            } else {
                ftvTree.setFamilyMember(family);
            }
        }
    }

    @Override
    public void showFailureView() {
        ToastMaster.toast(getString(R.string.toast_get_familytree_failure));
    }
}
