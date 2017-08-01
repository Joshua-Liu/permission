package android.by.com.permission.permission;

import android.by.com.permission.R;
import android.by.com.permission.base.TPBaseActivity;
import android.by.com.permission.util.AnimationUtil;
import android.by.com.permission.util.OSUtil;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;


/**
 * Created by yuanchao on 16/1/15.
 */
public class MiuiPermissionGuide extends TPBaseActivity {

    private View mConfirm;
    private TextView mStepTitle;
    private TextView mStepSubtitle;
    private View mMenuHight;
    private View mMenuHightHintLayout;
    private View mAppIconLayout;
    private View mAppIconHand;
    private View mAppLock;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(OSUtil.isMiuiV5()) {
            setContentView(getLayoutInflater().inflate(R.layout.dlg_miui5_background_guide,null));
        } else {
            setContentView(getLayoutInflater().inflate( R.layout.dlg_miui_background_guide,null));
        }

        mConfirm = findViewById(R.id.confirm);
        mConfirm.setOnClickListener(mClickListener);

        mStepTitle = (TextView) findViewById(R.id.step_title);
        mStepSubtitle = (TextView)findViewById(R.id.step_subtitle);
        mMenuHight = findViewById(R.id.menu_hightlight);
        mMenuHightHintLayout = findViewById(R.id.step_one);

        mAppIconLayout = findViewById(R.id.app_icon_layout);
        mAppIconHand = findViewById(R.id.miui_app_hand);
        mAppLock = findViewById(R.id.miui_app_lock);

        mHandler = new Handler();
        mHandler.postDelayed(mFirstRunnable, 1000);
        mHandler.postDelayed(mSecondRunnable, 4000);

    }



    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.confirm:
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// 注意
                    intent.addCategory(Intent.CATEGORY_HOME);
                    startActivity(intent);
                    finish();
            }
        }
    };

    private Runnable mFirstRunnable = new Runnable() {
        @Override
        public void run() {
            mStepTitle.setVisibility(View.VISIBLE);
            mStepSubtitle.setVisibility(View.VISIBLE);
            mMenuHight.setVisibility(View.VISIBLE);
            mMenuHightHintLayout.setVisibility(View.VISIBLE);
            AlphaAnimation alphaShow = new AlphaAnimation(0, 1);
            alphaShow.setDuration(1000);
            alphaShow.setRepeatMode(Animation.REVERSE);
            alphaShow.setRepeatCount(1);
            alphaShow.setAnimationListener(new AnimationUtil.SimpleAnimationListener() {

                @Override
                public void onAnimationEnd(Animation animation) {
                    super.onAnimationEnd(animation);
                    mStepTitle.setVisibility(View.INVISIBLE);
                    mMenuHight.setVisibility(View.INVISIBLE);
                    mStepSubtitle.setVisibility(View.INVISIBLE);
                    mStepSubtitle.setText(R.string.miui_background_protect_step_two_hint);
                    mMenuHightHintLayout.setVisibility(View.INVISIBLE);
                }
            });
            mStepTitle.startAnimation(alphaShow);
            mMenuHight.startAnimation(alphaShow);
            mMenuHightHintLayout.startAnimation(alphaShow);
        }
    };

    private Runnable mSecondRunnable = new Runnable() {
        @Override
        public void run() {
            final TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0,Animation.RELATIVE_TO_SELF, 0,Animation.RELATIVE_TO_SELF, 0,Animation.RELATIVE_TO_SELF, 1);
            translateAnimation.setDuration(2000);
            translateAnimation.setRepeatMode(Animation.REVERSE);
            translateAnimation.setRepeatCount(1);
            translateAnimation.setAnimationListener(new AnimationUtil.SimpleAnimationListener() {
                @Override
                public void onAnimationEnd(Animation animation) {
                    super.onAnimationEnd(animation);
                    mAppLock.setVisibility(View.VISIBLE);
                    mConfirm.setVisibility(View.VISIBLE);
                }
            });

            mStepTitle.setVisibility(View.VISIBLE);
            mStepSubtitle.setVisibility(View.VISIBLE);
            mStepTitle.setText(R.string.miui_background_protect_step_two);
            AlphaAnimation alphaShow = new AlphaAnimation(0, 1);
            alphaShow.setDuration(1000);
            alphaShow.setAnimationListener(new AnimationUtil.SimpleAnimationListener() {

                @Override
                public void onAnimationEnd(Animation animation) {
                    super.onAnimationEnd(animation);
                    mAppIconHand.setVisibility(View.VISIBLE);
                    mAppIconLayout.setVisibility(View.VISIBLE);
                    mAppIconLayout.startAnimation(translateAnimation);
                    final TranslateAnimation translateAnimation1 = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1);
                    translateAnimation1.setDuration(2000);
                    translateAnimation1.setFillAfter(true);
                    mAppIconHand.startAnimation(translateAnimation1);
                }
            });
            mStepTitle.startAnimation(alphaShow);
            mStepSubtitle.startAnimation(alphaShow);
        }
    };
}
