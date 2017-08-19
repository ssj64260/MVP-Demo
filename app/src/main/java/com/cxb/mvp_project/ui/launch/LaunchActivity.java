package com.cxb.mvp_project.ui.launch;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.cxb.mvp_project.R;
import com.cxb.mvp_project.app.BaseAppCompatActivity;
import com.cxb.mvp_project.config.Config;
import com.cxb.mvp_project.ui.main.MainActivity;
import com.cxb.mvp_project.utils.AppManager;
import com.cxb.mvp_project.widget.dialog.DefaultAlertDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;

import static com.cxb.mvp_project.config.Config.REQUEST_TO_SETTING;

/**
 * 启动页面
 */

public class LaunchActivity extends BaseAppCompatActivity {

    private int permissionPosition = 0;//当前请求权限位置
    private String[] permissions;
    private String[] errorTips;

    private DefaultAlertDialog permissionDialog;//获取权限对话框

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkPermission();

    }

    private void initData() {
        Observable
                .timer(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                        finish();
                    }
                })
                .subscribe();
    }

    private void checkPermission() {
        final String appName = getString(R.string.app_name);
        permissions = new String[]{
//                Config.PERMISSION_CONTACTS,
//                Config.PERMISSION_CALL,
//                Config.PERMISSION_CALENDAR,
//                Config.PERMISSION_CAMERA,
//                Config.PERMISSION_LOCATION,
                Config.PERMISSION_STORAGE
//                Config.PERMISSION_RECORD_AUDIO,
//                Config.PERMISSION_SMS,
//                Config.PERMISSION_BODY_SENSORS
        };
        errorTips = new String[]{
//                String.format(getString(R.string.text_contacts_permission_message), appName),
//                String.format(getString(R.string.text_call_permission_message), appName),
//                String.format(getString(R.string.text_calendar_permission_message), appName),
//                String.format(getString(R.string.text_camera_permission_message), appName),
//                String.format(getString(R.string.text_location_permission_message), appName),
                String.format(getString(R.string.text_storage_permission_message), appName)
//                String.format(getString(R.string.text_audio_permission_message), appName),
//                String.format(getString(R.string.text_sms_permission_message), appName),
//                String.format(getString(R.string.text_sensors_permission_message), appName)
        };

        final List<String> requestList = new ArrayList<>();
        final List<String> errorTipsList = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            String permission = permissions[i];
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                String tips = this.errorTips[i];
                requestList.add(permission);
                errorTipsList.add(tips);
            }
        }
        permissions = requestList.toArray(new String[requestList.size()]);
        errorTips = errorTipsList.toArray(new String[errorTipsList.size()]);
        requestPermission();
    }

    private void requestPermission() {
        if (permissionPosition < permissions.length) {
            ActivityCompat.requestPermissions(this, new String[]{permissions[permissionPosition]}, permissionPosition);
        } else {
            initData();
        }
    }

    private void showPermissionTipsDialog() {
        if (permissionDialog == null) {
            permissionDialog = new DefaultAlertDialog(this);
            permissionDialog.setTitle(getString(R.string.permission_dialog_title));
            permissionDialog.setConfirmButton(getString(R.string.permission_dialog_btn_setting), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    AppManager.showInstalledAppDetails(LaunchActivity.this, getPackageName(), REQUEST_TO_SETTING);
                }
            });
            permissionDialog.setCancelButton(getString(R.string.permission_dialog_btn_cancel), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
        }
        permissionDialog.setMessage(errorTips[permissionPosition]);
        permissionDialog.showDialog();
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                if (permissionPosition < errorTips.length) {
                    showPermissionTipsDialog();
                } else {
                    finish();
                }
            } else {
                permissionPosition++;
                requestPermission();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_TO_SETTING == requestCode) {
            if (permissionPosition < permissions.length) {
                if (ContextCompat.checkSelfPermission(this, permissions[permissionPosition]) != PackageManager.PERMISSION_GRANTED) {
                    finish();
                } else {
                    permissionPosition++;
                    requestPermission();
                }
            }
        }
    }

}
