// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ApplyForResultActivity_ViewBinding<T extends ApplyForResultActivity> implements Unbinder {
  protected T target;

  @UiThread
  public ApplyForResultActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.bank = Utils.findRequiredViewAsType(source, R.id.bank, "field 'bank'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.linePass = Utils.findRequiredViewAsType(source, R.id.line_pass, "field 'linePass'", LinearLayout.class);
    target.lineReject = Utils.findRequiredViewAsType(source, R.id.line_reject, "field 'lineReject'", LinearLayout.class);
    target.btnBack = Utils.findRequiredViewAsType(source, R.id.btn_back, "field 'btnBack'", Button.class);
    target.btnInfo = Utils.findRequiredViewAsType(source, R.id.btn_info, "field 'btnInfo'", Button.class);
    target.pass = Utils.findRequiredViewAsType(source, R.id.pass, "field 'pass'", TextView.class);
    target.reject = Utils.findRequiredViewAsType(source, R.id.reject, "field 'reject'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.bank = null;
    target.title = null;
    target.linePass = null;
    target.lineReject = null;
    target.btnBack = null;
    target.btnInfo = null;
    target.pass = null;
    target.reject = null;

    this.target = null;
  }
}
