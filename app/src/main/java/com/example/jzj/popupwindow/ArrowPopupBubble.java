package com.example.jzj.popupwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * 带有箭头的PopupWindow
 * <p/>
 *
 * @author jzj
 */
public class ArrowPopupBubble extends PopupWindow {

    public enum Position {LEFT, CENTER, RIGHT}

    private final ImageView mIcon;
    private final TextView mText;
    private final View mArrow;

    public ArrowPopupBubble(Context context) {
        super();
        View rootView = LayoutInflater.from(context).inflate(R.layout.arrow_popup_window, null);
        setContentView(rootView);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 点击弹窗以外区域弹窗消失
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable(0));
        setAnimationStyle(R.style.popup_window);
        mIcon = (ImageView) rootView.findViewById(R.id.popup_icon);
        mText = (TextView) rootView.findViewById(R.id.popup_text);
        mArrow = rootView.findViewById(R.id.popup_arrow);
        // 对控件尺寸进行测量
        rootView.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public void setIcon(int icon) {
        mIcon.setImageResource(icon);
    }

    public void setText(String text) {
        mText.setText(text);
    }

    /**
     * 显示气泡
     *
     * @param parent       Window中的任意一个View
     * @param position     左/中/右
     * @param windowMargin 气泡距离左右边缘距离
     * @param arrowMargin  箭头中心距离左右边缘距离
     * @param y            气泡距离底部距离
     */
    public void showAtBottom(View parent, Position position, int windowMargin, int arrowMargin, int y) {
        final int gravity;
        final int left, right;
        switch (position) {
            case LEFT:
                left = arrowMargin - windowMargin - getHalfArrowWidth();
                right = 0;
                gravity = Gravity.LEFT;
                break;
            case CENTER:
            default:
                left = 0;
                right = 0;
                gravity = Gravity.CENTER_HORIZONTAL;
                break;
            case RIGHT:
                left = 0;
                right = arrowMargin - windowMargin - getHalfArrowWidth();
                gravity = Gravity.RIGHT;
                break;
        }
        setArrowGravityAndMargins(gravity, left, right);
        showAtLocation(parent, gravity | Gravity.BOTTOM, windowMargin, y);
    }

    private void setArrowGravityAndMargins(int gravity, int left, int right) {
        try {
            left = left < 0 ? 0 : left;
            right = right < 0 ? 0 : right;
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mArrow.getLayoutParams();
            params.setMargins(left, 0, right, 0);
            params.gravity = gravity;
            mArrow.setLayoutParams(params);
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    private int getHalfArrowWidth() {
        return mArrow.getMeasuredWidth() / 2;
    }

    private int getArrowHeight() {
        return mArrow.getMeasuredHeight();
    }
}
