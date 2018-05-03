package com.anhquanha.passkeeper.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.anhquanha.passkeeper.R;
import com.anhquanha.passkeeper.asset.SharePrefer;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        new Handler().postDelayed(() -> checkDisplayScreen(), 2000);
    }

    private void checkDisplayScreen() {
        if(SharePrefer.getInstance().getLoginStatus()){
            startActivity(new Intent(SplashActivity.this, ReloginActivity.class));
            finish();
            return;
        }
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        finish();
    }
}
