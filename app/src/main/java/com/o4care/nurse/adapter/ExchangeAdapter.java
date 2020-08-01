package com.o4care.nurse.adapter;

import androidx.annotation.NonNull;

import com.o4care.nurse.R;
import com.o4care.nurse.bean.Exchange;
import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.adapter.recyclerview.XRecyclerAdapter;

import java.util.List;

/**
 * 客户列表
 */
public class ExchangeAdapter extends BaseRecyclerAdapter<Exchange> {
    private String TAG = "CarePlanAdapter";
    String[] status = new String[] {"未执行", "执行中", "已完成"};

    /**
     * @param custData 客户信息列表
     */
    public ExchangeAdapter(List<Exchange> custData) {
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
        return R.layout.adapter_exchange;
    }

    public XRecyclerAdapter refresh(List<Exchange> data) {
        return super.refresh(data);
    }

    @Override
    public void bindData(@NonNull RecyclerViewHolder holder, int position, Exchange model) {
        if (model == null) {
            return;
        }
        holder.text(R.id.tv_start_time, "时间：" + model.getPlan_time());
        holder.text(R.id.tv_ststus, status[model.getState()]);
        holder.text(R.id.tv_doworker, model.getDo_worker_name());
        holder.text(R.id.tv_plan_time, "60分钟");
        holder.text(R.id.tv_planworker, model.getPlan_worker_name());
        holder.text(R.id.tv_care_customer, model.getCust_name());
        holder.text(R.id.tv_address, model.getCust_address());
    }
}
