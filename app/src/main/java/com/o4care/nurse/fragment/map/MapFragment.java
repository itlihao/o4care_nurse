package com.o4care.nurse.fragment.map;

import com.o4care.nurse.fragment.BaseFragment;
import com.o4care.nurse.R;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.actionbar.TitleBar;

/**
 * @author xuexiang
 * @since 2019-10-30 00:19
 */
@Page(anim = CoreAnim.none)
public class MapFragment extends BaseFragment {

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
        return R.layout.fragment_map;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {
    }
}
