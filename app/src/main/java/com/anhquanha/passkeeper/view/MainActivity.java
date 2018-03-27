package com.anhquanha.passkeeper.view;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.anhquanha.passkeeper.MainApplication;
import com.anhquanha.passkeeper.R;
import com.anhquanha.passkeeper.view.fragment.AccountsFragment;
import com.anhquanha.passkeeper.view.fragment.BaseFragment;
import com.anhquanha.passkeeper.view.fragment.SettingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigationView)
    NavigationView navigationView;
    @BindView(R.id.frameLayoutMain)
    FrameLayout frameLayout;
    //@BindView(R.id.header_username_tv)
    TextView userNameHeaderTv;

    SettingFragment settingFragment;
    AccountsFragment accountsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        userNameHeaderTv = navigationView.findViewById(R.id.header_username_tv);

        accountsFragment = AccountsFragment.newInstance();
        settingFragment = SettingFragment.newInstance();
        //userNameHeaderTv.setText("Hi " + MainApplication.getUserInfo().getUsername());
        navigationView.setNavigationItemSelectedListener(item -> {
            item.setChecked(true);
            drawerLayout.closeDrawers();

            switch (item.getItemId()){
                case R.id.nav_accounts:

                    break;
                case R.id.nav_settings:
                    break;
            }
            return true;
        });
        pushStartingFragment();
        navigationView.setCheckedItem(R.id.nav_accounts);

    }

    private void pushStartingFragment() {
        pushFragment(accountsFragment, false);
    }

    public void pushFragment(BaseFragment fragment, boolean addBackStack){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.frameLayoutMain, fragment, null);

        if(addBackStack)
            ft.addToBackStack(fragment.getFragmentTag());

        ft.commitAllowingStateLoss();
    }


}
