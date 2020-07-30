package com.o4care.nurse.adapter;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.o4care.nurse.R;
import com.o4care.nurse.bean.CustomerInfoBean;
import com.o4care.nurse.widget.pictureselector.GlideEngine;
import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.adapter.recyclerview.XRecyclerAdapter;

import java.util.List;

/**
 * 客户列表
 */
public class CustomerInfoPhotoAdapter extends BaseRecyclerAdapter<String> {
    private String TAG = "CustomerInfoPhotoAdapter";

    private Context context;
    /**
     * @param custData 客户信息列表
     */
    public CustomerInfoPhotoAdapter(Context context, List<String> custData) {
        super();
        this.context = context;
    }

    /**
     * 布局
     *
     * @param viewType
     * @return
     */
    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.adapter_customer_photo;
    }

    public XRecyclerAdapter refresh(List<String> data) {
        return super.refresh(data);
    }

    @Override
    public void bindData(@NonNull RecyclerViewHolder holder, int position, String url) {
        if (url == null) {
            return;
        }

        GlideEngine.createGlideEngine().loadImage(context, url, holder.getImageView(R.id.iv_image));
    }
}
