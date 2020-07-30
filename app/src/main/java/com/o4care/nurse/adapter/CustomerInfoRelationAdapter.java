package com.o4care.nurse.adapter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.o4care.nurse.R;
import com.o4care.nurse.bean.CustomerInfo;
import com.o4care.nurse.bean.CustomerInfoBean;
import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.adapter.recyclerview.XRecyclerAdapter;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 客户列表
 */
public class CustomerInfoRelationAdapter extends BaseRecyclerAdapter<CustomerInfoBean.ContactEntity> {
    private String TAG = "CustomerInfoPhotoAdapter";

    /**
     * @param custData 客户信息列表
     */
    public CustomerInfoRelationAdapter(List<CustomerInfoBean.ContactEntity> custData) {
        super();
    }

    /**
     * 布局
     *
     * @param viewType
     * @return
     */
    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.adapter_customer_relation;
    }

    public XRecyclerAdapter refresh(List<CustomerInfoBean.ContactEntity> data) {
        return super.refresh(data);
    }

    @Override
    public void bindData(@NonNull RecyclerViewHolder holder, int position, CustomerInfoBean.ContactEntity model) {
        if (model == null) {
            return;
        }
        holder.text(R.id.tv_name, model.getName());
        holder.text(R.id.tv_relation, model.getRelation());
        holder.text(R.id.tv_phone, model.getTel());
    }
}
