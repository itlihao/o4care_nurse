package com.o4care.nurse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.o4care.nurse.R;
import com.o4care.nurse.fragment.BaseFragment;
import com.o4care.nurse.fragment.customer.CarePlanTimeFragment;
import com.o4care.nurse.fragment.customer.CustomerInfoFragment;
import com.o4care.nurse.fragment.customer.CustomerPlanFragment;
import com.o4care.nurse.fragment.customer.CustomerRecordFragment;
import com.o4care.nurse.fragment.customer.CustomerTaskFragment;
import com.xuexiang.xui.adapter.FragmentAdapter;

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

    MenuItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

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
                Log.e("Customer", String.valueOf(position));
                if (position != 1) {
                    item.setVisible(false);
                } else {
                    item.setVisible(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);

        item = menu.findItem(R.id.action_add);
        item.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            Bundle params = new Bundle();
            params.putString(CarePlanTimeFragment.KEY_EVENT_NAME, "123");
            params.putString(CarePlanTimeFragment.KEY_EVENT_DATA, "12");
            Intent intent = new Intent(CustomerActivity.this, CarePlanTimeActivity.class);
            intent.putExtra("Params", params);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
