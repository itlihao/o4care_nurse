package com.o4care.nurse.net;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.shanbay.mock.MockApiInterceptor;
import com.shanbay.mock.MockApiSuite;
import com.shanbay.mock.api.StandardMockApi;
import com.shanbay.mock.constant.MockHttpMethod;
import com.o4care.nurse.utils.MMKVUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetroFactory {

    //String BASE_URL_HEAD = "http://daojia.qdwanlin.com:10000/";
    static String BASE_URL_HEAD = "http://192.168.1.103:8080/";
    static String BASE_URL = BASE_URL_HEAD + "homecare/api/";
    static String BASE_UPDATE_URL = BASE_URL_HEAD + "upload/";
    static String WATCH_BASE_URL = BASE_URL + "watch/";

    static Context mContext;

    /**
     * 现在是base url不同,而非处理方式不同待考虑,
     */
    private static Retrofit jacksonRetrofit;
    private static Retrofit gsonRetrofit ;
    private static Retrofit upgradeRetrofit;
    private static Retrofit downRetrofit;

    public static RetrofitService getJacksonService( Context mContextIn ) {
        mContext = mContextIn;
        if(jacksonRetrofit == null) {
            jacksonRetrofit = new Retrofit.Builder().client(getClient())
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        }
        return jacksonRetrofit.create(RetrofitService.class);
    }

    public static RetrofitService getUpdateService( Context mContextIn ) {
        mContext = mContext;
        if(upgradeRetrofit == null) {
            upgradeRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_UPDATE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        }
        return upgradeRetrofit.create(RetrofitService.class);
    }

    public static RetrofitService getGsonService( Context mContextIn ) {
        mContext = mContextIn;
        if(gsonRetrofit == null) {
            gsonRetrofit = new Retrofit.Builder().client(getClient())
                .baseUrl(WATCH_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        }
        return gsonRetrofit.create(RetrofitService.class);
    }

    public static RetrofitService getDownService( Context mContextIn ) {
        mContext = mContextIn;
        if(downRetrofit == null) {
            downRetrofit = new Retrofit.Builder().baseUrl(BASE_URL).build();
        }
        return downRetrofit.create(RetrofitService.class);
    }

    /**
     * 把token加到请求头里
     *
     * @return
     */
    private static OkHttpClient getClient() {
        MockApiSuite suite = new MockApiSuite("customer");
        suite.addMockApi(new StandardMockApi(MockHttpMethod.GET, "/api/worker/allcust").setSuccessDataFile("customer.json"));
        suite.addMockApi(new StandardMockApi(MockHttpMethod.GET, "/api/care/allitems").setSuccessDataFile("service_items.json"));
        MockApiInterceptor mockApiInterceptor = new MockApiInterceptor(mContext);
        mockApiInterceptor.addMockApiSuite(suite);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("wyg", "retrofitBack = " + message);// 打印retrofit日志
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
        .addInterceptor(mockApiInterceptor)
        .addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request.Builder builder1 = request.newBuilder();

                String token =MMKVUtils.getString("token", "");
                if (!TextUtils.isEmpty(token)) {
                    builder1.addHeader("token", token);
                }
                Request build = builder1.build();
                return chain.proceed(build);
            }
        })
        .retryOnConnectionFailure(true)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor).build();

        return client;
    }
}
