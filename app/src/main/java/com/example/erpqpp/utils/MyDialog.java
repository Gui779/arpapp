package com.example.erpqpp.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * 弹出框
 */
public class MyDialog extends Dialog {

    private static int default_width = 200; //默认宽度
    private static int default_height = 120;//默认高度

    public MyDialog(Context context, View layout, int style) {
        this(context, default_width, default_height, layout, style);
    }

    public MyDialog(Context context, int width, int height, View layout, int style) {
        super(context, style);
        setContentView(layout);
        setwidthhegih(width,height);
    }



    public void setwidthhegih(int top,int botton){

        /**
         * 设置宽度全屏，要设置在show的后面
         * */

        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        getWindow().getDecorView().setPadding(40, top, 40, botton);

        getWindow().setAttributes(layoutParams);
    }

}
