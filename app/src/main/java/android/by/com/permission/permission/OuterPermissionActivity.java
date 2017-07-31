package android.by.com.permission.permission;

import android.by.com.permission.R;
import android.by.com.permission.base.TPBaseActivity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * Created by frankyang on 12/10/15.
 */
public class OuterPermissionActivity extends TPBaseActivity {
    public static final String VIEWSTUB_ID = "viewstub_id";
    public static final String GUIDEPIC_ID = "guidepic_id";
    public static final String GUIDE_HINTONE = "guide_hintone";
    public static final String GUIDE_HINTTWO = "guide_hinttwo";
    public static final String GUIDE_NO_BTN = "no_btn";
    public static final String GUIDE_INDICATOR_RIGHT_MARGIN = "indicator_right_margin";
    public static final String HUAWEI_GENERAL_GUIDE_HINTONE = "huawei_general_guide_hintone";
    public static final String HUAWEI_GENERAL_GUIDE_HINTTWO = "huawei_general_guide_hinttwo";
    public static final String HUAWEI_GENERAL_GUIDE_ALT = "huawei_general_guide_alt";
    public static final String HUAWEI_GENERAL_GUIDE_VERSION = "huawei_general_guide_version";
    public static final int GENERAL_PERMISSION = R.layout.general_permission_guide_mask;
    public static final int COOLPAD_PERMISSION_GENEAL_GUIDE_ID = R.layout.scr_coolpad_application_permission_guide;
    public static final int HUAWEI_PERMISSION_GENERAL_GUIDE = R.layout.huawei_permission_general_guide;


    private int layoutId = -1;
    private int guidePicId = -1;
    private boolean mNoBottomBtn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutId = getIntent().getIntExtra(VIEWSTUB_ID, -1);

        if (layoutId != -1) {
            setContentView(R.layout.permission_layout);
            ViewStub viewStub = (ViewStub) findViewById(R.id.stub_import);
            viewStub.setLayoutResource(layoutId);
            viewStub.inflate();
            adjustLayout(layoutId);
            if (mNoBottomBtn) {
                findViewById(R.id.btn_setup).setVisibility(View.GONE);
                findViewById(R.id.permission_layout_root).setOnClickListener(mGeneralListener);
            } else {
                findViewById(R.id.btn_setup).setOnClickListener(mGeneralListener);
            }
        }
    }


    private void adjustLayout(int layoutId) {
        switch (layoutId) {
            case GENERAL_PERMISSION:
                guidePicId = getIntent().getIntExtra(GUIDEPIC_ID, -1);
                if (guidePicId > 0) {
                    Drawable guidePic = getResources().getDrawable(guidePicId);
                    if (guidePic == null) {
                        return;
                    }
                    ImageView guidePicView = ((ImageView) findViewById(R.id.guide_pic));
//                    DisplayMetrics metrics = getResources().getDisplayMetrics();
//                    int picWidth = (int) (metrics.widthPixels - 12 * metrics.density);
//                    int picHeight = picWidth * guidePic.getIntrinsicHeight() / guidePic.getIntrinsicWidth();
//                    guidePicView.getLayoutParams().height = picHeight;
//                    guidePicView.getLayoutParams().width = picWidth;
//                    guidePicView.setImageDrawable(guidePic);
                    guidePicView.setImageDrawable(guidePic);
                }
                String hintOneMiui = getIntent().getStringExtra(GUIDE_HINTONE);
                String hintTwoMiuiSource = getIntent().getStringExtra(GUIDE_HINTTWO);
                CharSequence hintTwoMiui = Html.fromHtml(hintTwoMiuiSource);
                TextView hintOneView = (TextView)findViewById(R.id.miui_line_one);
                hintOneView.setText(hintOneMiui);
                TextView hintTwoView = (TextView)findViewById(R.id.miui_line_two);
                hintTwoView.setText(hintTwoMiui);
                int indicatorRightMargin = getIntent().getIntExtra(GUIDE_INDICATOR_RIGHT_MARGIN, -1);
                if (indicatorRightMargin >= 0) {
                    View indicator = findViewById(R.id.indicator);
                    RelativeLayout.LayoutParams indicatorLp = (RelativeLayout.LayoutParams) indicator.getLayoutParams();
                    indicatorLp.rightMargin = indicatorRightMargin;
                }
                break;
            case COOLPAD_PERMISSION_GENEAL_GUIDE_ID:
                TextView guideAlt = (TextView)findViewById(R.id.coolpad_guide_alt);
                Spanned alt = Html.fromHtml(getResources().getString(R.string.coolpad_alt_msg));
                guideAlt.setText(alt);
                break;
            case HUAWEI_PERMISSION_GENERAL_GUIDE:
                String hintOneHuawei = getIntent().getStringExtra(HUAWEI_GENERAL_GUIDE_HINTONE);
                String hintTwoHuawei = getIntent().getStringExtra(HUAWEI_GENERAL_GUIDE_HINTTWO);
                String altHuawei = getIntent().getStringExtra(HUAWEI_GENERAL_GUIDE_ALT);
                TextView lineOneView = (TextView) findViewById(R.id.line_one);
                lineOneView.setText(hintOneHuawei);
                TextView lineTwoView = (TextView) findViewById(R.id.line_two);
                lineTwoView.setText(hintTwoHuawei);
                TextView guideAltHuawei = (TextView) findViewById(R.id.huawei_guide_alt);
                guideAltHuawei.setText(altHuawei);
                int version = getIntent().getIntExtra(HUAWEI_GENERAL_GUIDE_VERSION, -1);
                if (version == 1) {
                    findViewById(R.id.huawei_guide_switch_v1).setVisibility(View.VISIBLE);
                } else if (version == 2 || version == 3 || version == 4 || version == 5) {
                    findViewById(R.id.huawei_guide_switch_v2).setVisibility(View.VISIBLE);
                }
        }
    }


    private View.OnClickListener mGeneralListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.permission_layout_root:
                case R.id.btn_setup:
                    finish();
                    break;
            }
        }
    };
}
