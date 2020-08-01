package com.o4care.nurse.adapter;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.o4care.nurse.R;
import com.o4care.nurse.bean.ImageBean;
import com.o4care.nurse.bean.Record;
import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.adapter.recyclerview.XRecyclerAdapter;

import java.util.List;

public class ImageListAdapter extends BaseRecyclerAdapter<ImageBean> {
    private Context mContext;

    /**
     * @param custData 客户信息列表
     */
    public ImageListAdapter(List<Record> custData, Context context) {
        super();
        this.mContext = context;
    }


    public XRecyclerAdapter refresh(List<ImageBean> data) {
        return super.refresh(data);
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.adapter_photo;
    }

    @Override
    protected void bindData(@NonNull RecyclerViewHolder holder, int position, ImageBean item) {
        if (item == null) {
            return;
        }

        holder.text(R.id.tv_imgname, item.getImageName());

        ImageView iv = (ImageView) holder.getView(R.id.iv_photo);
        Glide.with(mContext).load(item.getImagePath()).error(R.mipmap.ic_launcher).into(iv);
    }
}
