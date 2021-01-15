package com.azhyun.massxj.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.Keep;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import chihane.jdaddressselector.AddressProvider;
import chihane.jdaddressselector.AddressSelector;
import chihane.jdaddressselector.OnAddressSelectedListener;
import mlxy.utils.Dev;

/**
 * Created by ljh on 2017/3/30.
 */
@Keep
public class MyBottomDialog extends Dialog {
    private AddressSelector selector;
    private Context context1;

    public MyBottomDialog(Context context) {
        super(context, chihane.jdaddressselector.R.style.bottom_dialog);
        init(context);
    }

    public MyBottomDialog(Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    public MyBottomDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    private void init(Context context) {
        selector = new AddressSelector(context);
        context1 = context;
    }

    public void setSelectorDAta(AddressProvider addressProvider) {
        selector.setAddressProvider(addressProvider);
        setContentView(selector.getView());

        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = Dev.dp2px(context1, 360);
        window.setAttributes(params);

        window.setGravity(Gravity.BOTTOM);
    }

    public void setOnAddressSelectedListener(OnAddressSelectedListener listener) {
        this.selector.setOnAddressSelectedListener(listener);
    }

  /*  public static MyBottomDialog show(Context context) {
        return show(context, null);
    }

    public static MyBottomDialog show(Context context, OnAddressSelectedListener listener) {
        MyBottomDialog dialog = new MyBottomDialog(context, chihane.jdaddressselector.R.style.bottom_dialog);
        dialog.selector.setOnAddressSelectedListener(listener);
        dialog.show();

        return dialog;
    }*/
}
