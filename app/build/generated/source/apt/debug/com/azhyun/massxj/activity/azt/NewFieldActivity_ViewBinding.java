// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.activity.azt;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NewFieldActivity_ViewBinding<T extends NewFieldActivity> implements Unbinder {
  protected T target;

  @UiThread
  public NewFieldActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.mBank = Utils.findRequiredViewAsType(source, R.id.bank, "field 'mBank'", ImageView.class);
    target.mTitle = Utils.findRequiredViewAsType(source, R.id.title, "field 'mTitle'", TextView.class);
    target.wushuju = Utils.findRequiredViewAsType(source, R.id.wushuju, "field 'wushuju'", TextView.class);
    target.newfield_rv = Utils.findRequiredViewAsType(source, R.id.newfield_rv, "field 'newfield_rv'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mBank = null;
    target.mTitle = null;
    target.wushuju = null;
    target.newfield_rv = null;

    this.target = null;
  }
}
