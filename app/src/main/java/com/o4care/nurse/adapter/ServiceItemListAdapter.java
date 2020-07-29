package com.o4care.nurse.adapter;

import android.util.Log;

import com.o4care.nurse.R;
import com.o4care.nurse.bean.ServiceItem;
import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.adapter.recyclerview.XRecyclerAdapter;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * 客户列表
 */
public class ServiceItemListAdapter extends BaseRecyclerAdapter<ServiceItem> {
    private String TAG = "ServiceItemListAdapter";

    /**
     * @param serviceItem 服务项目列表
     */
    public ServiceItemListAdapter(List<ServiceItem> serviceItem) {
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

    public XRecyclerAdapter refresh(List<ServiceItem> data) {
        return super.refresh(data);
    }

    @Override
    public void bindData(@NonNull RecyclerViewHolder holder, int position, ServiceItem model) {
        if (model == null) {
            return;
        }
        //holder.text(R.id.tx_id, model.getId());
        holder.text(R.id.tx_name, model.getName());
        holder.text(R.id.tx_manual, model.getManual());
    }
}
