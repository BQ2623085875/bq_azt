// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.activity.azt;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SHIActivity_ViewBinding<T extends SHIActivity> implements Unbinder {
  protected T target;

  @UiThread
  public SHIActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.ed = Utils.findRequiredViewAsType(source, R.id.ed, "field 'ed'", EditText.class);
    target.bt = Utils.findRequiredViewAsType(source, R.id.bt, "field 'bt'", Button.class);
    target.tv = Utils.findRequiredViewAsType(source, R.id.tv, "field 'tv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ed = null;
    target.bt = null;
    target.tv = null;

    this.target = null;
  }
}
