package com.anhquanha.passkeeper.view.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.anhquanha.passkeeper.util.DialogUtil;

import butterknife.ButterKnife;

/**
 * Created by anhquan.ha on 3/6/2018.
 */

public abstract class BaseFragment extends android.support.v4.app.Fragment {
    Context context;
    int layoutId;

    public abstract int getLayoutId();

    public abstract void initView();

    public abstract void changeToolbarTitle();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layoutId = getLayoutId();
        View v = inflater.inflate(layoutId, container, false);
        ButterKnife.bind(this, v);
        changeToolbarTitle();
        initView();
        return v;
    }

    public void showProgressDialog(){
        DialogUtil.showProgressDialog(context);
    }

    public void hideProgressDialog(){
        DialogUtil.hideProgressDialog();
    }

    public void showToast(String mess){
        Toast.makeText(context, mess, Toast.LENGTH_SHORT).show();
    }

    public void pushFragment(){

    }

}
