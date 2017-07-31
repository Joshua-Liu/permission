package android.by.com.permission.util;

import android.util.TypedValue;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class LayoutParaUtil {
    
    public static LayoutParams fullPara() {
        return new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }
    
    public static LayoutParams wrapPara() {
        return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }
    
    public static LayoutParams wrapHorizontal() {
        return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
    }
    
    public static LayoutParams wrapVertical() {
        return new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    }
    
    public static void setTextSize(TextView view, int resId) {
        view.setTextSize(TypedValue.COMPLEX_UNIT_PX, DimentionUtil.getTextSize(resId));
    }
   
}
