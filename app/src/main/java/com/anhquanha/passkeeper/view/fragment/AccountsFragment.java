package com.anhquanha.passkeeper.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.widget.TextView;

import com.anhquanha.passkeeper.R;
import com.anhquanha.passkeeper.constant.FragmentID;
import com.anhquanha.passkeeper.view.activity.CreateAccountActivity;

import butterknife.BindView;

/**
 * Created by anhquan.ha on 3/26/18.
 */

public class AccountsFragment extends BaseFragment {
    @BindView(R.id.fragmentName)
    TextView fragmentNameTv;
    @BindView(R.id.addAccountBtn)
    FloatingActionButton addAccountBtn;

    @Override
    public int getLayoutId() {
        return R.layout.account_list_layout;
    }

    @Override
    public void initView() {
        addAccountBtn.setOnClickListener(v->{
            startActivity(new Intent(activity, CreateAccountActivity.class));
        });
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
