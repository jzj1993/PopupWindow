package com.example.jzj.popupwindow;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

/**
 * 显示悬浮控件
 * <p/>
 * uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW"
 * uses-permission android:name="android.permission.SYSTEM_OVERLAY_WINDOW"
 *
 * @author jzj
 */
public class FloatViewManager {

    private Context mContext;
    private WindowManager mWindowManager;

    public FloatViewManager(Context context) {
        mContext = context;
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    }

    public void show(int resource, int gravity, int width, int height) {
        final View v = LayoutInflater.from(mContext).inflate(resource, null);
        this.show(v, gravity, width, height);
    }

    public void showAtCenter(View v) {
        this.show(v, Gravity.CENTER, WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
    }

    public void show(View v, int gravity, int width, int height) {
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.gravity = gravity;
        params.width = width;
        params.height = height;
        this.show(v, params);
    }

    public void show(View v, WindowManager.LayoutParams params) {
        mWindowManager.addView(v, updateLayoutParams(params));
    }

    public void update(View v, int gravity, int width, int height) {
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.gravity = gravity;
        params.width = width;
        params.height = height;
        this.update(v, params);
    }

    public void update(View v, WindowManager.LayoutParams layoutParams) {
        mWindowManager.updateViewLayout(v, updateLayoutParams(layoutParams));
    }

    public void remove(View v) {
        try {
            mWindowManager.removeView(v);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private WindowManager.LayoutParams updateLayoutParams(WindowManager.LayoutParams params) {
        params.format = PixelFormat.RGBA_8888;
        params.type = WindowManager.LayoutParams.TYPE_PHONE;
        params.flags |= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        return params;
    }
}
