package com.o4care.nurse.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.cameraview.CameraView;
import com.xuexiang.xaop.annotation.IOThread;
import com.xuexiang.xaop.annotation.Permission;
import com.xuexiang.xui.widget.alpha.XUIAlphaImageView;
import com.o4care.nurse.R;
import com.o4care.nurse.utils.RotateSensorHelper;
import com.o4care.nurse.utils.Utils;
import com.o4care.nurse.utils.XToastUtils;
import com.xuexiang.xutil.common.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.o4care.nurse.utils.Constants.RC_TASK_PHOTO;
import static com.xuexiang.xaop.consts.PermissionConsts.CAMERA;
import static com.xuexiang.xaop.consts.PermissionConsts.STORAGE;

/**
 * 相机拍照界面
 *
 * @author xuexiang
 * @since 2019-09-29 13:58
 */
public class CameraActivity extends AppCompatActivity {
    private static final int[] FLASH_OPTIONS = {
        CameraView.FLASH_AUTO,
        CameraView.FLASH_OFF,
        CameraView.FLASH_ON,
    };

    private static final int[] FLASH_ICONS = {
        R.drawable.ic_flash_auto,
        R.drawable.ic_flash_off,
        R.drawable.ic_flash_on,
    };
    private int mCurrentFlash;

    @BindView(R.id.iv_flash_light)
    XUIAlphaImageView ivFlashLight;
    @BindView(R.id.iv_camera_close)
    XUIAlphaImageView ivCameraClose;
    @BindView(R.id.iv_take_photo)
    XUIAlphaImageView ivTakePhoto;
    @BindView(R.id.iv_picture_select)
    XUIAlphaImageView ivPictureSelect;
    @BindView(R.id.camera_view)
    CameraView mCameraView;

    private Unbinder mUnbinder;

    private RotateSensorHelper mSensorHelper;

    @Permission({CAMERA, STORAGE})
    public static void open(@NonNull Activity activity) {
        activity.startActivityForResult(new Intent(activity, CameraActivity.class), RC_TASK_PHOTO);
    }

    @Permission({CAMERA, STORAGE})
    public static void open(@NonNull Activity activity, int requestCode) {
        activity.startActivityForResult(new Intent(activity, CameraActivity.class), requestCode);
    }

    @Permission({CAMERA, STORAGE})
    public static void open(@NonNull Fragment fragment) {
        fragment.startActivityForResult(new Intent(fragment.getContext(), CameraActivity.class), RC_TASK_PHOTO);
    }

    @Permission({CAMERA, STORAGE})
    public static void open(@NonNull Fragment fragment, int requestCode) {
        fragment.startActivityForResult(new Intent(fragment.getContext(), CameraActivity.class), requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        mUnbinder = ButterKnife.bind(this);

        if (mCameraView != null) {
            mCameraView.addCallback(mCallback);
        }

        List<View> views = new ArrayList<>();
        views.add(ivFlashLight);
        views.add(ivCameraClose);
        views.add(ivTakePhoto);
        views.add(ivPictureSelect);
        mSensorHelper = new RotateSensorHelper(this, views);
    }

    @OnClick({R.id.iv_camera_close, R.id.iv_flash_light, R.id.iv_take_photo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_camera_close:
                finish();
                break;
            case R.id.iv_flash_light:
                if (mCameraView != null) {
                    mCurrentFlash = (mCurrentFlash + 1) % FLASH_OPTIONS.length;
                    ivFlashLight.setImageResource(FLASH_ICONS[mCurrentFlash]);
                    mCameraView.setFlash(FLASH_OPTIONS[mCurrentFlash]);
                }
                break;
            case R.id.iv_take_photo:
                if (mCameraView != null) {
                    mCameraView.takePicture();
                }
                break;
            default:
                break;
        }
    }

    /**
     * 拍照的回调
     */
    private CameraView.Callback mCallback = new CameraView.Callback() {
        @Override
        public void onCameraOpened(CameraView cameraView) {
        }

        @Override
        public void onCameraClosed(CameraView cameraView) {
        }

        @Override
        public void onPictureTaken(final CameraView cameraView, final byte[] data) {
             handlePictureTaken(data);
        }
    };

    @IOThread
    private void handlePictureTaken(byte[] data) {
        String picPath = Utils.handleOnPictureTaken(data);
        if (!StringUtils.isEmpty(picPath)) {
            Intent picIntent = new Intent();
            picIntent.putExtra("filePath", picPath);
            setResult(RC_TASK_PHOTO, picIntent);
            finish();
        } else {
            XToastUtils.error("图片保存失败！");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mCameraView != null) {
            mCameraView.start();
        }
    }

    @Override
    protected void onPause() {
        if (mCameraView != null) {
            mCameraView.stop();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        if (isFinishing()) {
            onRelease();
        }
        super.onStop();
    }

    /**
     * 资源释放
     */
    protected void onRelease() {
        mSensorHelper.recycle();
    }
}
