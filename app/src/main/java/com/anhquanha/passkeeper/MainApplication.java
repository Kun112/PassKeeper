package com.anhquanha.passkeeper;

import android.app.Application;

import com.anhquanha.passkeeper.model.User;

/**
 * Created by anhquan.ha on 3/26/18.
 */

public class MainApplication extends Application{

    public static User userInfo;
    @Override
    public void onCreate() {
        super.onCreate();

    }

    public static void setUserInfo(User user){
        userInfo = user;
    }
    public static User getUserInfo(){
        return userInfo;
    }

}
