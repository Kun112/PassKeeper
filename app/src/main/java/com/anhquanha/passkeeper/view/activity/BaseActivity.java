package com.anhquanha.passkeeper.view.activity;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by anhquan.ha on 3/10/18.
 */

public class BaseActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    FrameLayout frameLayout;

    @Override
    public void onBackPressed() {
        if(linearLayout.getVisibility() == View.VISIBLE){
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
            linearLayout.setVisibility(View.VISIBLE);
        }
    }

    public void setLayout(LinearLayout linearLayout, FrameLayout frameLayout){
        this.frameLayout = frameLayout;
        this.linearLayout = linearLayout;
    }


}
