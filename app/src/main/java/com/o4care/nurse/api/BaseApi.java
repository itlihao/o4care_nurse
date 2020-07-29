package com.o4care.nurse.api;

import com.google.gson.Gson;
import com.o4care.nurse.activity.BaseActivity;
import java.util.Map;
import okhttp3.RequestBody;

public class BaseApi {

    BaseActivity activity;
    
    protected RequestBody getRequestBody(Map<String, String> map) {
        return okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json;charset=utf-8"), new Gson().toJson(map));
    }

    protected RequestBody getRequestBody(Object jsonObject) {
        return okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json;charset=utf-8"), new Gson().toJson(jsonObject));
    }

    protected String getJsonStr(Object obj) {
        return new Gson().toJson(obj);
    }
    protected void showLoading(String mesg) {
        if (activity != null){
            //activity.showHd(mesg);
        }
    }
}
