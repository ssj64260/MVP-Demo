package com.cxb.mvp_project.config;

import android.Manifest;

/**
 * 请求链接
 */

public class Config {

    public static final String FAMILY_LIST_DATA_FILE_NAME = "family_tree.txt";//家庭成员列表文件名

    public static final int REQUEST_TO_SETTING = 0;//跳转到系统设置权限页面

    public static final int GANK_IMAGE_MAX_WIDTH = 150;//干货图片最大宽度限制

    public static final String PERMISSION_CONTACTS = Manifest.permission.READ_CONTACTS;//通讯录
    public static final String PERMISSION_CALL = Manifest.permission.CALL_PHONE;//电话
    public static final String PERMISSION_CALENDAR = Manifest.permission.READ_CALENDAR;//日历
    public static final String PERMISSION_CAMERA = Manifest.permission.CAMERA;//相机
    public static final String PERMISSION_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;//位置信息
    public static final String PERMISSION_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;//存储
    public static final String PERMISSION_RECORD_AUDIO = Manifest.permission.RECORD_AUDIO;//麦克风
    public static final String PERMISSION_SMS = Manifest.permission.READ_SMS;//短信
    public static final String PERMISSION_BODY_SENSORS = Manifest.permission.BODY_SENSORS;//身体传感器

}
