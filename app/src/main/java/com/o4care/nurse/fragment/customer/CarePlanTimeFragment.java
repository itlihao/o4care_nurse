package com.o4care.nurse.fragment.customer;

import android.util.Log;

import com.o4care.nurse.R;
import com.o4care.nurse.fragment.BaseFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xrouter.annotation.AutoWired;
import com.xuexiang.xrouter.launcher.XRouter;
import com.xuexiang.xui.widget.actionbar.TitleBar;

/**
 * @author wlcare
 */
@Page(anim = CoreAnim.none, name = "数据传递", params = {CarePlanTimeFragment.KEY_EVENT_NAME, CarePlanTimeFragment.KEY_EVENT_DATA})
public class CarePlanTimeFragment extends BaseFragment {
    private static final String TAG = "CarePlanTimeFragment";

    public final static String KEY_EVENT_NAME = "event_name";
    public final static String KEY_EVENT_DATA = "event_data";

    @AutoWired(name = KEY_EVENT_NAME)
    String eventName;
    @AutoWired(name = KEY_EVENT_DATA)
    String eventData;

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
    @Override
    protected void initViews() {
        Log.e(TAG, "workId = " + eventName + " custId = " + eventData);
    }

    @Override
    protected void initArgs() {
        XRouter.getInstance().inject(this);
    }
}
