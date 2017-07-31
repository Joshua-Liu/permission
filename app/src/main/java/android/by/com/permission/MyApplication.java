package android.by.com.permission;

import android.app.Application;
import android.by.com.permission.model.ModelManager;
import android.content.Context;

/**
 * Created by by.huang on 2017/7/31.
 */

public class MyApplication extends Application{

    private static MyApplication sAppCtx;
    @Override
    public void onCreate() {
        super.onCreate();
        sAppCtx = this;
        ModelManager.initialize(this);

    }


    public static Context getAppContext() {
        return sAppCtx;
    }

}
