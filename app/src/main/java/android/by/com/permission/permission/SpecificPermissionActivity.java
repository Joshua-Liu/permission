package android.by.com.permission.permission;

import android.by.com.permission.R;
import android.by.com.permission.base.TPBaseActivity;
import android.by.com.permission.constant.StatConst;
import android.by.com.permission.constant.TtfConst;
import android.by.com.permission.layout.AspectRatioImageView;
import android.by.com.permission.layout.FuncBarSecondaryView;
import android.by.com.permission.layout.TDialog;
import android.by.com.permission.pref.PrefKeys;
import android.by.com.permission.util.PrefUtil;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by herculewang on 16/4/19.
 */
public class SpecificPermissionActivity extends TPBaseActivity {

    IPermissionGuideStrategy mPermissionGuideStrategy;
    private int mPermissionType;
    private List<String> mPermissionList;
    private boolean mGoToSettingClicked;
    private int mPermissionGroupCount, mCurrentPermissionGroup;
    private List<List<View>> mDetailGroup;
    private TextView[] mArrowIcons;
    private ScrollView mScrollView;
    private int mClickedMaxIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPermissionType = getIntent().getIntExtra(PermissionGuideActivity.PERMISSION_LIST_TYPE,
                IPermissionGuideStrategy.NONE_TYPE);
        if (mPermissionType == IPermissionGuideStrategy.START_UP_TYPE) {
//            StatRecorder.record(StatConst.PATH_USAGE_SEQUENCE,StatConst.KEY_USAGE_SEQUENCE,StatConst.ID_OF_APPEAR_PERMISSION);
        }
        String[] permissionList = getIntent().getStringArrayExtra(PermissionGuideActivity.PERMISSION_LIST);
        if (permissionList != null) {
            mPermissionList = Arrays.asList(permissionList);
        }
        if (!shouldShowPermissionGuide()) {
            finish();
        } else {
            mPermissionGuideStrategy = PermissionGuideGenerator.generateGuideStratagy(this);
            setContentView(initView());
            Map<String, Object> statMap = new HashMap<String, Object>();
            statMap.put(StatConst.PERMISSION_GUIDE_PERMISSION_TYPE, mPermissionType);
            List<String> permissions = mPermissionList != null ? mPermissionList : mPermissionGuideStrategy.getPermissionList(mPermissionType);
            statMap.put(StatConst.PERMISSION_GUIDE_PERMISSION_LIST, Arrays.toString(permissions.toArray()));
//            StatRecorder.record(StatConst.PATH_PERMISSION_GUIDE, statMap);
        }
    }

    private boolean shouldShowPermissionGuide() {
        if (mPermissionType == IPermissionGuideStrategy.NONE_TYPE && mPermissionList == null)
            return false;
        if (mPermissionType == IPermissionGuideStrategy.START_UP_TYPE) {
            return PermissionGuideUtil.shouldShowStartUpGuide();
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mGoToSettingClicked
                && (mPermissionType == IPermissionGuideStrategy.START_UP_TYPE
                        || (mPermissionList != null && mPermissionList.size() == 1))) {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        confirmExit();
    }

    @Override
    public void finish() {
        if (getIntent().getBooleanExtra(PermissionGuideActivity.START_MAIN_SCREEN_WHEN_EXIT, false)) {
//by修改
//            Intent intent = IntentUtil.getStartupIntentClearTop(this);
//            if (getIntent().getBooleanExtra(PermissionGuideActivity.SHOW_WALLET_GUIDE_WHEN_START_MAIN_SCREEN, false)) {
//                intent.putExtra(TPDTabActivity.SHOW_WALLET_GUIDE, true);
//            }
//            intent.putExtra(TPDTabActivity.FISRT_SHOW_GUIDE, true);
            // 行为序列：完成权限引导
//            StatRecorder.record(StatConst.PATH_USAGE_SEQUENCE, StatConst.KEY_USAGE_SEQUENCE, StatConst.ID_OF_PERMISSION_GUIDE);
//            startActivity(intent);
        }
        if (mPermissionType == IPermissionGuideStrategy.START_UP_TYPE) {
            PrefUtil.setKey(PrefKeys.HAS_SHOWN_PERMISSION_GUIDE, true);
        }

        if (shouldShowPermissionGuide()) {
            Map<String, Object> statMap = new HashMap<String, Object>();
            statMap.put(StatConst.PERMISSION_GUIDE_CLICK_INDEX, mClickedMaxIndex);
            statMap.put(StatConst.PERMISSION_GUIDE_PERMISSION_TYPE, mPermissionType);
            List<String> permissionList = mPermissionList != null ? mPermissionList : mPermissionGuideStrategy.getPermissionList(mPermissionType);
            statMap.put(StatConst.PERMISSION_GUIDE_PERMISSION_LIST, Arrays.toString(permissionList.toArray()));
//            StatRecorder.record(StatConst.PATH_PERMISSION_GUIDE, statMap);
        }

        super.finish();
    }

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Object tag = v.getTag();
            if (tag instanceof String) {
                mPermissionGuideStrategy.generateButtonFunction((String) tag);
                mGoToSettingClicked = true;
                mClickedMaxIndex |= 1 << mCurrentPermissionGroup;
                if (mCurrentPermissionGroup < mPermissionGroupCount - 1) {
                    updateGroupView(mCurrentPermissionGroup + 1);
                }
            } else if (tag instanceof Integer) {
                int groupIndex = (Integer) tag;
                updateGroupView(groupIndex);
            }
        }
    };

    private View initView() {
        RelativeLayout container = new RelativeLayout(this);
        container.setBackgroundColor(getResources().getColor(R.color.grey_150));
        FuncBarSecondaryView header = new FuncBarSecondaryView(this);
        String title = mPermissionGuideStrategy.getPermissionTitle(
                mPermissionList != null && mPermissionList.size() > 0 ? mPermissionList.get(0) : "", mPermissionType);
        header.setTitleString(title);
        RelativeLayout.LayoutParams headerParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int)getResources().getDimension(R.dimen.funcbar_height));
        headerParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        container.addView(header, headerParams);
        header.setId(R.id.funcbar_secondary);
        header.findViewById(R.id.funcbar_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmExit();
            }
        });

        mScrollView = new ScrollView(this);
        RelativeLayout.LayoutParams scrollParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        scrollParams.addRule(RelativeLayout.BELOW, R.id.funcbar_secondary);
        container.addView(mScrollView, scrollParams);

        LinearLayout contentContainer = new LinearLayout(this);
        contentContainer.setOrientation(LinearLayout.VERTICAL);
        mScrollView.addView(contentContainer, new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        List<String> permissionList = mPermissionList != null ? mPermissionList : mPermissionGuideStrategy.getPermissionList(mPermissionType);
        int mainColor = mPermissionGuideStrategy.getColor();
        int pressedColor = mPermissionGuideStrategy.getPressedColor();
        float density = getResources().getDisplayMetrics().density;
        mPermissionGroupCount = calculatePermissionGroupCount(permissionList);
        mDetailGroup = new ArrayList<List<View>>(mPermissionGroupCount);
        mArrowIcons = new TextView[mPermissionGroupCount];
        int currentGroup = 0;
        if (permissionList != null) {
            for (String permission : permissionList) {
                PermissionGuideStepItem stepItem = mPermissionGuideStrategy.getPermissionGuideStepItem(permission, mPermissionType);
                View specificPermissionContainer = LayoutInflater.from(this).inflate(R.layout.specific_permission, null);

                LinearLayout detailContainer = (LinearLayout) specificPermissionContainer.findViewById(R.id.detail_container);
                if (mPermissionGroupCount > 1) {
                    detailContainer.setVisibility(View.GONE);
                }
                for (int i = stepItem.stepTitleRes.length - 1; i >= 0; i--) {
                    LinearLayout stepContainer = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.specific_permission_step_item, null);

                    View indicator = stepContainer.findViewById(R.id.indicator);
                    indicator.setBackgroundColor(mainColor);

                    TextView stepTitle = (TextView) stepContainer.findViewById(R.id.step_title);
                    stepTitle.setText(stepItem.stepTitleRes[i]);

                    int[] imageResources = stepItem.stepImageRes[i];
                    if (imageResources != null) {
                        for (int res : imageResources) {
                            AspectRatioImageView stepImage = new AspectRatioImageView(this);
                            stepImage.setImageResource(res);
                            stepImage.setScaleType(ImageView.ScaleType.FIT_XY);
                            LinearLayout.LayoutParams imageLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            imageLp.topMargin = (int) (12 * density);
                            stepContainer.addView(stepImage, imageLp);
                        }
                    }

                    LinearLayout.LayoutParams stepContainerLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    stepContainerLp.bottomMargin = (int) (12 * density);
                    detailContainer.addView(stepContainer, 0, stepContainerLp);
                }

                View titleContainer = specificPermissionContainer.findViewById(R.id.title_container);
                if (stepItem.titleRes > 0) {
                    TextView titleIcon = (TextView) specificPermissionContainer.findViewById(R.id.title_icon);
                    titleIcon.setText(TtfConst.ICON3_INFO);
                    titleIcon.setTextColor(mainColor);
                    titleIcon.setTypeface(TouchPalTypeface.ICON3);

                    TextView arrowIcon = (TextView) specificPermissionContainer.findViewById(R.id.arrow_icon);
                    arrowIcon.setTypeface(TouchPalTypeface.ICON2);
                    mArrowIcons[currentGroup] = arrowIcon;

                    TextView titleText = (TextView) specificPermissionContainer.findViewById(R.id.title);
                    titleText.setText(stepItem.titleRes);
                    RelativeLayout.LayoutParams titleTextLp = (RelativeLayout.LayoutParams) titleText.getLayoutParams();
                    titleTextLp.rightMargin = (int) (arrowIcon.getVisibility() == View.VISIBLE ? 6 * density : 16 * density);

                    List<View> detailGroup = new ArrayList<View>();
                    detailGroup.add(detailContainer);
                    mDetailGroup.add(detailGroup);

                    titleContainer.setOnClickListener(mClickListener);
                    titleContainer.setTag(currentGroup);

                    currentGroup++;
                } else {
                    titleContainer.setVisibility(View.GONE);
                    if (mPermissionGroupCount <= 1) {
                        LinearLayout.LayoutParams detailLp = (LinearLayout.LayoutParams) detailContainer.getLayoutParams();
                        detailLp.topMargin = (int) (16 * density);
                    }
                    if (mPermissionGroupCount > 1) {
                        mDetailGroup.get(currentGroup - 1).add(detailContainer);
                    }
                }

                View goToSetting = specificPermissionContainer.findViewById(R.id.go_to_setting);
                if (stepItem.showActionButton) {
                    goToSetting.setBackgroundDrawable(generateButtonBackground(mainColor, pressedColor));
                    goToSetting.setTag(permission);
                    goToSetting.setOnClickListener(mClickListener);
                } else {
                    goToSetting.setVisibility(View.GONE);
                }

                contentContainer.addView(specificPermissionContainer);

                if (mPermissionGroupCount > 1 && currentGroup < mPermissionGroupCount) {
                    View divider = new View(this);
                    divider.setBackgroundColor(getResources().getColor(R.color.black_transparency_150));
                    LinearLayout.LayoutParams dividerLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
                    contentContainer.addView(divider, dividerLp);
                }
            }
        }
        updateGroupView(0);

        return container;
    }

    private void updateGroupView(int clickedGroup) {
        if (mPermissionGroupCount <= 1) return;

        mCurrentPermissionGroup = clickedGroup;
        for (int i = 0; i < mPermissionGroupCount; i++) {
            TextView arrowIcon = mArrowIcons[i];
            arrowIcon.setVisibility(View.VISIBLE);
            List<View> detailContainers = mDetailGroup.get(i);
            if (i == clickedGroup) {
                boolean currentlyVisible = detailContainers.get(0).getVisibility() == View.VISIBLE;
                for (View detailContainer : detailContainers) {
                    detailContainer.setVisibility(currentlyVisible ? View.GONE : View.VISIBLE);
                }
                arrowIcon.setText(currentlyVisible ? TtfConst.ICON2_EXPAND_MORE : TtfConst.ICON2_EXPAND_LESS);
                arrowIcon.setTextColor(currentlyVisible
                        ? getResources().getColor(R.color.black_transparency_600) : mPermissionGuideStrategy.getColor());
            } else {
                for (View detailContainer : detailContainers) {
                    detailContainer.setVisibility(View.GONE);
                }
                arrowIcon.setText(TtfConst.ICON2_EXPAND_MORE);
                arrowIcon.setTextColor(getResources().getColor(R.color.black_transparency_600));
            }
        }
        mScrollView.post(new Runnable() {
            @Override
            public void run() {
                mScrollView.scrollTo(0 ,0);
            }
        });
    }

    private int calculatePermissionGroupCount(List<String> permissionList) {
        int groupCount = 0;
        if (permissionList != null) {
            for (String permission : permissionList) {
                PermissionGuideStepItem stepItem = mPermissionGuideStrategy.getPermissionGuideStepItem(permission, mPermissionType);
                if (stepItem != null && stepItem.titleRes > 0) {
                    groupCount++;
                }
            }
        }

        return groupCount;
    }

    private void confirmExit() {
        if (mPermissionType == IPermissionGuideStrategy.START_UP_TYPE) {
            final TDialog dialog = new TDialog(this, TDialog.STYLE_BUTTON_ZERO, false);
            View content = LayoutInflater.from(this).inflate(R.layout.dlg_permission_guide_exit_confirm, null);
            dialog.setContentView(content);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setTitle(R.string.permission_guide_exit_confirm_title);
            Button cancel = (Button)content.findViewById(R.id.cancel);
            Button confirm = (Button)content.findViewById(R.id.confirm);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    mPermissionGuideStrategy.actionDataPermission();
//                    StatRecorder.record(StatConst.PATH_USAGE_SEQUENCE,StatConst.KEY_USAGE_SEQUENCE,StatConst.ID_OF_SKIP_PERMISSION);
                    finish();
                }
            });
            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                }
            });
            dialog.show();
        } else {
            finish();
        }
    }

    private Drawable generateButtonBackground(int normalColor, int pressedColor) {
        GradientDrawable normal = new GradientDrawable();
        normal.setColor(normalColor);
        GradientDrawable pressed = new GradientDrawable();
        pressed.setColor(pressedColor);
        StateListDrawable sl = new StateListDrawable();
        sl.addState(new int[]{android.R.attr.state_pressed}, pressed);
        sl.addState(new int[]{},normal);
        return sl;
    }
}
