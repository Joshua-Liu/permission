/**
 * 
 */
package android.by.com.permission.base;

import android.os.Bundle;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Thomas.Ye(Thomas.Ye@CooTek.cn)
 */
public abstract class TSkinActivity extends TPBaseFragmentActivity  {
    public static final String ACTIVITY_STATUS_ON_CREATE = "activity_status_on_create";
    public static final String ACTIVITY_STATUS_ON_DESTROY = "activity_status_on_destroy";
    public static final String ACTIVITY_STATUS_ON_RESUME = "activity_status_on_resume";
    public static final String ACTIVITY_STATUS_ON_PAUSE = "activity_status_on_pause";
//    We can add more life time related keys for observable to use.
//    public static final String ACTIVITY_STATUS_ON_START = "activity_status_on_start";
//    public static final String ACTIVITY_STATUS_ON_STOP = "activity_status_on_stop";
//    public static final String ACTIVITY_STATUS_ON_RESTART = "activity_status_on_restart";
    
    private LifeTimeObservable mLifeTimeObservable;

    private class LifeTimeObservable extends Observable {
        public void notifyLifetimeObserver(String status) {
            this.setChanged();
            this.notifyObservers(status);
        }
    }
    
    @Override
//    @LaunchPerf
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        SkinManager.getInst().registerSkinListener(this);
        notifyLifetimeObserver(ACTIVITY_STATUS_ON_CREATE);
    }

    @Override
    protected void onDestroy() {
//        SkinManager.getInst().unregisterSkinListener(this);
        super.onDestroy();
        notifyLifetimeObserver(ACTIVITY_STATUS_ON_DESTROY);
    }

    @Override
//    @LaunchPerf
    protected void onResume() {
        super.onResume();
        notifyLifetimeObserver(ACTIVITY_STATUS_ON_RESUME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        notifyLifetimeObserver(ACTIVITY_STATUS_ON_PAUSE);
    }

//    @Override
//    public void onSkinChanged(String identifier) {
//        finish();
//    }

    public void addLifeTimeObserver(Observer observer) {
        if (mLifeTimeObservable == null) {
            mLifeTimeObservable = new LifeTimeObservable();
        }
        
        mLifeTimeObservable.addObserver(observer);
    }
    
    public void deleteLifeTimeObserver(Observer observer) {
        if (mLifeTimeObservable != null) {
            mLifeTimeObservable.deleteObserver(observer);
        }
    }
    
    protected void notifyLifetimeObserver(String status) {
        if (mLifeTimeObservable != null) {
            mLifeTimeObservable.notifyLifetimeObserver(status);
        }
    }
}
