package com.o4care.nurse.adapter;

import android.util.Log;
import com.o4care.nurse.R;
import com.o4care.nurse.bean.CustomerInfo;
import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.adapter.recyclerview.XRecyclerAdapter;

import java.util.List;
import androidx.annotation.NonNull;

/**
 * 客户列表
 */
public class CustomerListAdapter extends BaseRecyclerAdapter<CustomerInfo> {
    private String TAG = "CustomerListAdapter";

    /**
     * @param custData 客户信息列表
     */
    public CustomerListAdapter(List<CustomerInfo> custData) {
        super( custData );
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
        return R.layout.adapter_customer_list;
    }

    public XRecyclerAdapter refresh(List<CustomerInfo> data) {
        return super.refresh(data);
    }

    @Override
    public void bindData(@NonNull RecyclerViewHolder holder, int position, CustomerInfo model) {
        if (model == null) {
            return;
        }
        holder.text(R.id.tx_name, model.getName());
        holder.text(R.id.tx_address, model.getAddress());
        holder.text(R.id.tx_task, model.getNextTask());
    }
}
