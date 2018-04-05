package com.anhquanha.passkeeper;

import android.app.Application;
import android.content.Context;

import com.anhquanha.passkeeper.model.User;

/**
 * Created by anhquan.ha on 3/26/18.
 */

public class MainApplication extends Application{

    private static Context context;
    public static User userInfo;
    @Override
    public void onCreate() {
        super.onCreate();
        this.context = getApplicationContext();
    }

    public static void setUserInfo(User user){
        userInfo = user;
    }
    public static User getUserInfo(){
        return userInfo;
    }

    public static Context getContext(){
        return context;
    }
}
