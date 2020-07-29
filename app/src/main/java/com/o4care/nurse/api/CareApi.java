package com.o4care.nurse.api;

import android.content.Context;

import com.o4care.nurse.bean.ServiceItem;
import com.o4care.nurse.net.BaseTask;
import com.o4care.nurse.net.RetroFactory;

import java.util.HashMap;
import java.util.Map;

public class CareApi extends BaseApi {
    Context mContext;
    BaseTask.ResponseListener responseListener;

    public CareApi(Context context, BaseTask.ResponseListener responseListener) {
        mContext = context;
        this.responseListener = responseListener;
    }

    public void getAllServiceItems( String worker_id ) {
        Map<String, String> map = new HashMap<>();
        map.put("worker_id", worker_id);
        new BaseTask<ServiceItem>(mContext, RetroFactory.getJacksonService(mContext).getServiceItems(map) )
            .handleResponse(responseListener);
    }
}
