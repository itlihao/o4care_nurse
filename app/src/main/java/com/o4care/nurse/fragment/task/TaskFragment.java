package com.o4care.nurse.fragment.task;

import android.graphics.Color;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;
import com.o4care.nurse.adapter.CustomerListAdapter;
import com.o4care.nurse.api.WorkerApi;
import com.o4care.nurse.bean.CustomerInfo;
import com.o4care.nurse.net.BaseTask;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.o4care.nurse.fragment.BaseFragment;
import com.o4care.nurse.R;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 首页动态
 */
@Page(anim = CoreAnim.none)
public class TaskFragment extends BaseFragment implements CalendarView.OnCalendarSelectListener {
    private String TAG = "TaskFragment";
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rv_task)
    SwipeRecyclerView recyclerView;
    @BindView(R.id.calendarView)
    CalendarView calendarView;

    private CustomerListAdapter listCustomerAdapter;
    List<CustomerInfo> listCust;

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
        return R.layout.fragment_task;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {
        WidgetUtils.initRecyclerView(recyclerView, 0);

        /* 初始化客户列表 */
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        listCustomerAdapter = new CustomerListAdapter(new ArrayList<CustomerInfo>());
        recyclerView.setAdapter(listCustomerAdapter);

        initCalendarView();

        getCustomerList();
    }

    private void initCalendarView() {
        final int year = calendarView.getCurYear();
        final int month = calendarView.getCurMonth();
        Map<String, Calendar> map = new HashMap<>();
        for (int j = 5; j < 10; j++) {
            map.put(getSchemeCalendar(year, month, j, Color.RED).toString(), getSchemeCalendar(year, month, j, Color.RED));
        }
        for (int i = 10; i < 28; i++) {
            map.put(getSchemeCalendar(year, month, i, Color.TRANSPARENT).toString(), getSchemeCalendar(year, month, i, Color.TRANSPARENT));
        }
        calendarView.setSchemeDate(map);
    }

    public Calendar getSchemeCalendar(int year, int month, int day, int color) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        //如果单独标记颜色、则会使用这个颜色
        calendar.setSchemeColor(color);
        return calendar;
    }

    @Override
    protected void initListeners() {

        calendarView.setOnCalendarSelectListener(this);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(() -> {
//                    mAdapter.refresh(DemoDataProvider.getDemoNewInfos());
                    getCustomerList();
                    refreshLayout.finishRefresh();
                }, 1000);

            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(() -> {
//                    mAdapter.loadMore(DemoDataProvider.getDemoNewInfos());
                    refreshLayout.finishLoadMore();
                }, 1000);

            }
        });
        refreshLayout.autoRefresh();
    }

    @Override
    public void onCalendarOutOfRange(Calendar calendar) {

    }

    @Override
    public void onCalendarSelect(Calendar calendar, boolean isClick) {
        /*if (isClick) {
            if (calendarLayout.isExpand()) {
                calendarLayout.shrink();
            }
        }*/
    }

    void getCustomerList() {
        Log.d(TAG, "getCustomerList");
        new WorkerApi(getActivity(), new BaseTask.ResponseListener<List<CustomerInfo>>() {
            @Override
            public void onSuccess(int flag, List<CustomerInfo> listCustNet) {
                Log.d(TAG, "onSucess data");
                listCust = listCustNet;
                for (int i = 0; i < listCustNet.size(); i++) {
                    Log.d(TAG, listCustNet.get(i).getAddress());
                    Log.d(TAG, String.valueOf(listCustNet.get(i).getDoneHourMon()));
                }
                listCustomerAdapter.refresh(listCust);
            }

            @Override
            public void onSuccess(int flag, List<CustomerInfo> listCustNet, int total) {
                Log.d(TAG, "onSucess list");
            }

            @Override
            public void onFail(int flag, String message) {
                Log.d(TAG, "onFail");
            }
        }).getAllCustomer("12");
    }
}
