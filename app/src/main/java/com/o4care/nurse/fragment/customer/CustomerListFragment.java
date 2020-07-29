/*
 * Copyright (C) 2020 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.o4care.nurse.fragment.customer;

import android.util.Log;
import android.view.View;

import com.o4care.nurse.R;
import com.o4care.nurse.activity.CustomerActivity;
import com.o4care.nurse.adapter.CustomerListAdapter;
import com.o4care.nurse.api.WorkerApi;
import com.o4care.nurse.bean.CustomerInfo;
import com.o4care.nurse.fragment.BaseFragment;
import com.o4care.nurse.net.BaseTask;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xutil.app.ActivityUtils;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;

/**
 * @author o4lis
 */
@Page(anim = CoreAnim.none)
public class CustomerListFragment extends BaseFragment {
    private String TAG = "CustomerListFragment";
    @BindView(R.id.recyclerView_cust)
    SwipeRecyclerView listCustomer;

    private CustomerListAdapter listCustomerAdapter;
    List<CustomerInfo>  listCust;

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
        return R.layout.fragment_customer;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {
          /* 初始化客户列表 */
        listCustomer.setLayoutManager(new LinearLayoutManager(listCustomer.getContext()));
        listCustomer.setItemAnimator(new DefaultItemAnimator());
        listCustomerAdapter = new CustomerListAdapter( new ArrayList<CustomerInfo>() );
        listCustomer.setAdapter(listCustomerAdapter);

        getCustomerList();
    }

    @Override
    protected void initListeners() {
        listCustomerAdapter.setOnItemClickListener(new RecyclerViewHolder.OnItemClickListener<CustomerInfo>() {
            @Override
            public void onItemClick(View itemView, CustomerInfo item, int position) {
                //openNewPage(CustomerActivity.class);
                ActivityUtils.startActivity(CustomerActivity.class, "worker_id", 12);
            }
        });
    }

    void  getCustomerList(){
        Log.d(TAG, "getCustomerList");
        new WorkerApi(getActivity(), new BaseTask.ResponseListener<List<CustomerInfo>>() {
            @Override
            public void onSuccess(int flag, List<CustomerInfo> listCustNet) {
                Log.d(TAG, "onSucess data");
                listCust = listCustNet;
                for(int i=0;i<listCustNet.size();i++){
                    Log.d(TAG, listCustNet.get(i).getAddress());
                    Log.d(TAG, String.valueOf(listCustNet.get(i).getDoneHourMon()) );
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
