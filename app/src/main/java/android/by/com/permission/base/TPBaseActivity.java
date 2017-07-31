package android.by.com.permission.base;

import android.app.Activity;
import android.by.com.permission.model.ModelManager;
import android.os.Bundle;



import java.util.HashMap;

/**
 * Created by hengfengtian on 8/12/15.
 */
public abstract class TPBaseActivity extends Activity {
    private static final String TAG = "TPBaseActivity";
    public static final String AMOUNT_UPDATED = "AMOUNT_UPDATED";
    private int mStartSeconds;

    @Override
    protected void onDestroy(){
//        AppUtils.fixInputMethodManagerLeak(this);
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ModelManager.setupEnvironment(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mStartSeconds = (int) System.currentTimeMillis() / 1000;
//        EdenActive.activeIn(this.getClass().getName());
//        TPApplication.initQt(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("page_name",this.getClass().getName());
        int second = (int) System.currentTimeMillis() / 1000 - mStartSeconds;
        map.put("second",second);
//        StatRecorder.record(StatConst.APP_KEEP_PAGE_ACTIVE,map);
//        EdenActive.activeOut(this.getClass().getName());
    }
}
