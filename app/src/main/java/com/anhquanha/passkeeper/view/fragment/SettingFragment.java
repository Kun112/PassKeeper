package com.anhquanha.passkeeper.view.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.anhquanha.passkeeper.R;
import com.anhquanha.passkeeper.constant.FragmentID;

import butterknife.BindView;

public class SettingFragment extends BaseFragment {
    @BindView(R.id.fragmentName)
    TextView fragmentNameTv;

    @Override
    public int getLayoutId() {
        return R.layout.setting_fragment_layout;
    }

    @Override
    public void initView() {

    }

    @Override
    public void changeToolbarTitle() {
    }

    @Override
    public String getFragmentTag() {
        return FragmentID.SETTING_FRAGMENT;
    }

    public static SettingFragment newInstance() {

        return new SettingFragment();
    }
}
