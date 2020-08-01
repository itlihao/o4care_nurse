package com.o4care.nurse.adapter;

import com.chad.library.adapter.base.BaseNodeAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.o4care.nurse.adapter.provider.FirstNode;
import com.o4care.nurse.adapter.provider.FirstProvider;
import com.o4care.nurse.adapter.provider.SecondNode;
import com.o4care.nurse.adapter.provider.SecondProvider;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NodeTreeAdapter extends BaseNodeAdapter {

    public NodeTreeAdapter(FirstProvider firstProvider) {
        super();
        addNodeProvider(firstProvider);
        addNodeProvider(new SecondProvider());
    }

    @Override
    protected int getItemType(@NotNull List<? extends BaseNode> data, int position) {
        BaseNode node = data.get(position);
        if (node instanceof FirstNode) {
            return 1;
        } else if (node instanceof SecondNode) {
            return 2;
        }
        return -1;
    }

    public static final int EXPAND_COLLAPSE_PAYLOAD = 110;
}
