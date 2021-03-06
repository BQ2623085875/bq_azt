// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.fragment.azt;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OrderFragment_ViewBinding<T extends OrderFragment> implements Unbinder {
  protected T target;

  @UiThread
  public OrderFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.mOrder_rv = Utils.findRequiredViewAsType(source, R.id.order_rv, "field 'mOrder_rv'", RecyclerView.class);
    target.wushuju = Utils.findRequiredViewAsType(source, R.id.wushuju, "field 'wushuju'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mOrder_rv = null;
    target.wushuju = null;

    this.target = null;
  }
}
