package com.o4care.nurse.api;

import android.content.Context;

import com.o4care.nurse.bean.CarePlan;
import com.o4care.nurse.bean.Record;
import com.o4care.nurse.bean.RecordDetail;
import com.o4care.nurse.bean.ServiceItem;
import com.o4care.nurse.net.BaseTask;
import com.o4care.nurse.net.RetroFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CareApi extends BaseApi {
    Context mContext;
    BaseTask.ResponseListener responseListener;

    public CareApi(Context context, BaseTask.ResponseListener responseListener) {
        mContext = context;
        this.responseListener = responseListener;
    }

    public void getAllServiceItems(String worker_id) {
        Map<String, String> map = new HashMap<>();
        map.put("worker_id", worker_id);
        new BaseTask<ServiceItem>(mContext, RetroFactory.getJacksonService(mContext).getServiceItems(map))
                .handleResponse(responseListener);
    }

    public void getAllRecord(String worker_id, String custId) {
        Map<String, String> map = new HashMap<>();
        map.put("worker_id", worker_id);
        map.put("cust_id", custId);
        new BaseTask<Record>(mContext, RetroFactory.getJacksonService(mContext).getRecordItems(map))
                .handleResponse(responseListener);
    }

    public void getRecordDetial(String recordId) {
        Map<String, String> map = new HashMap<>();
        map.put("record_id", recordId);
        new BaseTask<RecordDetail>(mContext, RetroFactory.getJacksonService(mContext).getRecordDetail(map))
                .handleResponse(responseListener);
    }

    public void getCarePlan(String custId, String workId) {
        Map<String, String> map = new HashMap<>();
        map.put("cust_id", custId);
        map.put("worker_id", workId);
        new BaseTask<List<CarePlan>>(mContext, RetroFactory.getJacksonService(mContext).getCarePlan(map))
                .handleResponse(responseListener);
    }
}
