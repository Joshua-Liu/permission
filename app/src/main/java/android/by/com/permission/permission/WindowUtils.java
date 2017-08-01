package android.by.com.permission.permission;

import android.by.com.permission.R;
import android.by.com.permission.util.ScreenSizeUtil;
import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;



/**
 * Created by cheng on 2017/5/12.
 */

public class WindowUtils {
    private static final String LOG_TAG = "WindowUtils";
    private static View mView = null;
    private static int queueNum = 0;
    private static WindowManager mWindowManager = null;
    private static Context mContext = null;
    public static Boolean isShown = false;
    private static Handler mhand = new Handler();

    /**
     * 显示弹出框
     *
     * @param context
     * @param
     */
    public static void showPopupWindow(final Context context) {
        if (isShown) {
            Log.e(LOG_TAG, "return cause already shown");
            return;
        }
        isShown = true;
        Log.e(LOG_TAG, "showPopupWindow");
        // 获取应用的Context
        mContext = context.getApplicationContext();
        // 获取WindowManager
        mWindowManager = (WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE);
        mView = setUpView(context);
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        // 类型
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            params.type = WindowManager.LayoutParams.TYPE_TOAST;
        } else {
            params.type = WindowManager.LayoutParams.TYPE_PHONE;
        }

//        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        // WindowManager.LayoutParams.TYPE_SYSTEM_ALERT
        // 设置flag
        int flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        // | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        // 如果设置了WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE，弹出的View收不到Back键的事件
        params.flags = flags;
        // 不设置这个弹出框的透明遮罩显示为黑色
        params.format = PixelFormat.TRANSLUCENT;
        // FLAG_NOT_TOUCH_MODAL不阻塞事件传递到后面的窗口
        // 设置 FLAG_NOT_FOCUSABLE 悬浮窗口较小时，后面的应用图标由不可长按变为可长按
        // 不设置这个flag的话，home页的划屏会有问题
        params.width = LayoutParams.MATCH_PARENT;
        params.height = LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.CENTER;
        mWindowManager.addView(mView, params);


//        Handler mhand = new Handler();
        queueNum ++;
        mhand.postDelayed(new Runnable() {
            @Override
            public void run() {
                queueNum --;
                if(isShown && queueNum == 0) {
                    hidePopupWindow();
                }
                Log.e(LOG_TAG,"queueNum = "+queueNum);
            }
        },25000);

        Log.e(LOG_TAG, "add view");


    }
    /**
     * 隐藏弹出框
     */
    public static void hidePopupWindow() {
        Log.e(LOG_TAG, "hide " + isShown + ", " + mView);
        if (isShown && null != mView) {
            Log.e(LOG_TAG, "hidePopupWindow");
            mWindowManager.removeView(mView);
            isShown = false;
        }
    }
    private static View setUpView(final Context context) {
        Log.e(LOG_TAG, "setUp view");
//        View view = LayoutInflater.from(context).inflate(R.layout.popupwindow,null);

        View view = LayoutInflater.from(context).inflate(R.layout.permission_processing, null);

        ImageView frame = (ImageView) view.findViewById(R.id.frame);
        frame.setImageResource(R.drawable.permission_processing_anim);
        final AnimationDrawable anim = (AnimationDrawable) frame.getDrawable();
        anim.start();

        view.findViewById(R.id.attr).setVisibility(View.INVISIBLE);

        ImageView frame_bg = (ImageView) view.findViewById(R.id.bg_frame);
        ImageView frame_bg_1 = (ImageView) view.findViewById(R.id.bg_frame_1);
        TranslateAnimation tmp = new TranslateAnimation(ScreenSizeUtil.getScreenSize().widthPixels, 0f, 0f, 0f);
        tmp.setDuration(2000);
        tmp.setRepeatCount(-1);
        tmp.setFillBefore(true);
        tmp.setFillAfter(true);
        frame_bg.startAnimation(tmp);
        TranslateAnimation tmp2 = new TranslateAnimation(0f,-ScreenSizeUtil.getScreenSize().widthPixels, 0f, 0f);
        tmp2.setDuration(2000);
        tmp2.setRepeatCount(-1);
        tmp2.setFillBefore(true);
        tmp2.setFillAfter(true);
        frame_bg_1.startAnimation(tmp2);

        view.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                switch (keyCode) {
                    case KeyEvent.KEYCODE_BACK:
                        WindowUtils.hidePopupWindow();
                        return true;
                    default:
                        return false;
                }
            }
        });
        return view;
    }

}
