package com.o4care.nurse.adapter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.o4care.nurse.R;
import com.o4care.nurse.bean.RecordDetail;
import com.o4care.nurse.bean.ServiceItem;
import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.adapter.recyclerview.XRecyclerAdapter;

import java.util.List;

/**
 * 客户列表
 */
public class ServiceItemList2Adapter extends BaseRecyclerAdapter<RecordDetail.ItemsEntity> {
    private String TAG = "ServiceItemListAdapter";

    /**
     * @param serviceItem 服务项目列表
     */
    public ServiceItemList2Adapter(List<RecordDetail.ItemsEntity> serviceItem) {
        super( serviceItem );
        Log.d(TAG,"lisong");
    }

    /**
     * 布局
     *
     * @param viewType
     * @return
     */
    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.adapter_service_item_list;
    }

    public XRecyclerAdapter refresh(List<RecordDetail.ItemsEntity> data) {
        return super.refresh(data);
    }

    @Override
    public void bindData(@NonNull RecyclerViewHolder holder, int position, RecordDetail.ItemsEntity model) {
        if (model == null) {
            return;
        }
        holder.text(R.id.tx_manual, model.getName());
    }
}
