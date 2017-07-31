package android.by.com.permission.layout;
import android.annotation.SuppressLint;
import android.by.com.permission.R;
import android.by.com.permission.model.ModelManager;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;


public class CircleView extends ImageView {

    Path path;
    public PaintFlagsDrawFilter mPaintFlagsDrawFilter;// 毛边过滤
    Paint paint;
    Paint pressPaint;
    int mColor = Color.TRANSPARENT;
    int mPressedColor = getResources().getColor(R.color.black_transparency_100);
    int maxShadowPadding = 0;

    public CircleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleView(Context context) {
        super(context);
        init();
    }
    @SuppressLint("NewApi")
    public void init(){
        mPaintFlagsDrawFilter = new PaintFlagsDrawFilter(0,
                Paint.ANTI_ALIAS_FLAG| Paint.FILTER_BITMAP_FLAG);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setColor(mColor);
        pressPaint = new Paint();
        pressPaint.setAntiAlias(true);
        pressPaint.setFilterBitmap(true);
        pressPaint.setColor(mPressedColor);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

    }

    @Override
    protected void onDraw(Canvas cns) {
        float h = getMeasuredHeight();
        float w = getMeasuredWidth();
        if (path == null) {
            path = new Path();
            path.addCircle(
                    w/2.0f
                    , h/2.0f
                    , (float) Math.min(w/2.0f, (h / 2.0) - 2)
                    , Path.Direction.CCW);
            path.close();
        }
        cns.setDrawFilter(mPaintFlagsDrawFilter);
        cns.drawCircle(w/2.0f, h/2.0f,  Math.min(w/2.0f, h / 2.0f) - 2 - maxShadowPadding * 2, paint);
        if (isPressed() || isSelected()) {
            cns.drawCircle(w/2.0f, h/2.0f,  Math.min(w/2.0f, h / 2.0f) - 2 - maxShadowPadding * 2, pressPaint);
        }
         int saveCount = cns.getSaveCount();
        cns.save();
        super.onDraw(cns);
        cns.restoreToCount(saveCount);
    }

    public void addShadow(float dx, float dy, float radius, int shadowColor) {
        this.setLayerType(LAYER_TYPE_SOFTWARE, paint);
        paint.setShadowLayer(radius, dx, dy, shadowColor);
        pressPaint.setShadowLayer(radius, dx, dy, shadowColor);
        maxShadowPadding = (int) Math.max(dx, dy);
    }
    
    public void setColor(int color) {
        mColor = color;
        paint.setColor(color);
        invalidate();
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

}