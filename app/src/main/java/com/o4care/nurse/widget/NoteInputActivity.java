package com.o4care.nurse.activity;

import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.o4care.nurse.fragment.customer.CustomerInfoFragment;
import com.o4care.nurse.fragment.customer.CustomerListFragment;
import com.o4care.nurse.fragment.customer.CustomerPlanFragment;
import com.o4care.nurse.fragment.customer.CustomerRecordFragment;
import com.o4care.nurse.fragment.customer.CustomerTaskFragment;
import com.o4care.nurse.fragment.map.MapFragment;
import com.o4care.nurse.fragment.mine.MineFragment;
import com.o4care.nurse.fragment.task.TaskFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.adapter.FragmentAdapter;
import com.o4care.nurse.R;
import com.o4care.nurse.fragment.BaseFragment;

import butterknife.BindView;

import static com.google.android.material.tabs.TabLayout.MODE_FIXED;

/**
 * @author XUE
 * @since 2019/5/9 11:43
 */
public class NoteInputActivity extends BaseActivity {
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        initListeners();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    protected void initViews() {
 
    }

    protected void initListeners() {
    }
}
