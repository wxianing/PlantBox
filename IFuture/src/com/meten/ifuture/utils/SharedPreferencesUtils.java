package com.meten.ifuture.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

import com.meten.ifuture.activity.LoginActivity;
import com.meten.ifuture.model.User;

import org.kobjects.base64.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SharedPreferencesUtils {
    private static final String NAME = "iFuture";
    private static final String USERINFO = "userinfo";
    private static final String IS_PUSH = "ispush";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String IS_AUTO_LOGIN = "isAutoLogin";
    private Context context;
    private static SharedPreferencesUtils su;

    private SharedPreferencesUtils(Context context) {
        this.context = context;
    }

    public static SharedPreferencesUtils getInstance(Context context) {
        if (su == null) {
            su = new SharedPreferencesUtils(context);
        }
        return su;
    }

    public void saveIsPush(boolean isPush) {
        SharedPreferences sp = context.getSharedPreferences(NAME,
                Context.MODE_PRIVATE);
        Editor edit = sp.edit();
        edit.putBoolean(IS_PUSH, isPush);
        edit.commit();
    }

    public boolean getIsPush() {
        SharedPreferences sp = context.getSharedPreferences(NAME,
                Context.MODE_PRIVATE);
        return sp.getBoolean(IS_PUSH, true);
    }

    public boolean saveUser(User user) {
        if (user == null) {
            save(USERINFO, "");
            return true;
        }
        try {
            String userStr = toBase64(user);
            save(USERINFO, userStr);
            return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public User getUser() {
        String str = get(USERINFO);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            User user = (User) base64ToObject(str);
            return user;
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 序列化对象
     *
     * @param
     * @return
     * @throws IOException
     */
    private String toBase64(Object obj) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                byteArrayOutputStream);
        objectOutputStream.writeObject(obj);
        String serStr = new String(Base64.encode(byteArrayOutputStream
                .toByteArray()));
        objectOutputStream.close();
        byteArrayOutputStream.close();
        return serStr;
    }

    /**
     * 反序列化对象
     *
     * @param str
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private Object base64ToObject(String str) throws Exception,
            ClassNotFoundException {
        byte[] buf = Base64.decode(str);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                buf);
        ObjectInputStream objectInputStream = new ObjectInputStream(
                byteArrayInputStream);
        Object obj = objectInputStream.readObject();
        objectInputStream.close();
        byteArrayInputStream.close();
        return obj;
    }

    public void clearPwd() {
        save(PASSWORD, "");
    }

    public void saveUserNameAndPwd(String userName, String pwd) {
        save(USERNAME, userName);
        save(PASSWORD, pwd);
    }

    public String getUserName() {
        return get(USERNAME);
    }

    public String getPassword() {
        return get(PASSWORD);
    }

    public boolean isAutoLogin() {
        return getBoolean(IS_AUTO_LOGIN, true);
    }

    public void setIsAutoLogin(boolean isAutoLogin) {
        saveBoolean(IS_AUTO_LOGIN, isAutoLogin);
    }

    public void save(String key, String str) {
        SharedPreferences sp = context.getSharedPreferences(NAME,
                Context.MODE_PRIVATE);
        Editor edit = sp.edit();
        edit.putString(key, str);
        edit.commit();
    }

    public void saveBoolean(String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(NAME,
                Context.MODE_PRIVATE);
        Editor edit = sp.edit();
        edit.putBoolean(key, value);
        edit.commit();
    }

    public String get(String key) {
        SharedPreferences sp = context.getSharedPreferences(NAME,
                Context.MODE_PRIVATE);
        return sp.getString(key, "");
    }

    public boolean getBoolean(String key, boolean defValue) {
        SharedPreferences sp = context.getSharedPreferences(NAME,
                Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    public void clear() {
        SharedPreferences sp = context.getSharedPreferences(NAME,
                Context.MODE_PRIVATE);
        Editor edit = sp.edit();
        edit.remove(IS_AUTO_LOGIN);
        edit.remove(IS_PUSH);
        edit.remove(USERNAME);
        edit.remove(PASSWORD);
        edit.commit();
    }

    private static String CONFIG = "config";
    private static SharedPreferences sharedPreferences;

    public static void saveStringData(Context context, String key, String data) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(CONFIG,
                    Context.MODE_PRIVATE);
        }
        // 存储数据
        sharedPreferences.edit().putString(key, data).commit();
    }

    public static void saveIntData(Context context, String key, int data) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(CONFIG,
                    Context.MODE_PRIVATE);
        }
        // 存储数据
        sharedPreferences.edit().putInt(key, data).commit();
    }

    public static String getStringData(Context context, String key,
                                       String defValue) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(CONFIG,
                    Context.MODE_PRIVATE);
        }
        return sharedPreferences.getString(key, defValue);
    }

    public static int getIntData(Context context, String key, int defValue) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(CONFIG,
                    Context.MODE_PRIVATE);
        }
        return sharedPreferences.getInt(key, defValue);
    }

    public static void savebooleanData(Context context, String key, boolean data) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(CONFIG,
                    Context.MODE_PRIVATE);
        }
        // 存储数据
        sharedPreferences.edit().putBoolean(key, data).commit();
    }


    public static boolean getbooleanData(Context context, String key,
                                         boolean defValue) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(CONFIG,
                    Context.MODE_PRIVATE);
        }
        return sharedPreferences.getBoolean(key, defValue);
    }

    public static void clearData() {
        sharedPreferences.edit().clear().commit();
    }

    public static boolean getLoginTag(Context context) {
        return getbooleanData(context, "login", false);
    }

    public static void setLoginTag(Context context, boolean boo) {
        savebooleanData(context, "login", boo);
    }
}
