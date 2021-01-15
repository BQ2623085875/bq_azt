// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.activity.azt.farmingInsurance;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NxReserveActivity_ViewBinding<T extends NxReserveActivity> implements Unbinder {
  protected T target;

  @UiThread
  public NxReserveActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.bank = Utils.findRequiredViewAsType(source, R.id.bank, "field 'bank'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.edName = Utils.findRequiredViewAsType(source, R.id.ed_name, "field 'edName'", EditText.class);
    target.edIdentity = Utils.findRequiredViewAsType(source, R.id.ed_identity, "field 'edIdentity'", EditText.class);
    target.edMushu = Utils.findRequiredViewAsType(source, R.id.ed_mushu, "field 'edMushu'", EditText.class);
    target.tvAddress = Utils.findRequiredViewAsType(source, R.id.tv_address, "field 'tvAddress'", TextView.class);
    target.Next = Utils.findRequiredViewAsType(source, R.id.next, "field 'Next'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.bank = null;
    target.title = null;
    target.edName = null;
    target.edIdentity = null;
    target.edMushu = null;
    target.tvAddress = null;
    target.Next = null;

    this.target = null;
  }
}
