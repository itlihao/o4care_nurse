package com.o4care.nurse.api;

import android.content.Context;
import com.o4care.nurse.bean.LoginBean;
import com.o4care.nurse.net.BaseTask;
import com.o4care.nurse.net.RetroFactory;

import java.util.HashMap;
import java.util.Map;

public class LoginApi extends BaseApi {
    Context mContext;
    BaseTask.ResponseListener responseListener;

    public LoginApi(Context context, BaseTask.ResponseListener responseListener) {
        mContext = context;
        this.responseListener = responseListener;
    }

    public void login(String tel, String pwd) {
        Map<String, String> map = new HashMap<>();
        map.put("tel", tel);
        map.put("pwd", pwd);
        new BaseTask<LoginBean>(mContext, RetroFactory.getJacksonService(mContext).login(getRequestBody(map)) )
            .handleResponse(responseListener);
    }

    public void logout() {
        new BaseTask<String>(mContext, RetroFactory.getJacksonService(mContext).logout())
            .handleResponse(responseListener);
    }

    public void changePwd(String pwd, String newpwd) {
        Map<String, String> map = new HashMap<>();
        map.put("pwd", pwd);
        map.put("newpwd", newpwd);
        new BaseTask<String>(mContext, RetroFactory.getJacksonService(mContext).changePwd(getRequestBody(map)))
            .handleResponse(responseListener);
    }

    public void forgotPwd(String tel, String checkCode, String newpwd) {
        Map<String, String> map = new HashMap<>();
        map.put("tel", tel);
        map.put("checkCode", checkCode);
        map.put("newpwd", newpwd);
        new BaseTask<String>(mContext, RetroFactory.getJacksonService(mContext).
              forgotPwd(getRequestBody(map))).handleResponse(responseListener);
    }
}
