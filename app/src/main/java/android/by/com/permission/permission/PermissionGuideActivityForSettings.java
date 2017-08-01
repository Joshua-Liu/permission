package android.by.com.permission.permission;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.by.com.permission.R;
import android.by.com.permission.layout.FuncBarSecondaryView;
import android.by.com.permission.util.DimentionUtil;
import android.by.com.permission.util.ResUtil;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
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

/**
 * Created by yimao on 16-9-26.
 * 设置界面有一个入口，打开PermissionGuideActivityForSettings;
 * 展示所有的基础权限 + 进阶权限，各个权限按钮均可点击，没有状态;
 */
public class PermissionGuideActivityForSettings extends Activity {

    private IPermissionGuideStrategy mPermissionGuideStrategy;
    private int mColor;
    private View display;
    private ArrayList<String> mPermissions = new ArrayList<>();
    private int mPermissionSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPermissionGuideStrategy = PermissionGuideGenerator.generateGuideStratagy(this);
        if (mPermissionGuideStrategy.supportGuide() == false) {
            finish();
        }

        mPermissions = mPermissionGuideStrategy.getPermissionList(IPermissionGuideStrategy.TUTORIAL_TYPE);
        ArrayList<String> secondPermissions = mPermissionGuideStrategy.getPermissionList(IPermissionGuideStrategy.SECOND_TYPE);
        for (String p : secondPermissions) {
            mPermissions.add(p);
        }
        mPermissionSize = mPermissions.size();
        mColor = mPermissionGuideStrategy.getColor();
        display = initView();
        setContentView(display);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //检查所有的权限是否都点击过，如果是，则需要清除inapp的权限引导(如果已经弹出过inapp的话);
        if (mPermissionGuideStrategy.supportSecondGuide() == false) {
            Log.i("ycsss", "all clicked, begin clear message");
            //by修改
//            InAppMessageManager.getInstance().clearCertainMessage(InAppMessageManager.MESSAGE_PERMISSION_GUIDE);
//            InAppMessageManager.getInstance().clearCertainMessage(InAppMessageManager.MESSAGE_PERMISSION_GUIDE_2);
        }
    }

    @SuppressLint("NewApi")
    private View initView() {
        RelativeLayout container = new RelativeLayout(this);
        container.setBackgroundResource(R.color.main_background);
        FuncBarSecondaryView header = new FuncBarSecondaryView(this);
        header.setTitleString(getResources().getString(R.string.permission_settings_title));
        RelativeLayout.LayoutParams headerParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) getResources().getDimension(R.dimen.funcbar_height));
        headerParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        container.addView(header, headerParams);
        header.setId(R.id.funcbar_secondary);
        header.findViewById(R.id.funcbar_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
        SpannableString ss = new SpannableString(getResources().getString(R.string.permission_guide_hint_one));
        if (ResUtil.isChineseSimpleEnv() || ResUtil.isChineseTraditionalEnv()) {
            ss.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.highlight_color)), 7, 11, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
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
            final String permission = mPermissions.get(index);
            View button = LayoutInflater.from(this).inflate(R.layout.permission_guide_button, null);
            button.setBackgroundDrawable(getResources().getDrawable(R.drawable.permission_guide_button_enabled));
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
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPermissionGuideStrategy.generateButtonFunction(permission);
                }
            });
            button.setSelected(false);
            button.setEnabled(true);
            LinearLayout iconContainer = (LinearLayout) button.findViewById(R.id.icon_container);
            ((GradientDrawable) iconContainer.getBackground()).setColor(mColor);
            indexTextView.setTextColor(getResources().getColor(R.color.highlight_color));
            ((GradientDrawable) indexTextView.getBackground()).setStroke((int) getResources().getDimension(R.dimen.unit_dp), getResources().getColor(R.color.highlight_color));
            icon.setText(getText(R.string.permission_guide_to_open));
            LinearLayout.LayoutParams btParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DimentionUtil.getDimen(R.dimen.button_height));
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
//                Intent intent = new Intent(PermissionGuideActivityForSettings.this, BrowserActivity.class);
//                intent.putExtra(BrowserActivity.EXTRA_TARGET_FORWARD_URL, getString(R.string.about_permissions_url));
//                intent.putExtra(BrowserActivity.EXTRA_TARGET_FORWARD_TITLE, getString(R.string.about_permissions_title));
//                intent.putExtra(BrowserActivity.EXTRA_DISABLE_QUICK_BACK, true);
//                intent.putExtra(BrowserActivity.EXTRA_ENABLE_REFRESH_PAGE_TITLE, true);
//                try {
//                    startActivity(intent);
//                } catch (ActivityNotFoundException e) {
//                    if (TLog.DBG) {
//                        TLog.printStackTrace(e);
//                    }
//                } finally {
//
//                }
            }
        });
        contentContainer.addView(explain, explainParams);
        return container;
    }

}
