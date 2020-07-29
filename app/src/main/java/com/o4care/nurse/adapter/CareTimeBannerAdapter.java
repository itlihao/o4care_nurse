package com.o4care.nurse.adapter;

import androidx.annotation.NonNull;
import com.o4care.nurse.R;
import com.o4care.nurse.bean.CustomerInfo;
import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.adapter.recyclerview.XRecyclerAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * 照护比轮播
 */
public class CareTimeBannerAdapter extends BaseRecyclerAdapter<CustomerInfo> {
    /**
     * @param bannerData 轮播条的内容
     */
    public CareTimeBannerAdapter(List<CustomerInfo> bannerData) {
        super( bannerData);
    }

    /**
     * 布局
     *
     * @param viewType
     * @return
     */
    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.adapter_care_time_banner;
    }

    public XRecyclerAdapter refresh(List<CustomerInfo> data) {
        List<CustomerInfo> list = new ArrayList<>(data);
        //用于占位
        list.add(0, new CustomerInfo());
        return super.refresh(list);
    }

    @Override
    public void bindData(@NonNull RecyclerViewHolder holder, int position, CustomerInfo model) {
        if (model == null) {
            return;
        }
    }
}
