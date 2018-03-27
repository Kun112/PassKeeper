package com.anhquanha.passkeeper.view.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.anhquanha.passkeeper.R;
import com.anhquanha.passkeeper.constant.FragmentID;

import butterknife.BindView;

/**
 * Created by anhquan.ha on 3/26/18.
 */

public class AccountsFragment extends BaseFragment {
    @BindView(R.id.fragmentName)
    TextView fragmentNameTv;

    @Override
    public int getLayoutId() {
        return R.layout.account_list_layout;
    }

    @Override
    public void initView() {

    }

    @Override
    public void changeToolbarTitle() {
        fragmentNameTv.setText("Accounts");
    }

    @Override
    public String getFragmentTag() {
        return FragmentID.ACCOUNT_FRAGMENT;
    }

    public static AccountsFragment newInstance() {

        return new AccountsFragment();
    }
}
