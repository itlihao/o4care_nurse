package com.o4care.nurse.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.LruCache;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.just.agentweb.core.AgentWeb;
import com.just.agentweb.core.client.DefaultWebClient;
import com.luck.picture.lib.PictureSelectionModel;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.xuexiang.xui.utils.DrawableUtils;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.o4care.nurse.R;
import com.o4care.nurse.widget.webview.AgentWebActivity;
import com.o4care.nurse.widget.webview.MiddlewareWebViewClient;
import com.o4care.nurse.utils.update.CustomUpdateFailureListener;
import com.xuexiang.xupdate.XUpdate;
import com.xuexiang.xutil.data.DateUtils;
import com.xuexiang.xutil.file.FileIOUtils;
import com.xuexiang.xutil.file.FileUtils;

import java.io.File;

import static com.o4care.nurse.widget.webview.AgentWebFragment.KEY_URL;

/**
 * @author XUE
 * @since 2019/4/1 11:25
 */
public final class Utils {

    public final static String UPDATE_URL = "https://gitee.com/xuexiangjys/XUI/raw/master/jsonapi/update_api.json";

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }


    /**
     * 请求浏览器
     *
     * @param url
     */
    public static void goWeb(Context context, final String url) {
        Intent intent = new Intent(context, AgentWebActivity.class);
        intent.putExtra(KEY_URL, url);
        context.startActivity(intent);
    }

    /**
     * 创建AgentWeb
     *
     * @param fragment
     * @param viewGroup
     * @param url
     * @return
     */
    public static AgentWeb createAgentWeb(Fragment fragment, ViewGroup viewGroup, String url) {
        return AgentWeb.with(fragment)
            .setAgentWebParent(viewGroup, -1, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
            .useDefaultIndicator(-1, 3)
            .useMiddlewareWebClient(new MiddlewareWebViewClient())
            .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)
            .createAgentWeb()
            .ready()
            .go(url);
    }

    /**
     * 进行版本更新检查
     *
     * @param context
     */
    public static void checkUpdate(Context context, boolean needErrorTip) {
        XUpdate.newBuild(context).updateUrl(UPDATE_URL).update();
        XUpdate.get().setOnUpdateFailureListener(new CustomUpdateFailureListener(needErrorTip));
    }

    //==========图片选择===========//

    /**
     * 获取图片选择的配置
     *
     * @param fragment
     * @return
     */
    public static PictureSelectionModel getPictureSelector(Fragment fragment) {
        return PictureSelector.create(fragment)
            .openGallery(PictureMimeType.ofImage())
            .theme(SettingSPUtils.getInstance().isUseCustomTheme() ? R.style.XUIPictureStyle_Custom : R.style.XUIPictureStyle)
            .maxSelectNum(8)
            .minSelectNum(1)
            .selectionMode(PictureConfig.MULTIPLE)
            .previewImage(true)
            .isCamera(true)
            .enableCrop(false)
            .compress(true)
            .previewEggs(true);
    }

    public static PictureSelectionModel getPictureSelector(Activity activity) {
        return PictureSelector.create(activity)
            .openGallery(PictureMimeType.ofImage())
            .theme(SettingSPUtils.getInstance().isUseCustomTheme() ? R.style.XUIPictureStyle_Custom : R.style.XUIPictureStyle)
            .maxSelectNum(8)
            .minSelectNum(1)
            .selectionMode(PictureConfig.MULTIPLE)
            .previewImage(true)
            .isCamera(true)
            .enableCrop(false)
            .compress(true)
            .previewEggs(true);
    }

    //==========拍照===========//

    public static final String JPEG = ".jpeg";

    /**
     * 处理拍照的回调
     *
     * @param data
     * @return
     */
    public static String handleOnPictureTaken(byte[] data) {
        return handleOnPictureTaken(data, JPEG);
    }

    /**
     * 处理拍照的回调
     *
     * @param data
     * @return
     */
    public static String handleOnPictureTaken(byte[] data, String fileSuffix) {
        String picPath = FileUtils.getDiskCacheDir() + "/images/" + DateUtils.getNowMills() + fileSuffix;
        boolean result = FileIOUtils.writeFileFromBytesByStream(picPath, data);
        return result ? picPath : "";
    }

    public static String getImageSavePath() {
        return FileUtils.getDiskCacheDir("images") + File.separator + DateUtils.getNowMills() + JPEG;
    }

    //==========截图===========//

    /**
     * 显示截图结果
     *
     * @param view
     */
    public static void showCaptureBitmap(View view) {
        final MaterialDialog dialog = new MaterialDialog.Builder(view.getContext())
                .customView(R.layout.dialog_drawable_utils_createfromview, true)
                .title("截图结果")
                .build();
        ImageView displayImageView = dialog.findViewById(R.id.createFromViewDisplay);
        Bitmap createFromViewBitmap = DrawableUtils.createBitmapFromView(view);
        displayImageView.setImageBitmap(createFromViewBitmap);

        displayImageView.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    /**
     * 显示截图结果
     */
    public static void showCaptureBitmap(Context context, Bitmap bitmap) {
        final MaterialDialog dialog = new MaterialDialog.Builder(context)
                .customView(R.layout.dialog_drawable_utils_createfromview, true)
                .title("截图结果")
                .build();
        ImageView displayImageView = dialog.findViewById(R.id.createFromViewDisplay);
        displayImageView.setImageBitmap(bitmap);

        displayImageView.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }


    /**
     * 截图RecyclerView
     *
     * @param recyclerView
     * @return
     */
    public static Bitmap getRecyclerViewScreenSpot(RecyclerView recyclerView) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        Bitmap bigBitmap = null;
        if (adapter != null) {
            int size = adapter.getItemCount();
            int height = 0;
            Paint paint = new Paint();
            int iHeight = 0;
            final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
            final int cacheSize = maxMemory / 8;
            LruCache<String, Bitmap> bitmapCache = new LruCache<>(cacheSize);
            for (int i = 0; i < size; i++) {
                RecyclerView.ViewHolder holder = adapter.createViewHolder(recyclerView, adapter.getItemViewType(i));
                adapter.onBindViewHolder(holder, i);
                holder.itemView.measure(
                        View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), View.MeasureSpec.EXACTLY),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                holder.itemView.layout(0, 0, holder.itemView.getMeasuredWidth(),
                        holder.itemView.getMeasuredHeight());
                holder.itemView.setDrawingCacheEnabled(true);
                holder.itemView.buildDrawingCache();
                Bitmap drawingCache = holder.itemView.getDrawingCache();
                if (drawingCache != null) {
                    bitmapCache.put(String.valueOf(i), drawingCache);
                }
                height += holder.itemView.getMeasuredHeight();
            }
            // 这个地方容易出现OOM，关键是要看截取RecyclerView的展开的宽高
            bigBitmap = DrawableUtils.createBitmapSafely(recyclerView.getMeasuredWidth(), height, Bitmap.Config.ARGB_8888, 1);
            if (bigBitmap == null) {
                return null;
            }
            Canvas canvas = new Canvas(bigBitmap);
            Drawable background = recyclerView.getBackground();
            //先画RecyclerView的背景色
            if (background instanceof ColorDrawable) {
                ColorDrawable lColorDrawable = (ColorDrawable) background;
                int color = lColorDrawable.getColor();
                canvas.drawColor(color);
            }
            for (int i = 0; i < size; i++) {
                Bitmap bitmap = bitmapCache.get(String.valueOf(i));
                canvas.drawBitmap(bitmap, 0f, iHeight, paint);
                iHeight += bitmap.getHeight();
                bitmap.recycle();
            }
            canvas.setBitmap(null);
        }
        return bigBitmap;
    }
}
