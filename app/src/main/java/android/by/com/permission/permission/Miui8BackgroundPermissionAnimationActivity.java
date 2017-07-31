package android.by.com.permission.permission;

import android.app.Activity;
import android.by.com.permission.R;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * Created by yimao on 16-9-20.
 */
public class Miui8BackgroundPermissionAnimationActivity extends Activity {

    private static final long BTN_RIPPLE_DURATION = 800; //圆形波纹

    private TextView mStepText;
    private ImageView mGestureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.src_miui8_background_permission_animation);
        initViewMember();
        initStep1();
    }

    private void initViewMember() {
        mStepText = (TextView) findViewById(R.id.text);
        mGestureView = (ImageView) findViewById(R.id.gesture);
        findViewById(R.id.replay).setOnClickListener(mClickListener);
        findViewById(R.id.end).setOnClickListener(mClickListener);
    }

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.end:
                    finish();
                    break;

                case R.id.replay:
                    break;
            }
        }
    };

    private void initStep1() {
        mStepText.setText(getResources().getString(R.string.miui8_background_permission_text_step_1));

        mGestureView.setVisibility(View.VISIBLE);
        RelativeLayout.LayoutParams l = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        l.addRule(RelativeLayout.ABOVE, R.id.text);
        l.bottomMargin = -((int) getResources().getDimension(R.dimen.bpa_gesture_margin_bottom_step_1));
        l.leftMargin = (int) getResources().getDimension(R.dimen.bpa_gesture_margin_left_step_1);
        mGestureView.setLayoutParams(l);
        mGestureView.setAnimation(getGestureAnimation(mGestureAnimationStep1Listener));
    }

    private Animation.AnimationListener mGestureAnimationStep1Listener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            mGestureView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mGestureView.setVisibility(View.GONE);
                }
            }, BTN_RIPPLE_DURATION);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

    private TranslateAnimation getGestureAnimation(Animation.AnimationListener listener) {
        int d = (int) getResources().getDimension(R.dimen.bpa_gesture_translate_distance_step_1);
        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, d);
        animation.setDuration(600);
        animation.setRepeatCount(2);
        if (listener != null) {
            animation.setAnimationListener(listener);
        }
        return animation;
    }
}
