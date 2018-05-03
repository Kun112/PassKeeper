package com.anhquanha.passkeeper.asset;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.anhquanha.passkeeper.MainApplication;

public class SharePrefer {
    Context context;
    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;
    private static SharePrefer sharePrefer;

    private final String USERNAME_KEY = "username";
    private final String ID_USER_KEY = "id_user";
    private final String IS_LOGIN = "is_login";

    private SharePrefer(Context context) {
        this.context = context;
        mPref = PreferenceManager.getDefaultSharedPreferences(context);
        mEditor = mPref.edit();
        mEditor.apply();
    }

    public static SharePrefer getInstance(){
        if(sharePrefer == null){
            sharePrefer = new SharePrefer(MainApplication.getContext());
        }
        return sharePrefer;
    }

    public void putUserName(String userName){
        if(userName!= null){
            mEditor.putString(USERNAME_KEY, userName);
        }
        mEditor.commit();
    }
    public void putCurrentIdUser(String id){
        if(id!= null){
            mEditor.putString(ID_USER_KEY, id);
        }
        mEditor.commit();
    }

    public String getCurrentIdUser(){
        return mPref.getString(ID_USER_KEY, null);
    }
    public void putLoginStatus(boolean isLogin){
        mEditor.putBoolean(IS_LOGIN, isLogin);
        mEditor.commit();
    }

    public String getUserName(){
        return mPref.getString(USERNAME_KEY, null);
    }

    public boolean getLoginStatus(){
        return mPref.getBoolean(IS_LOGIN, false);
    }
}
