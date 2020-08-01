package com.o4care.nurse.net;

import com.o4care.nurse.bean.CareItem;
import com.o4care.nurse.bean.CarePlan;
import com.o4care.nurse.bean.CarePlanEntity;
import com.o4care.nurse.bean.CustomerInfo;
import com.o4care.nurse.bean.CustomerInfoBean;
import com.o4care.nurse.bean.Exchange;
import com.o4care.nurse.bean.LoginBean;
import com.o4care.nurse.bean.Record;
import com.o4care.nurse.bean.RecordDetail;
import com.o4care.nurse.bean.ServiceItem;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;

public interface RetrofitService {

    /**
     * 登录
     *
     * @param body
     * @return
     */
    @POST("user/login")
    Call<BaseEntity<LoginBean>> login(@Body RequestBody body);

    /**
     * 退出登录
     */
    @POST("user/logout")
    Call<BaseEntity<String>> logout();

    /**
     * 修改密码
     */
    @POST("user/changePwd")
    Call<BaseEntity<String>> changePwd(@Body RequestBody body);

    /**
     *
     * @param body
     * @return
     */
    @POST("user/forgotPwd")
    Call<BaseEntity<String>> forgotPwd(@Body RequestBody body);

    @GET("worker/allcust")
    Call<BaseEntity<List<CustomerInfo>>> getAllCustomer(@QueryMap Map<String, String> map);

    @GET("care/allitems")
    Call<BaseEntity<List<ServiceItem>>> getServiceItems(@QueryMap Map<String, String> map);

    @GET("customer/info")
    Call<BaseEntity<CustomerInfoBean>> getCustomerInfo(@QueryMap Map<String, String> map);

    @GET("care/record")
    Call<BaseEntity<List<Record>>> getRecordItems(@QueryMap Map<String, String> map);

    @GET("care/records/detail")
    Call<BaseEntity<RecordDetail>> getRecordDetail(@QueryMap Map<String, String> map);

    @GET("care/plan")
    Call<BaseEntity<List<CarePlan>>> getCarePlan(@QueryMap Map<String, String> map);

    @GET("care/items")
    Call<BaseEntity<List<CareItem>>> getCareItems(@QueryMap Map<String, String> map);

    /*@FormUrlEncoded
    @POST("care/plan/add")
    Call<BaseEntity<CarePlanEntity>> addCarePlan(@FieldMap Map<String, String> map);*/
    @GET("care/plan/add")
    Call<BaseEntity<CarePlanEntity>> addCarePlan(@QueryMap Map<String, String> map);

    @DELETE("care/plan/delete")
    Call<BaseEntity<CarePlanEntity>> delCarePlan(@QueryMap Map<String, String> map);

    @POST("care/plan/update")
    Call<BaseEntity<CarePlanEntity>> updateCarePlan(@QueryMap Map<String, String> map);

    @GET("user/exchange")
    Call<BaseEntity<List<Exchange>>> getExchange(@QueryMap Map<String, String> map);

    @Multipart
    @POST("upload/file")
    Call<BaseEntity<RecordDetail>> uploadFile(@PartMap Map<String,String> params, @Part MultipartBody.Part file);
}
