package com.o4care.nurse.widget;

import com.google.android.material.tabs.TabLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.Toolbar;

import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.o4care.nurse.activity.BaseActivity;
import com.o4care.nurse.adapter.ImageListAdapter;
import com.o4care.nurse.bean.ImageBean;
import com.o4care.nurse.fragment.customer.CustomerInfoFragment;
import com.o4care.nurse.fragment.customer.CustomerListFragment;
import com.o4care.nurse.fragment.customer.CustomerPlanFragment;
import com.o4care.nurse.fragment.customer.CustomerRecordFragment;
import com.o4care.nurse.fragment.customer.CustomerTaskFragment;
import com.o4care.nurse.fragment.map.MapFragment;
import com.o4care.nurse.fragment.mine.MineFragment;
import com.o4care.nurse.fragment.task.TaskFragment;
import com.o4care.nurse.lia.FileUploadObserver;
import com.o4care.nurse.lia.RetrofitClient;
import com.o4care.nurse.utils.TransfmtUtils;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.adapter.FragmentAdapter;
import com.o4care.nurse.R;
import com.o4care.nurse.fragment.BaseFragment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import butterknife.BindView;
import okhttp3.ResponseBody;

import static com.google.android.material.tabs.TabLayout.MODE_FIXED;

/**
 * @author XUE
 * @since 2019/5/9 11:43
 */
public class UploadFilesActivity extends BaseActivity {
    private static final String TAG = "UploadFilesActivity";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_photos)
    RecyclerView recyclerView;

    @BindView(R.id.tv_upload)
    Button upload;

    private ImageListAdapter listAdapter;

    private List<ImageBean> imageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        initListeners();

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setTitle("上传文件");

        imageList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listAdapter = new ImageListAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(listAdapter);

        getpic();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_upload;
    }

    protected void initViews() {
 
    }

    protected void initListeners() {
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
    }

    private void getData() {
        String url = "http://192.168.3.2/uploadTest/";

        ExecutorService executor = Executors.newFixedThreadPool(2);

        ThreadPoolExecutor pool = (ThreadPoolExecutor) executor;

        for (int i = 0; i < imageList.size(); i++) {
            pool.execute(new uploadTask(url, new File(imageList.get(i).getImagePath())));
        }
    }

    class uploadTask implements Runnable {
        String url;
        File file;

        uploadTask(String url, File file) {
            this.url = url;
            this.file = file;
        }

        @Override
        public void run() {
            RetrofitClient.getInstance()
                    .upLoadFile(url, file, new FileUploadObserver<ResponseBody>() {
                        @Override
                        public void onUpLoadSuccess(ResponseBody responseBody) {
                            Toast.makeText(UploadFilesActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                            try {
                                Log.d("上传进度", responseBody.string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onUpLoadFail(Throwable e) {
                            Toast.makeText(UploadFilesActivity.this, "上传失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onProgress(int progress) {
                            Log.d("上传进度", file.getName() + "  " + progress);
                        }
                    });
        }
    }

    public void getpic() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "-->getpic");

                String path = TransfmtUtils.getSDCardPath() + "Download/Upload";
                //查看指定文件夹里面的所有文件
                Cursor mCursor = getContentResolver().query(MediaStore.Files.getContentUri("external"), null,
                        "(" + MediaStore.Images.Media.MIME_TYPE + "=? or " + MediaStore.Images.Media.MIME_TYPE + "=?) and " +
                                MediaStore.Images.Media.DATA + " like ?",
                        new String[]{"image/jpeg", "image/png", path + "%"},
                        null);

                List<ImageBean> list = new ArrayList<>();
                if (mCursor != null) {
                    while (mCursor.moveToNext()) {
                        ImageBean imageBean = new ImageBean();
                        String displayName = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
                        String imgPath = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA));
                        Log.i(TAG, "-->" + displayName + " path " + imgPath);
                        imageBean.setImageName(displayName);
                        imageBean.setImagePath(imgPath);
                        list.add(imageBean);
                    }
                } else {
                    Log.i(TAG, "-->mCursor is null");
                }

                imageList.addAll(list);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listAdapter.refresh(imageList);
                    }
                });

            }
        }).start();


    }
}
