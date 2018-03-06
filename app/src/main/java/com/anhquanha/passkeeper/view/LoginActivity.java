package com.anhquanha.passkeeper.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.anhquanha.passkeeper.R;
import com.anhquanha.passkeeper.util.DialogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.account_login_btn)
    Button accountLoginBtn;
    @BindView(R.id.fingerprint_login_btn)
    Button fingerLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);

        accountLoginBtn.setOnClickListener(v->{
            DialogUtil.showProgressDialog(this);
        });

        fingerLoginBtn.setOnClickListener(v->{

        });
    }
}
