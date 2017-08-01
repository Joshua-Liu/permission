package android.by.com.permission.permission;

import android.annotation.SuppressLint;
import android.by.com.permission.R;
import android.by.com.permission.base.TPBaseActivity;
import android.by.com.permission.constant.GuideConst;
import android.by.com.permission.constant.StatConst;
import android.by.com.permission.pref.PrefKeys;
import android.by.com.permission.util.DimentionUtil;
import android.by.com.permission.util.OSUtil;
import android.by.com.permission.util.PackageUtil;
import android.by.com.permission.util.PrefUtil;
import android.content.Intent;
import android.content.res.ColorStateList;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yimao on 17-2-15.
 */
public class PermissionGuideDialogActivity extends TPBaseActivity {

    public static final String EXTRA_HAS_QUIT_HINT = "EXTRA_HAS_QUIT_HINT";
    public static final String EXTRA_STAT_ACTIVITY_UV = "EXTRA_STAT_ACTIVITY_UV";
    public static final String EXTRA_STAT_CLICK_SINGLE_PERM_PV = "EXTRA_STAT_CLICK_SINGLE_PERM_PV";
    public static final String EXTRA_STAT_CLICK_ALL_PERM = "EXTRA_STAT_CLICK_ALL_PERM";
    public static final String EXTRA_STAT_CLICK_CLOSE = "EXTRA_STAT_CLICK_CLOSE";

    IPermissionGuideStrategy mPermissionGuideStrategy;
    private int mCurrentState = 0;
    private int mFinishState = 0;
    private ArrayList<View> buttons = new ArrayList<>();
    private ArrayList<String> mPermissions = new ArrayList<>();
    private ArrayList<String> mClickedButtion = new ArrayList<>();
    private int mEnabledIndex = -1;
    private int mSelectedIndex = -1;
    private int mColor = -1;
    private static final String CURRENT_STATE = "current_state";
    private boolean mHasRefreshState = false;
    private boolean mHasPause = false;
    private boolean mHasQuitHintBtn = false;
    private int mPermissionSize;
    private String mStatClickSinglePermValue;
    private String mStatClickAllPermValue;

    public static final String PERMISSION_LIST = "permission_list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPermissionGuideStrategy = PermissionGuideGenerator.generateGuideStratagy(this);
        mPermissions = mPermissionGuideStrategy.getPermissionList(IPermissionGuideStrategy.INAPP_SECOND_GUIDE_TYPE);
        mPermissionSize = mPermissions.size();
        mColor = mPermissionGuideStrategy.getColor();
        mHasQuitHintBtn = getIntent().getBooleanExtra(EXTRA_HAS_QUIT_HINT, false);

        RelativeLayout root = new RelativeLayout(this);
        root.setBackgroundColor(getResources().getColor(R.color.black_transparency_700));
        int paddingH = (int) getResources().getDimension(R.dimen.permission_guide_dialog_padding_h);
        root.setPadding(paddingH, 0, paddingH, 0);
        RelativeLayout.LayoutParams l = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        l.addRule(RelativeLayout.CENTER_IN_PARENT);
        root.addView(initView(), l);
        setContentView(root);

        String statTag = getIntent().getStringExtra(EXTRA_STAT_ACTIVITY_UV);
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(CURRENT_STATE, mCurrentState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void finish() {
        if (mCurrentState == mFinishState && mFinishState > 0) {
            if (!TextUtils.isEmpty(mStatClickAllPermValue)) {
//                StatRecorder.recordCustomEvent(mStatClickAllPermValue);
            }
        }
        recordData();
        PrefUtil.setKey(PrefKeys.UPGRADE_PERMISSION_GUIDE_SHOW_JUST_NOW, false);
        super.finish();
    }

    @SuppressLint("NewApi")
    private View initView() {
        boolean hasLongTitle = false;
        for (int index = 0; index < mPermissionSize; ++index) {
            String permission = mPermissions.get(index);
            String title = getResources().getString(IPermissionGuideStrategy.mainTextMap.get(permission));
            if (!TextUtils.isEmpty(title) && title.length() >= 10) {
                hasLongTitle = true;
                break;
            }
        }
        int margin = hasLongTitle ? DimentionUtil.getDimen(R.dimen.permission_guide_dialog_content_padding_h_2) : DimentionUtil.getDimen(R.dimen.permission_guide_dialog_content_padding_h);

        LinearLayout container = new LinearLayout(this);
        container.setOrientation(LinearLayout.VERTICAL);
        container.setBackgroundResource(R.drawable.permission_guide_diglog_bg);

        if (!mHasQuitHintBtn) {
            container.setPadding(0, 0, 0, margin);
        }

        RelativeLayout header = new RelativeLayout(this);
        TextView tv = new TextView(this);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, DimentionUtil.getTextSize(R.dimen.basic_text_size_3));
        tv.setTextColor(getResources().getColor(R.color.grey_900));
        tv.setText(getResources().getString(R.string.permission_settings_title));
        RelativeLayout.LayoutParams tvL = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        tvL.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        header.addView(tv, tvL);
        LinearLayout.LayoutParams headerL = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DimentionUtil.getDimen(R.dimen.permission_guide_dialog_header_height));
        headerL.leftMargin = margin;
        container.addView(header, headerL);

        TextView hint = new TextView(this);
        hint.setTextSize(TypedValue.COMPLEX_UNIT_PX, DimentionUtil.getTextSize(R.dimen.basic_text_size_5));
        hint.setTextColor(getResources().getColor(R.color.black_transparency_600));
        SpannableString ss = new SpannableString(getResources().getString(R.string.upgrade_permission_guide_hint_one));
        ss.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.light_blue_500)), 17, 21, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        hint.setText(ss);
        LinearLayout.LayoutParams hintP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        hintP.leftMargin = margin;
        hintP.topMargin = margin;
        hintP.rightMargin = margin;
        hintP.bottomMargin = margin;
        container.addView(hint, hintP);

        LinearLayout buttonContainer = new LinearLayout(this);
        buttonContainer.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams btnsL = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        btnsL.leftMargin = margin;
        btnsL.rightMargin = margin;
        container.addView(buttonContainer, btnsL);
        for (int index = 0; index < mPermissionSize; ++index) {
            String permission = mPermissions.get(index);
            View button = LayoutInflater.from(this).inflate(R.layout.permission_guide_dialog_button, null);
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
            if (index == 0) {
                LinearLayout iconContainer = (LinearLayout) button.findViewById(R.id.icon_container);
                ((GradientDrawable) iconContainer.getBackground()).setColor(mColor);
                indexTextView.setTextColor(getResources().getColor(R.color.light_blue_500));
                ((GradientDrawable) indexTextView.getBackground()).setStroke((int) getResources().getDimension(R.dimen.unit_dp), getResources().getColor(R.color.light_blue_500));
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
            LinearLayout.LayoutParams btParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DimentionUtil.getDimen(R.dimen.permission_guide_dialog_button_height));
            buttonContainer.addView(button, btParams);

            if (index < mPermissionSize - 1) {
                View line = new View(this);
                line.setBackgroundColor(getResources().getColor(R.color.black_transparency_100));
                LinearLayout.LayoutParams l = new LinearLayout.LayoutParams(
                        (int) getResources().getDimension(R.dimen.permission_list_line_width),
                        (int) getResources().getDimension(R.dimen.permission_guide_dialog_list_line_height)
                );
                l.leftMargin = (int) getResources().getDimension(R.dimen.permission_list_line_margin_left);
                buttonContainer.addView(line, l);
            }
        }

        if (mHasQuitHintBtn) {
            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setGravity(Gravity.CENTER);
            int rPaddingV = DimentionUtil.getDimen(R.dimen.permission_bottom_quit_hint_padding_v);
            row.setPadding(0, rPaddingV, 0, rPaddingV);

            TextView close = new TextView(this);
            close.setText(getResources().getString(R.string.upgrade_permission_guide_quit_text));
            close.setTextSize(TypedValue.COMPLEX_UNIT_PX, DimentionUtil.getTextSize(R.dimen.basic_text_size_7));
            close.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
            int[] colors = new int[2];
            int pressedState = android.R.attr.state_pressed;
            colors[0] = getResources().getColor(R.color.light_blue_500);
            colors[1] = getResources().getColor(R.color.light_blue_700);
            int[][] states = {{-pressedState},{pressedState}};
            close.setTextColor(new ColorStateList(states, colors));
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String stat = getIntent().getStringExtra(EXTRA_STAT_CLICK_CLOSE);
                    if (!TextUtils.isEmpty(stat)) {
//                        StatRecorder.recordCustomEvent(stat);
                    }
                    finish();
                }
            });
            row.addView(close, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            TextView closeHint = new TextView(this);
            closeHint.setText(getResources().getString(R.string.upgrade_permission_guide_quit_hint));
            closeHint.setTextColor(getResources().getColor(R.color.grey_500));
            closeHint.setTextSize(TypedValue.COMPLEX_UNIT_PX, DimentionUtil.getTextSize(R.dimen.basic_text_size_7));
            row.addView(closeHint, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            LinearLayout.LayoutParams rL = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            rL.topMargin = DimentionUtil.getDimen(R.dimen.permission_bottom_quit_hint_margin_top);
            rL.bottomMargin = DimentionUtil.getDimen(R.dimen.permission_bottom_quit_hint_margin_bottom);
            container.addView(row, rL);
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

            mClickedButtion.add(mPermissions.get(buttonIndex));

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
    };

    private void launchSpecificPermissionGuide(String permission) {
        Intent intent = new Intent(this, SpecificPermissionActivity.class);
        intent.putExtra(PERMISSION_LIST, new String[]{permission});
        startActivity(intent);
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
        indexTextView.setTextColor(getResources().getColor(R.color.light_blue_500));
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
        indexTextView.setTextColor(getResources().getColor(R.color.light_blue_500));
        ((GradientDrawable) indexTextView.getBackground()).setStroke((int) getResources().getDimension(R.dimen.unit_dp), getResources().getColor(R.color.light_blue_500));
    }

    private void recordData() {
        Map<String, Object> map = new HashMap<>();
        map.put(StatConst.PERMISSION_LIST_UPGRADE_CLICK_SUM, mPermissionSize);
        StringBuffer clickedButtionStr = new StringBuffer();
        for (int i=0; i<mClickedButtion.size(); i++) {
            clickedButtionStr.append(mClickedButtion.get(i));
            if (i != mClickedButtion.size() - 1) {
                clickedButtionStr.append("$");
            }
        }
        map.put(StatConst.PERMISSION_LIST_UPGRADE_CLICK_CONTENT, clickedButtionStr.toString());
        map.put(StatConst.PERMISSION_LIST_UPGRADE_CLICK_CLICKED_SUM, mClickedButtion.size());
//        StatRecorder.record(StatConst.PATH_PERMISSION_LIST_UPGRADE_CLICK, map);
    }

}
