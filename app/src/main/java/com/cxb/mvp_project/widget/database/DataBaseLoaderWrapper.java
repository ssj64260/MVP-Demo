package com.cxb.mvp_project.widget.database;

import com.cxb.mvp_project.model.FamilyBean;

import java.util.List;

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

    List<FamilyBean> findChildrenByParentId(String parentId);

    List<FamilyBean> findFamiliesByParentId(String myId, String fatherId, String motherId);
}
