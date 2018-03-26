package com.anhquanha.passkeeper.view;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.anhquanha.passkeeper.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        userNameHeaderTv = navigationView.findViewById(R.id.header_username_tv);
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

    }


}
