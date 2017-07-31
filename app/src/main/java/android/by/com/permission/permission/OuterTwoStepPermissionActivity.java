package android.by.com.permission.permission;

import android.by.com.permission.R;
import android.by.com.permission.base.TPBaseActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;



/**
 * Created by yimao on 25/8/16.
 */
public class OuterTwoStepPermissionActivity extends TPBaseActivity {

    public static final String GUIDE_TEXT_ROW_ONE = "guide_text_row_1";
    public static final String GUIDE_TEXT_ROW_TWO = "guide_text_row_2";
    public static final String GUIDE_TEXT_ROW_THREE = "guide_text_row_3";
    public static final String GUIDE_IMG_ROW_ONE = "guide_img_row_1";
    public static final String GUIDE_IMG_ROW_ONE_TWO = "guide_img_row_1_2";
    public static final String GUIDE_IMG_ROW_TWO = "guide_img_row_2";
    public static final String GUIDE_IMG_ROW_TWO_TWO = "guide_img_row_2_2";
    public static final String GUIDE_IMG_ROW_THREE = "guide_img_row_3";
    public static final String GUIDE_NEED_GESTURE_ONE = "guide_gesture_row_1";
    public static final String GUIDE_NEED_GESTURE_TWO = "guide_gesture_row_2";
    public static final String GUIDE_NEED_GESTURE_THREE = "guide_gesture_row_3";
    public static final String GUIDE_NEED_MARGIN_BOTTOM_ONE = "guide_margin_bottom_1";
    public static final String GUIDE_NEED_MARGIN_BOTTOM_TWO = "guide_margin_bottom_2";
    public static final String GUIDE_NEED_GESTURE_MARGIN_LEFT_ONE = "guide_gesture_maring_left_row_1";
    public static final String GUIDE_NEED_GESTURE_MARGIN_LEFT_TWO = "guide_gesture_maring_left_row_2";
    public static final String GUIDE_NEED_GESTURE_MARGIN_LEFT_THREE = "guide_gesture_maring_left_row_3";
    public static final String GUIDE_ROW_MARGIN_BOTTOM_ONE = "guide_row_margin_bottom_row_1";
    public static final String GUIDE_ROW_MARGIN_TOP_ONE = "guide_row_margin_top_row_1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String text1 = intent.getStringExtra(GUIDE_TEXT_ROW_ONE);
        String text2 = intent.getStringExtra(GUIDE_TEXT_ROW_TWO);
        String text3 = intent.getStringExtra(GUIDE_TEXT_ROW_THREE);
        int img1 = intent.getIntExtra(GUIDE_IMG_ROW_ONE, -1);
        int img1_2 = intent.getIntExtra(GUIDE_IMG_ROW_ONE_TWO, -1);
        int img2 = intent.getIntExtra(GUIDE_IMG_ROW_TWO, -1);
        int img2_2 = intent.getIntExtra(GUIDE_IMG_ROW_TWO_TWO, -1);
        int img3 = intent.getIntExtra(GUIDE_IMG_ROW_THREE, -1);
        boolean guideGesture1 = intent.getBooleanExtra(GUIDE_NEED_GESTURE_ONE, false);
        boolean guideGesture2 = intent.getBooleanExtra(GUIDE_NEED_GESTURE_TWO, false);
        boolean guideGesture3 = intent.getBooleanExtra(GUIDE_NEED_GESTURE_THREE, false);
        int rowMarginBottom1 = intent.getIntExtra(GUIDE_ROW_MARGIN_BOTTOM_ONE, -1);
        int rowMarginTop1 = intent.getIntExtra(GUIDE_ROW_MARGIN_TOP_ONE, -1);
        int guideMarginBottom1 = intent.getIntExtra(GUIDE_NEED_MARGIN_BOTTOM_ONE,-1);
        int guideMarginBottom2 = intent.getIntExtra(GUIDE_NEED_MARGIN_BOTTOM_TWO,-1);
        setContentView(R.layout.permission_two_step_layout);

        View row1 = findViewById(R.id.permission_row1);
        if (!TextUtils.isEmpty(text1)) {
            ((TextView) row1.findViewById(R.id.permission_text)).setText(text1);
        }
        if (img1 != -1) {
            ((ImageView) row1.findViewById(R.id.permission_img)).setImageDrawable(getResources().getDrawable(img1));
        }
        if (img1_2 != -1) {
            ((ImageView) row1.findViewById(R.id.permission_img_two)).setImageDrawable(getResources().getDrawable(img1_2));
        }
        if (guideGesture1) {
            row1.findViewById(R.id.gesture_img).setVisibility(View.VISIBLE);
            int marginLeftId = intent.getIntExtra(GUIDE_NEED_GESTURE_MARGIN_LEFT_ONE, -1);
            if (marginLeftId != -1) {
                LinearLayout.LayoutParams l = (LinearLayout.LayoutParams) row1.findViewById(R.id.gesture_img).getLayoutParams();
                l.leftMargin = (int) getResources().getDimension(marginLeftId);
                row1.findViewById(R.id.gesture_img).setLayoutParams(l);
            }

            ConstraintLayout.LayoutParams rowL = (ConstraintLayout.LayoutParams) row1.getLayoutParams();
            rowL.bottomMargin = (int) getResources().getDimension(R.dimen.permission_img_margin_bottom_when_has_gesture);
            row1.setLayoutParams(rowL);
        }
        if (rowMarginBottom1 > -1) {
            ConstraintLayout.LayoutParams rowL = (ConstraintLayout.LayoutParams) row1.getLayoutParams();
            rowL.bottomMargin = (int) getResources().getDimension(rowMarginBottom1);
            rowL.topMargin = (int) getResources().getDimension(R.dimen.permission_row_1_margin_top_when_assigned_margin_bottom);
            row1.setLayoutParams(rowL);
        }
        if (rowMarginTop1 > -1) {
            ConstraintLayout.LayoutParams rowL = (ConstraintLayout.LayoutParams) row1.getLayoutParams();
            rowL.topMargin = (int) getResources().getDimension(rowMarginTop1);
            row1.setLayoutParams(rowL);
        }

        View row2 = findViewById(R.id.permission_row2);
        if (!TextUtils.isEmpty(text2)) {
            ((TextView) row2.findViewById(R.id.permission_text)).setText(text2);
        } else if (guideMarginBottom1 != -1){
            ((ConstraintLayout.LayoutParams)row2.getLayoutParams()).bottomMargin = guideMarginBottom1;
        }
        if (img2 != -1) {
            ((ImageView) row2.findViewById(R.id.permission_img)).setImageDrawable(getResources().getDrawable(img2));
        }
        if (img2_2 != -1) {
            ((ImageView) row2.findViewById(R.id.permission_img_two)).setImageDrawable(getResources().getDrawable(img2_2));
        }
        if (guideGesture2) {
            row2.findViewById(R.id.gesture_img).setVisibility(View.VISIBLE);
            int marginLeftId = intent.getIntExtra(GUIDE_NEED_GESTURE_MARGIN_LEFT_TWO, -1);
            if (marginLeftId != -1) {
                LinearLayout.LayoutParams l = (LinearLayout.LayoutParams) row2.findViewById(R.id.gesture_img).getLayoutParams();
                l.leftMargin = (int) getResources().getDimension(marginLeftId);
                row2.findViewById(R.id.gesture_img).setLayoutParams(l);
            }
        }

        View row3 = findViewById(R.id.permission_row3);
        if (row3 != null) {
            if (!TextUtils.isEmpty(text3)) {
                ((TextView) row3.findViewById(R.id.permission_text)).setText(text3);
            } else if (guideMarginBottom2 != -1){
                ((ConstraintLayout.LayoutParams)row2.getLayoutParams()).bottomMargin = guideMarginBottom2;
            }
            if (img3 != -1) {
                ((ImageView) row3.findViewById(R.id.permission_img)).setImageDrawable(getResources().getDrawable(img3));
            }
        }
        if (guideGesture3) {
            row3.findViewById(R.id.gesture_img).setVisibility(View.VISIBLE);
            int marginLeftId = intent.getIntExtra(GUIDE_NEED_GESTURE_MARGIN_LEFT_THREE, -1);
            if (marginLeftId != -1) {
                LinearLayout.LayoutParams l = (LinearLayout.LayoutParams) row3.findViewById(R.id.gesture_img).getLayoutParams();
                l.leftMargin = (int) getResources().getDimension(marginLeftId);
                row3.findViewById(R.id.gesture_img).setLayoutParams(l);
            }
        }

        View btnView = findViewById(R.id.button_view);
        btnView.setVisibility(View.VISIBLE);

        findViewById(R.id.permission_two_step_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
