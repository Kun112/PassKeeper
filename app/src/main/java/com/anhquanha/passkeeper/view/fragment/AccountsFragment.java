package com.anhquanha.passkeeper.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anhquanha.passkeeper.MainApplication;
import com.anhquanha.passkeeper.R;
import com.anhquanha.passkeeper.adapter.AccountAdapter;
import com.anhquanha.passkeeper.callback.OnAccountItemClickListener;
import com.anhquanha.passkeeper.constant.FragmentID;
import com.anhquanha.passkeeper.model.Account;
import com.anhquanha.passkeeper.view.activity.CreateAccountActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by anhquan.ha on 3/26/18.
 */

public class AccountsFragment extends BaseFragment implements OnAccountItemClickListener {
    @BindView(R.id.fragmentName)
    TextView fragmentNameTv;
    @BindView(R.id.addAccountBtn)
    FloatingActionButton addAccountBtn;
    @BindView(R.id.accountRv)
    RecyclerView accountRv;
    AccountAdapter adapter;
    List<Account> listAccount = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.account_list_layout;
    }

    @Override
    public void initView() {
        registerForContextMenu(accountRv);
        addAccountBtn.setOnClickListener(v->{
            startActivity(new Intent(activity, CreateAccountActivity.class));
        });
        //loadAccountsData();
    }

    private void loadAccountsData() {
        listAccount = MainApplication.getDatabaseHandler().getAccountsDependOnOwner(MainApplication.getUserInfo().getId());
        adapter = new AccountAdapter(context, listAccount, this);
        accountRv.setAdapter(adapter);
        accountRv.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public void onResume() {
        super.onResume();
        loadAccountsData();
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


    @Override
    public void onEditAccount(Account account) {

    }

    @Override
    public void onDeleteAccount(Account account) {
        //MainApplication.getDatabaseHandler().deleteAccount(account.ge);
    }
}
