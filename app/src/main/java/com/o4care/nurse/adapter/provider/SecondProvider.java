package com.o4care.nurse.adapter.provider;

import android.view.View;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.o4care.nurse.R;

import org.jetbrains.annotations.NotNull;

public class SecondProvider extends BaseNodeProvider {

    @Override
    public int getItemViewType() {
        return 2;
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_service_item_list;
    }

    @Override
    public void convert(@NotNull BaseViewHolder helper, @NotNull BaseNode data) {
        SecondNode entity = (SecondNode) data;
        helper.setText(R.id.tx_manual, entity.getTitle());

        /*if (entity.isExpanded()) {
            helper.setImageResource(R.id.iv, R.mipmap.arrow_b);
        } else {
            helper.setImageResource(R.id.iv, R.mipmap.arrow_r);ffffff
        }*/
    }

    @Override
    public void onClick(@NotNull BaseViewHolder helper, @NotNull View view, BaseNode data, int position) {
        /*SecondNode entity = (SecondNode) data;
        if (entity.isExpanded()) {
            getAdapter().collapse(position);
        } else {
            getAdapter().expandAndCollapseOther(position);
        }*/
    }
}
