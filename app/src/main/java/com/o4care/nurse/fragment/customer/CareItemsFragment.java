package com.o4care.nurse.fragment.customer;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.o4care.nurse.R;
import com.o4care.nurse.adapter.CareItemAdapter;
import com.o4care.nurse.adapter.CareItemContentAdapter;
import com.o4care.nurse.api.CareApi;
import com.o4care.nurse.bean.CareItem;
import com.o4care.nurse.bean.CarePlanEntity;
import com.o4care.nurse.fragment.BaseFragment;
import com.o4care.nurse.net.BaseTask;
import com.o4care.nurse.utils.Constants;
import com.o4care.nurse.utils.XToastUtils;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @author wlcare
 */
@Page(anim = CoreAnim.none, params = {CarePlanTimeFragment.KEY_EVENT_NAME, CarePlanTimeFragment.KEY_EVENT_DATA})
public class CareItemsFragment extends BaseFragment {
    private static final String TAG = "CareItemsFragment";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_item_head)
    RecyclerView rvHeader;
    @BindView(R.id.rv_item_content)
    RecyclerView rvContent;

    private int index = 1;

    private List<CareItem> careList = new ArrayList<>();
    private Map<Integer, List<CareItem>> map;

    private CareItemAdapter careItemAdapter;
    private CareItemContentAdapter contentAdapter;

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
        return R.layout.fragment_items;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {
        toolbar.setTitle("选择服务项");
        toolbar.setNavigationIcon(R.drawable.ic_navigation_back_white);
        // 设置 NavigationIcon 点击事件
        toolbar.setNavigationOnClickListener(onClickListener);
        toolbar.inflateMenu(R.menu.menu_next);
        toolbar.setOnMenuItemClickListener(menuItemClickListener);

        careItemAdapter = new CareItemAdapter(new ArrayList<>());
        rvHeader.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvHeader.setAdapter(careItemAdapter);
        careItemAdapter.setOnItemClickListener(new RecyclerViewHolder.OnItemClickListener<String>() {
            @Override
            public void onItemClick(View itemView, String item, int position) {
                contentAdapter.refresh(new ArrayList<>());
                index = position + 1;
                contentAdapter.refresh(map.get(index));
            }
        });

        WidgetUtils.initRecyclerView(rvContent, 1);
        contentAdapter = new CareItemContentAdapter(new ArrayList<>());
        rvContent.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvContent.setAdapter(contentAdapter);

        getCareItems();

    }

    private View.OnClickListener onClickListener = v -> getActivity().finish();

    Toolbar.OnMenuItemClickListener menuItemClickListener = item -> {
        switch (item.getItemId()) {
            case R.id.action_next:
                addOrEditCare();
                break;
            default:
                break;
        }
        return false;
    };

    private void addOrEditCare() {
        new CareApi(getActivity(), new BaseTask.ResponseListener<CarePlanEntity>() {
            @Override
            public void onSuccess(int flag, CarePlanEntity result) {
                Log.d(TAG, "onSucess data");
                if (result.getResult() == 1) {
                    getActivity().finish();
                    Toast.makeText(getActivity(), "添加计划成功", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onSuccess(int flag, CarePlanEntity careItems, int total) {
                Log.d(TAG, "onSucess list");
            }

            @Override
            public void onFail(int flag, String message) {
                Log.d(TAG, "onFail");
                Toast.makeText(getActivity(), "添加计划失败", Toast.LENGTH_SHORT).show();
            }
        }).addCarePlan("123", "12", "3", "10:00", new String[] {});
    }

    private void setData() {
        List<CareItem> items;
        map = new HashMap<>();
        List<String> head = new ArrayList<>();

        for (int n = 1; n <= 4; n++) {
            items = new ArrayList<>();
            for (int i = 0; i < careList.size(); i++) {
                if (careList.get(i).getCategory() == n) {
                    items.add(careList.get(i));
                }
            }
            head.add(Constants.careItem[n - 1]);
            map.put(n, items);
        }

        careItemAdapter.refresh(head);

        List<CareItem> careItems = map.get(index);
        contentAdapter.refresh(careItems);

        Log.e(TAG, " size " + map.size());
    }

    private void getCareItems() {
        new CareApi(getActivity(), new BaseTask.ResponseListener<List<CareItem>>() {
            @Override
            public void onSuccess(int flag, List<CareItem> careItems) {
                Log.d(TAG, "onSucess data");
                careList = careItems;
                setData();
            }

            @Override
            public void onSuccess(int flag, List<CareItem> careItems, int total) {
                Log.d(TAG, "onSucess list");
            }

            @Override
            public void onFail(int flag, String message) {
                Log.d(TAG, "onFail");
            }
        }).getCareItems("123");
    }

}
