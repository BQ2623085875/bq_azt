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

public class FarmingServiceOrderActivity_ViewBinding<T extends FarmingServiceOrderActivity> implements Unbinder {
  protected T target;

  @UiThread
  public FarmingServiceOrderActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.bank = Utils.findRequiredViewAsType(source, R.id.bank, "field 'bank'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.img = Utils.findRequiredViewAsType(source, R.id.img, "field 'img'", ImageView.class);
    target.tvClassify = Utils.findRequiredViewAsType(source, R.id.tv_classify, "field 'tvClassify'", TextView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvPrice = Utils.findRequiredViewAsType(source, R.id.tv_price, "field 'tvPrice'", TextView.class);
    target.edtNumber = Utils.findRequiredViewAsType(source, R.id.edt_number, "field 'edtNumber'", EditText.class);
    target.tvTime = Utils.findRequiredViewAsType(source, R.id.tv_time, "field 'tvTime'", TextView.class);
    target.tvArea = Utils.findRequiredViewAsType(source, R.id.tv_area, "field 'tvArea'", TextView.class);
    target.tvNewPrice = Utils.findRequiredViewAsType(source, R.id.tv_new_price, "field 'tvNewPrice'", TextView.class);
    target.btnSubmit = Utils.findRequiredViewAsType(source, R.id.btn_submit, "field 'btnSubmit'", Button.class);
    target.editName = Utils.findRequiredViewAsType(source, R.id.edit_name, "field 'editName'", EditText.class);
    target.editPhone = Utils.findRequiredViewAsType(source, R.id.edit_phone, "field 'editPhone'", EditText.class);
    target.editRemarks = Utils.findRequiredViewAsType(source, R.id.edt_remarks, "field 'editRemarks'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.bank = null;
    target.title = null;
    target.img = null;
    target.tvClassify = null;
    target.tvName = null;
    target.tvPrice = null;
    target.edtNumber = null;
    target.tvTime = null;
    target.tvArea = null;
    target.tvNewPrice = null;
    target.btnSubmit = null;
    target.editName = null;
    target.editPhone = null;
    target.editRemarks = null;

    this.target = null;
  }
}
