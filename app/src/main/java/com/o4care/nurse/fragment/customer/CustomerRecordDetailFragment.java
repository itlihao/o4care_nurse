package com.o4care.nurse.fragment.customer;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.ScreenUtils;
import com.o4care.nurse.R;
import com.o4care.nurse.adapter.CustomerInfoPhotoAdapter;
import com.o4care.nurse.adapter.ServiceItemList2Adapter;
import com.o4care.nurse.api.CareApi;
import com.o4care.nurse.bean.RecordDetail;
import com.o4care.nurse.fragment.BaseFragment;
import com.o4care.nurse.net.BaseTask;
import com.o4care.nurse.widget.pictureselector.FullyGridLayoutManager;
import com.o4care.nurse.widget.pictureselector.GlideEngine;
import com.o4care.nurse.widget.pictureselector.GridImageAdapter;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
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

    @BindView(R.id.iv_audio)
    ImageView ivAudio;
    @BindView(R.id.iv_video)
    ImageView ivVideo;

    @BindView(R.id.riv_signa)
    RadiusImageView ivSigna;

    private RecordDetail recordInfo;

    private ServiceItemList2Adapter listSerivceItemAdapter;
//    private CustomerInfoPhotoAdapter infoPhotoAdapter;

    private GridImageAdapter mAdapter;

    private List<LocalMedia> mediaSources;
    private LocalMedia signaImg;

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

        /*recyclerPhotos.setLayoutManager(new GridLayoutManager(recyclerPhotos.getContext(), 4));
        infoPhotoAdapter = new CustomerInfoPhotoAdapter(getContext(), new ArrayList<>());
        recyclerPhotos.setAdapter(infoPhotoAdapter);*/
        mediaSources = new ArrayList<>();
        //添加服务拍照控件
        FullyGridLayoutManager manager = new FullyGridLayoutManager(getActivity(), 4, GridLayoutManager.VERTICAL, false);
        recyclerPhotos.setLayoutManager( manager );
        recyclerPhotos.addItemDecoration(new GridSpacingItemDecoration(4, ScreenUtils.dip2px(getActivity(), 8), false));
        recyclerPhotos.setAdapter(mAdapter = new GridImageAdapter(getActivity(), null));
        mAdapter.setList(mediaSources);
        mAdapter.setShow(true);

        getRecordDetial();
    }

    @Override
    protected void initListeners() {
        mAdapter.setOnItemClickListener((v, position) -> {
            List<LocalMedia> selectList = mAdapter.getData();
            if (selectList.size() > 0) {
                LocalMedia media = selectList.get(position);
                String mimeType = media.getMimeType();
                int mediaType = PictureMimeType.getMimeType(mimeType);
                switch (mediaType) {
                    case PictureConfig.TYPE_VIDEO:
                        // 预览视频
                        PictureSelector.create(CustomerRecordDetailFragment.this).externalPictureVideo(media.getPath());
                        break;
                    case PictureConfig.TYPE_AUDIO:
                        // 预览音频
                        PictureSelector.create(CustomerRecordDetailFragment.this).externalPictureAudio(media.getPath());
                        break;
                    default:
                        // 预览图片 可自定长按保存路径
                        PictureSelector.create(CustomerRecordDetailFragment.this)
                                .themeStyle(R.style.XUIPictureStyle) // xml设置主题
                                //.isNotPreviewDownload(true)// 预览图片长按是否可以下载
                                .imageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                                .openExternalPreview(position, selectList);
                        break;
                }
            }
        });

        ivAudio.setOnClickListener(v -> {
            // 预览音频
            PictureSelector.create(CustomerRecordDetailFragment.this).externalPictureAudio(recordInfo.getAudio().get(0));
        });

        ivVideo.setOnClickListener(v -> {
            // 预览视频
            PictureSelector.create(CustomerRecordDetailFragment.this).externalPictureVideo(recordInfo.getVideo().get(0));
        });

        ivSigna.setOnClickListener(v -> {
            List<LocalMedia> media = new ArrayList<>();
            media.add(signaImg);
            PictureSelector.create(CustomerRecordDetailFragment.this)
                    .themeStyle(R.style.XUIPictureStyle) // xml设置主题
                    //.isNotPreviewDownload(true)// 预览图片长按是否可以下载
                    .imageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                    .openExternalPreview(0, media);
        });
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
                recordInfo = recordDetail;
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
        signaImg = new LocalMedia();
        signaImg.setPath(detail.getSignature());

        List<String> photos = detail.getPhotos();
        /*infoPhotoAdapter.refresh(photos);*/
        for (int i = 0; i < photos.size(); i++) {
            LocalMedia media = new LocalMedia();
            media.setPath(photos.get(i));
            mediaSources.add(media);
        }
//                infoPhotoAdapter.refresh(mediaSources);
        mAdapter.setList(mediaSources);
        mAdapter.notifyDataSetChanged();

        ivSigna.setCircle(true);
        GlideEngine.createGlideEngine().loadImage(getActivity(), detail.getSignature(), ivSigna);

        tvTemperature.setText(detail.getTemperature());
        tvBloodPressure.setText(detail.getBlood_pressure());
        tvBloodSugar.setText(detail.getBlood_sugar());
        tvHeartRate.setText(detail.getHeart_rate());

        tvStart.setText(detail.getStart_time());
        tvEnd.setText(detail.getEnd_time());

        tvLog.setText(detail.getNote());
    }
}
