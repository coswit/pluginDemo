package xyz.coswit.pluginhost.reflect;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * @author Created by zhengjing on 2020/6/11.
 */
public class StubBaseActivity extends Activity {

    public static final String ACTIVITY_NAME = "ActivityName";
    public static final String PLUGIN_PATH = "pluginPath";

    protected ClassLoader classLoader;
    protected String activityName;
    private AssetManager pluginAssetManager;
    private Resources pluginResources;
    private Resources.Theme pluginTheme;
    private String dexoutPath;
    private String pluginPath;
//    private String nativeLibDir;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String  nativeLibDir = new File(getFilesDir(),"pluginlib").getAbsolutePath();
        pluginPath = getIntent().getStringExtra(PLUGIN_PATH);
        activityName = getIntent().getStringExtra(ACTIVITY_NAME);
        dexoutPath = new File(getFilesDir(), "dexout").getAbsolutePath();
        classLoader = new DexClassLoader(pluginPath, dexoutPath, nativeLibDir, getClassLoader());

        handlerResource();
    }

    private void handlerResource() {
        try {
            pluginAssetManager = AssetManager.class.newInstance();
            Method addAssetPathMethod =pluginAssetManager.getClass().getMethod("addAssetPath", String.class);
            addAssetPathMethod.invoke(pluginAssetManager, pluginPath);

            pluginResources = new Resources(pluginAssetManager, super.getResources().getDisplayMetrics(),
                    super.getResources().getConfiguration());

            pluginTheme = pluginResources.newTheme();
            pluginTheme.setTo(super.getTheme());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ClassLoader getClassLoader() {
        return classLoader==null?super.getClassLoader():classLoader;
    }

//    @Override
//    public Resources.Theme getTheme() {
//        return pluginTheme == null ? super.getTheme() : pluginTheme;
//    }

    @Override
    public Resources getResources() {
        return pluginResources == null ? super.getResources() : pluginResources;
    }

}
