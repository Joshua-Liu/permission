package android.by.com.permission.permission;

import android.by.com.permission.R;
import android.by.com.permission.base.TPBaseActivity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by yimao on 25/8/16.
 */
public class OuterAnimationGuideActivity extends TPBaseActivity {

    public static final String GUIDE_TEXT_STEP_1 = "guide_text_step_1";
    public static final String GUIDE_TEXT_STEP_2 = "guide_text_step_2";
    public static final String GUIDE_TEXT_STEP_3 = "guide_text_step_3";

    public static final String GUIDE_ROW_MARGIN_BOTTOM_ONE = "guide_row_margin_bottom_row_1";
    public static final String GUIDE_ROW_MARGIN_TOP_ONE = "guide_row_margin_top_row_1";
    public static final String GUIDE_ROW_BUTTON_INSTEAD_FULLSCREEN = "guide_row_button_instead_fullscreen";

    private Timer timer;
//    private TimerTask task;

    private ImageView frameAnim ;
    private TextView textView;
    private String text1,text2,text3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        text1 = intent.getStringExtra(GUIDE_TEXT_STEP_1);
        text2 = intent.getStringExtra(GUIDE_TEXT_STEP_2);
        text3 = intent.getStringExtra(GUIDE_TEXT_STEP_3);


        boolean rowButton = intent.getBooleanExtra(GUIDE_ROW_BUTTON_INSTEAD_FULLSCREEN,false);
        setContentView(R.layout.animation_guide_acitivity);

        textView =(TextView)findViewById(R.id.permission_text);




        frameAnim = (ImageView) findViewById(R.id.frame_image);

        if(rowButton){
            View btnView = findViewById(R.id.button_view);
            btnView.setVisibility(View.VISIBLE);
            findViewById(R.id.permission_two_step_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }else {
            findViewById(R.id.permission_layout_root).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }
    private final Handler mHandler = new Handler();

    private void setText1Back1 (){

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                AnimationDrawable anim = (AnimationDrawable) frameAnim.getBackground();
                textView.setText(text2);
                anim.start();
                setText2Anim1Start();
            }
        },1500);
    }

    private void setText2Anim1Start () {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                frameAnim.setBackgroundResource(R.drawable.outer_animation_guide_step2);
                AnimationDrawable anim = (AnimationDrawable) frameAnim.getBackground();
                textView.setText(text3);
                anim.start();
                setText3Anim2Start();
            }
        },2000);
    }

    private void setText3Anim2Start () {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                frameAnim.setBackgroundResource(R.drawable.outer_animation_guide_step1);
                textView.setText(text1);
                setText1Back1();
            }
        },2500);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);

        frameAnim.setBackgroundResource(R.drawable.outer_animation_guide_step1);

        textView.setText(text1);

        setText1Back1();



    }

}
