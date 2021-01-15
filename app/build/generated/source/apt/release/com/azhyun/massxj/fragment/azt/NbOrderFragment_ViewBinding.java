// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.fragment.azt;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NbOrderFragment_ViewBinding<T extends NbOrderFragment> implements Unbinder {
  protected T target;

  @UiThread
  public NbOrderFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.tt = Utils.findRequiredViewAsType(source, R.id.tt, "field 'tt'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.tt = null;

    this.target = null;
  }
}
