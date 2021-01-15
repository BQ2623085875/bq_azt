// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.activity.azt;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OrderPayActivity_ViewBinding<T extends OrderPayActivity> implements Unbinder {
  protected T target;

  @UiThread
  public OrderPayActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.bank = Utils.findRequiredViewAsType(source, R.id.bank, "field 'bank'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.pay_price = Utils.findRequiredViewAsType(source, R.id.pay_price, "field 'pay_price'", TextView.class);
    target.weixin_anniu = Utils.findRequiredViewAsType(source, R.id.weixin_anniu, "field 'weixin_anniu'", ImageView.class);
    target.yinlian_anniu = Utils.findRequiredViewAsType(source, R.id.yinlian_anniu, "field 'yinlian_anniu'", ImageView.class);
    target.rl_liji_pay = Utils.findRequiredViewAsType(source, R.id.rl_liji_pay, "field 'rl_liji_pay'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.bank = null;
    target.title = null;
    target.pay_price = null;
    target.weixin_anniu = null;
    target.yinlian_anniu = null;
    target.rl_liji_pay = null;

    this.target = null;
  }
}
