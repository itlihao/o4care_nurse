package com.o4care.nurse.fragment.customer;

import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.o4care.nurse.R;
import com.o4care.nurse.adapter.CustomerInfoPhotoAdapter;
import com.o4care.nurse.adapter.ServiceItemList2Adapter;
import com.o4care.nurse.api.CareApi;
import com.o4care.nurse.bean.RecordDetail;
import com.o4care.nurse.fragment.BaseFragment;
import com.o4care.nurse.net.BaseTask;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author wlcare
 */
@Page(anim = CoreAnim.none)
public class CustomerRecordDetailFragment extends BaseFragment {
    private static final String TAG = "CustomerRecordDetail";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_service_items)
    RecyclerView rvServiceItems;
    @BindView(R.id.rv_photos)
    SwipeRecyclerView recyclerPhotos;

    @BindView(R.id.tv_temperature)
    TextView tvTemperature;
    @BindView(R.id.tv_blood_pressure)
    TextView tvBloodPressure;
    @BindView(R.id.tv_blood_sugar)
    TextView tvBloodSugar;
    @BindView(R.id.tv_heart_rate)
    TextView tvHeartRate;

    @BindView(R.id.tv_start)
    TextView tvStart;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.tv_duration)
    TextView tvDuration;

    @BindView(R.id.tv_logs)
    TextView tvLog;

    private ServiceItemList2Adapter listSerivceItemAdapter;
    private CustomerInfoPhotoAdapter infoPhotoAdapter;

    /**
     * @return 返回为 null意为不需要导航栏
     */
    @Override
    protected TitleBar initTitle() {
        return null;
    }

    /**
     * `
     * 布局的资源id
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_record_detail;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("记录详情");
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setHasOptionsMenu(true);

        WidgetUtils.initRecyclerView(rvServiceItems, 1);

        rvServiceItems.setLayoutManager(new LinearLayoutManager(rvServiceItems.getContext()));
        rvServiceItems.setItemAnimator(new DefaultItemAnimator());
        listSerivceItemAdapter = new ServiceItemList2Adapter(new ArrayList<>());
        rvServiceItems.setAdapter(listSerivceItemAdapter);

        recyclerPhotos.setLayoutManager(new GridLayoutManager(recyclerPhotos.getContext(), 4));
        infoPhotoAdapter = new CustomerInfoPhotoAdapter(getContext(), new ArrayList<>());
        recyclerPhotos.setAdapter(infoPhotoAdapter);

        getRecordDetial();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getActivity().finish();
        }
        return true;
    }

    private void getRecordDetial() {
        new CareApi(getActivity(), new BaseTask.ResponseListener<RecordDetail>() {
            @Override
            public void onSuccess(int flag, RecordDetail recordDetail) {
                Log.d(TAG, "onSucess data");
                setView(recordDetail);
            }

            @Override
            public void onSuccess(int flag, RecordDetail recordDetail, int total) {
                Log.d(TAG, "onSucess list");
            }

            @Override
            public void onFail(int flag, String message) {
                Log.d(TAG, "onFail");
            }
        }).getRecordDetial("12");
    }

    private void setView(RecordDetail detail) {

        List<RecordDetail.ItemsEntity> item = detail.getItems();
        listSerivceItemAdapter.refresh(item);

        List<String> photos = detail.getPhotos();
        infoPhotoAdapter.refresh(photos);

        tvTemperature.setText(detail.getTemperature());
        tvBloodPressure.setText(detail.getBlood_pressure());
        tvBloodSugar.setText(detail.getBlood_sugar());
        tvHeartRate.setText(detail.getHeart_rate());

        tvStart.setText(detail.getStart_time());
        tvEnd.setText(detail.getEnd_time());

        tvLog.setText(detail.getNote());
    }
}
