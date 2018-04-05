package com.anhquanha.passkeeper.view.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.anhquanha.passkeeper.R;
import com.anhquanha.passkeeper.asset.DatabaseHandler;
import com.anhquanha.passkeeper.view.fragment.BaseFragment;
import com.anhquanha.passkeeper.view.fragment.LoginFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.account_login_btn)
    Button accountLoginBtn;
    @BindView(R.id.fingerprint_login_btn)
    Button fingerLoginBtn;
    @BindView(R.id.frameLoginLayout)
    FrameLayout frameLayout;
    @BindView(R.id.loginLinearLay)
    LinearLayout startingLoginlayout;

    DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);

        databaseHandler = new DatabaseHandler(this);
        accountLoginBtn.setOnClickListener(v->{
            startingLoginlayout.setVisibility(View.INVISIBLE);
            //openLoginScreen();
            pushFragment(LoginFragment.newInstance(), false);
            frameLayout.setVisibility(View.VISIBLE);
        });

        fingerLoginBtn.setOnClickListener(v->{

        });
    }


    public void pushFragment(BaseFragment baseFragment, boolean addBackStack){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.frameLoginLayout, baseFragment, null);

        if(addBackStack)
            ft.addToBackStack(baseFragment.getFragmentTag());

        ft.commitAllowingStateLoss();

    }

    @Override
    public void onBackPressed() {
        if(startingLoginlayout.getVisibility() == View.VISIBLE){
            moveTaskToBack(true);
            return;
        }
        popPreviousFragment();
    }

    public void popPreviousFragment(){
        FragmentManager fm = getSupportFragmentManager();
        if(fm.getBackStackEntryCount()>0) {
            fm.popBackStack();
        }else{
            frameLayout.setVisibility(View.INVISIBLE);
            startingLoginlayout.setVisibility(View.VISIBLE);
        }

    }


}
