// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import com.zhy.autolayout.AutoRelativeLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ReleaseTheSupplyPubackActivity_ViewBinding<T extends ReleaseTheSupplyPubackActivity> implements Unbinder {
  protected T target;

  @UiThread
  public ReleaseTheSupplyPubackActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.textSite = Utils.findRequiredViewAsType(source, R.id.text_site, "field 'textSite'", TextView.class);
    target.aupplyShipAddress = Utils.findRequiredViewAsType(source, R.id.aupply_ship_address, "field 'aupplyShipAddress'", AutoRelativeLayout.class);
    target.textNextStep = Utils.findRequiredViewAsType(source, R.id.text_confirm_the_delivery, "field 'textNextStep'", TextView.class);
    target.bank = Utils.findRequiredViewAsType(source, R.id.bank, "field 'bank'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.edtName = Utils.findRequiredViewAsType(source, R.id.edt_name, "field 'edtName'", TextInputEditText.class);
    target.edtPhone = Utils.findRequiredViewAsType(source, R.id.edt_phone, "field 'edtPhone'", TextInputEditText.class);
    target.edtAddress = Utils.findRequiredViewAsType(source, R.id.edt_address, "field 'edtAddress'", TextInputEditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.textSite = null;
    target.aupplyShipAddress = null;
    target.textNextStep = null;
    target.bank = null;
    target.title = null;
    target.edtName = null;
    target.edtPhone = null;
    target.edtAddress = null;

    this.target = null;
  }
}
