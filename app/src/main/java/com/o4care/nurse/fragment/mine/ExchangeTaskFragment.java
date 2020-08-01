package com.o4care.nurse.fragment.mine;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.o4care.nurse.api.WorkerApi;
import com.o4care.nurse.bean.CustomerInfoBean;
import com.o4care.nurse.bean.Exchange;
import com.o4care.nurse.fragment.BaseFragment;
import com.o4care.nurse.fragment.AboutFragment;
import com.o4care.nurse.R;
import com.o4care.nurse.net.BaseTask;
import com.xuexiang.xaop.annotation.SingleClick;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.util.List;

import butterknife.BindView;

import static com.google.android.material.tabs.TabLayout.MODE_FIXED;

/**
 * @author xuexiang
 * @since 2019-10-30 00:18
 */
@Page(anim = CoreAnim.none)
public class ExchangeTaskFragment extends BaseFragment {
    private static final String TAG = "ExchangeTaskFragment";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;


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
        return R.layout.fragment_exchange;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {
        toolbar.setTitle("换班记录");
        toolbar.setNavigationIcon(R.drawable.ic_navigation_back_white);
        // 设置 NavigationIcon 点击事件
        toolbar.setNavigationOnClickListener(onClickListener);

        tabLayout.addTab(tabLayout.newTab().setText("全部记录"));
        tabLayout.addTab(tabLayout.newTab().setText("我申请的"));
        tabLayout.addTab(tabLayout.newTab().setText("我代班的"));

        getExchange(0);
    }

    private View.OnClickListener onClickListener = v -> getActivity().finish();


    @Override
    protected void initListeners() {
    }

    private void getExchange(int type) {
        new WorkerApi(getActivity(), new BaseTask.ResponseListener<List<Exchange>>() {
            @Override
            public void onSuccess(int flag, List<Exchange> exchanges) {
                Log.d(TAG, "onSucess data");

                for (int i = 0; i < exchanges.size(); i++) {
                    Log.d(TAG, exchanges.get(i).toString());
                }

            }

            @Override
            public void onSuccess(int flag, List<Exchange> exchanges, int total) {
                Log.d(TAG, "onSucess list");
            }

            @Override
            public void onFail(int flag, String message) {
                Log.d(TAG, "onFail");
            }
        }).getExchange("123", 0, "20200701", "20200705");
    }
}
