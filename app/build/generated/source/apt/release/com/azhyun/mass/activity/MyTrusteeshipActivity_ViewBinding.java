// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import com.azhyun.massxj.utils.AlignTextView;
import com.zhy.autolayout.AutoLinearLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyTrusteeshipActivity_ViewBinding<T extends MyTrusteeshipActivity> implements Unbinder {
  protected T target;

  @UiThread
  public MyTrusteeshipActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.bank = Utils.findRequiredViewAsType(source, R.id.bank, "field 'bank'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.edtTitle = Utils.findRequiredViewAsType(source, R.id.edt_title, "field 'edtTitle'", EditText.class);
    target.edtName = Utils.findRequiredViewAsType(source, R.id.edt_name, "field 'edtName'", EditText.class);
    target.edtPhone = Utils.findRequiredViewAsType(source, R.id.edt_phone, "field 'edtPhone'", EditText.class);
    target.selectedArea = Utils.findRequiredViewAsType(source, R.id.selected_area, "field 'selectedArea'", AutoLinearLayout.class);
    target.textArea = Utils.findRequiredViewAsType(source, R.id.text_area, "field 'textArea'", TextView.class);
    target.edtAddress = Utils.findRequiredViewAsType(source, R.id.edt_address, "field 'edtAddress'", EditText.class);
    target.edtAcreage = Utils.findRequiredViewAsType(source, R.id.edt_acreage, "field 'edtAcreage'", EditText.class);
    target.describe = Utils.findRequiredViewAsType(source, R.id.describe, "field 'describe'", AlignTextView.class);
    target.edtDescribe = Utils.findRequiredViewAsType(source, R.id.edt_describe, "field 'edtDescribe'", EditText.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
    target.btnSubmit = Utils.findRequiredViewAsType(source, R.id.btn_submit, "field 'btnSubmit'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.bank = null;
    target.title = null;
    target.edtTitle = null;
    target.edtName = null;
    target.edtPhone = null;
    target.selectedArea = null;
    target.textArea = null;
    target.edtAddress = null;
    target.edtAcreage = null;
    target.describe = null;
    target.edtDescribe = null;
    target.recyclerView = null;
    target.btnSubmit = null;

    this.target = null;
  }
}
