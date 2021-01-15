// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.activity;

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

public class UserInfoPhoneAlterActivity_ViewBinding<T extends UserInfoPhoneAlterActivity> implements Unbinder {
  protected T target;

  @UiThread
  public UserInfoPhoneAlterActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.bank = Utils.findRequiredViewAsType(source, R.id.bank, "field 'bank'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.titleNext = Utils.findRequiredViewAsType(source, R.id.title_next, "field 'titleNext'", TextView.class);
    target.tvContent = Utils.findRequiredViewAsType(source, R.id.tv_content, "field 'tvContent'", TextView.class);
    target.editNewPhone = Utils.findRequiredViewAsType(source, R.id.edit_new_phone, "field 'editNewPhone'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.bank = null;
    target.title = null;
    target.titleNext = null;
    target.tvContent = null;
    target.editNewPhone = null;

    this.target = null;
  }
}
