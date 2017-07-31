package android.by.com.permission.constant;

/**
 * Created by frankyang on 12/2/15.
 */
public class GuideConst {

    public static final String TRUST_APPLICATION_PERMISSION = "background_protect_permission"; //开启“我信任该程序”权限
    public static final String TRUST_APPLICATION_PERMISSION_MIUI5 = "background_protect_permission_MIUI5"; //miui5: 开启”我信任该程序权限”
    public static final String AUTOBOOT_PERMISSION = "autoboot_permission";//开启”后台自启动”权限
    public static final String ENTER_AUTOBOOT_PERMISSION_PAGE = "enter_autoboot_permission_page";//进入”后台自启动”页面
    public static final String ENTER_FLOAT_PERMISSION_PAGE = "enter_float_permission_page";//进入”悬浮窗管理”页面
    public static final String TOAST_PERMISSION = "toast_permission";//开启悬浮窗权限
    public static final String DATA_PERMISSION = "data_permission";//允许系统相关权限
    public static final String TRUST_APPLICATION_PERMISSION_COOLPAD = "application_permission"; //开启应用权限（Coopad）
    public static final String TRUST_APPLICATION_PERMISSION_MIUI6 = "call_permission"; //开启“打电话“权限(MIUI)
    public static final String BACKGROUND_PROTECT_PERMISSION = "background_protect_permission_lock";
    public static final String MIUI_BACKGROUND_PROTECT_PERMISSION = "background_protect_permission_miui";
    public static final String VIVO_BACKGROUND_PROTECT_PERMISSION = "background_protect_permission_vivo";
    public static final String VIVO_BACKGROUND_USE_CPU_PERMISSION = "background_use_cpu_permission_vivo";
    public static final String HUAWEI_V4_BACKGROUND_PROTECT_PERMISSION = "background_protect_permission_huawei_v4";
    public static final String HUAWEI_V6_SET_PERMISSION = "huawei_v6_set_permission";

    public static final String SMARTISION_BACKGROUND_PROTECT_PERMISSION = "smartision_background_protect_permission";
    public static final String MEIZU_WHITE_LIST = "meizu_white_list";
    public static final String ZTE_WHITE_LIST = "zte_white_list";
    public static final String WHITE_LIST = "white_list";
    public static final String OPEN_NOTIFICATION = "notification";
    public static final String CALL_PHONE_PERMISSION = "call_phone_permission";
    public static final String DEFAULT_PERMISSION = "default_permission";
    public static final String BACKGROUND_FROZEN_PERMISSION = "background_frozen_permission";
    public static final String OPPO_BACKGROUND_FROZEN_PERMISSION = "oppo_background_frozen_permission";
    public static final String BACKGROUND_RUNNING_PERMISSION = "background_running_permission";
    public static final String READ_CALLOG_PERMISSION = "read_calllog_permission";
    public static final String READ_CONTACT_PERMISSION = "read_contact_permission";


    public static final int TRUST_APPLICATION_PERMISSION_DONE = 1;
    public static final int AUTOBOOT_PERMISSION_DONE = 2;
    public static final int TOAST_PERMISSION_DONE = 4;
    public static final int DATA_PERMISSION_DONE = 8;
    public static final int BACKGROUND_PROTECT_PERMISSION_DONE = 16;
    public static final int WHITE_LIST_PERMISSION_DONE = 32;
    public static final int OPEN_NOTIFICATION_DONE = 64;
    public static final int CALL_PHONE_PERMISSION_DONE = 128;
    public static final int BACKGOUND_FROZEN_PERMISSION_DONE = 256;
    public static final int BACKGROUNP_USE_CPU_PERMISSION_DONE = 512;


    public static final String MIUI_V5_PERMISSION_PACKAGE_NAME = "com.android.settings";
    public static final String MIUI_V5_APP_PERMISSION_ACTIVITY_NAME = "com.miui.securitycenter.permission.AppPermissionsEditor";
    public static final String MIUI_V6_PERMISSION_PACKAGE_NAME = "com.miui.securitycenter";
    public static final String MIUI_V6_APP_PERMISSION_ACTIVITY_NAME = "com.miui.permcenter.permissions.AppPermissionsEditorActivity";
    public static final String MIUI_V6_AUTO_START_PERMISSION_ACTIVITY_NAME = "com.miui.permcenter.autostart.AutoStartManagementActivity";
    public static final String MIUI_V6_MANAGE_APPLICATIONS_ACTIVITY_NAME = "com.android.settings.applications.ManageApplicationsActivity";



    public static final String HUAWEI_PERMISSION_PACKAGE_NAME = "com.huawei.systemmanager";
    public static final String HUAWEI_APP_PERMISSION_ACTIVITY_NAME = "com.huawei.permissionmanager.ui.MainActivity";
    public static final String HUAWEI_PERMISSION_MAIN_ACTIVITY_NAME = "com.huawei.systemmanager.mainscreen.MainScreenActivity";
    public static final String HUAWEI_AUTO_START_PERMISSION_ACTIVITY_NAME = "com.huawei.systemmanager.optimize.bootstart.BootStartActivity";
    public static final String HUAWEI_AUTO_START_PERMISSION_ACTIVITY_NAME_V2 = "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity";
    public static final String HUAWEI_BACKGROUND_PROTECT_PERMISSION_ACTIVITY_NAME = "com.huawei.systemmanager.optimize.process.ProtectActivity";
    public static final String HUAWEI_TOAST_PERMISSION_ACTIVITY_NAME_V1 = "com.huawei.systemmanager.SystemManagerMainActivity";
    public static final String HUAWEI_TOAST_PERMISSION_ACTIVITY_NAME_TWO = "com.huawei.systemmanager.mainscreen.MainScreenActivity";
    public static final String HUAWEI_TOAST_PERMISSION_ACTIVITY_NAME_V2 = "com.huawei.systemmanager.addviewmonitor.AddViewMonitorActivity";
    public static final String HUAWEI_TOAST_PERMISSION_ACTIVITY_NAME_V3 = "com.huawei.notificationmanager.ui.NotificationManagmentActivity";
    public static final String HUAWEI_TOAST_PERMISSION_ACTIVITY_NAME_AUTO = "com.huawei.systemmanager.SpecialToolsActivity";


    public static final int CALLLOG_PERMISSION = 1;
    public static final int CONTACT_PERMISSON = 2;
    public static final int CALL_PERMISSION = 3;

    public static final String ZTE_PERMISSION_PACKAGE_NAME = "com.zte.heartyservice";
    public static final String ZTE_PERMISSION_PACKAGE_AUTOBOOT_V6 = "com.zte.smartpower";
    public static final String ZTE_PERMISSION_ACTIVITY_V7 = "com.zte.heartyservice.permission.AppPermissonsControlActivity";
    public static final String ZTE_PERMISSION_ACTIVITY_V6 = "com.zte.heartyservice.permission.PermissionHost";
    public static final String ZTE_AUTOBOOT_ACTIVITY_V7 = "com.zte.heartyservice.autorun.AppAutoRunManager";
    public static final String ZTE_AUTOBOOT_ACTIVITY_V6 = "com.zte.smartpower.SelfStartActivity";
    public static final String ZTE_WHITELIST_ACTIVITY_V6 = "com.zte.heartyservice.setting.ClearAppSettingsActivity";
    public static final String ZTE_BACKGROUND_ACTIVITY_V7 = "com.zte.heartyservice.setting.ClearAppSettingsActivity";

}
