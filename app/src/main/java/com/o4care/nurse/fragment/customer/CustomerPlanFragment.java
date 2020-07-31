package com.o4care.nurse.fragment.customer;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.o4care.nurse.R;
import com.o4care.nurse.adapter.NodeTreeAdapter;
import com.o4care.nurse.adapter.provider.FirstNode;
import com.o4care.nurse.adapter.provider.SecondNode;
import com.o4care.nurse.api.CareApi;
import com.o4care.nurse.bean.CarePlan;
import com.o4care.nurse.fragment.BaseFragment;
import com.o4care.nurse.net.BaseTask;
import com.o4care.nurse.utils.Constants;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author wlcare
 */
@Page(anim = CoreAnim.none)
public class CustomerPlanFragment extends BaseFragment {
    private static final String TAG = "CustomerPlanFragment";

    @BindView(R.id.recycle_record)
    RecyclerView rvServiceItems;

    private NodeTreeAdapter adapter = new NodeTreeAdapter();

    /**
     * @return 返回为 null意为不需要导航栏
     */
    @Override
    protected TitleBar initTitle() {
        return null;
    }

    /**
     * 布局的资源id
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_care_plan;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {
        WidgetUtils.initRecyclerView(rvServiceItems, 1);

        rvServiceItems.setLayoutManager(new LinearLayoutManager(rvServiceItems.getContext()));
        rvServiceItems.setAdapter(adapter);

        getCarePlan();
    }

    private void getCarePlan() {
        new CareApi(getActivity(), new BaseTask.ResponseListener<List<CarePlan>>() {
            @Override
            public void onSuccess(int flag, List<CarePlan> carePlans) {
                Log.d(TAG, "onSucess data");

                List<BaseNode> list = new ArrayList<>();
                for (int i = 0; i < carePlans.size(); i++) {
                    List<BaseNode> secondNodeList = new ArrayList<>();
                    List<CarePlan.ItemsEntity> it = carePlans.get(i).getItems();
                    for (int n = 0; n < it.size(); n++) {
                        SecondNode seNode = new SecondNode(it.get(n).getName());
                        secondNodeList.add(seNode);
                    }

                    String title = String.format("每周 %s %s", Constants.week[carePlans.get(i).getWeek()], carePlans.get(i).getTime());
                    FirstNode entity = new FirstNode(secondNodeList, title);

                    // 模拟 默认第0个是展开的
                    entity.setExpanded(i == 0);

                    list.add(entity);
                }
                adapter.setList(list);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onSuccess(int flag, List<CarePlan> carePlans, int total) {
                Log.d(TAG, "onSucess list");
            }

            @Override
            public void onFail(int flag, String message) {
                Log.d(TAG, "onFail");
            }
        }).getCarePlan("123", "12");
    }
}
