package com.o4care.nurse.fragment.customer;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.o4care.nurse.R;
import com.o4care.nurse.adapter.CustomerRecordAdapter;
import com.o4care.nurse.api.CareApi;
import com.o4care.nurse.bean.Record;
import com.o4care.nurse.fragment.BaseFragment;
import com.o4care.nurse.net.BaseTask;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author wlcare
 */
@Page(anim = CoreAnim.none)
public class CustomerRecordFragment extends BaseFragment {
    private static final String TAG = "CustomerRecordFragment";

    private CustomerRecordAdapter recordAdapter;

    @BindView(R.id.rv_record)
    SwipeRecyclerView recyclerView;

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
        return R.layout.fragment_record;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {

        /* 初始化客户列表 */
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recordAdapter = new CustomerRecordAdapter(new ArrayList<>());
        recyclerView.setAdapter(recordAdapter);

        getRecordList();
    }

    @Override
    protected void initListeners() {
        recordAdapter.setOnItemClickListener(new RecyclerViewHolder.OnItemClickListener<Record>() {
            @Override
            public void onItemClick(View itemView, Record item, int position) {
                openNewPage(CustomerRecordDetailFragment.class);
//                ActivityUtils.startActivity(RecordDetailActivity.class);
            }
        });
    }

    private void getRecordList() {
        Log.d(TAG, "getCustomerList");
        new CareApi(getActivity(), new BaseTask.ResponseListener<List<Record>>() {
            @Override
            public void onSuccess(int flag, List<Record> recordList) {
                Log.d(TAG, "onSucess data");
                for (int i = 0; i < recordList.size(); i++) {
                    Log.d(TAG, recordList.get(i).toString());
                }
                recordAdapter.refresh(recordList);
            }

            @Override
            public void onSuccess(int flag, List<Record> recordList, int total) {
                Log.d(TAG, "onSucess list");
            }

            @Override
            public void onFail(int flag, String message) {
                Log.d(TAG, "onFail");
            }
        }).getAllRecord("12", "234");
    }
}
