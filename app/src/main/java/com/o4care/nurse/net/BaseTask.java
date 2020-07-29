package com.o4care.nurse.net;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.o4care.nurse.utils.FileUtil;
import com.o4care.nurse.utils.XToastUtils;
import com.o4care.nurse.activity.LoginActivity;
import com.o4care.nurse.activity.BaseActivity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseTask<T> {
    int flag;
    String downName;
    BaseActivity activity;

    private Call<BaseEntity<T>> mCall;
    private Call<ResponseBody> mRCall;
    private Context mContext;

    String TAG = BaseTask.class.getSimpleName();

    public BaseTask(Context context, Call call) {
        mContext = context;
        mCall = call;
    }

    public BaseTask(Context mContext, Call<ResponseBody> mRCall, String downName ) {
        this.mContext = mContext;
        this.mRCall   = mRCall;
        this.downName = downName;
    }

    public BaseTask(Context context, Call call, int flag) {
        mCall     = call;
        mContext  = context;
        this.flag = flag;
    }

    public BaseTask(Context context, BaseActivity activity, Call call, int flag) {
        mCall     = call;
        mContext  = context;
        this.flag = flag;
        this.activity = activity;
    }

    public void handleResponse(final ResponseListener listener) {
        mCall.enqueue(new Callback<BaseEntity<T>>() {
            @Override
            public void onResponse(Call<BaseEntity<T>> call, Response<BaseEntity<T>> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    BaseEntity<T> entity = response.body();
                    if (entity.getStatus() == 1) {
                        if (entity.getTotal() == -1) {
                            listener.onSuccess(flag, entity.getData());
                        } else {
                            listener.onSuccess(flag, entity.getRows());
                            listener.onSuccess(flag, entity.getRows(),entity.getTotal());
                        }
                    } else {
                        listener.onFail(flag, entity.getErrMsg());
                        if (entity.getErrMsg() != null) {
                            Log.i("tag", "onResponse: " + entity.getErrMsg());
                            if(mContext != null) {
                                XToastUtils.toast(entity.getErrMsg());
                            }
                        }
                    }
                } else if (response.code() == 401) {
                    //showOffLineDia();
                    Log.i("tag", "need to login\r\n");
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                    mContext.startActivity(intent);
                } else {
                    listener.onFail(flag, " 操作失败 !");
                }
            }

            @Override
            public void onFailure(Call<BaseEntity<T>> call, Throwable t) {
                listener.onFail(flag, t.getMessage());
                Log.i(TAG, "onResponse: " + t.getMessage());
//                if (mContext != null) {
//                    Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG).show();
//                }
            }
        });
    }

    /**
     * 下载安装包
     *
     * @param listener
     * @param updateUIListener
     */
    public void handleDownloadResponse(final ResponseListener listener, final FileUtil.UpdateUIListener updateUIListener) {

        mRCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "server contacted and has file");
                    //FileUtil.getInstance(mContext).setUpdateUIListener(updateUIListener);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            boolean writtenToDisk = FileUtil.getInstance(mContext).writeApkResponseBodyToDisk(response.body(), downName);
                            if (writtenToDisk) {
                                listener.onSuccess(flag, downName);
                            } else {
                                listener.onFail(flag, "写入磁盘失败,请在浏览器安装");
                            }
                            Log.d(TAG, "file download was a success? " + writtenToDisk);
                        }
                    }).start();

                } else {
                    Log.d(TAG, "server contact failed" + response.message());
                    //Toast.makeText(mContext, response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public interface ResponseListener<T> {
        void onSuccess(int flag, T t);
        void onSuccess(int flag, T t, int total);
        void onFail(int flag, String message);
    }
}