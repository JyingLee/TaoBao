package com.jying.taobao;

import android.app.Application;
import android.content.Context;

import com.jying.taobao.Utils.ToastUtils;

/**
 * Created by Jying on 2017/11/20.
 */

public class TaoBaoApplication extends Application {
    private static Context appContext;
    private static long exitTime = 0;

    public static Context getAppContext() {
        return appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
    }

    public static void exitApp() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            ToastUtils.shortToast(getAppContext(), "再按一次退出");
            exitTime = System.currentTimeMillis();
        } else {
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }
}
