package android.by.com.permission.util;

import android.annotation.SuppressLint;
import android.by.com.permission.model.ModelManager;
import android.by.com.permission.model.provider.PreferenceProvider;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.util.Log;


/**
 * PrefUtil is used as a setting saver. All settings should save here.
 */
@SuppressLint("CommitPrefEdits")
public class PrefUtil {

    private static final String TAG = "PrefUtil";

    public static String ControlPrefix = "control_";
    public static final String CONTROL_ENABLE = "YES";
    public static final String CONTROL_DISABLE = "NO";

    private static final String QUERY_EXIST_SELECTION = PreferenceProvider.QUERY_TYPE + "=? AND " + PreferenceProvider.KEY + "=?";
    private static final String QUERY_VALUE_SELECTION = PreferenceProvider.QUERY_TYPE + "=? AND " + PreferenceProvider.KEY + "=? AND "
            + PreferenceProvider.TYPE + "=? AND " + PreferenceProvider.DEFAULT + "=?";



    public static void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        ModelManager.getContext().getSharedPreferences(ModelManager.getContext().getPackageName() + "_preferences", 0).registerOnSharedPreferenceChangeListener(listener);
    }

    public static void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        ModelManager.getContext().getSharedPreferences(ModelManager.getContext().getPackageName() + "_preferences", 0).unregisterOnSharedPreferenceChangeListener(listener);
    }

    public static int getKeyInt(final String key, final int defaultVal) {
        int value = defaultVal;
        try {
            String[] selectionArgs = new String[] {PreferenceProvider.QUERY_TYPE_VALUE, key, PreferenceProvider.INT_TYPE, String.valueOf(defaultVal)};
            Cursor cursor = ModelManager.getContext().getContentResolver().query(PreferenceProvider.BASE_URI, null, QUERY_VALUE_SELECTION, selectionArgs, null);
            if (cursor == null)
                return defaultVal;

            if (cursor.moveToFirst()) {
                value = cursor.getInt(0);
            }

            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return value;
    }

    public static int getKeyIntRes(final String key, final int defaultValRes) {
        return getKeyInt(key,
                ModelManager.getContext().getResources().getInteger(defaultValRes));
    }

    public static boolean getKeyBoolean(final String key,
                                        final boolean defaultVal) {
        String[] selectionArgs = new String[] {PreferenceProvider.QUERY_TYPE_VALUE, key, PreferenceProvider.BOOLEAN_TYPE, String.valueOf(defaultVal)};
        boolean value = defaultVal;
        try {
            Cursor cursor = ModelManager.getContext().getContentResolver().query(PreferenceProvider.BASE_URI, null, QUERY_VALUE_SELECTION, selectionArgs, null);
            if (cursor == null)
                return defaultVal;
            if (cursor.moveToFirst()) {
                value = cursor.getInt(0) > 0;
            }
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static boolean getKeyBooleanRes(final String key,
                                           final int defaultValRes) {
        return getKeyBoolean(key,
                ModelManager.getContext().getResources().getBoolean(defaultValRes));
    }

    public static String getKeyString(final String key, final String defaultVal){
        String value = defaultVal;
        try {
            String[] selectionArgs = new String[]{PreferenceProvider.QUERY_TYPE_VALUE, key, PreferenceProvider.STRING_TYPE, defaultVal};
            Cursor cursor = ModelManager.getContext().getContentResolver().query(PreferenceProvider.BASE_URI, null, QUERY_VALUE_SELECTION, selectionArgs, null);
            if (cursor == null)
                return defaultVal;

            if (cursor.moveToFirst()) {
                value = cursor.getString(0);
            }

            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }


    public static String getKeyStringRes(final String key,
                                         final int defaultValRes) {
        return getKeyString(key,
                ModelManager.getContext().getResources().getString(defaultValRes));
    }

    public static long getKeyLong(final String key, final long defaultVal) {
        long value = defaultVal;
        try {
            String[] selectionArgs = new String[] {PreferenceProvider.QUERY_TYPE_VALUE, key, PreferenceProvider.LONG_TYPE, String.valueOf(defaultVal)};
            Cursor cursor = ModelManager.getContext().getContentResolver().query(PreferenceProvider.BASE_URI, null, QUERY_VALUE_SELECTION, selectionArgs, null);
            if (cursor == null)
                return defaultVal;

            if (cursor.moveToFirst()) {
                value = cursor.getLong(0);
            }

            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static float getKeyFloat(final String key, final float defaultVal) {
        float value = defaultVal;
        try {
            String[] selectionArgs = new String[] {PreferenceProvider.QUERY_TYPE_VALUE, key, PreferenceProvider.FLOAT_TYPE, String.valueOf(defaultVal)};
            Cursor cursor = ModelManager.getContext().getContentResolver().query(PreferenceProvider.BASE_URI, null, QUERY_VALUE_SELECTION, selectionArgs, null);
            if (cursor == null)
                return defaultVal;

            if (cursor.moveToFirst()) {
                value = cursor.getFloat(0);
            }

            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static void setKey(final String key, final int value) {
        ContentValues values = new ContentValues();
        values.put(key, value);
        try {
            ModelManager.getContext().getContentResolver().insert(PreferenceProvider.BASE_URI, values);
        } catch (Exception e) {
            Log.i(TAG, "setKey int exception=[%s]"+ e.getMessage());
        }
    }

    public static void setKey(final String key, final boolean value) {
        ContentValues values = new ContentValues();
        values.put(key, value);
        try {
            ModelManager.getContext().getContentResolver().insert(PreferenceProvider.BASE_URI, values);
        } catch (Exception e) {
            Log.i(TAG, "setKey bool exception=[%s]"+ e.getMessage());
        }
    }

    public static void setKey(final String key, final String value) {
        ContentValues values = new ContentValues();
        values.put(key, value);
        try {
            ModelManager.getContext().getContentResolver().insert(PreferenceProvider.BASE_URI, values);
        } catch (Exception e) {
            Log.i(TAG, "setKey string exception=[%s]"+ e.getMessage());
        }
    }

    public static void setKey(final String key, final long value) {
        ContentValues values = new ContentValues();
        values.put(key, value);
        try {
            ModelManager.getContext().getContentResolver().insert(PreferenceProvider.BASE_URI, values);
        } catch (Exception e) {
            Log.i(TAG, "setKey long exception=[%s]"+ e.getMessage());
        }
    }

    public static void setKey(final String key, final float value) {
        ContentValues values = new ContentValues();
        values.put(key, value);

        try {
            ModelManager.getContext().getContentResolver().insert(PreferenceProvider.BASE_URI, values);
        } catch (Exception e) {
            Log.i(TAG, "setKey float exception=[%s]"+ e.getMessage());
        }
    }

    public static void deleteKey(final String key) {
        ContentValues values = new ContentValues();
        values.putNull(key);

        try {
            ModelManager.getContext().getContentResolver().insert(PreferenceProvider.BASE_URI, values);
        } catch (Exception e) {
            Log.i(TAG, "deleteKey exception=[%s]"+ e.getMessage());
        }
    }

    public static boolean containsKey(String key) {
        boolean result = false;
        try {
            String[] selectionArgs = new String[] {PreferenceProvider.QUERY_TYPE_EXIST, key};
            Cursor cursor = ModelManager.getContext().getContentResolver().query(PreferenceProvider.BASE_URI, null, QUERY_EXIST_SELECTION, selectionArgs, null);

            if (cursor != null && cursor.getCount() > 0) {
                result = true;
            }

            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static int getNormalPhoneAdValue(){
        return 1;
//        try{
//            int ret = PrefUtil.getKeyInt(PrefKeys.OPEN_NORMAL_PHONE_OUTGOING_AD, BuildConfig.NORMAL_PHONE_OUTGOING_AD_DEFAULT);
//            if(ret != 0){
//                return ret;
//            }
//            int tmp = PrefUtil.getKeyInt(PrefKeys.OPEN_NORMAL_PHONE_OUTGOING_AD_OLD, BuildConfig.NORMAL_PHONE_OUTGOING_AD_DEFAULT);
//            if(tmp != 0){
//                PrefUtil.setKey(PrefKeys.OPEN_NORMAL_PHONE_OUTGOING_AD, tmp);
//            }
//            return tmp;
//        } catch (Exception e){
//            return PrefUtil.getKeyInt(PrefKeys.OPEN_NORMAL_PHONE_OUTGOING_AD, 0);
//        }
    }

}