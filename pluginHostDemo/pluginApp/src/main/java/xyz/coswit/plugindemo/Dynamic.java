package xyz.coswit.plugindemo;


import android.content.Context;

import xyz.coswit.pluginlibrary.IDynamic;

public class Dynamic implements IDynamic {

    @Override
    public String getStringForResId(Context context) {
        return context.getResources().getString(R.string.plgin_app_hello);
    }
}