// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UserInfoAlterActivity_ViewBinding<T extends UserInfoAlterActivity> implements Unbinder {
  protected T target;

  @UiThread
  public UserInfoAlterActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.bank = Utils.findRequiredViewAsType(source, R.id.bank, "field 'bank'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.edt = Utils.findRequiredViewAsType(source, R.id.edt, "field 'edt'", EditText.class);
    target.imgDelete = Utils.findRequiredViewAsType(source, R.id.img_delete, "field 'imgDelete'", ImageView.class);
    target.btnAlter = Utils.findRequiredViewAsType(source, R.id.btn_alter, "field 'btnAlter'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.bank = null;
    target.title = null;
    target.edt = null;
    target.imgDelete = null;
    target.btnAlter = null;

    this.target = null;
  }
}
