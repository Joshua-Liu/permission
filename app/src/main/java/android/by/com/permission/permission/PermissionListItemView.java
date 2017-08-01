package android.by.com.permission.permission;

import android.by.com.permission.R;
import android.by.com.permission.util.PrefUtil;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.HashMap;

/**
 * Created by lisi on 17/6/20.
 */

public class PermissionListItemView extends LinearLayout {

    private TextView mLeftIcon;
    private TextView mTitle;
    private TextView mSuccessIcon, mFaildIcon, mHandOpenIcon;
    private IPermissionGuideStrategy mStrategy;
    public PermissionListItemView(Context context) {
        super(context);
        init(context);
    }

    public PermissionListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public PermissionListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PermissionListItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }


    void init(Context context){
        inflate(context, R.layout.permission_list_item, this);
        mLeftIcon = (TextView) findViewById(R.id.list_icon);
        mTitle = (TextView) findViewById(R.id.list_title);
        mSuccessIcon = (TextView) findViewById(R.id.list_waring);
        mFaildIcon = (TextView) findViewById(R.id.list_text);
        mHandOpenIcon = (TextView) findViewById(R.id.list_handopen);
        mSuccessIcon.setTypeface(TouchPalTypeface.ICON3);
        mSuccessIcon.setText("n");
        mSuccessIcon.setTextColor(getResources().getColor(R.color.green_500));

    }

    void setStrategy(IPermissionGuideStrategy strategy){
        mStrategy =strategy;
    }

    void setLeftItem(String text, Typeface typeface){
        mLeftIcon.setText(text);
        mLeftIcon.setTypeface(typeface);
    }

    void setTitle (String title){
        mTitle.setText(title);
    }

    void openSuccess(){
        mSuccessIcon.setVisibility(VISIBLE);
        mFaildIcon.setVisibility(GONE);
        mHandOpenIcon.setVisibility(GONE);
    }

    void openFaild(){
        mSuccessIcon.setVisibility(GONE);
        mFaildIcon.setVisibility(VISIBLE);
        mHandOpenIcon.setVisibility(GONE);
    }

    void handOpen(final String permission){
        mSuccessIcon.setVisibility(GONE);
        mFaildIcon.setVisibility(GONE);
        mHandOpenIcon.setVisibility(VISIBLE);
        mHandOpenIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                PrefUtil.setKey(IPermissionGuideStrategy.PREF_KEY_DONE_PREFIX + permission, true);
                HashMap map = new HashMap();
                map.put("permission",permission);
//                StatRecorder.recordCustomEvent(StatConst.CUSTOM_EVENT_ACCESSIBILITY_GUIDE_HAND_OPEN,map);
                mStrategy.generateButtonFunction(permission);
            }
        });

    }
}
