package com.o4care.nurse.fragment;

import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;
import android.widget.TextView;

import com.o4care.nurse.widget.webview.AgentWebActivity;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.grouplist.XUIGroupListView;
import com.xuexiang.xutil.app.AppUtils;
import com.o4care.nurse.R;

import butterknife.BindView;

/**
 */
@Page(name = "关于")
public class AboutFragment extends BaseFragment {
    @BindView(R.id.version)
    TextView mVersionTextView;
    @BindView(R.id.about_list)
    XUIGroupListView mAboutGroupListView;
    @BindView(R.id.copyright)
    TextView mCopyrightTextView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_about;
    }

    @Override
    protected void initViews() {
        mVersionTextView.setText(String.format("版本号：%s", AppUtils.getAppVersionName()));

        XUIGroupListView.newSection(getContext())
        .addItemView(mAboutGroupListView.createItemView(getResources().getString(R.string.about_item_homepage)), v -> AgentWebActivity.goWeb(getContext(), getString(R.string.url_homegage)))
        .addTo(mAboutGroupListView);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy", Locale.CHINA);
        String currentYear = dateFormat.format(new Date());
        mCopyrightTextView.setText(String.format(getResources().getString(R.string.about_copyright), currentYear));
    }
}
