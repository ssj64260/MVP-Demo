package com.cxb.mvp_project.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * 家族人员
 */

public class FamilyBean extends RealmObject implements Parcelable {

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
    private List<FamilyBean> brothers;//兄弟姐妹
    @Ignore
    private List<FamilyBean> children;//儿女
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

    public List<FamilyBean> getBrothers() {
        return brothers;
    }

    public void setBrothers(List<FamilyBean> brothers) {
        this.brothers = brothers;
    }

    public List<FamilyBean> getChildren() {
        return children;
    }

    public void setChildren(List<FamilyBean> children) {
        this.children = children;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.memberId);
        dest.writeString(this.memberName);
        dest.writeString(this.call);
        dest.writeString(this.memberImg);
        dest.writeString(this.fatherId);
        dest.writeString(this.motherId);
        dest.writeString(this.spouseId);
        dest.writeString(this.mothersId);
        dest.writeString(this.fathersId);
        dest.writeParcelable(this.spouse, flags);
        dest.writeParcelable(this.fosterFather, flags);
        dest.writeParcelable(this.fosterMother, flags);
        dest.writeParcelable(this.father, flags);
        dest.writeParcelable(this.mother, flags);
        dest.writeTypedList(this.brothers);
        dest.writeTypedList(this.children);
        dest.writeByte(this.isSelect ? (byte) 1 : (byte) 0);
    }

    public FamilyBean() {
    }

    protected FamilyBean(Parcel in) {
        this.memberId = in.readString();
        this.memberName = in.readString();
        this.call = in.readString();
        this.memberImg = in.readString();
        this.fatherId = in.readString();
        this.motherId = in.readString();
        this.spouseId = in.readString();
        this.mothersId = in.readString();
        this.fathersId = in.readString();
        this.spouse = in.readParcelable(FamilyBean.class.getClassLoader());
        this.fosterFather = in.readParcelable(FamilyBean.class.getClassLoader());
        this.fosterMother = in.readParcelable(FamilyBean.class.getClassLoader());
        this.father = in.readParcelable(FamilyBean.class.getClassLoader());
        this.mother = in.readParcelable(FamilyBean.class.getClassLoader());
        this.brothers = in.createTypedArrayList(FamilyBean.CREATOR);
        this.children = in.createTypedArrayList(FamilyBean.CREATOR);
        this.isSelect = in.readByte() != 0;
    }

    public static final Creator<FamilyBean> CREATOR = new Creator<FamilyBean>() {
        @Override
        public FamilyBean createFromParcel(Parcel source) {
            return new FamilyBean(source);
        }

        @Override
        public FamilyBean[] newArray(int size) {
            return new FamilyBean[size];
        }
    };
}
