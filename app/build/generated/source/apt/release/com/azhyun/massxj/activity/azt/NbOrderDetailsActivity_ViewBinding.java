// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.activity.azt;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NbOrderDetailsActivity_ViewBinding<T extends NbOrderDetailsActivity> implements Unbinder {
  protected T target;

  @UiThread
  public NbOrderDetailsActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.bank = Utils.findRequiredViewAsType(source, R.id.bank, "field 'bank'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.nb_order_tu = Utils.findRequiredViewAsType(source, R.id.nb_order_tu, "field 'nb_order_tu'", ImageView.class);
    target.nb_order_tu_tv = Utils.findRequiredViewAsType(source, R.id.nb_order_tu_tv, "field 'nb_order_tu_tv'", TextView.class);
    target.nb_order_address = Utils.findRequiredViewAsType(source, R.id.nb_order_address, "field 'nb_order_address'", TextView.class);
    target.nb_order_user_name = Utils.findRequiredViewAsType(source, R.id.nb_order_user_name, "field 'nb_order_user_name'", TextView.class);
    target.nb_order_user_phone = Utils.findRequiredViewAsType(source, R.id.nb_order_user_phone, "field 'nb_order_user_phone'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.bank = null;
    target.title = null;
    target.nb_order_tu = null;
    target.nb_order_tu_tv = null;
    target.nb_order_address = null;
    target.nb_order_user_name = null;
    target.nb_order_user_phone = null;

    this.target = null;
  }
}
