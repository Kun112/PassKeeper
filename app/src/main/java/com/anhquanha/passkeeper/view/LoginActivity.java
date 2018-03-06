package com.anhquanha.passkeeper.view;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.anhquanha.passkeeper.R;
import com.anhquanha.passkeeper.util.DialogUtil;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);

        accountLoginBtn.setOnClickListener(v->{
            startingLoginlayout.setVisibility(View.INVISIBLE);
            openLoginScreen();
            frameLayout.setVisibility(View.VISIBLE);
        });

        fingerLoginBtn.setOnClickListener(v->{

        });
    }

    private void openLoginScreen() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        BaseFragment fragment = LoginFragment.newInstance();
        ft.replace(R.id.frameLoginLayout, fragment, null);

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
