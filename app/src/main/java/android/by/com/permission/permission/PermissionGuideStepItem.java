package android.by.com.permission.permission;

/**
 * Created by herculewang on 16/4/19.
 */
public class PermissionGuideStepItem {

    public int titleRes;
    public int[] stepTitleRes;
    public int[][] stepImageRes;
    public boolean showActionButton;

    public PermissionGuideStepItem(int titleRes, int[] stepTitleRes, int[][] stepImageRes) {
        this(titleRes, stepTitleRes, stepImageRes, true);
    }

    public PermissionGuideStepItem(int titleRes, int[] stepTitleRes, int[][] stepImageRes, boolean showActionButton) {
        this.titleRes = titleRes;
        this.stepTitleRes = stepTitleRes;
        this.stepImageRes = stepImageRes;
        this.showActionButton = showActionButton;
    }
}
