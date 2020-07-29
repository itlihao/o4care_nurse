package com.o4care.nurse.fragment.mine;

import com.o4care.nurse.fragment.BaseFragment;
import com.o4care.nurse.fragment.AboutFragment;
import com.o4care.nurse.R;
import com.xuexiang.xaop.annotation.SingleClick;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import butterknife.BindView;

/**
 * @author xuexiang
 * @since 2019-10-30 00:18
 */
@Page(anim = CoreAnim.none)
public class ExchangeTaskFragment extends BaseFragment {
  

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
        return R.layout.fragment_mine;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {
    }
}
