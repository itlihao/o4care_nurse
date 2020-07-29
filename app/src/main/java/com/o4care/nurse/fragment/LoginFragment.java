package com.o4care.nurse.fragment;

import android.graphics.Color;
import android.view.View;

import com.xuexiang.xaop.annotation.SingleClick;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;
import com.o4care.nurse.R;
import com.o4care.nurse.activity.MainActivity;
//import com.xuexiang.xuidemo.utils.SettingSPUtils;
//import com.wlcare.team.utils.TokenUtils;
//import com.xuexiang.xuidemo.utils.XToastUtils;
import com.xuexiang.xutil.app.ActivityUtils;
//import com.xuexiang.xutil.common.RandomUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录页面
 *
 * @author
 * @since 2019-11-17 22:15
 */
@Page(anim = CoreAnim.none)
public class LoginFragment extends BaseFragment {
    String TAG = LoginFragment.class.getSimpleName();

    @BindView(R.id.et_phone_number)
    MaterialEditText etPhoneNumber;
    @BindView(R.id.et_password)
    MaterialEditText etPassword;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected TitleBar initTitle() {
        TitleBar titleBar = super.initTitle().setImmersive(true);
        titleBar.setBackgroundColor(Color.TRANSPARENT);
        titleBar.setTitle("");
        titleBar.setLeftImageDrawable(ResUtils.getVectorDrawable(getContext(), R.drawable.ic_login_close));
        return titleBar;
    }

    @Override
    protected void initViews() {
        loginin();
    }

    @SingleClick
    @OnClick({R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                if (etPhoneNumber.validate()) {
                    loginByPassword(etPhoneNumber.getEditValue(), etPassword.getEditValue());
                }
                break;
            default:
                break;
        }
    }

    /**
     * 根据验证码登录
     *
     * @param phoneNumber 手机号
     * @param password  验证码
     */
    private void loginByPassword(String phoneNumber, String password) {
        //String token = RandomUtils.getRandomNumbersAndLetters(16);
        //if (TokenUtils.handleLoginSuccess(token)) {
            //popToBack();
            ActivityUtils.startActivity(MainActivity.class);
        //}
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    void loginin(){
        /*
        Log.d(TAG, "login TESTEST");
        new LoginApi(getActivity(), new BaseTask.ResponseListener<LoginBean>() {
            @Override
            public void onSuccess(int flag, LoginBean loginBean) {
                Log.d(TAG, loginBean.getToken());
            }

            @Override
            public void onSuccess(int flag, LoginBean loginBean, int total) {
            }

            @Override
            public void onFail(int flag, String message) {
            }
        }).login("18561495832", "123456");
         */
    }
}
