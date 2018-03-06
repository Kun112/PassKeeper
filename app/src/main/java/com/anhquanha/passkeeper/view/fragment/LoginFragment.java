package com.anhquanha.passkeeper.view.fragment;

import android.support.annotation.BinderThread;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.anhquanha.passkeeper.R;
import com.anhquanha.passkeeper.view.LoginActivity;

import butterknife.BindView;


/**
 * Created by anhquan.ha on 3/6/2018.
 */

public class LoginFragment extends BaseFragment{

    @BindView(R.id.backBtn)
    ImageView backBtn;
    @BindView(R.id.fragmentNameTv)
    TextView fragmentNameTv;
    @BindView(R.id.idUserTv)
    EditText idTv;
    @BindView(R.id.passwordTv)
    EditText passwordTv;
    @BindView(R.id.login_accept_btn)
    Button loginBtn;
    @BindView(R.id.create_account_tv)
    TextView createAccountTv;

    String id = "";
    String password = "";

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }


    @Override
    public int getLayoutId() {
        return R.layout.login_fragment_layout;
    }

    @Override
    public void initView() {
        backBtn.setOnClickListener(v->{
            if(context instanceof LoginActivity)
                ((LoginActivity) context).onBackPressed();
        });

        loginBtn.setOnClickListener(v->{
            id = idTv.getText().toString();
            password = passwordTv.getText().toString();
            if(id.equals("") || password.equals("")){
                if(id.equals("")){
                    idTv.setError(context.getResources().getString(R.string.blank_id_error));
                }
                if(password.equals("")){
                    passwordTv.setError(context.getResources().getString(R.string.blank_pass_error));
                }
                return;
            }
            showToast("Login success!");

        });

    }

    @Override
    public void changeToolbarTitle() {
        fragmentNameTv.setText(context.getResources().getString(R.string.login));
    }


}
