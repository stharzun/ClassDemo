package com.stharzun.demoapp;

import android.content.Context;
import android.content.SharedPreferences;

public class UserData {
    private Context context;
    private static final String fileName = "UserInfo";

    //Constructor chaixa
    public UserData(Context context) {
        this.context = context;
    }

    public void setLoginStatus(boolean isLogin) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor e = sp.edit();
        e.putBoolean("isLogin", isLogin);
        e.apply();
        e.commit();
    }

    public boolean getLoginStatus() {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.getBoolean("isLogin", false);
    }
}
