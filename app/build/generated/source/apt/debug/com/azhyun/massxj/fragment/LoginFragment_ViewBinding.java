// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import com.azhyun.massxj.view.DownButton;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginFragment_ViewBinding<T extends LoginFragment> implements Unbinder {
  protected T target;

  @UiThread
  public LoginFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.textPhone = Utils.findRequiredViewAsType(source, R.id.text_phone, "field 'textPhone'", TextView.class);
    target.btnLogin = Utils.findRequiredViewAsType(source, R.id.btn_login, "field 'btnLogin'", Button.class);
    target.edtHone = Utils.findRequiredViewAsType(source, R.id.edt_hone, "field 'edtHone'", EditText.class);
    target.edtCode = Utils.findRequiredViewAsType(source, R.id.edt_code, "field 'edtCode'", EditText.class);
    target.getCode = Utils.findRequiredViewAsType(source, R.id.get_code, "field 'getCode'", DownButton.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.textPhone = null;
    target.btnLogin = null;
    target.edtHone = null;
    target.edtCode = null;
    target.getCode = null;

    this.target = null;
  }
}
