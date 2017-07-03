package com.cxb.mvp_project.widget.database;

import com.cxb.mvp_project.model.FamilyBean;

import java.util.List;

import io.realm.RealmList;

/**
 * 数据库工具 接口
 */

public interface DataBaseLoaderWrapper {

    void saveData(List<FamilyBean> families);

    void deleteTable();

    void closeDB();

    FamilyBean findFamilyById(String id);

    FamilyBean findFamilyTreeById(String familyId);

    FamilyBean findFamilyAndParentById(String familyId);

    RealmList<FamilyBean> findChildrenByParentId(String parentId);

    RealmList<FamilyBean> findFamiliesByParentId(String myId, String fatherId, String motherId);
}
