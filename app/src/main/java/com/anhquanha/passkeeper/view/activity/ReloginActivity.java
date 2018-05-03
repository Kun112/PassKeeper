package com.anhquanha.passkeeper.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.anhquanha.passkeeper.MainApplication;
import com.anhquanha.passkeeper.R;
import com.anhquanha.passkeeper.asset.SharePrefer;
import com.anhquanha.passkeeper.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReloginActivity extends AppCompatActivity {
    @BindView(R.id.greetingTv)
    TextView greetingTv;
    @BindView(R.id.passwordReloginTv)
    EditText passwordEt;
    @BindView(R.id.reloginBtn)
    Button reloginBtn;
    @BindView(R.id.logoutBtn)
    Button logoutBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relogin_activity_layout);
        ButterKnife.bind(this);

        greetingTv.setText(getResources().getString(R.string.greeting_string, SharePrefer.getInstance().getUserName()));

        reloginBtn.setOnClickListener(v -> {
            checkLoginInfo();
        });

        logoutBtn.setOnClickListener(v -> {
            logOutCurrentUser();
        });
    }

    private void logOutCurrentUser() {
        SharePrefer.getInstance().putLoginStatus(false);
        startActivity(new Intent(ReloginActivity.this, LoginActivity.class));
        finish();
    }

    private void checkLoginInfo() {
        String password = passwordEt.getText().toString();
        String userId = SharePrefer.getInstance().getCurrentIdUser();
        User user = MainApplication.getDatabaseHandler().getSinlgeEntry(userId);
        if (user == null) {
            return;
        }
        if (user.getPassword().equals(password)) {
            MainApplication.setUserInfo(user);
            startActivity(new Intent(ReloginActivity.this, MainActivity.class));
            finish();
        }
    }
}
