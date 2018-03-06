package com.anhquanha.passkeeper.view.fragment;

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
    }

    @Override
    public void changeToolbarTitle() {
        fragmentNameTv.setText(context.getResources().getString(R.string.login));
    }


}
