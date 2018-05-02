package com.anhquanha.passkeeper.util;

import com.anhquanha.passkeeper.MainApplication;

public class StringUtil {

    public static String getStringResource(int id){
        return MainApplication.getContext().getResources().getString(id);
    }

}
