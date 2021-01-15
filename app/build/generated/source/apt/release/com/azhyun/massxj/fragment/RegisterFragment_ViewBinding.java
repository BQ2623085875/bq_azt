// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import com.azhyun.massxj.view.DownButton;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RegisterFragment_ViewBinding<T extends RegisterFragment> implements Unbinder {
  protected T target;

  @UiThread
  public RegisterFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.textPhone = Utils.findRequiredViewAsType(source, R.id.text_phone, "field 'textPhone'", TextView.class);
    target.edtPhone = Utils.findRequiredViewAsType(source, R.id.edt_phone, "field 'edtPhone'", EditText.class);
    target.selectedArea = Utils.findRequiredViewAsType(source, R.id.selected_area, "field 'selectedArea'", LinearLayout.class);
    target.tvArea = Utils.findRequiredViewAsType(source, R.id.tv_area, "field 'tvArea'", TextView.class);
    target.edtLinkman = Utils.findRequiredViewAsType(source, R.id.edt_linkman, "field 'edtLinkman'", EditText.class);
    target.btnRegister = Utils.findRequiredViewAsType(source, R.id.btn_register, "field 'btnRegister'", Button.class);
    target.edtCode = Utils.findRequiredViewAsType(source, R.id.edt_code, "field 'edtCode'", EditText.class);
    target.getCode = Utils.findRequiredViewAsType(source, R.id.get_code, "field 'getCode'", DownButton.class);
    target.tuijianren = Utils.findRequiredViewAsType(source, R.id.tuijianren, "field 'tuijianren'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.textPhone = null;
    target.edtPhone = null;
    target.selectedArea = null;
    target.tvArea = null;
    target.edtLinkman = null;
    target.btnRegister = null;
    target.edtCode = null;
    target.getCode = null;
    target.tuijianren = null;

    this.target = null;
  }
}
