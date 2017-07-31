//package android.by.com.permission.permission;
//
//import android.by.com.permission.R;
//import android.by.com.permission.base.TPBaseActivity;
//import android.by.com.permission.constant.GuideConst;
//import android.by.com.permission.pref.PrefKeys;
//import android.by.com.permission.util.OSUtil;
//import android.by.com.permission.util.PrefUtil;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Typeface;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.ScrollView;
//import android.widget.TextView;
//
//
//
//import java.util.HashMap;
//import java.util.List;
//
//
///**
// * Created by AliceXie on 17/3/7.
// */
//
//public class AccessibilityPermissionProcessActivity extends TPBaseActivity {
//    public static final String INAPP = "inapp";
//    public static final String LOGIN = "login";
//    public static final String CALLLOG = "callog";
//    public static final String UPGRADE = "upgrade";
//    public static final String FROM = "from";
//    public static final String ACCESSIBLITY_TYPE = "permission";
//    public Context mContext = null;
//
//    private final String TAG = "OppoColorOSPermissionGuideStrategy";
//    private IPermissionGuideStrategy mStrategy, mStrategyHand;
//    private List<String> mPermissionList;
//
//    private TextView mOptimizeView;
//    private View mBannerView;
//    private TextView mBannerTips;
//    private boolean dialogFlag;
//    private ScrollView mHardPermissionCodeList;
//    private LinearLayout mPermissionListContent;
//    private ScrollView mPermissionListScroll;
//    private boolean hasFaildPermission;
//
//
//    private boolean mStartGuide;
//
//    private int[] mWarmingids = {R.id.list_waring_1,R.id.list_waring_2,R.id.list_waring_3,R.id.list_waring_4};
//    private int[] mTextids = {R.id.list_text_1,R.id.list_text_2,R.id.list_text_3,R.id.list_text_4};
//    private int mPermissionType;
//    private boolean hasHandOpen = false;
//
//    //为miui8做的权限UI适配
//    private String[] MIUI8PermissionList = { GuideConst.CALL_PHONE_PERMISSION, GuideConst.AUTOBOOT_PERMISSION,GuideConst.OPEN_NOTIFICATION, GuideConst.TOAST_PERMISSION};
//
//    private HashMap<String, String> MIUI8TextMap = new HashMap<String, String>() {{
//        put( GuideConst.CALL_PHONE_PERMISSION , "u" );
//        put( GuideConst.AUTOBOOT_PERMISSION , "6" );
//        put( GuideConst.OPEN_NOTIFICATION , "F" );
//        put( GuideConst.TOAST_PERMISSION , "i" );
//    }};
//    private HashMap<String, Typeface> MIUI8TextFaceMap = new HashMap<String, Typeface>() {{
//        put( GuideConst.CALL_PHONE_PERMISSION , TouchPalTypeface.ICON1);
//        put( GuideConst.AUTOBOOT_PERMISSION , TouchPalTypeface.ICON2);
//        put( GuideConst.OPEN_NOTIFICATION , TouchPalTypeface.ICON2);
//        put( GuideConst.TOAST_PERMISSION , TouchPalTypeface.ICON1);
//    }};
//    private HashMap<String, Integer> MIUI8TitleMap = new HashMap<String, Integer>() {{
//        put( GuideConst.CALL_PHONE_PERMISSION , R.string.permission_auto_list_setting_text1);
//        put( GuideConst.AUTOBOOT_PERMISSION , R.string.permission_auto_list_fail_text3);
//        put( GuideConst.OPEN_NOTIFICATION , R.string.permission_auto_list_fail_text4);
//        put( GuideConst.TOAST_PERMISSION , R.string.permission_auto_list_fail_text2);
//
//    }};
//
//
//    private int[] mTexts = {
//            R.string.permission_auto_proccess_textlist_1,
//            R.string.permission_auto_proccess_textlist_2,
//            R.string.permission_auto_proccess_textlist_3,
//            R.string.permission_auto_proccess_textlist_4
//
//    };
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        PrefUtil.setKey(PrefKeys.ACCESSIBILITY_PERMISSION_GUIDE_HAS_SHOW, true);
//        mStrategy = PermissionGuideGenerator.generateGuideStratagy(this,true);
//        mStrategyHand = PermissionGuideGenerator.generateGuideStratagy(this,false);
//
//        mPermissionType = getIntent().getIntExtra(ACCESSIBLITY_TYPE,IPermissionGuideStrategy.TUTORIAL_TYPE);
//        mPermissionList = mStrategy.getPermissionList(mPermissionType);
//        mContext = AccessibilityPermissionProcessActivity.this;
//
//        TLog.e(TAG,"mPermissionList = "+mPermissionList);
//
//        if (mPermissionList == null || mStrategy.allPermissionEnable(mPermissionList)) {
//            finish();
//            return;
//        }
//        TLog.d(TAG,"onCreate = " + mPermissionType + ",size=" + mPermissionList.size());
//        HashMap map = new HashMap();
//        map.put(FROM,getIntent().getStringExtra(FROM));
//
////        StatRecorder.recordCustomEvent(StatConst.CUSTOM_EVENT_ACCESSIBILITY_GUIDE_SHOW,map);
//        setContentView(R.layout.activity_permission_process);
//        initView();
//    }
//
//    @Override
//    public void onBackPressed() {
//        TLog.d(TAG,"onBackPressed ....." );
//        boolean allEnable = mStrategy.allPermissionEnable(mPermissionList);
//        if (allEnable) {
//            super.onBackPressed();
//        } else {
//            showBackDilog(allEnable);
//        }
//    }
//
//    private void showBackDilog(boolean enable) {
//        final TDialog dialog =
//                new TDialog(this, TDialog.STYLE_BUTTON_ZERO,false);
//
//        RelativeLayout contentView = (RelativeLayout) LayoutInflater.from(this)
//                .inflate(R.layout.permission_accessibility_dlg_warning, null);
//
//        TextView tv = (TextView) contentView.findViewById(R.id.button_ack);
//        if(OSUtil.isMiuiV8() && mStartGuide){
//            tv.setText("立即开启");
//        }
//        contentView.findViewById(R.id.button_cancel).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(OSUtil.isMiuiV8() && mStartGuide) {
////                    StatRecorder.recordCustomEvent(StatConst.CUSTOM_EVENT_ACCESSIBILITY_GUIDE_DIALOG_GIVEUP);
//                }else {
////                    StatRecorder.recordCustomEvent(StatConst.CUSTOM_EVENT_ACCESSIBILITY_GUIDE_WARNING_GIVEUP);
//                }
//                dialog.dismiss();
//                finish();
//            }
//        });
//        dialog.hideTitle();
//        if(OSUtil.isMiuiV8() && mStartGuide){
//            contentView.findViewById(R.id.button_ack).setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View v) {
//                    dialog.dismiss();
//                }
//            });
//        }else {
//            contentView.findViewById(R.id.button_ack).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (OSUtil.isMiuiV8() && mStartGuide) {
////                        StatRecorder.recordCustomEvent(StatConst.CUSTOM_EVENT_ACCESSIBILITY_GUIDE_DIALOG_ACCEPT);
//                    } else {
////                        StatRecorder.recordCustomEvent(StatConst.CUSTOM_EVENT_ACCESSIBILITY_GUIDE_WARNING_ACCEPT);
//                    }
//                    mStartGuide = true;
//                    Intent accessbility = new Intent(AccessibilityPermissionProcessActivity.this, PermissionAccessibilityGuide.class);
//                    accessbility.putExtra(PermissionAccessibilityGuide.ACCESSIBLITY_TYPE, mPermissionType);
//                    startActivity(accessbility);
////                    StatRecorder.recordCustomEvent(StatConst.CUSTOM_EVENT_ACCESSIBILITY_GUIDE_CLICK);
//                    dialog.dismiss();
//                }
//            });
//        }
//        dialog.setContentView(contentView);
//        dialog.show();
//    }
//
//
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        TLog.d(TAG,"onResume");
//        if(WindowUtils.isShown) {
//            WindowUtils.hidePopupWindow();
//        }
//        setTextIcon();
//        hasFaildPermission = false;
//        mHardPermissionCodeList.setVisibility(View.VISIBLE);
//        mPermissionListScroll.setVisibility(View.GONE);
//        mPermissionListContent.removeAllViewsInLayout();
//        boolean allEnable = mStrategy.allPermissionEnable(mPermissionList);
//        if (mOptimizeView != null && mStartGuide) {
//
//            optimizeDone(allEnable);
//        }
//    }
//
//    private void setTextIcon(){
//        TextView tmp = (TextView) findViewById(R.id.list_icon_1);
//        tmp.setTypeface(TouchPalTypeface.ICON1);
//
//        tmp = (TextView) findViewById(R.id.list_icon_2);
//        tmp.setTypeface(TouchPalTypeface.ICON1);
//
//        tmp = (TextView) findViewById(R.id.list_icon_3);
//        tmp.setTypeface(TouchPalTypeface.ICON2);
//
//        tmp = (TextView) findViewById(R.id.list_icon_4);
//        tmp.setTypeface(TouchPalTypeface.ICON2);
//    }
//
//    private void initView() {
//        if(OSUtil.isMiuiV8()){
//            findViewById(R.id.permission_item_4).setVisibility(View.VISIBLE);
//        }
//        mOptimizeView = (TextView) findViewById(R.id.optimize);
//        mBannerView = findViewById(R.id.banner);
//        mBannerTips = (TextView) findViewById(R.id.banner_tips);
//        mHardPermissionCodeList = (ScrollView) findViewById(R.id.permission_list_content_scollhardcode);
//        mPermissionListContent = (LinearLayout) findViewById(R.id.permission_list_content);
//        mPermissionListScroll = (ScrollView) findViewById(R.id.permission_list_content_scoll);
//        mOptimizeView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TLog.e(TAG,"mPermissionList = "+mPermissionList);
//                if (mStrategy.allPermissionEnable(mPermissionList)) {
//
//                    finish();
//                } else {
//                    if(hasFaildPermission){
//                        return;
//                    }
//                    mStartGuide = true;
//                    Intent accessbility = new Intent(AccessibilityPermissionProcessActivity.this, PermissionAccessibilityGuide.class);
//                    accessbility.putExtra(PermissionAccessibilityGuide.ACCESSIBLITY_TYPE, mPermissionType);
//                    startActivity(accessbility);
////                    StatRecorder.recordCustomEvent(StatConst.CUSTOM_EVENT_ACCESSIBILITY_GUIDE_CLICK);
//                }
//            }
//        });
//
//        TextView funcbarBack = (TextView)  findViewById(R.id.funcbar_back);
//        if(INAPP.equals(getIntent().getStringExtra(FROM)) || CALLLOG.equals(getIntent().getStringExtra(FROM))){
//            funcbarBack.setVisibility(View.VISIBLE);
//        }
//        funcbarBack.setTypeface(TouchPalTypeface.ICON2);
//        funcbarBack.setText(TtfConst.ICON2_BACK_1);
//        funcbarBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
//        setTextIcon();
//        permissionSyle(false);
//
//        if (mPermissionType == IPermissionGuideStrategy.TUTORIAL_TYPE) {
//            mBannerTips.setText(R.string.permission_auto_setting_reason);
//            mBannerView.setBackgroundResource(R.color.light_blue_500);
//            mOptimizeView.setText(R.string.permission_auto_setting);
//            mOptimizeView.setBackgroundResource(R.drawable.permission_auto_optimize_bg);
//            ((TextView) findViewById(R.id.title)).setText(R.string.permission_auto_setting_title);
//            for(int i=0;i< mTextids.length;i++){
//                TextView tv = (TextView) findViewById(mTextids[i]);
//                tv.setVisibility(View.VISIBLE);
//                tv.setTextColor(SkinManager.getInst().getColor(R.color.black_transparency_300));
//            }
//
//        } else{
//            ((TextView) findViewById(R.id.title)).setText(R.string.permission_auto_title);
//            mBannerView.setBackgroundResource(R.color.red_500);
//            mBannerTips.setText(R.string.permission_auto_desc_reason);
//
//            TLog.e(TAG,"form = "+getIntent().getStringExtra(FROM));
//            mOptimizeView.setText(R.string.permission_auto_setting_failed);
//            if( UPGRADE.equals(getIntent().getStringExtra(FROM) )) {
//
//                mOptimizeView.setText(R.string.permission_auto_optimize);
//            }
//            mOptimizeView.setBackgroundResource(R.drawable.permission_auto_optimize_bg_orange);
//        }
//    }
//
//
//    private void permissionSyleFaildForMIUI8(){
//        mHardPermissionCodeList.setVisibility(View.GONE);
//        mPermissionListScroll.setVisibility(View.VISIBLE);
//        mPermissionListContent.removeAllViewsInLayout();
//        StringBuilder autoOpen = new StringBuilder();
//        StringBuilder openFaild = new StringBuilder();
//        if(!hasHandOpen){
//            for(String permission : MIUI8PermissionList){
//                boolean done = PrefUtil.getKeyBoolean(IPermissionGuideStrategy.PREF_KEY_DONE_PREFIX + permission, false);
//                if(done){
//                    autoOpen.append(" ").append(permission);
//                }else{
//                    openFaild.append(" ").append(permission);
//                }
//            }
//            HashMap map = new HashMap();
//            map.put("auto_open_success", autoOpen.toString());
//            map.put("auto_open_faild", openFaild.toString());
////            StatRecorder.recordCustomEvent(StatConst.CUSTOM_EVENT_ACCESSIBILITY_GUIDE_AUTO_OPEN,map);
//        }
//        for(String permission : MIUI8PermissionList) {
//            if(!mPermissionList.contains(permission)){
//                continue;
//            }
//            boolean done = PrefUtil.getKeyBoolean(IPermissionGuideStrategy.PREF_KEY_DONE_PREFIX + permission, false);
//            if(MIUI8TextMap.containsKey(permission)) {
//                PermissionListItemView listItem = new PermissionListItemView(this);
//                listItem.setStrategy(mStrategyHand);
//                listItem.setLeftItem(MIUI8TextMap.get(permission), MIUI8TextFaceMap.get(permission));
//                listItem.setTitle(getString(MIUI8TitleMap.get(permission)));
//                if (done) {
//                    listItem.openSuccess();
//                } else {
//                    listItem.handOpen(permission);
//                    hasHandOpen = true;
//                    hasFaildPermission = true;
//                }
//                mPermissionListContent.addView(listItem);
//            }
//        }
//        mBannerTips.setText(R.string.permission_auto_setting_reason_failed_todo);
//        mOptimizeView.setBackgroundResource(R.drawable.permission_auto_optimize_miui8_faild_bg);
//        mOptimizeView.setText(R.string.permission_auto_setting_success);
//    }
//
//    private void permissionSyle(boolean success) {
//        for (int i=0;i<mWarmingids.length;i++) {
//            TextView icon = (TextView) findViewById(mWarmingids[i]);
//            if (success) {
//                icon.setTypeface(TouchPalTypeface.ICON3);
//                icon.setText("n");
//                icon.setTextColor(SkinManager.getInst().getColor(R.color.green_500));
//                icon.setVisibility(View.VISIBLE);
//            } else {
//                icon.setVisibility(View.GONE);
//            }
//        }
//        for (int i=0;i < mTextids.length;i++) {
//            TextView tv = (TextView) findViewById(mTextids[i]);
//            tv.setVisibility(success ? View.INVISIBLE : View.VISIBLE);
//            if (success) {
//                tv.setTextColor(SkinManager.getInst().getColor(R.color.green_500));
//            } else {
//                tv.setTextColor(SkinManager.getInst().getColor(R.color.red_500));
//            }
//        }
//        if(mPermissionType == IPermissionGuideStrategy.TUTORIAL_TYPE) {
//            if (success) {
//                ((TextView) findViewById(R.id.list_title_1)).setText(R.string.permission_auto_list_setting_success_text1);
//                ((TextView) findViewById(R.id.list_title_2)).setText(R.string.permission_auto_list_setting_success_text2);
//                ((TextView) findViewById(R.id.list_title_3)).setText(R.string.permission_auto_list_setting_success_text3);
//                ((TextView) findViewById(R.id.list_title_4)).setText(R.string.permission_auto_list_setting_success_text4);
//            } else {
//                ((TextView) findViewById(R.id.list_title_1)).setText(R.string.permission_auto_list_setting_text1);
//                ((TextView) findViewById(R.id.list_title_2)).setText(R.string.permission_auto_list_setting_text2);
//                ((TextView) findViewById(R.id.list_title_3)).setText(R.string.permission_auto_list_setting_text3);
//                ((TextView) findViewById(R.id.list_title_4)).setText(R.string.permission_auto_list_setting_text4);
//            }
//        } else {
//            if (success) {
//                ((TextView) findViewById(R.id.list_title_1)).setText(R.string.permission_auto_list_setting_success_text1);
//                ((TextView) findViewById(R.id.list_title_2)).setText(R.string.permission_auto_list_setting_success_text2);
//                ((TextView) findViewById(R.id.list_title_3)).setText(R.string.permission_auto_list_setting_success_text3);
//                ((TextView) findViewById(R.id.list_title_4)).setText(R.string.permission_auto_list_setting_success_text4);
//            } else {
//                ((TextView) findViewById(R.id.list_title_1)).setText(R.string.permission_auto_list_fail_text1);
//                ((TextView) findViewById(R.id.list_title_2)).setText(R.string.permission_auto_list_fail_text2);
//                ((TextView) findViewById(R.id.list_title_3)).setText(R.string.permission_auto_list_fail_text3);
//                ((TextView) findViewById(R.id.list_title_4)).setText(R.string.permission_auto_list_fail_text4);
//            }
//        }
//
//        ((ImageView)findViewById(R.id.banner_icon)).setImageResource(success ?
//              R.drawable.permission_auto_banner_done : R.drawable.permission_auto_banner_optimize );
//        if(success){
//            mBannerView.setBackgroundResource(R.color.green_500);
//            mOptimizeView.setBackgroundResource(R.drawable.permission_auto_optimize_bg_done);
//            mBannerTips.setText(R.string.permission_auto_setting_reason_success);
//        }
////        mBannerView.setBackgroundResource(success ? R.color.green_500 : R.color.orange_400);
//        mOptimizeView.setSelected(success);
//    }
//
//    private void optimizeDone(boolean success) {
//        permissionSyle(success);
//        if (mPermissionType == IPermissionGuideStrategy.TUTORIAL_TYPE) {
//            if (success) {
//                if(OSUtil.isMiuiV8() && hasHandOpen){
////                    StatRecorder.recordCustomEvent(StatConst.CUSTOM_EVENT_ACCESSIBILITY_GUIDE_MIUI8_SUCCESS);
//                }
//                mBannerTips.setText(R.string.permission_auto_setting_reason_success);
//                ((TextView) findViewById(R.id.title)).setText(R.string.permission_auto_setting_title);
//                mOptimizeView.setText(success ? R.string.permission_auto_setting_success : R.string.permission_auto_setting_failed);
//            } else {
//                ((TextView) findViewById(R.id.title)).setText(R.string.permission_auto_title);
//                mBannerView.setBackgroundResource(R.color.red_500);
//                if(OSUtil.isMiuiV8()){
//                    permissionSyleFaildForMIUI8();
//                }else {
//                    mBannerTips.setText(R.string.permission_auto_setting_reason_failed);
//                    mOptimizeView.setBackgroundResource(R.drawable.permission_auto_optimize_bg_orange);
//                    mOptimizeView.setText(success ? R.string.permission_auto_setting_success : R.string.permission_auto_setting_failed);
//                }
//            }
//        } else{
////            mBannerTips.setVisibility(success ? View.INVISIBLE : View.VISIBLE);
//            mBannerTips.setText(R.string.permission_auto_setting_reason_success);
//            mOptimizeView.setText(success ? R.string.permission_auto_setting_success : R.string.permission_auto_setting_failed);
//            if (!success) {
//                if(OSUtil.isMiuiV8() && hasHandOpen){
////                    StatRecorder.recordCustomEvent(StatConst.CUSTOM_EVENT_ACCESSIBILITY_GUIDE_MIUI8_SUCCESS);
//                }
//                ((TextView) findViewById(R.id.title)).setText(R.string.permission_auto_title);
//                mBannerView.setBackgroundResource(R.color.red_500);
//                if(OSUtil.isMiuiV8()){
//                    permissionSyleFaildForMIUI8();
//                }else{
//                    mBannerTips.setText(R.string.permission_auto_banner_failed);
//                    mOptimizeView.setBackgroundResource(R.drawable.permission_auto_optimize_bg_orange);
//
//                }
//            } else {
//                ((TextView) findViewById(R.id.title)).setText(R.string.permission_auto_setting_title);
//            }
//        }
//
//        if(!success){
//            findViewById(R.id.funcbar_back).setVisibility(View.VISIBLE);
//        }
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        PrefUtil.setKey(PrefKeys.PERMISSION_SETTING_CLICK, true);
//        TLog.d(TAG,"onPause ");
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        PrefUtil.setKey(PrefKeys.UPGRADE_PERMISSION_GUIDE_SHOW_JUST_NOW, false);
//        TLog.d(TAG,"onDestroy");
//    }
//
//
//}
