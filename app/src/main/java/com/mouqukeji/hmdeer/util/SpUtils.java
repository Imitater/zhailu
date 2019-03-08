package com.mouqukeji.hmdeer.util;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

public class SpUtils {

    /**
     * 保存在手机里的SP文件名
     */
    public static final String FILE_NAME = "mouqu_zhailu";

    /**
     * 保存数据
     */
    public static void put(Context context, String key, Object obj) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if (obj instanceof Boolean) {
            editor.putBoolean(key, (Boolean) obj);
        } else if (obj instanceof Float) {
            editor.putFloat(key, (Float) obj);
        } else if (obj instanceof Integer) {
            editor.putInt(key, (Integer) obj);
        } else if (obj instanceof Long) {
            editor.putLong(key, (Long) obj);
        } else {
            editor.putString(key, (String) obj);
        }
        editor.commit();
    }


    /**
     * 获取指定数据
     */
    public static Object get(Context context, String key, Object defaultObj) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, context.MODE_PRIVATE);
        if (defaultObj instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObj);
        } else if (defaultObj instanceof Float) {
            return sp.getFloat(key, (Float) defaultObj);
        } else if (defaultObj instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObj);
        } else if (defaultObj instanceof Long) {
            return sp.getLong(key, (Long) defaultObj);
        } else if (defaultObj instanceof String) {
            return sp.getString(key, (String) defaultObj);
        }
        return null;
    }

    /**
     * 删除指定数据
     */
    public static void remove(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.commit();
    }


    /**
     * 返回所有键值对
     */
    public static Map<String, ?> getAll(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, context.MODE_PRIVATE);
        Map<String, ?> map = sp.getAll();
        return map;
    }

    /**
     * 删除所有数据
     */
    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }

    /**
     * 检查key对应的数据是否存在
     */
    public static boolean contains(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, context.MODE_PRIVATE);
        return sp.contains(key);
    }


    private static final String SPNAME ="FirstTime" ;
    private static final String MTAB_HEIGHT = "M_HEIGHT";
    private static SharedPreferences sp;

    public static void putBoolean(String key, boolean value, Context context) {
        if (sp==null){
            sp = context.getSharedPreferences(SPNAME, Context.MODE_PRIVATE);
        }
        sp.edit().putBoolean(key, value).commit();
    }
    public static boolean getBoolean(String key, Context context) {
        if (sp == null) {
            sp = context.getSharedPreferences(SPNAME, Context.MODE_PRIVATE);
        }
        boolean b = sp.getBoolean(key, false);
        return b;
    }

    public static void putInt(String key, int value, Context context) {
        if (sp==null){
            sp = context.getSharedPreferences(MTAB_HEIGHT, Context.MODE_PRIVATE);
        }
        sp.edit().putInt(key, value).commit();
    }
    public static int  getInt(String key, Context context) {
        if (sp == null) {
            sp = context.getSharedPreferences(MTAB_HEIGHT, Context.MODE_PRIVATE);
        }
        int  b = sp.getInt(key, 0);
        return b;
    }
    public static void putString(String key,String value, Context context) {
        if (sp==null){
            sp = context.getSharedPreferences(MTAB_HEIGHT, Context.MODE_PRIVATE);
        }
        sp.edit().putString(key,value).commit();
    }
    public static String  getString(String key, Context context) {
        if (sp == null) {
            sp = context.getSharedPreferences(MTAB_HEIGHT, Context.MODE_PRIVATE);
        }
        String  b = sp.getString(key, "");
        return b;
    }
}
