package com.cxb.mvp_project.app;

import android.app.Application;

import com.cxb.mvp_project.R;
import com.orhanobut.logger.Logger;
import com.tencent.bugly.crashreport.CrashReport;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * application
 */

public class APP extends Application {

    private static APP INStANCE;

    public APP() {
        INStANCE = this;
    }

    public static APP getInstance() {
        if (INStANCE == null) {
            synchronized (APP.class) {
                if (INStANCE == null) {
                    INStANCE = new APP();
                }
            }
        }
        return INStANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();

//        LeakCanary.install(getInstance());//内存泄漏监听

        CrashReport.initCrashReport(getApplicationContext(), "c260d28228", false);//Bugly

        Logger.init(getString(R.string.app_name));

        Realm.init(getInstance());
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(getString(R.string.app_name) + ".realm")
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
