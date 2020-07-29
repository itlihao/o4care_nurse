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
public class CustomerActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        initListeners();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_customer;
    }

    protected void initViews() {
        String[] titles = new String[]{"任务", "计划", "记录", "信息"};
        toolbar.setTitle(titles[0]);

        FragmentAdapter<BaseFragment> adapter = new FragmentAdapter<>(getSupportFragmentManager());
        adapter.addFragment(new CustomerTaskFragment(), titles[0]);
        adapter.addFragment(new CustomerPlanFragment(), titles[1]);
        adapter.addFragment(new CustomerRecordFragment(), titles[2]);
        adapter.addFragment(new CustomerInfoFragment(), titles[3]);
        tabLayout.setTabMode(MODE_FIXED);
        viewPager.setOffscreenPageLimit(titles.length - 1);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }


    protected void initListeners() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
    }
}
