package android.by.com.permission.util;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.by.com.permission.model.ModelManager;
import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;




public class AnimationUtil {

    public static final int TYPE_FADE_OUT = 0;
    public static final int TYPE_FADE_IN = 1;

    public static final int FILL_NONE = 0;
    public static final int FILL_BEFORE = 1;
    public static final int FILL_AFTER = 2;
    public static final int FILL_BOTH = 3;

    public static Animation createExpandAnimation(View view, int duration) {
        return new ExpandAnimation(view, duration);
    }

    public static Animation createCollapseAnimation(View view, int duration) {
        return new CollapseAnimation(view, duration);
    }

    private static class ExpandAnimation extends Animation {
        private View mView;
        private int mEndHeight;

        /**
         * @param view
         *            The view to animate
         * @param duration
         */
        public ExpandAnimation(View view, int duration) {
            setDuration(duration);
            mView = view;
            mEndHeight = mView.getLayoutParams().height;
            mView.getLayoutParams().height = 0;
            mView.setVisibility(View.GONE);
        }

        @Override
        protected void applyTransformation(float interpolatedTime,
                Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            if (mView.getVisibility() == View.GONE) {
                mView.setVisibility(View.VISIBLE);
            }
            if (interpolatedTime < 1.0f) {
                mView.getLayoutParams().height = (int) (mEndHeight * interpolatedTime);
                mView.requestLayout();
            } else {
                mView.getLayoutParams().height = mEndHeight;
                mView.requestLayout();
            }
        }
    }

    private static class CollapseAnimation extends Animation {
        private View mView;
        private int mEndHeight;

        /**
         * @param view
         *            The view to animate
         * @param duration
         */
        public CollapseAnimation(View view, int duration) {
            setDuration(duration);
            mView = view;
            mEndHeight = mView.getLayoutParams().height;
        }

        @Override
        protected void applyTransformation(float interpolatedTime,
                Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            if (interpolatedTime < 1.0f) {
                mView.getLayoutParams().height = mEndHeight
                        - (int) (mEndHeight * interpolatedTime);
                mView.requestLayout();
            } else {
                mView.getLayoutParams().height = 0;
                mView.setVisibility(View.GONE);
                mView.requestLayout();
            }
        }
    }
    
    private static class Rotate3DAnimation extends Animation {
        private final float mFromDegrees;
        private final float mToDegrees;
        private final float mCenterX;
        private final float mCenterY;
        private final float mDepthZ;
        private final boolean mReverse;
        private Camera mCamera;

        public Rotate3DAnimation(float fromDegrees, float toDegrees, float centerX,
                float centerY, float depthZ, boolean reverse) {
            mFromDegrees = fromDegrees;
            mToDegrees = toDegrees;
            mCenterX = centerX;
            mCenterY = centerY;
            mDepthZ = depthZ;
            mReverse = reverse;
        }

        @Override
        public void initialize(int width, int height, int parentWidth,
                int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
            mCamera = new Camera();
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            final float fromDegrees = mFromDegrees;
            float degrees = fromDegrees
                    + ((mToDegrees - fromDegrees) * interpolatedTime);

            final float centerX = mCenterX;
            final float centerY = mCenterY;
            final Camera camera = mCamera;
            final Matrix matrix = t.getMatrix();

            camera.save();
            if (mReverse) {
                camera.translate(0.0f, 0.0f, mDepthZ * interpolatedTime);
            } else {
                camera.translate(0.0f, 0.0f, mDepthZ * (1.0f - interpolatedTime));
            }
            camera.rotateX(degrees);
            camera.getMatrix(matrix);
            camera.restore();

            matrix.preTranslate(-centerX, -centerY);
            matrix.postTranslate(centerX, centerY);
            mCamera.save(); 
        }
    }
    
    public static AnimationSet getAppUpdateAnimation(View view) {
        view.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        WindowManager wm = (WindowManager) ModelManager.getContext().getSystemService(
                Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        AnimationSet set = new AnimationSet(true);
        final Rotate3DAnimation rotation = new Rotate3DAnimation(0, 20,
                view.getMeasuredWidth() / 2, view.getMeasuredHeight(), 0f, true);
        rotation.setDuration(400);
        rotation.setInterpolator(new AccelerateInterpolator());
        rotation.setFillAfter(true);
        RotateAnimation rotate = new RotateAnimation(0, -10, view.getMeasuredWidth(), view.getMeasuredHeight());
        rotate.setDuration(100);
        rotate.setStartOffset(400);
        ScaleAnimation scale = new ScaleAnimation(1f,0.1f,1f,0.3f, Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0f);
        scale.setStartOffset(450);
        scale.setDuration(250);
        TranslateAnimation translate = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF,
                -1f,
                Animation.RELATIVE_TO_SELF, 0f, Animation.ABSOLUTE,
                -dm.heightPixels);
        translate.setStartOffset(500);
        translate.setDuration(300);
        translate.setFillAfter(true);
        AlphaAnimation alpha = new AlphaAnimation(0.8f, 0.2f);
        alpha.setStartOffset(500);
        alpha.setDuration(300);
        set.addAnimation(rotation);
        set.addAnimation(scale);
        set.addAnimation(translate);
        set.addAnimation(rotate);
        set.addAnimation(alpha);
        set.setFillBefore(true);
        set.setFillAfter(true);
        return set;
    }

    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    public static Animation getFadeAnimation(int fadeType, int fillType, long startOffset, long fadeDuration) {
        float startAlpha = 0f;
        float endAlpha = 1f;

        switch (fadeType) {
            case TYPE_FADE_IN:
                startAlpha = 0f;
                endAlpha = 1f;
                break;
            case TYPE_FADE_OUT:
                startAlpha = 1f;
                endAlpha = 0f;
                break;
            default:
                return null;
        }
        AlphaAnimation alpha = new AlphaAnimation(startAlpha, endAlpha);
        if (fillType == FILL_NONE) {
            alpha.setFillEnabled(true);
        } else {
            alpha.setFillEnabled(false);
        }
        switch (fillType) {
            case FILL_NONE:
                alpha.setFillBefore(false);
                alpha.setFillAfter(false);
                break;
            case FILL_BEFORE:
                alpha.setFillBefore(true);
                alpha.setFillAfter(false);
                break;
            case FILL_AFTER:
                alpha.setFillBefore(false);
                alpha.setFillAfter(true);
                break;
            case FILL_BOTH:
                alpha.setFillBefore(true);
                alpha.setFillAfter(true);
                break;
            default:
                break;
        }
        alpha.setStartOffset(startOffset);
        alpha.setDuration(fadeDuration);
        return alpha;
    }

    public static class SimpleAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class SimpleAnimatorListener implements Animator.AnimatorListener {

        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {

        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    }

    private static final String LOADING_GIF_FILE = "loading_animation.gif";
//    public static void showV6LoadingAnimation(ViewGroup viewGroup) {
//        // If loading view is already shown, just return.
//        View loadingView = viewGroup.findViewById(R.id.loading_view);
//        if (loadingView != null) return;
//
//        Context context = viewGroup.getContext();
//        LayoutInflater inflater = LayoutInflater.from(context);
//        inflater.inflate(R.layout.loading_view_v6, viewGroup);
//        GifImageView gifAnim = (GifImageView) viewGroup.findViewById(R.id.loading_anim);
//        try {
//            gifAnim.setImageDrawable(new GifDrawableBuilder().from(context.getResources().getAssets(), LOADING_GIF_FILE).build());
//            if (gifAnim.getDrawable() instanceof GifDrawable) {
//                GifDrawable gif = (GifDrawable) gifAnim.getDrawable();
//                gif.start();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void hideV6LoadingAnimation(ViewGroup viewGroup) {
//        if (viewGroup == null) {
//            return;
//        }
//        View loadingView = viewGroup.findViewById(R.id.loading_view);
//        if (loadingView != null) {
//            GifImageView gifAnim = (GifImageView) loadingView.findViewById(R.id.loading_anim);
//            if (gifAnim.getDrawable() instanceof GifDrawable) {
//                GifDrawable gif = (GifDrawable) gifAnim.getDrawable();
//                if (gif.isRunning()) {
//                    gif.stop();
//                }
//            }
//            viewGroup.removeView(loadingView);
//        }
//    }
}
