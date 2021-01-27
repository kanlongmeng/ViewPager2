package com.jicanghai.viewpager2;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SpUtils {

    public static void saveContralState(Context context, boolean control) {
        SharedPreferences sp = context.getSharedPreferences("control:file", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("control", control);
        editor.commit();
    }

    public static boolean getContralState(Context context) {
        SharedPreferences setting = context.getSharedPreferences("control:file", Activity.MODE_PRIVATE);
        return setting.getBoolean("control", true);
    }
}
