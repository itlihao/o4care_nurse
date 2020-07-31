package com.o4care.nurse.adapter;

import androidx.annotation.NonNull;

import com.o4care.nurse.R;
import com.o4care.nurse.bean.CustomerInfoBean;
import com.o4care.nurse.bean.Record;
import com.o4care.nurse.utils.Constants;
import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.adapter.recyclerview.XRecyclerAdapter;

import java.util.List;

/**
 * 客户列表
 */
public class CustomerRecordAdapter extends BaseRecyclerAdapter<Record> {
    private String TAG = "CustomerInfoPhotoAdapter";

    /**
     * @param custData 客户信息列表
     */
    public CustomerRecordAdapter(List<Record> custData) {
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
        return R.layout.adapter_customer_record;
    }

    public XRecyclerAdapter refresh(List<Record> data) {
        return super.refresh(data);
    }

    @Override
    public void bindData(@NonNull RecyclerViewHolder holder, int position, Record model) {
        if (model == null) {
            return;
        }
        String start = model.getStart_time();
        String[] time = start.split("\\s+");
        holder.text(R.id.tv_start, time[0]);
        holder.text(R.id.tv_time, time[1]);
        holder.text(R.id.tv_week, Constants.week[model.getWeek()]);
        holder.text(R.id.tv_duration, String.format("%smin", model.getDuration()));

    }
}
