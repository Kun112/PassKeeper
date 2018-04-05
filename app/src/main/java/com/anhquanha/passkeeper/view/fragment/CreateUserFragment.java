package com.anhquanha.passkeeper.view.fragment;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.anhquanha.passkeeper.R;
import com.anhquanha.passkeeper.asset.DatabaseHandler;
import com.anhquanha.passkeeper.constant.FragmentID;
import com.anhquanha.passkeeper.model.User;
import com.anhquanha.passkeeper.view.activity.LoginActivity;

import butterknife.BindView;

/**
 * Created by anhquan.ha on 3/10/18.
 */

public class CreateUserFragment extends BaseFragment {

    @BindView(R.id.usernameTv)
    EditText userNameTv;
    @BindView(R.id.idUserTv)
    EditText idUserTv;
    @BindView(R.id.passwordTv)
    EditText passwordTv;
    @BindView(R.id.confirmPassTv)
    EditText confirmPassTv;

    @BindView(R.id.fragmentNameTv)
    TextView fragmentNameTv;
    @BindView(R.id.backBtn)
    ImageView backBtn;
    @BindView(R.id.register_btn)
    Button registerBtn;

    String userName = "";
    String idUser = "";
    String password = "";
    String confirmPassword = "";

    DatabaseHandler databaseHandler;

    public static CreateUserFragment newInstance() {

        return new CreateUserFragment();
    }
    @Override
    public int getLayoutId() {
        return R.layout.create_user_fragment;
    }

    @Override
    public void initView() {
        databaseHandler = new DatabaseHandler(context);
        backBtn.setOnClickListener(v->{
            if(context instanceof LoginActivity)
                ((LoginActivity) context).onBackPressed();
        });

        registerBtn.setOnClickListener(v->{
            createNewUser();
        });

    }

    private void createNewUser() {
        userName = userNameTv.getText().toString();
        idUser = idUserTv.getText().toString();
        password = passwordTv.getText().toString();
        confirmPassword = confirmPassTv.getText().toString();
        if(checkNewUserCondition(userName, idUser, password, confirmPassword)){
            /** create new user **/
        databaseHandler.createUser(new User(idUser, userName, password));
        showToast(context.getResources().getString(R.string.create_user_success));
        }
        else{
            /** do nothing **/
            return;
        }
    }

    private boolean checkNewUserCondition(String userName, String idUser, String password, String confirmPassword) {
        /** check if any field is blank */
        if(userName.equals("") || idUser.equals("") || password.equals("") || confirmPassword.equals("")){
            if(userName.equals(""))
                userNameTv.setError(context.getResources().getString(R.string.blank_error));
            if(idUser.equals(""))
                idUserTv.setError(context.getResources().getString(R.string.blank_error));
            if(password.equals(""))
                passwordTv.setError(context.getResources().getString(R.string.blank_error));
            if(confirmPassword.equals(""))
                confirmPassTv.setError(context.getResources().getString(R.string.blank_error));
            return false;
        }
        /** check if passwords is not match **/
        if(!password.equals(confirmPassword)) {
            confirmPassTv.setError(context.getResources().getString(R.string.not_corresponding_pass));
            return false;
        }
        if(databaseHandler.checkExistUser(idUser)){
            idUserTv.setError(context.getResources().getString(R.string.used_account_error));
            showToast(context.getResources().getString(R.string.used_account_error));
            return false;
        }

        return true;
    }

    @Override
    public void changeToolbarTitle() {
        fragmentNameTv.setText("Create account");
    }

    @Override
    public String getFragmentTag() {
        return FragmentID.CREATE_USER_FRAGMENT;
    }
}
