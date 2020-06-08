package xyz.coswit.pluginhost;

import android.app.Application;
import android.content.Context;

/**
 * @author Created by zhengjing on 2020/6/6.
 */
public class App extends Application {

    private static Context context;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        context = base;
    }

    public static Context getContext(){
        return context;
    }
}
