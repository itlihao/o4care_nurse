package com.o4care.nurse.activity;

import android.os.Bundle;
import android.view.KeyEvent;

import com.xuexiang.xui.utils.KeyboardUtils;
import com.o4care.nurse.fragment.LoginFragment;

/**
 * 登录页面
 *
 * @author xuexiang
 * @since 2020-06-17 22:21
 */
public class LoginActivity extends BaseActivity {
    String  TAG = LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openPage(LoginFragment.class, getIntent().getExtras());
    }

    /*
    @Override
    protected void initStatusBarStyle() {
        StatusBarUtils.initStatusBarStyle(this, false, Colors.TRANSPARENT);
    }
    */

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return KeyboardUtils.onDisableBackKeyDown(keyCode) && super.onKeyDown(keyCode, event);
    }

}
