package android.by.com.permission.permission;

import android.accessibilityservice.AccessibilityService;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;


/**
 * Created by AliceXie on 17/3/2.
 */

public class PermissionAccessibilityService extends AccessibilityService {

    private final String TAG = "PermissionService";
    private static PermissionAccessibilityService sService = null;
    public interface AccessibilityListenner {
        public void onAccessibilityEvent(AccessibilityEvent event);
        public void onServiceConnected(AccessibilityService service);
        public void onInterrupt();
    }

    public static AccessibilityListenner mListenner;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        final int eventType = event.getEventType();
        Log.d(TAG,"onAccessibilityEvent 1= " + eventType);
        if(mListenner != null) {
            mListenner.onAccessibilityEvent(event);
        }
    }

    public static void setAccessibilityListenner(AccessibilityListenner listener) {
        mListenner = listener;
    }

    public static PermissionAccessibilityService getService() {
        return sService;
    }

    @Override
    protected boolean onKeyEvent(KeyEvent event) {
        return super.onKeyEvent(event);
    }

    @Override
    public void onInterrupt() {
        sService = null;
        if (mListenner != null) {
            mListenner.onInterrupt();
        }
    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        sService = this;
        Log.i(TAG, "config success!");
        if (mListenner != null) {
            mListenner.onServiceConnected(this);
        }
    }



}
