package com.cxb.mvp_project.presenter;

import android.content.Context;

import com.cxb.mvp_project.model.FamilyBean;
import com.cxb.mvp_project.model.FamilyModel;
import com.cxb.mvp_project.view.IFamilyView;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * 家谱树 连接 Model 的 Presenter
 */

public class FamilyPresenter implements IBasePresenter<IFamilyView> {

    private IFamilyView mView;
    private FamilyModel mFamilyModel;

    public FamilyPresenter() {
        mFamilyModel = new FamilyModel();
    }

    public void loadFamilyFromLoacl(final Context context, final String familyId) {
        if (mView != null) {
            mView.showProgress();
            mFamilyModel
                    .initLocalFamilyTree(context)
                    .subscribe(new Consumer<List<FamilyBean>>() {
                        @Override
                        public void accept(@NonNull List<FamilyBean> familyList) throws Exception {
                            saveFamilyData(familyList, familyId);
                        }
                    });
        }
    }

    public void loadFamilyFromRemote(final String familyId) {
        if (mView != null) {
            mView.showProgress();
            mFamilyModel
                    .initRemoteFamilyTree(familyId)
                    .subscribe(new Consumer<List<FamilyBean>>() {
                        @Override
                        public void accept(@NonNull List<FamilyBean> familyList) throws Exception {
                            saveFamilyData(familyList, familyId);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(@NonNull Throwable throwable) throws Exception {
                            loadFailure();
                        }
                    });
        }
    }

    private void saveFamilyData(final List<FamilyBean> familyList, final String familyId) {
        if (familyList != null) {
            mFamilyModel
                    .saveData(familyList)
                    .subscribe(new Consumer<Integer>() {
                        @Override
                        public void accept(@NonNull Integer code) throws Exception {
                            if (code == 1) {
                                getFamilyData(familyId);
                            }
                        }
                    });
        }
    }

    public void getFamilyData(final String familyId) {
        mFamilyModel
                .getLocalData(familyId)
                .subscribe(new Consumer<FamilyBean>() {
                    @Override
                    public void accept(@NonNull FamilyBean family) throws Exception {
                        if (family != null) {
                            loadSuccess(family);
                        } else {
                            loadFailure();
                        }
                    }
                });
    }

    private void loadSuccess(final FamilyBean family) {
        if (mView != null) {
            mView.showFamilyTree(family);
            mView.hideProgress();
        }
    }

    private void loadFailure() {
        if (mView != null) {
            mView.hideProgress();
            mView.showFailureView();
        }
    }

    @Override
    public void attachView(IFamilyView view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

}
