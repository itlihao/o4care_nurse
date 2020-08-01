package com.o4care.nurse.fragment.customer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.o4care.nurse.R;
import com.o4care.nurse.activity.CareItemsActivity;
import com.o4care.nurse.activity.CarePlanTimeActivity;
import com.o4care.nurse.activity.CustomerActivity;
import com.o4care.nurse.fragment.BaseFragment;
import com.o4care.nurse.utils.Constants;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xrouter.annotation.AutoWired;
import com.xuexiang.xrouter.launcher.XRouter;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.zyyoona7.wheel.WheelView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author wlcare
 */
@Page(anim = CoreAnim.none, name = "数据传递", params = {CarePlanTimeFragment.KEY_EVENT_NAME, CarePlanTimeFragment.KEY_EVENT_DATA})
public class CarePlanTimeFragment extends BaseFragment {
    private static final String TAG = "CarePlanTimeFragment";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.wv_week)
    WheelView wvWeek;
    @BindView(R.id.wv_hour)
    WheelView wvHour;
    @BindView(R.id.wv_min)
    WheelView wvMin;

    public final static String KEY_EVENT_NAME = "event_name";
    public final static String KEY_EVENT_DATA = "event_data";
    public final static String KEY_EVENT_DATA_TIME = "event_data_time";

    @AutoWired(name = KEY_EVENT_NAME)
    String eventName;
    @AutoWired(name = KEY_EVENT_DATA)
    String eventData;

    public CarePlanTimeFragment() {
    }

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
        return R.layout.fragment_plan_time;
    }

    /**
     * 初始化控件
     */
    @SuppressLint("DefaultLocale")
    @Override
    protected void initViews() {
        Log.e(TAG, "workId = " + eventName + " custId = " + eventData);
        /*((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("记录详情");
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setHasOptionsMenu(true);*/
        //设置 Toolbar menu

        toolbar.setTitle("添加服务时间");
        toolbar.setNavigationIcon(R.drawable.ic_navigation_back_white);
        // 设置 NavigationIcon 点击事件
        toolbar.setNavigationOnClickListener(onClickListener);
        toolbar.inflateMenu(R.menu.menu_next);
        toolbar.setOnMenuItemClickListener(menuItemClickListener);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            list.add(Constants.week[i]);
        }
        //设置数据
        wvWeek.setData(list);
        wvWeek.setSelectedItemPosition(1);

        List<String> listH = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            listH.add(String.format("%02d时", i));
        }
        //设置数据
        wvHour.setData(listH);

        List<String> listM = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            listM.add(String.format("%02d分", i));
        }
        //设置数据
        wvMin.setData(listM);
        wvMin.setSelectedItemPosition(0);
    }

    private View.OnClickListener onClickListener = v -> getActivity().finish();

    Toolbar.OnMenuItemClickListener menuItemClickListener = item -> {
        switch (item.getItemId()){
            case R.id.action_next:
                String week = (String) wvWeek.getSelectedItemData();
                String hour = (String) wvHour.getSelectedItemData();
                String min = (String) wvMin.getSelectedItemData();
                String time = week + "  " + hour + "  " + min;
                Log.e(TAG, "服务时间 = " + time);

                Bundle params = new Bundle();
                params.putString(CarePlanTimeFragment.KEY_EVENT_NAME, "123");
                params.putString(CarePlanTimeFragment.KEY_EVENT_DATA, "12");
                params.putString(CarePlanTimeFragment.KEY_EVENT_DATA, time);
                Intent intent = new Intent(getActivity(), CareItemsActivity.class);
                intent.putExtra("Params", params);
                startActivity(intent);
                getActivity().finish();
                break;
            default:
                break;
        }
        return false;
    };


    @Override
    protected void initArgs() {
        XRouter.getInstance().inject(this);
    }
}
