package com.cxb.mvp_project.model;

import android.content.Context;

import com.cxb.mvp_project.config.Config;
import com.cxb.mvp_project.config.ServiceApi;
import com.cxb.mvp_project.service.ServiceCilent;
import com.cxb.mvp_project.utils.AssetsUtil;
import com.cxb.mvp_project.widget.database.DataBaseLoader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * 家庭成员对象操作接逻辑实现
 */

public class FamilyModel {

    public Observable<List<FamilyBean>> initLocalFamilyTree(final Context context) {
        return Observable
                .create(new ObservableOnSubscribe<List<FamilyBean>>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<List<FamilyBean>> e) throws Exception {
                        String json = AssetsUtil.getAssetsTxtByName(context, Config.FAMILY_LIST_DATA_FILE_NAME);
                        final Gson gson = new Gson();
                        final Type type = new TypeToken<List<FamilyBean>>() {
                        }.getType();
                        List<FamilyBean> list = gson.fromJson(json, type);
                        if (!e.isDisposed()) {
                            e.onNext(list);
                            e.onComplete();
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<FamilyBean>> initRemoteFamilyTree(final String familyId) {
        ServiceApi api = ServiceCilent.getService();

        return api.getFamilyTreeCall(familyId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Integer> saveData(final List<FamilyBean> mList) {
        return Observable
                .create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                        final DataBaseLoader mDBLoader = new DataBaseLoader();
                        mDBLoader.saveData(mList);
                        mDBLoader.closeDB();
                        e.onNext(1);
                        e.onComplete();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<FamilyBean> getLocalData(final String familyId) {
        return Observable
                .create(new ObservableOnSubscribe<FamilyBean>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<FamilyBean> e) throws Exception {
                        final DataBaseLoader mDBLoader = new DataBaseLoader();
                        final FamilyBean family = mDBLoader.findFamilyTreeById(familyId);
                        mDBLoader.closeDB();

                        e.onNext(family);
                        e.onComplete();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
