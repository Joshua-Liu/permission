package android.by.com.permission.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;



/**
 * Created by frankyang on 2/25/16.
 */
public class HomeButtonReceiver extends BroadcastReceiver {
    public static final String SYSTEM_REASON = "reason";
    public static final String SYSTEM_HOME_KEY = "homekey";
    String SYSTEM_HOME_KEY_LONG = "recentapps";

    private HomeButtonCallback mHomeButtonCallback;

    public HomeButtonReceiver(HomeButtonCallback homeButtonCallback) {
        mHomeButtonCallback = homeButtonCallback;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
            String reason = intent.getStringExtra(SYSTEM_REASON);
            if (TextUtils.equals(reason, SYSTEM_HOME_KEY)) {
                //表示按了home键,程序到了后台
//                StatRecorder.record(StatConst.PATH_EXIT, StatConst.PATH_EXIT_TAB, TPDTabActivity.mCurrentItem);
                if (mHomeButtonCallback != null) {
                    mHomeButtonCallback.onClick();
                }
            }else if(TextUtils.equals(reason, SYSTEM_HOME_KEY_LONG)){
                //表示长按home键,显示最近使用的程序列表
            }
        }
    }


    public interface HomeButtonCallback{
         void onClick();
    }
}
