package com.anhquanha.passkeeper.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.anhquanha.passkeeper.MainApplication;
import com.anhquanha.passkeeper.R;
import com.anhquanha.passkeeper.asset.DatabaseHandler;
import com.anhquanha.passkeeper.constant.FragmentID;
import com.anhquanha.passkeeper.model.User;
import com.anhquanha.passkeeper.view.activity.LoginActivity;
import com.anhquanha.passkeeper.view.activity.MainActivity;

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
    @BindView(R.id.noti_error_tv)
    TextView notiTv;

    String id = "";
    String password = "";

    DatabaseHandler databaseHandler;
    User user = null;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }


    @Override
    public int getLayoutId() {
        return R.layout.login_fragment_layout;
    }

    @Override
    public void initView() {
        databaseHandler = new DatabaseHandler(context);
        backBtn.setOnClickListener(v->{
            if(context instanceof LoginActivity)
                ((LoginActivity) context).onBackPressed();
        });

        loginBtn.setOnClickListener(v->{
            id = idTv.getText().toString();
            password = passwordTv.getText().toString();
            if(haveEnoughConditionsForLogin(id, password)){
                /** login success, open main act here **/
                notiTv.setVisibility(View.INVISIBLE);
                MainApplication.setUserInfo(user);
                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
                //showToast("Login success!");
            }else{
                /** input again **/
                notiTv.setVisibility(View.VISIBLE);
            }


        });

        createAccountTv.setOnClickListener(v->{
            createNewAccount();
        });


    }

    private boolean haveEnoughConditionsForLogin(String id, String password) {
        if(id.equals("") || password.equals("")){
            if(id.equals("")){
                idTv.setError(context.getResources().getString(R.string.blank_id_error));
            }
            if(password.equals("")){
                passwordTv.setError(context.getResources().getString(R.string.blank_pass_error));
            }
            return false;
        }
        user = databaseHandler.getSinlgeEntry(id);
        if(user == null){
            return false;
        }else{
            if(user.getPassword().equals(password))
                return true;
            return false;
        }
    }

    private void createNewAccount() {
        if(context instanceof LoginActivity)
            ((LoginActivity) context).pushFragment(CreateUserFragment.newInstance(), true);
    }

    @Override
    public void changeToolbarTitle() {
        fragmentNameTv.setText(context.getResources().getString(R.string.login));
    }

    @Override
    public String getFragmentTag() {
        return FragmentID.LOGIN_FRAGMENT;
    }


}
