package com.o4care.nurse.fragment.customer;

import android.content.Intent;
import android.util.Log;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.luck.picture.lib.tools.ScreenUtils;
import com.o4care.nurse.R;
import com.o4care.nurse.activity.SignatureActivity;
import com.o4care.nurse.adapter.ServiceItemListAdapter;
import com.o4care.nurse.api.CareApi;
import com.o4care.nurse.bean.ServiceItem;
import com.o4care.nurse.fragment.BaseFragment;
import com.o4care.nurse.net.BaseTask;
import com.o4care.nurse.utils.Constants;
import com.o4care.nurse.widget.pictureselector.FullyGridLayoutManager;
import com.o4care.nurse.widget.pictureselector.GlideEngine;
import com.o4care.nurse.widget.pictureselector.GridImageAdapter;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * @author wlcare
 */
@Page(anim = CoreAnim.none)
public class CustomerTaskFragment extends BaseFragment implements GridImageAdapter.onAddPicClickListener{
    private static String TAG = "CustomerTaskFragment";

    @BindView(R.id.rv_service_items)
    RecyclerView rvServiceItems;
    @BindView(R.id.stv_sign)
    SuperTextView  stvSign;
    @BindView(R.id.rv_photos)
    RecyclerView rvPhotos;

    private ServiceItemListAdapter listSerivceItemAdapter;
    List<ServiceItem> listItem;

    private GridImageAdapter mAdapter;
    private List<LocalMedia> mSelectList = new ArrayList<>();

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
        return R.layout.fragment_cust_task;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {
        rvServiceItems.setLayoutManager(new LinearLayoutManager(rvServiceItems.getContext()));
        rvServiceItems.setItemAnimator(new DefaultItemAnimator());
        listSerivceItemAdapter = new ServiceItemListAdapter( new ArrayList<ServiceItem>() );
        rvServiceItems.setAdapter(listSerivceItemAdapter);
        getServiceItemList();

        stvSign.setOnSuperTextViewClickListener(superTextView->{
            //XToastUtils.toast("整个item的点击事件");
            Intent intentSign = new Intent(getActivity(), SignatureActivity.class);
            startActivityForResult( intentSign, Constants.RC_TASK_SIGNATURE);
        });

        //添加服务拍照控件
        FullyGridLayoutManager manager = new FullyGridLayoutManager(getActivity(), 4, GridLayoutManager.VERTICAL, false);
        rvPhotos.setLayoutManager( manager );
        rvPhotos.addItemDecoration(new GridSpacingItemDecoration(4, ScreenUtils.dip2px(getActivity(), 8), false));
        rvPhotos.setAdapter(mAdapter = new GridImageAdapter(getActivity(), this));
        mAdapter.setList(mSelectList);
        mAdapter.setSelectMax(8);
        /*
        mAdapter.setOnItemClickListener(
            (v, position) -> PictureSelector.create(CustomerTaskFragment.this)
            .themeStyle(R.style.XUIPictureStyle).openExternalPreview(position, mSelectList)
        );*/

        mAdapter.setOnItemClickListener((v, position) -> {
            List<LocalMedia> selectList = mAdapter.getData();
            if (selectList.size() > 0) {
                LocalMedia media = selectList.get(position);
                String mimeType = media.getMimeType();
                int mediaType = PictureMimeType.getMimeType(mimeType);
                switch (mediaType) {
                    case PictureConfig.TYPE_VIDEO:
                        // 预览视频
                        PictureSelector.create(CustomerTaskFragment.this).externalPictureVideo(media.getPath());
                        break;
                    case PictureConfig.TYPE_AUDIO:
                        // 预览音频
                        PictureSelector.create(CustomerTaskFragment.this).externalPictureAudio(media.getPath());
                        break;
                    default:
                        // 预览图片 可自定长按保存路径
//                        PictureWindowAnimationStyle animationStyle = new PictureWindowAnimationStyle();
//                        animationStyle.activityPreviewEnterAnimation = R.anim.picture_anim_up_in;
//                        animationStyle.activityPreviewExitAnimation = R.anim.picture_anim_down_out;
                        PictureSelector.create(CustomerTaskFragment.this)
                                .themeStyle(R.style.XUIPictureStyle) // xml设置主题
                                //.setPictureStyle(mPictureParameterStyle)// 动态自定义相册主题
                                //.setPictureWindowAnimationStyle(animationStyle)// 自定义页面启动动画
                                //.isNotPreviewDownload(true)// 预览图片长按是否可以下载
                                .imageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                                .openExternalPreview(position, selectList);
                        break;
                }
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch ( resultCode ) {
            case Constants.RC_TASK_SIGNATURE: {
                String filePath = data.getStringExtra("filePath");
                Log.d(TAG, filePath);
            }
            break;
            case PictureConfig.REQUEST_CAMERA:
                // 图片选择
                Log.d(TAG, "REQUEST_CAMERA");
                mSelectList = PictureSelector.obtainMultipleResult(data);
                mAdapter.setList(mSelectList);
                mAdapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
    }

    @Override
    public void onAddPicClick() {
        PictureSelector.create(CustomerTaskFragment.this)
        .openCamera(PictureMimeType.ofAll())// 单独拍照，也可录像或也可音频 看你传入的类型是图片or视频
        .imageEngine(GlideEngine.createGlideEngine())
        .theme(R.style.XUIPictureStyle )
        .maxSelectNum(8)
        .minSelectNum(1)
        .isCamera(true)
        .isUseCustomCamera(true)
        .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
        .selectionData(mAdapter.getData())// 是否传入已选图片
        .forResult(new MyResultCallback(mAdapter));
    }

    /**
     * 返回结果回调
     */
    private static class MyResultCallback implements OnResultCallbackListener<LocalMedia> {
        private WeakReference<GridImageAdapter> mAdapterWeakReference;

        public MyResultCallback(GridImageAdapter adapter) {
            super();
            this.mAdapterWeakReference = new WeakReference<>(adapter);
        }

        @Override
        public void onResult(List<LocalMedia> result) {
            for (LocalMedia media : result) {
                /*
                Log.i(TAG, "是否压缩:" + media.isCompressed());
                Log.i(TAG, "压缩:" + media.getCompressPath());
                Log.i(TAG, "原图:" + media.getPath());
                Log.i(TAG, "是否裁剪:" + media.isCut());
                Log.i(TAG, "裁剪:" + media.getCutPath());
                Log.i(TAG, "是否开启原图:" + media.isOriginal());
                Log.i(TAG, "原图路径:" + media.getOriginalPath());
                Log.i(TAG, "Android Q 特有Path:" + media.getAndroidQToPath());
                Log.i(TAG, "宽高: " + media.getWidth() + "x" + media.getHeight());
                Log.i(TAG, "Size: " + media.getSize());
                */

                // TODO 可以通过PictureSelectorExternalUtils.getExifInterface();方法获取一些额外的资源信息，如旋转角度、经纬度等信息
            }
            if (mAdapterWeakReference.get() != null) {
                mAdapterWeakReference.get().setList(result);
                mAdapterWeakReference.get().notifyDataSetChanged();
            }
        }

        @Override
        public void onCancel() {
            //Log.i(TAG, "PictureSelector Cancel");
        }
    }


    void  getServiceItemList(){
        Log.d(TAG, "getServiceItemList");
        new CareApi(getActivity(), new BaseTask.ResponseListener<List<ServiceItem>>() {
            @Override
            public void onSuccess(int flag, List<ServiceItem> listItemNet) {
                Log.d(TAG, "onSucess data");
                listItem = listItemNet;
                for(int i=0;i<listItemNet.size();i++){
                    Log.d(TAG, listItemNet.get(i).getName());
                    Log.d(TAG, String.valueOf(listItemNet.get(i).getManual()) );
                }
                listSerivceItemAdapter.refresh(listItemNet);
            }

            @Override
            public void onSuccess(int flag, List<ServiceItem> listItemNet, int total) {
                Log.d(TAG, "onSucess list");
            }

            @Override
            public void onFail(int flag, String message) {
                Log.d(TAG, "onFail");
            }
        }).getAllServiceItems("12");
    }
}
