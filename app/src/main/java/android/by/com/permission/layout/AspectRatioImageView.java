package android.by.com.permission.layout;

import android.by.com.permission.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;



public class AspectRatioImageView extends ImageView {
    
    private boolean mIsSquare;
    private Paint mPressPaint;
    private boolean mShouldPaintPressedState;
    
    public AspectRatioImageView(Context context) {
        super(context);
        init();
    }

    public AspectRatioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AspectRatioImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }
    
    public void setIsSquare(boolean isSquare) {
        mIsSquare = isSquare;
    }

    public void setShouldPaintPressState(boolean paintPressState) {
        mShouldPaintPressedState = paintPressState;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Drawable drw = getDrawable();
        if (null == drw || drw.getIntrinsicWidth() <= 0) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } else {
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = mIsSquare ? width : width * drw.getIntrinsicHeight() / drw.getIntrinsicWidth();
            setMeasuredDimension(width, height);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float h = getMeasuredHeight();
        float w = getMeasuredWidth();
        if ((isPressed() || isSelected()) && mShouldPaintPressedState) {
            canvas.drawRect(0, 0, w, h, mPressPaint);
        }
    }

    @Override
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);
        invalidate();
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        invalidate();
    }

    private void init() {
        mPressPaint = new Paint();
        mPressPaint.setAntiAlias(true);
        mPressPaint.setFilterBitmap(true);
        mPressPaint.setColor(getResources().getColor(R.color.black_transparency_100));
    }
    
}
