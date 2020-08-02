package com.o4care.nurse.fragment.customer;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.ScreenUtils;
import com.o4care.nurse.R;
import com.o4care.nurse.adapter.CustomerInfoRelationAdapter;
import com.o4care.nurse.api.WorkerApi;
import com.o4care.nurse.bean.CustomerInfoBean;
import com.o4care.nurse.fragment.BaseFragment;
import com.o4care.nurse.net.BaseTask;
import com.o4care.nurse.widget.pictureselector.FullyGridLayoutManager;
import com.o4care.nurse.widget.pictureselector.GlideEngine;
import com.o4care.nurse.widget.pictureselector.GridImageAdapter;
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
public class CustomerInfoFragment extends BaseFragment {
    private String TAG = "CustomerInfoFragment";

    @BindView(R.id.rv_photos)
    SwipeRecyclerView recyclerPhotos;
    @BindView(R.id.rv_homeinfo)
    SwipeRecyclerView recyclerHomeinfo;

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_age)
    TextView tvAge;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.tv_blood)
    TextView tvBlood;

    @BindView(R.id.tv_height)
    TextView tvHeight;
    @BindView(R.id.tv_weight)
    TextView tvWeight;
    @BindView(R.id.tv_tel)
    TextView tvTel;
    @BindView(R.id.tv_addr)
    TextView tvAddr;
    @BindView(R.id.tv_idcard)
    TextView tvIdCard;
    @BindView(R.id.tv_case)
    TextView tvCase;

    @BindView(R.id.iv_image)
    ImageView ivImage;

    private CustomerInfoRelationAdapter infoRelationAdapter;
    //    private CustomerInfoPhotoAdapter infoPhotoAdapter;
    private GridImageAdapter mAdapter;

    private List<LocalMedia> mediaSources;

    /**
     * @return 返回为 null意为不需要导航栏
     */
    @Override
    protected TitleBar initTitle() {
        return null;
    }

    /**
     * 布局的资源id
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_map;
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
                        PictureSelector.create(CustomerInfoFragment.this).externalPictureVideo(media.getPath());
                        break;
                    case PictureConfig.TYPE_AUDIO:
                        // 预览音频
                        PictureSelector.create(CustomerInfoFragment.this).externalPictureAudio(media.getPath());
                        break;
                    default:
                        // 预览图片 可自定长按保存路径
                        PictureSelector.create(CustomerInfoFragment.this)
                                .themeStyle(R.style.XUIPictureStyle) // xml设置主题
                                //.isNotPreviewDownload(true)// 预览图片长按是否可以下载
                                .imageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                                .openExternalPreview(position, selectList);
                        break;
                }
            }
        });
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {

        WidgetUtils.initRecyclerView(recyclerHomeinfo, 1);

        /* 初始化客户列表 */
        recyclerHomeinfo.setLayoutManager(new LinearLayoutManager(recyclerHomeinfo.getContext()));
        recyclerHomeinfo.setItemAnimator(new DefaultItemAnimator());
        infoRelationAdapter = new CustomerInfoRelationAdapter(new ArrayList<>());
        recyclerHomeinfo.setAdapter(infoRelationAdapter);

        /*recyclerPhotos.setLayoutManager(new GridLayoutManager(recyclerHomeinfo.getContext(), 4));
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
        mAdapter.setSelectMax(8);

        getCustomerInfo();
    }

    void getCustomerInfo() {
        Log.d(TAG, "getCustomerList");
        new WorkerApi(getActivity(), new BaseTask.ResponseListener<CustomerInfoBean>() {
            @Override
            public void onSuccess(int flag, CustomerInfoBean customerInfo) {
                Log.d(TAG, "onSucess data");
                Log.d(TAG, customerInfo.toString());

                setView(customerInfo);

                List<CustomerInfoBean.ContactEntity> contact = customerInfo.getContact();
                infoRelationAdapter.refresh(contact);

                List<String> photos = customerInfo.getDisease_pic();

                for (int i = 0; i < photos.size(); i++) {
                    LocalMedia media = new LocalMedia();
                    media.setPath(photos.get(i));
                    mediaSources.add(media);
                }
//                infoPhotoAdapter.refresh(mediaSources);
                mAdapter.setList(mediaSources);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onSuccess(int flag, CustomerInfoBean customerInfo, int total) {
                Log.d(TAG, "onSucess list");
            }

            @Override
            public void onFail(int flag, String message) {
                Log.d(TAG, "onFail");
            }
        }).getCustomerInfo("123");
    }

    private void setView(CustomerInfoBean info) {
        tvName.setText(info.getName());
        tvAge.setText(String.valueOf(info.getAge()));
        tvBirthday.setText(info.getBirthday());
        tvBlood.setText(info.getBlood());
        tvHeight.setText(String.valueOf(info.getHeight()));
        tvWeight.setText(String.valueOf(info.getWeight()));
        tvTel.setText(info.getTel());
        tvAddr.setText(info.getAddress());
        tvIdCard.setText(info.getIdcard());

        tvCase.setText(info.getDisease());

        GlideEngine.createGlideEngine().loadImage(this.mActivity, info.getImg_url(), ivImage);
    }
}
