package com.cxb.mvp_project.config;

/**
 * 网络请求链接
 */

public class API {

//    public static final String BASE_HOST = "http://112.74.177.248/";
    public static final String BASE_HOST = "http://xyhz.me:8080/";

    public static final String URL_LOGIN = BASE_HOST + "json/member/login.htm";//登录接口
    public static final String URL_REGISTER = BASE_HOST + "json/member/regByPhone.htm";//注册接口
    public static final String URL_CAPTCHA = BASE_HOST + "json/member/captchaByPhoneReg.htm";//获取验证码

    public static final String URL_MEMBER_INFO = BASE_HOST + "json/member/getJson.htm";//根据id得到个人信息
    public static final String URL_UPDATEINFO = BASE_HOST + "json/member/updateInfo.htm";//修改个人信息
    public static final String URL_APPLY = BASE_HOST + "json/member/relative/create.htm";//创建家族关系
    public static final String URL_ADD_FAMILY = BASE_HOST + "json/member/createRelative.htm";//添加家属成员

    public static final String URL_GET_PRODUCT_LIST = BASE_HOST + "json/member/commodity/commodityList.htm";//获取商品列表

    public static final String URL_GET_ALIPAY_ORDER_INFO = BASE_HOST + "json/sys/alipay.htm";//获取支付宝支付订单信息
    public static final String URL_PURCHASER = BASE_HOST + "json/member/purchaser.htm";//搜索购买人

    private static final String URL_UPLOAD = BASE_HOST + "common/upload/fileupload.htm";//上传文件

    public static final String URL_GET_FRIEND_LIST = BASE_HOST + "json/member/tag/friends.htm";//获取好友列表
    public static final String URL_GET_BLACK_LIST = BASE_HOST + "json/member/tag/blacklist.htm";//获取者黑名单

    public static final String URL_GET_BALANCE = BASE_HOST + "json/member/getBalance.htm";//获取账户余额
    public static final String URL_INCOME_DETAIL = BASE_HOST + "json/orders/getDetails.htm";//获取收支明细

    public static final String URL_GET_WITHDRAW_INFO = BASE_HOST + "json/sys/withdraw.htm";//获取提现信息
    public static final String URL_DO_WITHDRAW = BASE_HOST + "json/alipay/transfer.htm";//提现
    public static final String URL_GET_PAYMENT = BASE_HOST + "json/member/getPayment.htm";//获取默认支付账户
    public static final String URL_SET_PAYMENT_PASSWORD = BASE_HOST + "json/sys/paymentPassword.htm";//设置支付密码
    public static final String URL_CREATE_PAYMENT = BASE_HOST + "json/member/createPayment.htm";//创建提现账户

    public static final String URL_SEARCH_FRIEND = BASE_HOST + "json/member/searchList.htm";//id或手机号码搜索好友

}
