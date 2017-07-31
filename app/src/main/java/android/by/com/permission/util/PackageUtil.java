package android.by.com.permission.util;


import android.app.ActivityManager;
import android.by.com.permission.model.ModelManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

public class PackageUtil {
    private static final String TAG = "PackageUtil";

    private static int sOnlinePackageExistCheck;

    private static final int PACKAGE_EXIST_UNKNOWN = 0;
    public static final int PACKAGE_NO_EXIST = -1;
    public static final int PACKAGE_EXIST = 1;
    public static final String QIHOO_360_SAFE = "com.qihoo360.mobilesafe";
    public static final String QIHOO_360_SAFE_MTK6573 = "com.qihoo360.mobilesafe_mtk6573";
    public static final String TENCENT_QQPIMSECURE = "com.tencent.qqpimsecure";
    public static final String BAIDU_SHOUSHOU = "cn.opda.a.phonoalbumshoushou";
    //new add 5.1.5 preinstall
    public static final String LEB_SECURITY = "com.lbe.security";
    public static final String SOUGOU_SLEDOG = "com.sg.sledog";
    public static final String YULORE_YELLOWPAGE = "com.yulore.yellowpage";
    public static final String BLOVE_STORM = "com.blovestorm";
    public static final String TENCENT_QQPHONEBOOK = "com.tencent.qqphonebook";
    public static final String CHINAMOBLIE_CONTACTS = "com.chinamobile.contacts.im";

    //new add 2014-11-7
    public static final String AILIAOICAL = "com.ailiaoicall";
    public static final String WEIHUA = "com.weihua.superphone";
    public static final String YX = "com.yx";
    public static final String YYMEET = "com.yy.yymeet";

    //new add 2015-03-31
    public static final String SAFETYCONTACTS_360 = "com.qihoo360.contacts";
    public static final String DINGDING = "com.alibaba.android.rimet";
    public static final String YIXIN = "im.yixin";
    public static final String LIEBAO = "com.cleanmaster.mguard_cn";

    public static final String HUAWEI_PHONE_MANAGER = "com.huawei.systemmanager";
    public static final String VIVO_PHONE_MANAGER = "com.iqoo.secure";
    public static final String SMARTISANOS_CENTER = "com.smartisanos.securitycenter";
    public static final String SAMSUNG_SECCENTER = "com.samsung.memorymanager";

    public static final String MEIZU_PHONE_MANAGER = "com.meizu.safe";

    //oppo
    public static final String[] OPPO_2_0_PERMISSION_SETTING_PACKAGE_NAMES = new String[]{
            "com.oppo.safe"
    };

    //oppo
    public static final String[] OPPO_PERMISSION_SETTING_PACKAGE_NAMES = new String[]{
            "com.color.safecenter"
    };

    //oppo R9 use coloros 3.0;
    public static final String[] OPPO_COLOROS_PERMISSION_SETTING_PACKAGE_NAMES = new String[]{
            "com.coloros.safecenter"
    };

    //samsung

    public static final String[] SAMSUNG_PERMISSION_SETTINGS_PACKAGE_NAMES = new String[]{
            "com.samsung.memorymanager",
            "com.samsung.android.sm"
    };

    //coolpad seccenter
    public static final String COOLPAD_PHONE_MANAGER = "com.yulong.android.seccenter";
    public static final String OLD_COOLPAD_PHONE_MANAGER_NAME = "云安全"; //fix bug : for coolpad API<16

    public static final String[] TOAST_DELAY_PACKAGENAME = {QIHOO_360_SAFE, QIHOO_360_SAFE_MTK6573, TENCENT_QQPIMSECURE, BAIDU_SHOUSHOU, SOUGOU_SLEDOG};
    public static final String[] CHECK_APP_LIST = {QIHOO_360_SAFE, QIHOO_360_SAFE_MTK6573, TENCENT_QQPIMSECURE, BAIDU_SHOUSHOU,
            LEB_SECURITY, SOUGOU_SLEDOG, YULORE_YELLOWPAGE, BLOVE_STORM, TENCENT_QQPHONEBOOK, CHINAMOBLIE_CONTACTS,
            AILIAOICAL, WEIHUA, YX, YYMEET, YIXIN, DINGDING, SAFETYCONTACTS_360, LIEBAO};

    //    public static final int[] CHECK_APP_LIST_SHOW_NAME = {R.string.qihoo_360safe, R.string.qihoo_360safe_mtk6573, R.string.qq_ime_secure,R.string.baidu_shoushou,
//								R.string.lbe_security,R.string.sougou_sledog,R.string.yulore_yellowpage,R.string.blovestorm,
//								R.string.tencent_qq_phonebook,R.string.chinamoblie_contacts,
//								R.string.ailiaoical,R.string.weihua,R.string.yx,R.string.yymeet,R.string.yixin,R.string.dingding,R.string.safety_360,R.string.liebao};
//
//    private static HashMap<String, PackageResult> sAppList = new HashMap<String, PackageResult>();
    public static volatile boolean hasLoadedAppList = false;

    private static HashSet<String> sLauncher;
    private static HashMap<String, String> sLauncherPackage;

//    public static boolean isOnlinePackageInstalled() {
//        if (sOnlinePackageExistCheck == PACKAGE_EXIST_UNKNOWN) {
//            sOnlinePackageExistCheck = isPackageInstalled(ModelManager.getContext().getString(
//                    R.string.channel_app_name)) ? PACKAGE_EXIST : PACKAGE_NO_EXIST;
//        }
//        return sOnlinePackageExistCheck == PACKAGE_EXIST ? true : false;
//    }

    public static void setOnlinePackageInstalled(int installed) {
        sOnlinePackageExistCheck = installed;
    }

    public static boolean isPackageInstalled(String pkgName) {
        try {
            PackageManager pm = ModelManager.getContext().getPackageManager();
            pm.getPackageInfo(pkgName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (NameNotFoundException e) {
        } catch (RuntimeException e) {
        }
        return false;
    }

    public static boolean isPermissionGuideOverview() {
        if (PackageUtil.isPackageInstalled(PackageUtil.OPPO_COLOROS_PERMISSION_SETTING_PACKAGE_NAMES)
                || PackageUtil.isPackageInstalled(PackageUtil.OPPO_PERMISSION_SETTING_PACKAGE_NAMES)
                || PackageUtil.isPackageInstalled(PackageUtil.OPPO_2_0_PERMISSION_SETTING_PACKAGE_NAMES)) {
            return false;
        } else if (OSUtil.isMiuiV6() || OSUtil.isMiuiV7() || OSUtil.isMiuiV8()) {
            return false;
        }
        return true;
    }

    public static boolean isAutoPermission() {
        if (PackageUtil.isPackageInstalled(PackageUtil.OPPO_COLOROS_PERMISSION_SETTING_PACKAGE_NAMES)
                || PackageUtil.isPackageInstalled(PackageUtil.OPPO_PERMISSION_SETTING_PACKAGE_NAMES)
                || PackageUtil.isPackageInstalled(PackageUtil.OPPO_2_0_PERMISSION_SETTING_PACKAGE_NAMES)) {
            try {
                String display = Build.DISPLAY;
                Log.i(TAG, "display=" + display);
                String[] tmp = display.split("_");
                String displayVersion = tmp[tmp.length - 1];
                if (Integer.parseInt(displayVersion) >= 170500 && Integer.parseInt(displayVersion) < 170603) {
                    return false;
                }

            } catch (Exception e) {
                Log.e("weyl", e + "");
            }
            return true;
        } else if (PackageUtil.isPackageInstalled(PackageUtil.HUAWEI_PHONE_MANAGER)) {
            if (Build.VERSION.SDK_INT < 19) {
                return false;
            }

            if ((Locale.getDefault().getLanguage()).equals("en")
                    || ((Locale.getDefault().getLanguage()).equals("zh")
                    && (Locale.getDefault().getCountry()).equals("CN")))
                return true;
            else
                return false;
        } else if (OSUtil.isMiuiV6() || OSUtil.isMiuiV7()) {
            return false;
        } else if (OSUtil.isMiuiV8()) {
            if ((Locale.getDefault().getLanguage()).equals("en")
                    || ((Locale.getDefault().getLanguage()).equals("zh")
                    && (Locale.getDefault().getCountry()).equals("CN")))
                return true;
            else
                return false;
        }
//        else if(OSUtil.isMiuiV5()) {
//            return true;
//        }
        return false;
    }

    /**
     * return true if any one of the packageNames is detected. otherwise return false.
     *
     * @param packageNames a list of package names.
     * @return
     */
    public static boolean isPackageInstalled(String[] packageNames) {
        if (packageNames == null || packageNames.length == 0) return false;
        boolean isInstalled = false;
        for (String pkgName : packageNames) {
            if (isPackageInstalled(pkgName)) {
                isInstalled = true;
                break;
            }
        }
        return isInstalled;
    }


    public static String getProcStatInfo() {
        int myProcessID = Process.myPid();
        String cmd = "cat /proc/" + String.valueOf(myProcessID) + "/stat";
        java.lang.Process process = null;
        BufferedReader reader = null;
        String result = null;
        String line;
        try {
            process = Runtime.getRuntime().exec(cmd);
            InputStream outs = process.getInputStream();
            InputStreamReader isrout = new InputStreamReader(outs);
            reader = new BufferedReader(isrout, 8 * 1024);
            while ((line = reader.readLine()) != null) {
                result += line;
            }
            Log.i("LALAqq", "proc_stat" + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

//    public static String getCompetingAppShowName(int pos) {
//    	if (pos >= 0
//    			&& pos < CHECK_APP_LIST_SHOW_NAME.length) {
//    		return ModelManager.getContext().getResources().getString(CHECK_APP_LIST_SHOW_NAME[pos]);
//    	}
//    	return "";
//    }

    public static boolean isHuaweiNewManager(String pkgName) {
        try {
            PackageInfo info = ModelManager.getContext().getPackageManager()
                    .getPackageInfo(pkgName, PackageManager.GET_ACTIVITIES);
            if (info.versionCode == 1) {
                return info.activities.length > 60;
            } else {
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }

//    public static List<ISearchResult> queryPackage(String rawString, boolean numberic) {
//        boolean isNumber = false;
//        if (numberic && StrUtil.isNumeric(rawString)) {
//            isNumber = true;
//        }
//        List<ISearchResult> result = new ArrayList<ISearchResult>();
//
//        for(String pkgName : getAppList().keySet()) {
//        	if(!Constants.PACKAGE_NAME.equals(pkgName)) {
//                PackageResult info = getAppList().get(pkgName);
//                String fullSpell = info.fullSpell;
//                String chineseFullSpell = info.chineseFullSpell;
//                String firstSpell = info.firstSpell;
//                String chineseFirstSpell = info.chineseFirstSpell;
//                if (!isNumber) {
//                	String queryWithoutSpace = getWithoutSpaceString(rawString);
//                	String labelWithoutSpace = getWithoutSpaceString(info.label.toLowerCase(Locale.US));
//                	String chineseLabelWithoutSpace = getWithoutSpaceString(info.chineseLabel.toLowerCase(Locale.US));
//                    if (firstSpell.startsWith(queryWithoutSpace) || fullSpell.startsWith(queryWithoutSpace)
//                            || firstSpell.toLowerCase(Locale.US).startsWith(queryWithoutSpace)
//                            || fullSpell.toLowerCase(Locale.US).startsWith(queryWithoutSpace)
//                            || labelWithoutSpace.contains(queryWithoutSpace)) {
//                        info.setMain(info.label);
//                        result.add(info);
//                    } else if (chineseFirstSpell.startsWith(queryWithoutSpace) || chineseFullSpell.startsWith(queryWithoutSpace)
//                            || chineseFirstSpell.toLowerCase(Locale.US).startsWith(queryWithoutSpace)
//                            || chineseFullSpell.toLowerCase(Locale.US).startsWith(queryWithoutSpace)
//                            || chineseLabelWithoutSpace.contains(queryWithoutSpace)) {
//                        info.setMain(info.chineseLabel);
//                        result.add(info);
//                    }
//                } else {
//                	String labelWithoutSpace = getWithoutSpaceString(info.label.toLowerCase(Locale.US));
//                    String chineseLabelWithoutSpace = getWithoutSpaceString(info.chineseLabel.toLowerCase(Locale.US));
//                    String fullSpellNumber = getNumberString(fullSpell.toLowerCase(Locale.US));
//                    String chineseFullSpellNumber = getNumberString(chineseFullSpell.toLowerCase(Locale.US));
//                    String firstSpellNumber = getNumberString(firstSpell.toLowerCase(Locale.US));
//                    String chineseFirstSpellNumber = getNumberString(chineseFirstSpell.toLowerCase(Locale.US));
//
//                    if (firstSpellNumber.startsWith(rawString) || fullSpellNumber.startsWith(rawString)
//                    		|| labelWithoutSpace.contains(rawString)) {
//                        info.setMain(info.label);
//                        result.add(info);
//                    } else if (chineseFirstSpellNumber.startsWith(rawString)
//                            || chineseFullSpellNumber.startsWith(rawString)
//                            || chineseLabelWithoutSpace.contains(rawString)) {
//                        info.setMain(info.chineseLabel);
//                        result.add(info);
//                    }
//                }
//        	}
//        }
//        return result;
//    }

    public static String getWithoutSpaceString(String rawString) {
        StringBuffer sb = new StringBuffer();
        char[] arr = rawString.toCharArray();
        for (int i = 0; i < rawString.length(); ++i) {
            if (arr[i] != ' ') {
                sb.append(arr[i]);
            }
        }
        return sb.toString();
    }

//    public static void preparePackageInfoList() {
//        sAppList.clear();
//        Intent intent = new Intent(Intent.ACTION_MAIN);
//        intent.addCategory(Intent.CATEGORY_LAUNCHER);
//
//        try {
//            List<ResolveInfo> list = ModelManager.getContext().getPackageManager().queryIntentActivities(intent, 0);
//            for(ResolveInfo info : list) {
//                String label = info.loadLabel(ModelManager.getContext().getPackageManager()).toString();
//                String fullSpell = TEngine.getInst().nativeGetFullSpell(label);
//                String firstSpell = TEngine.getInst().nativeGetFirstSpell(label);
//                String packageName = info.activityInfo.packageName;
//                PackageResult i = new PackageResult(label, fullSpell, firstSpell, packageName);
//                i.chineseLabel = getChineseLabel(packageName);
//                i.chineseFullSpell = TEngine.getInst().nativeGetFullSpell(i.chineseLabel);
//                i.chineseFirstSpell = TEngine.getInst().nativeGetFirstSpell(i.chineseLabel);
//                if (!sAppList.containsKey(packageName)) {
//                    sAppList.put(packageName, i);
//                }
//            }
//        } catch (RuntimeException e) {
//
//        }
//    }

//    private static String getChineseLabel(String packageName) {
//        if(!Constants.PACKAGE_NAME.equals(packageName)) {
//            try {
//                Context myAppContext = ModelManager.getContext();
//                Context otherAppContext = myAppContext.createPackageContext(packageName, myAppContext.CONTEXT_IGNORE_SECURITY);
//                Configuration appConfig = new Configuration();
//                appConfig.locale = Locale.SIMPLIFIED_CHINESE;
//                otherAppContext.getResources().updateConfiguration(appConfig, myAppContext.getResources().getDisplayMetrics());
//                PackageInfo appInfo = myAppContext.getPackageManager().getPackageInfo(packageName, 0);
//                return otherAppContext.getResources().getString(appInfo.applicationInfo.labelRes);
//            } catch(Exception e) {
//                e.printStackTrace();
//                return "";
//            }
//        } else {
//            return "";
//        }
//    }

//    public static void initPackageInfoList() {
//        if (!hasLoadedAppList) {
//            preparePackageInfoListInThread();
//            hasLoadedAppList = true;
//        }
//    }

//    public static void preparePackageInfoListInThread() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                preparePackageInfoList();
//            }
//        }).start();
//    }
//
//    public static HashMap<String, PackageResult> getAppList() {
//        if (sAppList == null || sAppList.size() == 0) {
//            preparePackageInfoList();
//        }
//        return sAppList;
//    }

    private static String getNumberString(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            switch (c) {
                case 'a':
                case 'b':
                case 'c':
                    sb.append('2');
                    break;
                case 'd':
                case 'e':
                case 'f':
                    sb.append('3');
                    break;
                case 'g':
                case 'h':
                case 'i':
                    sb.append('4');
                    break;
                case 'j':
                case 'k':
                case 'l':
                    sb.append('5');
                    break;
                case 'm':
                case 'n':
                case 'o':
                    sb.append('6');
                    break;
                case 'p':
                case 'q':
                case 'r':
                case 's':
                    sb.append('7');
                    break;
                case 't':
                case 'u':
                case 'v':
                    sb.append('8');
                    break;
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                    sb.append('9');
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }

    /**
     * to determine whether the user is currently at launcher home.
     * (implemented by comparing top package name(api 20+) or top activity name.
     *
     * @return true if the user is at launcher home currently; else return false.
     */
//    public static boolean isAtLauncherHome() {
//        Log.d(TAG, "isAtLauncherHome");
//        if (sLauncher == null || sLauncherPackage == null) {
//            sLauncher = new HashSet<String>();
//            sLauncherPackage = new HashMap<String, String>();
//            PackageManager pm = ModelManager.getContext().getPackageManager();
//
//            Intent launcherIntent = new Intent();
//            launcherIntent.setAction(Intent.ACTION_MAIN);
//            launcherIntent.addCategory(Intent.CATEGORY_HOME);
//            List<ResolveInfo> list = pm.queryIntentActivities(launcherIntent,
//                    PackageManager.MATCH_DEFAULT_ONLY);
//            for (ResolveInfo info : list) {
//                String activityName = info.activityInfo.name;
//                Log.d(TAG, "activityName = " + activityName);
//                //no filter!
//                sLauncher.add(activityName);
//                if (!sLauncherPackage.containsKey(info.activityInfo.packageName))
//                    sLauncherPackage.put(info.activityInfo.packageName, activityName);
//            }
//        }
//        KeyguardManager mKeyguardManager = (KeyguardManager) ModelManager.getInst().getContext().getSystemService(Context.KEYGUARD_SERVICE);
//        boolean flag = mKeyguardManager.inKeyguardRestrictedInputMode();
//        String activityName = getTopActivityOrPackageName();
//        if (TLog.DBG) {
//            StringBuilder sb = new StringBuilder();
//            sb.append("abroadGuide, topActivity: " + activityName);
//            sb.append(", flag:" + flag);
//            sb.append(", sLauncher: " + sLauncher);
//            sb.append(", sLauncherPackage:" + sLauncherPackage);
//            TLog.d(TAG, sb.toString());
//        }
//        if (Build.VERSION.SDK_INT < 20) {
//            if ((sLauncher.contains(activityName)) && !flag) {
//                TLog.d(TAG, "launcher, version = %d ", Build.VERSION.SDK_INT);
//                return true;
//            }
//        } else {
//            if ((sLauncherPackage.containsKey(activityName)) && !flag) {
//                TLog.d(TAG, "launcher packages, version = %d ", Build.VERSION.SDK_INT);
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public static boolean isTPDTabActivityAtTop() {
//        String topActivityName = getTopActivityOrPackageName();
//        boolean isAtTop = TPDTabActivity.class.getName().equals(topActivityName);
//        if (TLog.DBG) {
//            StringBuilder sb = new StringBuilder();
//            sb.append("isTPDTabActivityAtTop()");
//            sb.append("\n activityName = " + topActivityName);
//            sb.append("\n TPDTabActivity.class.getName() = " + TPDTabActivity.class.getName());
//            sb.append("\n isAtTop = " + isAtTop);
//            TLog.d(TAG, sb.toString());
//        }
//        return isAtTop;
//    }
//
//    public static String getTopActivityOrPackageName() {
//        return Build.VERSION.SDK_INT >= 20 ?
//                BalloonLauncher.getTopPackagesInLollipop() : BalloonLauncher.getTopActivityPreLollipop();
//    }
    public static String getTopActivity(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);


        if (runningTaskInfos != null)
            return runningTaskInfos.get(0).topActivity.getClassName();
        else
            return "";
    }

//    public static boolean isOurAppAtTop() {
//        String topName = getTopActivityOrPackageName();
//        if (TextUtils.isEmpty(topName)) return false;
//        return topName.contains(ModelManager.getContext().getPackageName())
//                || topName.contains(Constants.PACKAGE_ANDES_PREFIX);
//    }

    public static void launchApp(String packageName, String defaultActivityName) {
        if (!TextUtils.isEmpty(defaultActivityName)) {
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setClassName(packageName, defaultActivityName);
            ModelManager.getInst().getContext().startActivity(intent);
        } else {
            PackageManager pm = ModelManager.getInst().getContext().getPackageManager();
            Intent app_intent = pm.getLaunchIntentForPackage(packageName);
            if (app_intent != null) {
                ModelManager.getInst().getContext().startActivity(app_intent);
            }
        }
    }

    public static String getVersionName(String packageName) {
        String ret = "";
        final PackageManager pm = ModelManager.getContext().getPackageManager();
        try {
          PackageInfo pl = pm.getPackageInfo(packageName, 0);
          ret = pl.versionName;

        } catch ( Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static String getAppName(String appName) {
        final PackageManager pm = ModelManager.getContext().getPackageManager();
        ApplicationInfo ai;
        try {
            ai = pm.getApplicationInfo(appName, 0);
        } catch (final NameNotFoundException e) {
            ai = null;
        }
        final String applicationName = (String) (ai != null ? pm.getApplicationLabel(ai) : "(unknown)");
        return applicationName;
    }

}
