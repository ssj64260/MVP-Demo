package com.cxb.mvp_project.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * 家族人员
 */

public class FamilyBean extends RealmObject {

    @PrimaryKey
    private String memberId;//人员ID
    private String memberName;//姓名
    private String call;//称呼
    private String memberImg;//头像

    private String fatherId;//父亲ID
    private String motherId;//母亲ID
    private String spouseId;//配偶ID

    private String mothersId;//养母ID
    private String fathersId;//养父ID

    @Ignore
    private FamilyBean spouse;//配偶
    @Ignore
    private FamilyBean fosterFather;//养父
    @Ignore
    private FamilyBean fosterMother;//养母
    @Ignore
    private FamilyBean father;//父亲
    @Ignore
    private FamilyBean mother;//母亲
    @Ignore
    private RealmList<FamilyBean> brothers;//兄弟姐妹
    @Ignore
    private RealmList<FamilyBean> children;//儿女
    @Ignore
    private boolean isSelect = false;//是否选中

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getCall() {
        return call;
    }

    public void setCall(String call) {
        this.call = call;
    }

    public String getMemberImg() {
        return memberImg;
    }

    public void setMemberImg(String memberImg) {
        this.memberImg = memberImg;
    }

    public String getFatherId() {
        return fatherId;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }

    public String getMotherId() {
        return motherId;
    }

    public void setMotherId(String motherId) {
        this.motherId = motherId;
    }

    public String getSpouseId() {
        return spouseId;
    }

    public void setSpouseId(String spouseId) {
        this.spouseId = spouseId;
    }

    public String getMothersId() {
        return mothersId;
    }

    public void setMothersId(String mothersId) {
        this.mothersId = mothersId;
    }

    public String getFathersId() {
        return fathersId;
    }

    public void setFathersId(String fathersId) {
        this.fathersId = fathersId;
    }

    public FamilyBean getSpouse() {
        return spouse;
    }

    public void setSpouse(FamilyBean spouse) {
        this.spouse = spouse;
    }

    public FamilyBean getFosterFather() {
        return fosterFather;
    }

    public void setFosterFather(FamilyBean fosterFather) {
        this.fosterFather = fosterFather;
    }

    public FamilyBean getFosterMother() {
        return fosterMother;
    }

    public void setFosterMother(FamilyBean fosterMother) {
        this.fosterMother = fosterMother;
    }

    public FamilyBean getFather() {
        return father;
    }

    public void setFather(FamilyBean father) {
        this.father = father;
    }

    public FamilyBean getMother() {
        return mother;
    }

    public void setMother(FamilyBean mother) {
        this.mother = mother;
    }

    public RealmList<FamilyBean> getBrothers() {
        return brothers;
    }

    public void setBrothers(RealmList<FamilyBean> brothers) {
        this.brothers = brothers;
    }

    public RealmList<FamilyBean> getChildren() {
        return children;
    }

    public void setChildren(RealmList<FamilyBean> children) {
        this.children = children;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
