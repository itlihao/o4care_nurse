package com.o4care.nurse.adapter;

import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;

import com.o4care.nurse.R;
import com.o4care.nurse.bean.CareItem;
import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.adapter.recyclerview.XRecyclerAdapter;

import java.util.List;

/**
 * 客户列表
 */
public class CareItemContentAdapter extends BaseRecyclerAdapter<CareItem> {
    private String TAG = "CarePlanAdapter";

    /**
     * @param custData 客户信息列表
     */
    public CareItemContentAdapter(List<CareItem> custData) {
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
        return R.layout.adapter_care_item_cont;
    }

    public XRecyclerAdapter refresh(List<CareItem> data) {
        return super.refresh(data);
    }

    @Override
    public void bindData(@NonNull RecyclerViewHolder holder, int position, CareItem model) {
        if (model == null) {
            return;
        }
        holder.text(R.id.tv_item_cont, model.getName());

        AppCompatCheckBox box = (AppCompatCheckBox) holder.getView(R.id.cb_box);
        box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
    }
}
