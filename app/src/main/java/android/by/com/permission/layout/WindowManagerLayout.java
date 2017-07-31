package android.by.com.permission.layout;

import android.content.Context;
import android.view.KeyEvent;
import android.widget.LinearLayout;


public class WindowManagerLayout extends LinearLayout {
    public WindowManagerLayout(Context context) {
        super(context);
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (onKeyListener != null) {
                onKeyListener.onKey(this, KeyEvent.KEYCODE_BACK, event);
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    OnKeyListener onKeyListener = null;

    @Override
    public void setOnKeyListener(OnKeyListener l) {
        this.onKeyListener = l;
    }
}
