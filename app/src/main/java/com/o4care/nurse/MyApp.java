package com.o4care.nurse;

import android.app.Application;
import android.content.Context;
import androidx.multidex.MultiDex;
import com.o4care.nurse.utils.XBasicLibInit;
import com.o4care.nurse.utils.update.XUpdateInit;

/**
 */
public class MyApp extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //解决4.x运行崩溃的问题
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initLibs();
    }

    /**
     * 初始化基础库
     */
    private void initLibs() {
        XBasicLibInit.init(this);
        XUpdateInit.init(this);
    }


    /**
     * @return 当前app是否是调试开发模式
     */
    public static boolean isDebug() {
        return true;
    }
}
