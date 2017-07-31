package android.by.com.permission.permission;

import android.accessibilityservice.AccessibilityService;
import android.by.com.permission.R;
import android.by.com.permission.base.TPBaseActivity;
import android.by.com.permission.layout.CircleView;
import android.by.com.permission.model.ModelManager;
import android.by.com.permission.util.OSUtil;
import android.by.com.permission.util.PackageUtil;
import android.by.com.permission.util.ScreenSizeUtil;
import android.by.com.permission.util.WindowUtils;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.by.com.permission.permission.IPermissionGuideStrategy.TUTORIAL_TYPE;


/**
 * Created by AliceXie on 17/3/9.
 */

public class PermissionAccessibilityGuide extends TPBaseActivity {

    public static final String ACCESSIBLITY_TYPE = "permission";
    public int KMaxRetryCount = 2;
    public Context mContext = null;
    public static long startTime = 0, endTime = 0;

    private final String TAG = "PermissionGuide";
    private IPermissionGuideStrategy mStrategy;
    private List<String> mPermissionList;
    private AccessibilityService mService;
    private boolean mStartupAutoChecking = false;
    private boolean mPermissionOverview;

    private int mSelectPageIndex = 0;
    private int mStep = 0;
    private boolean mFisrtLoad = true;
    private Handler mHander = new Handler();
    private HashMap<String, Integer> mExcuteMap = new HashMap<String, Integer>();

    private List<CircleView> mIconView = new ArrayList<CircleView>();
    private int[] mFuncStr = {R.string.permission_auto_proccess_step_1,
            R.string.permission_auto_proccess_step_2,
            R.string.permission_auto_proccess_step_3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (OSUtil.isMiuiV8()) {
            KMaxRetryCount = 1;
        }
        mService = PermissionAccessibilityService.getService();
        mStrategy = PermissionGuideGenerator.generateGuideStratagy(this, true);
        Log.e(TAG, "Service = " + mService + "\nStrategy = " + mStrategy);
        int type = getIntent().getIntExtra(ACCESSIBLITY_TYPE, TUTORIAL_TYPE);
        mPermissionList = mStrategy.getPermissionList(type);
        PermissionAccessibilityService.setAccessibilityListenner(mListenner);
        setContentView(R.layout.activity_accessbility_guide);

        mContext = PermissionAccessibilityGuide.this;
        mPermissionOverview = PackageUtil.isPermissionGuideOverview();


        Intent accessbility = new Intent(android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS);
        startActivity(accessbility);

        if (mService == null) {

            final Intent guideIntent = new Intent(PermissionAccessibilityGuide.this, OuterTwoStepPermissionActivity.class);
            guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.permission_accessibility_outer_two_steps1));
            guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_TWO, mContext.getString(R.string.permission_accessibility_outer_two_steps2));

            guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, outerTwoStepsImage(1));
            guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO, outerTwoStepsImage(2));

            guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_TWO, true);
            guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_MARGIN_LEFT_TWO, R.dimen.permission_oppo_coloros_trust_gesture_maring_left_step2);

            ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mContext.startActivity(guideIntent);
                }
            }, 200);


//            StatRecorder.recordCustomEvent(StatConst.CUSTOM_EVENT_ACCESSIBILITY_GUIDE_STARTUP);
        } else {
            if (!WindowUtils.isShown && mPermissionOverview) {
                WindowUtils.showPopupWindow(mContext);
            }
//            StatRecorder.recordCustomEvent(StatConst.CUSTOM_EVENT_ACCESSIBILITY_GUIDE_RESTART);
        }

    }

    private int outerTwoStepsImage(int index) {
        int result = 0;

        if (OSUtil.isMiuiV5() || OSUtil.isMiuiV6() || OSUtil.isMiuiV7() || OSUtil.isMiuiV8()) {
            if (index == 1) {
                result = R.drawable.accessibility_auto_miui_01;
            } else if (index == 2) {
                result = R.drawable.accessibility_auto_miui_02;
            }

        } else if (PackageUtil.isPackageInstalled(PackageUtil.OPPO_COLOROS_PERMISSION_SETTING_PACKAGE_NAMES)
                || PackageUtil.isPackageInstalled(PackageUtil.OPPO_PERMISSION_SETTING_PACKAGE_NAMES)
                || PackageUtil.isPackageInstalled(PackageUtil.OPPO_2_0_PERMISSION_SETTING_PACKAGE_NAMES)) {
            if (index == 1) {
                result = R.drawable.accessibility_auto_oppo_r9_01;
            } else if (index == 2) {
                result = R.drawable.accessibility_auto_oppo_r9_02;
            }
        } else if (PackageUtil.isPackageInstalled(PackageUtil.HUAWEI_PHONE_MANAGER)) {
            if (index == 1) {
                result = R.drawable.accessibility_auto_oppo_r9_01;
            } else if (index == 2) {
                result = R.drawable.accessibility_auto_oppo_r9_02;
            }
        }

        return result;
    }


    private void clearAllIconViewSelected() {
        for (CircleView view : mIconView) {
            view.setColor(getResources().getColor(R.color.white_transparency_300));
        }
    }

    private void setIconViewSelected(int index) {
        if (mIconView != null && mIconView.size() > index) {
            mIconView.get(index).setColor(getResources().getColor(R.color.white));
        }
    }


    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {
            if (i != mSelectPageIndex) {
                Log.d(TAG, "onPageScrolled =" + i + ",index=" + mSelectPageIndex);
                mSelectPageIndex = i;
                clearAllIconViewSelected();
                setIconViewSelected(i);
            }
        }

        @Override
        public void onPageSelected(int i) {
        }

        @Override
        public void onPageScrollStateChanged(int i) {
        }
    };


    @Override
    public void onBackPressed() {
        if (mService != null) {

        } else {
            HashMap map = new HashMap();
            map.put("service", "disable");
            map.put("home", false);
//            StatRecorder.recordCustomEvent(StatConst.CUSTOM_EVENT_ACCESSIBILITY_GUIDE_FAILED, map);
            closeActivity();
        }
    }

    private void initWaitingView() {
        setContentView(R.layout.permission_processing);
        ImageView frame = (ImageView) findViewById(R.id.frame);
        frame.setImageResource(R.drawable.permission_processing_anim);
        final AnimationDrawable anim = (AnimationDrawable) frame.getDrawable();
        anim.start();

        ImageView frame_bg = (ImageView) findViewById(R.id.bg_frame);
        ImageView frame_bg_1 = (ImageView) findViewById(R.id.bg_frame_1);
        TranslateAnimation tmp = new TranslateAnimation(ScreenSizeUtil.getScreenSize().widthPixels, 0f, 0f, 0f);
        tmp.setDuration(2000);
        tmp.setRepeatCount(-1);
        tmp.setFillBefore(true);
        tmp.setFillAfter(true);
        frame_bg.startAnimation(tmp);
        TranslateAnimation tmp2 = new TranslateAnimation(0f, -ScreenSizeUtil.getScreenSize().widthPixels, 0f, 0f);
        tmp2.setDuration(2000);
        tmp2.setRepeatCount(-1);
        tmp2.setFillBefore(true);
        tmp2.setFillAfter(true);
        frame_bg_1.startAnimation(tmp2);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume.....");
        startOptimize();

    }

    private void closeActivity() {
        mService = null;
        mStrategy = null;
        if (startTime != 0) {
            endTime = SystemClock.elapsedRealtime();
            long tmp = (endTime - startTime);
            Log.e(TAG, "popWindowTime = " + tmp);
//            StatRecorder.recordCustomEvent(StatConst.CUSTOM_EVENT_ACCESSIBILITY_GUIDE_OVERVIEW_TIME, tmp);
        }
        finish();

    }

    private void startOptimize() {
        if (mPermissionList != null && mService != null && mPermissionList.size() > 0) {
            if (!WindowUtils.isShown && mPermissionOverview) {
                WindowUtils.showPopupWindow(mContext);
            }
            if (mStrategy.allPermissionEnable(mPermissionList)) {
                HashMap map = new HashMap();
                int i = mPermissionList.size() - 1;
                try {
                    map.put("key", mPermissionList.get(i));
                    map.put("step", i);
//                    StatRecorder.recordCustomEvent(StatConst.CUSTOM_EVENT_ACCESSIBILITY_PERMISSION_ENABLE, map);
//                    StatRecorder.recordCustomEvent(StatConst.CUSTOM_EVENT_ACCESSIBILITY_GUIDE_SUCCESS);
                } catch (Exception e) {
                }
                closeActivity();
                return;
            }
            if (canExitProcess()) {
                closeActivity();
                return;
            }

            if (mStartupAutoChecking) {
                if (mStep >= mFuncStr.length) {
                    mHander.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            executePermissionAction();
                        }
                    }, 500);
                } else {
                    if (!mPermissionOverview) {
                        updateProcessView();
                        mHander.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                executePermissionAction();
                            }
                        }, 500);
                    } else {
                        mHander.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                executePermissionAction();
                            }
                        }, 200);
                    }
                }
            } else {
                if (!mPermissionOverview) {
                    initWaitingView();
                    updateProcessView();
                    mHander.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            executePermissionAction();
                        }
                    }, 2000);
                } else {
                    mHander.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            executePermissionAction();
                        }
                    }, 200);
                }
            }
            if (canExitProcess()) {
                closeActivity();
                return;
            }
        } else if (mService == null && mStartupAutoChecking == true) {
            HashMap map = new HashMap();
            map.put("service", "enablethendisable");
            map.put("home", false);
//            StatRecorder.recordCustomEvent(StatConst.CUSTOM_EVENT_ACCESSIBILITY_GUIDE_FAILED, map);
            closeActivity();
        } else if (mService == null && mFisrtLoad == true) {
            mFisrtLoad = false;
        } else {
            closeActivity();
        }
    }

    private boolean canExitProcess() {
        int count = mPermissionList.size();
        for (int i = 0; i < count; i++) {
            String key = mPermissionList.get(i);
            int time = 0;
            if (mExcuteMap.get(key) != null) {
                time = mExcuteMap.get(key).intValue();
            }
            if (time != KMaxRetryCount && !mStrategy.getPermissionDone(key)) {
                return false;
            }
        }
        //
        return true;
    }

    private void updateProcessView() {
        int step = mStep;
        if (mStep >= 2) {
            step = 2;
        }
        if (!mPermissionOverview) {
            ((TextView) findViewById(R.id.waiting)).setText(R.string.permission_auto_proccess_desc_old);
            ((TextView) findViewById(R.id.fun)).setText(mFuncStr[step]);
        }
        int left = 2 - step;
        String attr;
        if (left == 0) {
            attr = getString(R.string.permission_auto_proccess_last);
        } else {
            attr = getString(R.string.permission_auto_proccess_attr, left);
        }
        ((TextView) findViewById(R.id.attr)).setText(attr);
    }

    private void executePermissionAction() {
        if (mStrategy == null) {
            return;
        }
        mStartupAutoChecking = true;
        mStep++;
        int count = mPermissionList.size();
        String preKey;
        String key = null;
        for (int i = 0; i < count; i++) {
            preKey = key;
            key = mPermissionList.get(i);
            boolean done = mStrategy.getPermissionDone(key);
            Log.d(TAG, "startAuto key=" + done + ",key=" + key);
            if (!done) {
                if (preKey != null) {
                    HashMap map = new HashMap();
                    map.put("key", preKey);
                    map.put("step", i - 1);
//                    StatRecorder.recordCustomEvent(StatConst.CUSTOM_EVENT_ACCESSIBILITY_PERMISSION_ENABLE, map);
                }

                int time = 0;
                if (mExcuteMap.get(key) != null) {
                    time = mExcuteMap.get(key).intValue();
                }
                Log.d(TAG, "executePermissionAction = " + time + ",size=" + mExcuteMap.size());
                if (time < KMaxRetryCount) {
                    mExcuteMap.put(key, time + 1);
                    mStrategy.generateButtonFunction(key);
                    break;
                }
            }
        }
    }

    private PermissionAccessibilityService.AccessibilityListenner mListenner = new PermissionAccessibilityService.AccessibilityListenner() {
        @Override
        public void onAccessibilityEvent(AccessibilityEvent event) {
            if (mService != null && mStrategy != null) {
                Log.e(TAG, "onAccessibilityEvent ......");
                mStrategy.handleAccessbilityEvent(event, mService);
            }
        }

        @Override
        public void onServiceConnected(AccessibilityService service) {
            Log.e(TAG, "onServiceConnected  .....");
            mService = service;
            Log.e(TAG, "Service = " + mService + "\nStrategy = " + mStrategy);
//            StatRecorder.recordCustomEvent(StatConst.CUSTOM_EVENT_ACCESSIBILITY_SERVICE_ENABLE);
            if (mStrategy == null) {
                try {
                    mStrategy = PermissionGuideGenerator.generateGuideStratagy(mContext, true);
                } catch (Exception e) {
                }
            }
            if (mStrategy != null) {
                mStrategy.configAccessbility(service);
                if (mPermissionOverview) {
                    WindowUtils.showPopupWindow(mContext);
                    startTime = SystemClock.elapsedRealtime();
                }
            }

        }

        @Override
        public void onInterrupt() {
            mService = null;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeActivity();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.e("ssskey", "keycode = " + keyCode + " , event = " + event);
        if (KeyEvent.KEYCODE_HOME == keyCode) {
            closeActivity();
            HashMap map = new HashMap();
            map.put("service", mService != null ? "enable" : "disable");
            map.put("home", true);
//            StatRecorder.recordCustomEvent(StatConst.CUSTOM_EVENT_ACCESSIBILITY_GUIDE_FAILED, map);
            if (WindowUtils.isShown) {
                WindowUtils.hidePopupWindow();
            }
            Log.e("hidewindow", "");
        }
        return super.onKeyDown(keyCode, event);
    }

    private class MyTabPagerAdapter extends PagerAdapter {

        public ArrayList<View> mListViews;

        public MyTabPagerAdapter(ArrayList<View> listViews) {
            mListViews = listViews;
        }

        @Override
        public int getCount() {
            return mListViews.size();
        }

        public View getPostion(int index) {
            return mListViews.get(index);
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView(mListViews.get(position));
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            View layout = mListViews.get(position);
            ((ViewPager) container).addView(layout, 0);
            return layout;
        }
    }
}
