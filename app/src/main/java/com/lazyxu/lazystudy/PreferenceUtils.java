//package com.lazyxu.lazystudy;
//
//import android.app.Application;
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.content.SharedPreferences.Editor;
//import android.preference.PreferenceManager;
//
///**
// * SP工具类
// */
//public final class PreferenceUtils {
//    public final static String FIRST_INSTALL = "mmkj_firstinstall";
//    public final static String USER_PHONE = "mmd_user_phone";
//    public final static String USER_SESSIONID = "mmd_user_sessionid";
//    public final static String USER_REALNAME = "mmd_user_realname";
//    public final static String USER_IDCARD = "mmd_user_idcard";
//    public final static String USER_SETP = "mmd_user_step";
//    public final static String USER_ID = "mmd_user_id";
//    public final static String USER_IDCARDTYPE = "mmd_user_idcardtype";
//    public final static String JUMPTHREE = "certificate_main_jumpthree";
//    public static final String REFERRER = "referrer";
//    public static final String INSTALL_REFERRER = "install_referrer";
//    public final static String CHANNELNAME = "mmkj_channelname";
//
//    public final static String SERVICE_PHONE = "service_phone";
//    public static final String ROLL_BACK="rollback";
//    private static Application application;
//    public static final String HASALLPERMISSION="hasall_permission";
//    public static void init(Application application1) {
//        application = application1;
//    }
//
//    /**
//     * 清空数据
//     */
//    public static void reset(final Context ctx) {
//        Editor edit = PreferenceManager.getDefaultSharedPreferences(ctx).edit();
//        edit.clear();
//        edit.commit();
//    }
//
//
//    public static String getUserSessionid() {
//        return getString(PreferenceUtils.USER_SESSIONID, "");
//    }
//
//    public static String getString(String key, String defValue) {
//
//        return PreferenceManager.getDefaultSharedPreferences(application).getString(key, defValue);
//    }
//
//    public static long getLong(String key, long defValue) {
//
//        return PreferenceManager.getDefaultSharedPreferences(application).getLong(key, defValue);
//    }
//
//    public static float getFloat(String key, float defValue) {
//
//        return PreferenceManager.getDefaultSharedPreferences(application).getFloat(key, defValue);
//    }
//
//    public static void put(String key, String value) {
//
//        putString(key, value);
//    }
//
//    public static void put(String key, int value) {
//
//        putInt(key, value);
//    }
//
//    public static void put(String key, float value) {
//
//        putFloat(key, value);
//    }
//
//    public static void put(String key, long value) {
//        putLong(key, value);
//    }
//
//    public static void put(String key, boolean value) {
//
//        putBoolean(key, value);
//    }
//
//    public static void putFloat(String key, float value) {
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(application);
//        Editor editor = sharedPreferences.edit();
//        editor.putFloat(key, value);
//        editor.commit();
//    }
//
//    public static SharedPreferences getPreferences() {
//        return PreferenceManager.getDefaultSharedPreferences(application);
//    }
//
//    public static int get(String key, int defValue) {
//        return PreferenceManager.getDefaultSharedPreferences(application).getInt(key, defValue);
//    }
//
//    public static int getInt(String key, int defValue) {
//        return PreferenceManager.getDefaultSharedPreferences(application).getInt(key, defValue);
//    }
//
//    public static boolean getBoolean(String key, boolean defValue) {
//        return PreferenceManager.getDefaultSharedPreferences(application).getBoolean(key, defValue);
//    }
//
//    public static boolean get(String key, Boolean defValue) {
//        return PreferenceManager.getDefaultSharedPreferences(application).getBoolean(key, defValue);
//    }
//
//
//    public static boolean hasString(String key) {
//
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(application);
//        return sharedPreferences.contains(key);
//    }
//
//    public static void putString(String key, String value) {
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(application);
//        Editor editor = sharedPreferences.edit();
//        editor.putString(key, value);
//        editor.commit();
//    }
//
//    public static void putLong(String key, long value) {
//
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(application);
//        Editor editor = sharedPreferences.edit();
//        editor.putLong(key, value);
//        editor.commit();
//    }
//
//    public static void putBoolean(String key, boolean value) {
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(application);
//        Editor editor = sharedPreferences.edit();
//        editor.putBoolean(key, value);
//        editor.apply();
//    }
//
//    public static void putInt(String key, int value) {
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(application);
//        Editor editor = sharedPreferences.edit();
//        editor.putInt(key, value);
//        editor.apply();
//    }
//
//    public static void remove(String... keys) {
//        if (keys != null) {
//            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(application);
//            Editor editor = sharedPreferences.edit();
//            for (String key : keys) {
//                editor.remove(key);
//            }
//            editor.apply();
//        }
//    }
//}
