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
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PhoneAlterVerifyAcitvity_ViewBinding<T extends PhoneAlterVerifyAcitvity> implements Unbinder {
  protected T target;

  @UiThread
  public PhoneAlterVerifyAcitvity_ViewBinding(T target, View source) {
    this.target = target;

    target.bank = Utils.findRequiredViewAsType(source, R.id.bank, "field 'bank'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.relationTitle = Utils.findRequiredViewAsType(source, R.id.relation_title, "field 'relationTitle'", AutoRelativeLayout.class);
    target.tvContent = Utils.findRequiredViewAsType(source, R.id.tv_content, "field 'tvContent'", TextView.class);
    target.tvPhone = Utils.findRequiredViewAsType(source, R.id.tv_phone, "field 'tvPhone'", TextView.class);
    target.linePhone = Utils.findRequiredViewAsType(source, R.id.line_phone, "field 'linePhone'", AutoLinearLayout.class);
    target.tvCode = Utils.findRequiredViewAsType(source, R.id.tv_code, "field 'tvCode'", EditText.class);
    target.tvSubmit = Utils.findRequiredViewAsType(source, R.id.tv_submit, "field 'tvSubmit'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.bank = null;
    target.title = null;
    target.relationTitle = null;
    target.tvContent = null;
    target.tvPhone = null;
    target.linePhone = null;
    target.tvCode = null;
    target.tvSubmit = null;

    this.target = null;
  }
}
