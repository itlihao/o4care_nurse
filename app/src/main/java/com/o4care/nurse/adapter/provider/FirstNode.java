package com.o4care.nurse.adapter.provider;

import com.chad.library.adapter.base.entity.node.BaseExpandNode;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.o4care.nurse.bean.CarePlan;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FirstNode extends BaseExpandNode {

    private List<BaseNode> childNode;
    private String title;
    private CarePlan carePlans;

    public FirstNode(List<BaseNode> childNode, CarePlan carePlan, String title) {
        this.childNode = childNode;
        this.title = title;
        this.carePlans = carePlan;

        setExpanded(false);
    }

    public String getTitle() {
        return title;
    }


    @Nullable
    @Override
    public List<BaseNode> getChildNode() {
        return childNode;
    }

    public CarePlan getCarePlans() {
        return carePlans;
    }
}
