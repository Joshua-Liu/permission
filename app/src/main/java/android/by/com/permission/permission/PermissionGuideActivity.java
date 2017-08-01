package android.by.com.permission.permission;

import android.annotation.SuppressLint;
import android.by.com.permission.R;
import android.by.com.permission.base.TPBaseActivity;
import android.by.com.permission.constant.GuideConst;
import android.by.com.permission.constant.StatConst;
import android.by.com.permission.layout.FuncBarSecondaryView;
import android.by.com.permission.layout.TDialog;
import android.by.com.permission.pref.PrefKeys;
import android.by.com.permission.util.DimentionUtil;
import android.by.com.permission.util.OSUtil;
import android.by.com.permission.util.PackageUtil;
import android.by.com.permission.util.PrefUtil;
import android.by.com.permission.util.ResUtil;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by frankyang on 12/3/15.
 */
public class PermissionGuideActivity extends TPBaseActivity {
    IPermissionGuideStrategy mPermissionGuideStrategy;
    private View display;
    private int mCurrentState = 0;
    private int mFinishState = 0;
    private ArrayList<View> buttons = new ArrayList<View>();
    private ArrayList<String> mPermissions = new ArrayList<>();
    private ArrayList<String> mClickedButtion = new ArrayList<>();
    private int mEnabledIndex = -1;
    private int mSelectedIndex = -1;
    private int mColor = -1;
    private static final String CURRENT_STATE = "current_state";
    private boolean mHasRefreshState = false;
    private boolean mHasPause = false;
    private int mPermissionType;
    private int mPermissionSize;
    private String mStatClickSinglePermValue;
    private String mStatClickAllPermValue;

    public static final String START_MAIN_SCREEN_WHEN_EXIT = "start_main_screen_when_exit";
    public static final String START_LOGIN_SCREEN_WHEN_EXIT = "start_login_guide_screen_when_exit";
    public static final String SHOW_OLD_CALL_GUIDE = "show_old_call_guide";
    public static final String SHOW_WALLET_GUIDE_WHEN_START_MAIN_SCREEN = "show_wallet_guide_when_start_main_screen";
    public static final String PERMISSION_LIST_TYPE = "permission_list_type";
    public static final String PERMISSION_LIST = "permission_list";
    public static final String FROM_INAPP_GUIDE_FIRST_TIME = "from_inapp_guide_first_time";
    public static final String FROM_INAPP_GUIDE_SECOND_TIME = "from_inapp_guide_second_time";
    public static final String EXTRA_TITLE = "EXTRA_TITLE";
    public static final String EXTRA_SUB_TITLE = "EXTRA_SUB_TITLE";
    public static final String EXTRA_SUB_TITLE_SPAN_LEFT = "EXTRA_SUB_TITLE_SPAN_LEFT";
    public static final String EXTRA_SUB_TITLE_SPAN_RIGHT = "EXTRA_SUB_TITLE_SPAN_RIGHT";
    public static final String EXTRA_HAS_BOTTOM_HINT = "EXTRA_HAS_BOTTOM_HINT";
    public static final String EXTRA_STAT_FORCE_QUIT = "EXTRA_STAT_FORCE_QUIT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPermissionGuideStrategy = PermissionGuideGenerator.generateGuideStratagy(this);
        if (mPermissionGuideStrategy.supportGuide() == false) {
            finish();
            return;
        }

        mPermissionType = getIntent().getIntExtra(PERMISSION_LIST_TYPE, IPermissionGuideStrategy.TUTORIAL_TYPE);
        mPermissions = mPermissionGuideStrategy.getPermissionList(mPermissionType);

        mPermissionSize = mPermissions.size();

        if (mPermissionType == IPermissionGuideStrategy.INAPP_TYPE && mPermissionSize == 1) {
            launchSpecificPermissionGuide(mPermissions.get(0));
            finish();
        } else {
//            StatRecorder.record(StatConst.PATH_PERMISSION_GUIDE, StatConst.PERMISSION_GUIDE_MODEL,
//                    Build.MANUFACTURER+"|"+Build.MODEL);
            mColor = mPermissionGuideStrategy.getColor();
            display = initView();
            setContentView(display);
        }

        String statTag = getIntent().getStringExtra(PermissionGuideDialogActivity.EXTRA_STAT_ACTIVITY_UV);
        if (!TextUtils.isEmpty(statTag)) {
//            StatRecorder.recordCustomEvent(statTag);
        }

        mStatClickSinglePermValue = getIntent().getStringExtra(PermissionGuideDialogActivity.EXTRA_STAT_CLICK_SINGLE_PERM_PV);
        mStatClickAllPermValue = getIntent().getStringExtra(PermissionGuideDialogActivity.EXTRA_STAT_CLICK_ALL_PERM);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!(mPermissionGuideStrategy instanceof DefaultStrategy) && mHasPause) {
            if (mSelectedIndex >= 0 && mSelectedIndex < mPermissionSize) {
                refreshSelectItemView();
            }
            if (mEnabledIndex < mPermissionSize && mEnabledIndex >= 0) {
                refreshEnabledItemView();
            }
            if (mCurrentState == mFinishState) {
                finish();
            }
            mHasRefreshState = true;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHasPause = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        mCurrentState = savedInstanceState.getInt(CURRENT_STATE);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (!(mPermissionGuideStrategy instanceof DefaultStrategy) && !mHasRefreshState) {
            if (mSelectedIndex >= 0 && mSelectedIndex < mPermissionSize) {
                refreshSelectItemView();
            }
            if (mEnabledIndex < mPermissionSize && mEnabledIndex >= 0) {
                refreshEnabledItemView();
            }
            if (mCurrentState == mFinishState) {
                finish();
            }
        }
    }

    private void refreshSelectItemView() {
        View button = buttons.get(mSelectedIndex);
        button.setSelected(true);
        LinearLayout iconContainer = (LinearLayout)button.findViewById(R.id.icon_container);
        ((GradientDrawable)iconContainer.getBackground()).setColor(getResources().getColor(R.color.white));
        TextView icon = (TextView)button.findViewById(R.id.icon);
        icon.setText(getResources().getString(R.string.permission_guide_to_reset));
        icon.setTextColor(mColor);
        ((TextView) button.findViewById(R.id.line1)).setTextColor(getResources().getColor(R.color.white));
        ((TextView) button.findViewById(R.id.line2)).setTextColor(getResources().getColor(R.color.white_transparency_600));
        TextView indexTextView = (TextView) button.findViewById(R.id.index);
        indexTextView.setTypeface(TouchPalTypeface.ICON2);
        indexTextView.setText("d");
        indexTextView.setTextColor(getResources().getColor(R.color.highlight_color));
        indexTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int) getResources().getDimension(R.dimen.permission_btn_left_icon_typefaced_textsize));
        ((GradientDrawable) indexTextView.getBackground()).setStroke((int) getResources().getDimension(R.dimen.unit_dp), getResources().getColor(R.color.white));
    }

    private void refreshEnabledItemView() {
        View button = buttons.get(mEnabledIndex);
        button.setEnabled(true);
        TextView icon = (TextView)button.findViewById(R.id.icon);
        icon.setText(getText(R.string.permission_guide_to_open));
        LinearLayout iconContainer = (LinearLayout)button.findViewById(R.id.icon_container);
        ((GradientDrawable)iconContainer.getBackground()).setColor(mColor);
        TextView indexTextView = (TextView) button.findViewById(R.id.index);
        indexTextView.setTextColor(getResources().getColor(R.color.highlight_color));
        ((GradientDrawable) indexTextView.getBackground()).setStroke((int) getResources().getDimension(R.dimen.unit_dp), getResources().getColor(R.color.light_blue_500));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(CURRENT_STATE, mCurrentState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        confirmExit();
    }

    @Override
    public void finish() {
        if (mCurrentState == mFinishState && mFinishState > 0) {
            if (!TextUtils.isEmpty(mStatClickAllPermValue)) {
//                StatRecorder.recordCustomEvent(mStatClickAllPermValue);
                mStatClickAllPermValue = null; //只记录一次;
            }
        }

        if (getIntent().getBooleanExtra(START_MAIN_SCREEN_WHEN_EXIT, false)) {
            //by修改
//            Intent intent = IntentUtil.getStartupIntentClearTop(this);
//            if (getIntent().getBooleanExtra(SHOW_WALLET_GUIDE_WHEN_START_MAIN_SCREEN, false)) {
//                intent.putExtra(TPDTabActivity.SHOW_WALLET_GUIDE, true);
//            }
//            StatRecorder.record(StatConst.PATH_PERMISSION_GUIDE, StatConst.PERMISSION_GUIDE_ENTER_MAIN, 1);
            // 行为序列：完成权限引导
//            StatRecorder.record(StatConst.PATH_USAGE_SEQUENCE, StatConst.KEY_USAGE_SEQUENCE, StatConst.ID_OF_PERMISSION_GUIDE);
//
//            startActivity(intent);
        }

        if (getIntent().getBooleanExtra(START_LOGIN_SCREEN_WHEN_EXIT, false)) {
            //by修改
//            String tag = TouchLifeController.getInst().getExperimentResult(TouchLifeController.EXPERIMENT_FREE_CALL_NEW_GUIDE);
//            if (TouchLifeController.FREE_CALL_NEW_GUIDE_SHOW.equals(tag)) {
//                Intent intent = new Intent(this, FreeCallGuide.class);
//                startActivity(intent);
//            } else if (TouchLifeController.FREE_CALL_NEW_GUIDE_NOT_SHOW.equals(tag)) {
//                if (getIntent().getBooleanExtra(SHOW_OLD_CALL_GUIDE, false)) {
//                    Intent intent = new Intent(this, LoginGuide.class);
//                    startActivity(intent);
//                }
//            }
        }
        PrefUtil.setKey(PrefKeys.HAS_SHOWN_PERMISSION_GUIDE, true);

        if (PrefUtil.getKeyBoolean(PrefKeys.PERMISSIONLIST_GUIDE_FIRST_SHOW, true)) {
            recordData();
        }

        PrefUtil.setKey(PrefKeys.PERMISSIONLIST_GUIDE_FIRST_SHOW, false);

        if (getIntent().getBooleanExtra(FROM_INAPP_GUIDE_SECOND_TIME, false)
                && mCurrentState == mFinishState) {
//            InAppMessageManager.getInstance().clearCertainMessage(InAppMessageManager.MESSAGE_PERMISSION_GUIDE_2);
        }

        if (mFinishState > 0) {
            String tag = (mCurrentState == mFinishState) ? "finish_click" : "not_finish_click";
//            StatRecorder.recordEvent(StatConst.PATH_PERMISSION_INAPP_GUIDE, tag);
        }
        super.finish();
    }


    @SuppressLint("NewApi")
    private View initView() {
        RelativeLayout container = new RelativeLayout(this);
        container.setBackgroundResource(R.color.main_background);
        FuncBarSecondaryView header = new FuncBarSecondaryView(this);
        String title = mPermissionGuideStrategy.getPermissionTitle("", mPermissionType);
        if (!TextUtils.isEmpty(getIntent().getStringExtra(EXTRA_TITLE))) {
            title = getIntent().getStringExtra(EXTRA_TITLE);
        }
        header.setTitleString(title);
        RelativeLayout.LayoutParams headerParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) getResources().getDimension(R.dimen.funcbar_height));
        headerParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        container.addView(header, headerParams);
        header.setId(R.id.funcbar_secondary);
        header.findViewById(R.id.funcbar_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmExit();
            }
        });

        ScrollView scrollView = new ScrollView(this);
        RelativeLayout.LayoutParams scrollParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        scrollParams.addRule(RelativeLayout.BELOW, R.id.funcbar_secondary);
        container.addView(scrollView, scrollParams);

        RelativeLayout contentContainer = new RelativeLayout(this);
        contentContainer.setPadding(0, 0, 0, DimentionUtil.getDimen(R.dimen.explain_margin_bottom));
        scrollView.addView(contentContainer);
        TextView hintOne = new TextView(this);
        hintOne.setId(R.id.hint_one);
        RelativeLayout.LayoutParams lineOneParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lineOneParams.addRule(RelativeLayout.BELOW, R.id.funcbar_secondary);
        lineOneParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        lineOneParams.topMargin = DimentionUtil.getDimen(R.dimen.guide_hintone_margin_top);
        lineOneParams.rightMargin = DimentionUtil.getDimen(R.dimen.guide_hintone_margin_left_right);
        lineOneParams.leftMargin = DimentionUtil.getDimen(R.dimen.guide_hintone_margin_left_right);
        hintOne.setTextSize(TypedValue.COMPLEX_UNIT_PX, DimentionUtil.getTextSize(R.dimen.permission_top_hint_textsize));
        hintOne.setTextColor(getResources().getColor(R.color.black_transparency_600));

        SpannableString ss;
        if (TextUtils.isEmpty(getIntent().getStringExtra(EXTRA_SUB_TITLE))) {
            ss = new SpannableString(getResources().getString(R.string.permission_guide_hint_one));
            if (ResUtil.isChineseSimpleEnv() || ResUtil.isChineseTraditionalEnv()) {
                ss.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.light_blue_500)), 7, 11, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            }
        } else {
            ss = new SpannableString(getIntent().getStringExtra(EXTRA_SUB_TITLE));
            if (ResUtil.isChineseSimpleEnv() || ResUtil.isChineseTraditionalEnv()) {
                int left = getIntent().getIntExtra(EXTRA_SUB_TITLE_SPAN_LEFT, -1);
                int right = getIntent().getIntExtra(EXTRA_SUB_TITLE_SPAN_RIGHT, -1);
                if (left > -1 && right > -1) {
                    ss.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.light_blue_500)), left, right, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                }
            }

        }
        hintOne.setText(ss);
        contentContainer.addView(hintOne, lineOneParams);
        LinearLayout buttonContainer = new LinearLayout(this);
        buttonContainer.setOrientation(LinearLayout.VERTICAL);
        RelativeLayout.LayoutParams buttonContainerParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        buttonContainerParams.addRule(RelativeLayout.BELOW, R.id.hint_one);
        buttonContainerParams.leftMargin = DimentionUtil.getDimen(R.dimen.button_container_margin_left_right);
        buttonContainerParams.rightMargin = DimentionUtil.getDimen(R.dimen.button_container_margin_left_right);
        buttonContainerParams.topMargin = DimentionUtil.getDimen(R.dimen.button_container_margin_top);
        contentContainer.addView(buttonContainer, buttonContainerParams);
        buttonContainer.setId(R.id.permission_guide_id);
        for (int index = 0; index < mPermissionSize; ++index) {
            String permission = mPermissions.get(index);
            View button = LayoutInflater.from(this).inflate(R.layout.permission_guide_button, null);
            if (Build.VERSION.SDK_INT > 15) {
                button.setBackground(getButtonBackground(mColor));
            } else {
                button.setBackgroundDrawable(getButtonBackground(mColor));
            }
            TextView indexTextView = (TextView) button.findViewById(R.id.index);
            TextView icon = (TextView) button.findViewById(R.id.icon);
            TextView tx1 = (TextView) button.findViewById(R.id.line1);
            indexTextView.setText(String.valueOf(index + 1));
            indexTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int) getResources().getDimension(R.dimen.permission_btn_left_icon_textsize));
            tx1.setText(IPermissionGuideStrategy.mainTextMap.get(permission));
            if (IPermissionGuideStrategy.SUB_TEXT_MAP.containsKey(permission)) {
                TextView tx2 = (TextView) button.findViewById(R.id.line2);
                tx2.setText(getResources().getString(IPermissionGuideStrategy.SUB_TEXT_MAP.get(permission)));
                tx2.setVisibility(View.VISIBLE);
            }
            button.setTag(permission);
            button.setOnClickListener(mClickListner);
            LinearLayout.LayoutParams btParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DimentionUtil.getDimen(R.dimen.button_height));
            if (index == 0) {
                LinearLayout iconContainer = (LinearLayout) button.findViewById(R.id.icon_container);
                ((GradientDrawable) iconContainer.getBackground()).setColor(mColor);
                indexTextView.setTextColor(getResources().getColor(R.color.highlight_color));
                ((GradientDrawable) indexTextView.getBackground()).setStroke((int) getResources().getDimension(R.dimen.unit_dp), getResources().getColor(R.color.highlight_color));
                icon.setText(getText(R.string.permission_guide_to_open));
                button.setSelected(false);
                button.setEnabled(true);
            } else {
                LinearLayout iconContainer = (LinearLayout) button.findViewById(R.id.icon_container);
                ((GradientDrawable) iconContainer.getBackground()).setColor(getResources().getColor(R.color.grey_350));
                indexTextView.setTextColor(getResources().getColor(R.color.grey_350));
                ((GradientDrawable) indexTextView.getBackground()).setStroke((int) getResources().getDimension(R.dimen.unit_dp), getResources().getColor(R.color.grey_350));
                icon.setText(getText(R.string.permission_guide_to_open));
                button.setSelected(false);
                button.setEnabled(false);
            }
            buttons.add(button);
            mFinishState |= IPermissionGuideStrategy.statusMap.get(permission);
            buttonContainer.addView(button, btParams);

            if (index < mPermissionSize - 1) {
                View line = new View(this);
                line.setBackgroundColor(getResources().getColor(R.color.black_transparency_100));
                LinearLayout.LayoutParams l = new LinearLayout.LayoutParams(
                        (int) getResources().getDimension(R.dimen.permission_list_line_width),
                        (int) getResources().getDimension(R.dimen.permission_list_line_height)
                );
                l.leftMargin = (int) getResources().getDimension(R.dimen.permission_list_line_margin_left);
                buttonContainer.addView(line, l);
            }
        }

        if (getIntent().getBooleanExtra(EXTRA_HAS_BOTTOM_HINT, true)) {
            TextView explain = new TextView(this);
            explain.setGravity(Gravity.CENTER);
            explain.setPaintFlags(explain.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            RelativeLayout.LayoutParams explainParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, DimentionUtil.getDimen(R.dimen.explain_height));
            explainParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            explainParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            explainParams.bottomMargin = DimentionUtil.getDimen(R.dimen.explain_margin_bottom);
            explainParams.topMargin = DimentionUtil.getDimen(R.dimen.explain_margin_top);
            explainParams.addRule(RelativeLayout.BELOW, buttonContainer.getId());
            explain.setTextColor(getResources().getColor(R.color.permission_guide_explain_text));
            explain.setText(getResources().getText(R.string.permission_guide_explain));
            explain.setTextSize(TypedValue.COMPLEX_UNIT_PX, DimentionUtil.getDimen(R.dimen.basic_text_size_6));
            explain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //by修改
//                    Intent intent = new Intent(PermissionGuideActivity.this, BrowserActivity.class);
//                    intent.putExtra(BrowserActivity.EXTRA_TARGET_FORWARD_URL, getString(R.string.about_permissions_url));
//                    intent.putExtra(BrowserActivity.EXTRA_TARGET_FORWARD_TITLE, getString(R.string.about_permissions_title));
//                    intent.putExtra(BrowserActivity.EXTRA_DISABLE_QUICK_BACK, true);
//                    intent.putExtra(BrowserActivity.EXTRA_ENABLE_REFRESH_PAGE_TITLE, true);
//                    try {
//                        startActivity(intent);
//                    } catch (ActivityNotFoundException e) {
//                        if (TLog.DBG) {
//                            TLog.printStackTrace(e);
//                        }
//                    } finally {

//                    }
                }
            });
            contentContainer.addView(explain, explainParams);
        }
        return container;
    }

    View.OnClickListener mClickListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String tag = (String) v.getTag();
            if (!TextUtils.isEmpty(mStatClickSinglePermValue)) {
//                StatRecorder.recordCustomEvent(mStatClickSinglePermValue);
            }

            if (OSUtil.isMiuiV6() || OSUtil.isMiuiV7() || OSUtil.isMiuiV5() || OSUtil.isMiuiV8()
                    || PackageUtil.isPackageInstalled(PackageUtil.HUAWEI_PHONE_MANAGER)
                    || PackageUtil.isPackageInstalled(PackageUtil.OPPO_2_0_PERMISSION_SETTING_PACKAGE_NAMES)
                    || PackageUtil.isPackageInstalled(PackageUtil.OPPO_PERMISSION_SETTING_PACKAGE_NAMES)
                    || PackageUtil.isPackageInstalled(PackageUtil.OPPO_COLOROS_PERMISSION_SETTING_PACKAGE_NAMES)
                    || PackageUtil.isPackageInstalled(PackageUtil.MEIZU_PHONE_MANAGER)
                    || PackageUtil.isPackageInstalled(PackageUtil.VIVO_PHONE_MANAGER)) {
                mPermissionGuideStrategy.generateButtonFunction(tag);
            } else {
                launchSpecificPermissionGuide(tag);
            }

            mCurrentState |= IPermissionGuideStrategy.statusMap.get(tag);
            int buttonIndex = mPermissions.indexOf(tag);
//            StatRecorder.record(StatConst.PATH_PERMISSION_GUIDE, StatConst.PERMISSION_GUIDE_CLICK_INDEX, buttonIndex);

            if (PrefUtil.getKeyBoolean(PrefKeys.PERMISSIONLIST_GUIDE_FIRST_SHOW, true)) {
                mClickedButtion.add(mPermissions.get(buttonIndex));
            }

            if (buttonIndex > mSelectedIndex) {
                mSelectedIndex = buttonIndex;
                if (buttonIndex < buttons.size() - 1) {
                    mEnabledIndex = buttonIndex + 1;
                }
            }
            if (mCurrentState == mFinishState) {
                if (mFinishState == GuideConst.DATA_PERMISSION_DONE) {
                    finish();
                }
            }

        }
    };

    private void launchSpecificPermissionGuide(String permission) {
        Intent intent = new Intent(this, SpecificPermissionActivity.class);
        intent.putExtra(PERMISSION_LIST, new String[]{permission});
        startActivity(intent);
    }

    private void confirmExit() {
        if (mCurrentState != mFinishState) {
            final TDialog dialog = TDialog.getDefaultDialog(this, TDialog.STYLE_BUTTON_TWO, R.string.permission_guide_exit_confirm_title, R.string.permission_guide_exit_confirm_content)
                    .setNegativeBtnText(R.string.permission_guide_exit_confirm_cancel_button)
                    .setPositiveBtnText(R.string.permission_guide_exit_confirm_confirm_buttong)
                    .setCancelOnTouchOutside(false);
            dialog.setOnNegativeBtnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    StatRecorder.record(StatConst.PATH_PERMISSION_GUIDE,
//                            StatConst.PERMISSION_GUIDE_CLICK_INDEX, -1);
                    dialog.dismiss();
                    mPermissionGuideStrategy.actionDataPermission();
                    String statForceQuitValue = getIntent().getStringExtra(EXTRA_STAT_FORCE_QUIT);
                    if (!TextUtils.isEmpty(statForceQuitValue)) {
//                        StatRecorder.recordCustomEvent(statForceQuitValue);
                    }
                    finish();
                }
            });
            dialog.setOnPositiveBtnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        } else {
            finish();
        }
    }

    private Drawable getButtonBackground(int color) {
        GradientDrawable normal = (GradientDrawable) getResources().getDrawable(R.drawable.permission_guide_button);
        GradientDrawable enabled = (GradientDrawable) getResources().getDrawable(R.drawable.permission_guide_button_enabled);
        GradientDrawable clicked = (GradientDrawable) getResources().getDrawable(R.drawable.permission_guide_button_clicked);
        enabled.setStroke(DimentionUtil.getDimen(R.dimen.button_stroke_width), color);
        clicked.setStroke(DimentionUtil.getDimen(R.dimen.button_stroke_width), color);
        clicked.setColor(color);
        StateListDrawable sl = new StateListDrawable();
        sl.addState(new int[]{-android.R.attr.state_enabled, -android.R.attr.state_selected }, normal);
        sl.addState(new int[]{android.R.attr.state_enabled, -android.R.attr.state_selected }, enabled);
        sl.addState(new int[]{android.R.attr.state_selected,android.R.attr.state_enabled }, clicked);
        return sl;
    }

    private void recordData() {
        Map<String, Object> map = new HashMap<>();
        map.put(StatConst.PERMISSION_LIST_CLICK_SUM, mPermissionSize);
        StringBuffer clickedButtionStr = new StringBuffer();
        for (int i=0; i<mClickedButtion.size(); i++) {
            clickedButtionStr.append(mClickedButtion.get(i));
            if (i != mClickedButtion.size() - 1) {
                clickedButtionStr.append("$");
            }
        }
        map.put(StatConst.PERMISSION_LIST_CLICK_CONTENT, clickedButtionStr.toString());
        map.put(StatConst.PERMISSION_LIST_CLICK_CLICKED_SUM, mClickedButtion.size());
//        StatRecorder.record(StatConst.PATH_PERMISSION_LIST_CLICK, map);
    }
}
