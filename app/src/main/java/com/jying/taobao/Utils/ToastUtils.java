package com.jying.taobao.Utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Jying on 2017/11/19.
 */

public class ToastUtils {

    private ToastUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void shortToast(Context context, CharSequence s) {
        show(context,s,Toast.LENGTH_SHORT);
    }
    public static void longToast(Context context,CharSequence s){
        show(context,s,Toast.LENGTH_LONG);
    }

    public static void show(Context context, CharSequence msg, int duration) {
        Toast.makeText(context.getApplicationContext(), msg, duration).show();
    }
}
