package com.o4care.nurse.activity;

import android.view.KeyEvent;
import com.o4care.nurse.R;
import com.o4care.nurse.utils.MMKVUtils;
import com.xuexiang.xui.utils.KeyboardUtils;
import com.xuexiang.xui.widget.activity.BaseSplashActivity;
import com.xuexiang.xutil.app.ActivityUtils;

import me.jessyan.autosize.internal.CancelAdapt;

/**
 * 启动页【无需适配屏幕大小】
 */
public class SplashActivity extends BaseSplashActivity implements CancelAdapt {

    @Override
    protected long getSplashDurationMillis() {
        return 500;
    }

    /**
     * activity启动后的初始化
     */
    @Override
    protected void onCreateActivity() {
        initSplashView(R.drawable.xui_config_bg_splash);
        startSplash(false);
    }

    /**
     * 启动页结束后的动作
     */
    @Override
    protected void onSplashFinished() {
        String token = MMKVUtils.getString("key_token", "");
        if (token==null||token.isEmpty()) {
            //ActivityUtils.startActivity(LoginActivity.class);//方便调试
            ActivityUtils.startActivity(MainActivity.class);
            finish();
        } else {
            ActivityUtils.startActivity(MainActivity.class);
            finish();
        }
    }

    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return KeyboardUtils.onDisableBackKeyDown(keyCode) && super.onKeyDown(keyCode, event);
    }
}
