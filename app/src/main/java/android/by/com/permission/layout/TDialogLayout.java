package android.by.com.permission.layout;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by WEI ZHENG on 15/11/12.
 */

public class TDialogLayout extends LinearLayout {
    private static final int CONTAINER_INDEX = 2;
    private int mBackgroundColor = 0;
    private ViewGroup mInnerLayout;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public TDialogLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initial(context);
    }

    public TDialogLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initial(context);
    }

    public TDialogLayout(Context context) {
        super(context);
        initial(context);
    }

    int mScreenHeight = 0;
    int mScreenWidth = 0;

    private void initial(Context context) {
        mScreenHeight = TDialogUtils.getFullScreenAppHeight(context);
        mScreenWidth = TDialogUtils.getFullScreenAppWidth(context);

        if (mScreenHeight == 0 || mScreenWidth == 0) {
            Log.e("TDialogLayout", "TDialogLayout can not get correct height or width");
        }
    }

    /**
     * Set content view dynamically.
     * @param contentView
     * @param params could be {@code null}
     */
    public void addContainer(View contentView, ViewGroup.LayoutParams params) {
        mInnerLayout = (ViewGroup)this.getChildAt(0);
        if (mInnerLayout.getChildCount() == 4) {
            mInnerLayout.removeViewAt(CONTAINER_INDEX);
            if (params == null) {
                mInnerLayout.addView(contentView, CONTAINER_INDEX);
            } else {
                mInnerLayout.addView(contentView, CONTAINER_INDEX, params);
            }
        } else if (mInnerLayout.getChildCount() == 3) {
            if (params == null) {
                mInnerLayout.addView(contentView, CONTAINER_INDEX);
            } else {
                mInnerLayout.addView(contentView, CONTAINER_INDEX, params);
            }
        } else {
            throw new IllegalArgumentException("Be sure this layout is bibi_dlg_standard_frame.xmle.xml or compatible components.");
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mInnerLayout = (ViewGroup)this.getChildAt(0);
        View container = null;
        try {
            int count = mInnerLayout.getChildCount();
            if (count == 4) {
                container = mInnerLayout.getChildAt(2);

                container.getLayoutParams().height = LayoutParams.WRAP_CONTENT;
            }
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Be sure this layout is bibi_dlg_standard_frame.xmle.xml or compatible components.");
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /*
        try {
            int count = mInnerLayout.getChildCount();
            if (count == 4) {
                View title = mInnerLayout.getChildAt(1);
                View bottom = mInnerLayout.getChildAt(3);

                int padding = mScreenWidth / 20;
                this.setPadding(padding, 0, padding, 0);

                int maxHeight = mScreenHeight * 17 / 20 - title.getMeasuredHeight() - bottom.getMeasuredHeight();
                if (container.getMeasuredHeight() > maxHeight) {
                    container.getLayoutParams().height = maxHeight;
                }
            } else if (count == 3) {
                int padding = mInnerLayout.getWidth() / 10;
                this.setPadding(padding, 0, padding, 0);
            } else {
                throw new IllegalArgumentException("Be sure this layout is bibi_dlg_standard_frame.xmle.xml or compatible components.");
            }
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Be sure this layout is bibi_dlg_standard_frameframe.xml or compatible components.");
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        */
    }


}

