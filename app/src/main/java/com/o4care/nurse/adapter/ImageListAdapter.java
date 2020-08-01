package com.o4care.nurse.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.o4care.nurse.R;
import com.o4care.nurse.bean.ImageBean;

import java.util.List;

public class ImageListAdapter extends BaseQuickAdapter<ImageBean, BaseViewHolder> {
    private Context mContext;

    public ImageListAdapter(Context context, @Nullable List<ImageBean> data) {
        super(R.layout.adapter_photo, data);
        mContext = context;
    }


    @Override
    protected void convert(@NonNull BaseViewHolder holder, ImageBean image) {
        holder.setText(R.id.tv_imgname, image.getImageName());

        ImageView iv = holder.getView(R.id.iv_photo);
        Glide.with(mContext).load(image.getImagePath()).error(R.mipmap.ic_launcher).into(iv);

        Log.i("UploadFilesActivity", "-->progress is " + image.getProgress());
        ProgressBar bar = holder.getView(R.id.progress);
        bar.setProgress(image.getProgress());

        String total = image.getImgSize() + "k";
        String curByte = (image.getProgress() * image.getImgSize()) / 100 + "k";
        Log.i("UploadFilesActivity", "-->size " + curByte + " total size " + total);
        String txPro = image.getProgress() + "%" + "  " + curByte + "/" + total;

        holder.setText(R.id.tv_prog, txPro);
    }
}
