package com.o4care.nurse.api;

import android.content.Context;
import com.o4care.nurse.bean.LoginBean;
import com.o4care.nurse.net.BaseTask;
import com.o4care.nurse.net.RetroFactory;

import java.util.HashMap;
import java.util.Map;

public class WorkerApi extends BaseApi {
    Context mContext;
    BaseTask.ResponseListener responseListener;

    public WorkerApi(Context context, BaseTask.ResponseListener responseListener) {
        mContext = context;
        this.responseListener = responseListener;
    }

    public void getAllCustomer( String worker_id ) {
        Map<String, String> map = new HashMap<>();
        map.put("worker_id", worker_id);
        new BaseTask<LoginBean>(mContext, RetroFactory.getJacksonService(mContext).getAllCustomer(map) )
            .handleResponse(responseListener);
    }
}
