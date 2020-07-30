package com.o4care.nurse.adapter;

import androidx.annotation.NonNull;

import com.o4care.nurse.R;
import com.o4care.nurse.bean.CarePlan;
import com.o4care.nurse.bean.Record;
import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.adapter.recyclerview.XRecyclerAdapter;

import java.util.List;

/**
 * 客户列表
 */
public class CarePlanAdapter extends BaseRecyclerAdapter<CarePlan> {
    private String TAG = "CarePlanAdapter";

    /**
     * @param custData 客户信息列表
     */
    public CarePlanAdapter(List<CarePlan> custData) {
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
        return R.layout.adapter_care_plan;
    }

    public XRecyclerAdapter refresh(List<CarePlan> data) {
        return super.refresh(data);
    }

    @Override
    public void bindData(@NonNull RecyclerViewHolder holder, int position, CarePlan model) {
        if (model == null) {
            return;
        }


    }
}
