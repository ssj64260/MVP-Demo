package com.cxb.mvp_project.widget.database;

import android.text.TextUtils;

import com.cxb.mvp_project.model.FamilyBean;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * 数据库工具
 */

public class DataBaseLoader implements DataBaseLoaderWrapper {

    private Realm mRealm;

    public DataBaseLoader() {
        mRealm = Realm.getDefaultInstance();
    }

    @Override
    public void saveData(List<FamilyBean> families) {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(families);
        mRealm.commitTransaction();
    }

    @Override
    public void deleteTable() {
        mRealm.beginTransaction();
        mRealm.delete(FamilyBean.class);
        mRealm.commitTransaction();
    }

    @Override
    public void closeDB() {
        if (mRealm != null) {
            mRealm.close();
        }
    }

    @Override
    public FamilyBean findFamilyById(String id) {
        if (!TextUtils.isEmpty(id)) {
            RealmResults<FamilyBean> results = mRealm.where(FamilyBean.class)
                    .equalTo("memberId", id)
                    .findAll();
            if (results.size() > 0) {
                return mRealm.copyFromRealm(results.get(0));
            }
        }
        return null;
    }

    @Override
    public FamilyBean findFamilyTreeById(String familyId) {
        FamilyBean family = findFamilyById(familyId);
        if (family != null) {
            String fosterFather = family.getFathersId();
            String fosterMother = family.getMothersId();
            String fatherId = family.getFatherId();
            String motherId = family.getMotherId();
            String spouseId = family.getSpouseId();

            family.setFosterFather(findFamilyAndParentById(fosterFather));
            family.setFosterMother(findFamilyAndParentById(fosterMother));
            family.setFather(findFamilyAndParentById(fatherId));
            family.setMother(findFamilyAndParentById(motherId));
            family.setSpouse(findFamilyById(spouseId));
            family.setBrothers(findFamiliesByParentId(familyId, fatherId, motherId));
            family.setChildren(findChildrenByParentId(familyId));

            family.setSelect(true);
            return family;
        }
        return null;
    }

    @Override
    public FamilyBean findFamilyAndParentById(String familyId) {
        if (!TextUtils.isEmpty(familyId)) {
            FamilyBean family = findFamilyById(familyId);
            if (family != null) {
                String fatherId = family.getFatherId();
                String motherId = family.getMotherId();
                FamilyBean father = findFamilyById(fatherId);
                if (father != null) {
                    family.setFather(father);
                }
                FamilyBean mother = findFamilyById(motherId);
                if (mother != null) {
                    family.setMother(mother);
                }
                return family;
            }
        }
        return null;
    }

    @Override
    public List<FamilyBean> findChildrenByParentId(String parentId) {
        List<FamilyBean> children = findFamiliesByParentId(parentId, parentId, parentId);
        for (FamilyBean child : children) {
            String childId = child.getMemberId();
            String childSpouseId = child.getSpouseId();

            child.setSpouse(findFamilyById(childSpouseId));
            child.setChildren(findFamiliesByParentId(childId, childId, childId));
        }

        return children;
    }

    @Override
    public List<FamilyBean> findFamiliesByParentId(String myId, String fatherId, String motherId) {
        String parentId = null;
        if (!TextUtils.isEmpty(fatherId)) {
            parentId = fatherId;
        } else if (!TextUtils.isEmpty(motherId)) {
            parentId = motherId;
        }

        if (!TextUtils.isEmpty(parentId)) {
            RealmResults<FamilyBean> results = mRealm.where(FamilyBean.class)
                    .notEqualTo("memberId", myId)
                    .findAll()
                    .where()
                    .equalTo("fatherId", parentId)
                    .or()
                    .equalTo("motherId", parentId)
                    .findAll();
            return mRealm.copyFromRealm(results);
        }

        return null;
    }
}
