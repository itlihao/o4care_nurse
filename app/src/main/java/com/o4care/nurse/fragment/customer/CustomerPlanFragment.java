package com.o4care.nurse.fragment.customer;

import android.util.Log;

import com.o4care.nurse.R;
import com.o4care.nurse.api.CareApi;
import com.o4care.nurse.bean.CarePlan;
import com.o4care.nurse.bean.RecordDetail;
import com.o4care.nurse.fragment.BaseFragment;
import com.o4care.nurse.net.BaseTask;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.actionbar.TitleBar;

import java.util.List;

/**
 * @author wlcare
 */
@Page(anim = CoreAnim.none)
public class CustomerPlanFragment extends BaseFragment {
    private static final String TAG = "CustomerPlanFragment";

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
        return R.layout.fragment_plan;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {

        getCarePlan();
    }

    private void getCarePlan() {
        new CareApi(getActivity(), new BaseTask.ResponseListener<List<CarePlan>>() {
            @Override
            public void onSuccess(int flag, List<CarePlan> carePlans) {
                Log.d(TAG, "onSucess data");

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
