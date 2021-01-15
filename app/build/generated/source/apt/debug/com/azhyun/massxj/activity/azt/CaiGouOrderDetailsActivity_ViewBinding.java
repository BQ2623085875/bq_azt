// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.activity.azt;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CaiGouOrderDetailsActivity_ViewBinding<T extends CaiGouOrderDetailsActivity> implements Unbinder {
  protected T target;

  @UiThread
  public CaiGouOrderDetailsActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.bank = Utils.findRequiredViewAsType(source, R.id.bank, "field 'bank'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.details_address = Utils.findRequiredViewAsType(source, R.id.details_address, "field 'details_address'", TextView.class);
    target.details_name = Utils.findRequiredViewAsType(source, R.id.details_name, "field 'details_name'", TextView.class);
    target.details_phone = Utils.findRequiredViewAsType(source, R.id.details_phone, "field 'details_phone'", TextView.class);
    target.details_canpay_name = Utils.findRequiredViewAsType(source, R.id.details_canpay_name, "field 'details_canpay_name'", TextView.class);
    target.details_comm_name = Utils.findRequiredViewAsType(source, R.id.details_comm_name, "field 'details_comm_name'", TextView.class);
    target.details_comm_price = Utils.findRequiredViewAsType(source, R.id.details_comm_price, "field 'details_comm_price'", TextView.class);
    target.details_comm_shuliang = Utils.findRequiredViewAsType(source, R.id.details_comm_shuliang, "field 'details_comm_shuliang'", TextView.class);
    target.details_comm_guige = Utils.findRequiredViewAsType(source, R.id.details_comm_guige, "field 'details_comm_guige'", TextView.class);
    target.details_order_code = Utils.findRequiredViewAsType(source, R.id.details_order_code, "field 'details_order_code'", TextView.class);
    target.details_order_tiem = Utils.findRequiredViewAsType(source, R.id.details_order_tiem, "field 'details_order_tiem'", TextView.class);
    target.details_qian_tiem = Utils.findRequiredViewAsType(source, R.id.details_qian_tiem, "field 'details_qian_tiem'", TextView.class);
    target.details_comm_but_price = Utils.findRequiredViewAsType(source, R.id.details_comm_but_price, "field 'details_comm_but_price'", TextView.class);
    target.details_yunfei = Utils.findRequiredViewAsType(source, R.id.details_yunfei, "field 'details_yunfei'", TextView.class);
    target.details_img = Utils.findRequiredViewAsType(source, R.id.details_img, "field 'details_img'", ImageView.class);
    target.details_heji = Utils.findRequiredViewAsType(source, R.id.details_heji, "field 'details_heji'", TextView.class);
    target.qiantiem_ll = Utils.findRequiredViewAsType(source, R.id.qiantiem_ll, "field 'qiantiem_ll'", LinearLayout.class);
    target.order_img = Utils.findRequiredViewAsType(source, R.id.order_img, "field 'order_img'", ImageView.class);
    target.order_name = Utils.findRequiredViewAsType(source, R.id.order_name, "field 'order_name'", TextView.class);
    target.orsder_item_nuitname = Utils.findRequiredViewAsType(source, R.id.orsder_item_nuitname, "field 'orsder_item_nuitname'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.bank = null;
    target.title = null;
    target.details_address = null;
    target.details_name = null;
    target.details_phone = null;
    target.details_canpay_name = null;
    target.details_comm_name = null;
    target.details_comm_price = null;
    target.details_comm_shuliang = null;
    target.details_comm_guige = null;
    target.details_order_code = null;
    target.details_order_tiem = null;
    target.details_qian_tiem = null;
    target.details_comm_but_price = null;
    target.details_yunfei = null;
    target.details_img = null;
    target.details_heji = null;
    target.qiantiem_ll = null;
    target.order_img = null;
    target.order_name = null;
    target.orsder_item_nuitname = null;

    this.target = null;
  }
}
