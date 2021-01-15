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

public class NbOrderDetailsActivity_ViewBinding<T extends NbOrderDetailsActivity> implements Unbinder {
  protected T target;

  @UiThread
  public NbOrderDetailsActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.bank = Utils.findRequiredViewAsType(source, R.id.bank, "field 'bank'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.nb_order_tu_tv = Utils.findRequiredViewAsType(source, R.id.nb_order_tu_tv, "field 'nb_order_tu_tv'", TextView.class);
    target.nb_order_address = Utils.findRequiredViewAsType(source, R.id.nb_order_address, "field 'nb_order_address'", TextView.class);
    target.nb_order_user_name = Utils.findRequiredViewAsType(source, R.id.nb_order_user_name, "field 'nb_order_user_name'", TextView.class);
    target.nb_order_user_phone = Utils.findRequiredViewAsType(source, R.id.nb_order_user_phone, "field 'nb_order_user_phone'", TextView.class);
    target.nb_order_number = Utils.findRequiredViewAsType(source, R.id.nb_order_number, "field 'nb_order_number'", TextView.class);
    target.nb_order_canpay_name = Utils.findRequiredViewAsType(source, R.id.nb_order_canpay_name, "field 'nb_order_canpay_name'", TextView.class);
    target.nb_order_name = Utils.findRequiredViewAsType(source, R.id.nb_order_name, "field 'nb_order_name'", TextView.class);
    target.nb_order_jine = Utils.findRequiredViewAsType(source, R.id.nb_order_jine, "field 'nb_order_jine'", TextView.class);
    target.nb_order_zeren = Utils.findRequiredViewAsType(source, R.id.nb_order_zeren, "field 'nb_order_zeren'", TextView.class);
    target.nb_order_img = Utils.findRequiredViewAsType(source, R.id.nb_order_img, "field 'nb_order_img'", ImageView.class);
    target.nb_order_tu = Utils.findRequiredViewAsType(source, R.id.nb_order_tu, "field 'nb_order_tu'", ImageView.class);
    target.nb_order_dingbainhao = Utils.findRequiredViewAsType(source, R.id.nb_order_dingbainhao, "field 'nb_order_dingbainhao'", TextView.class);
    target.nb_order_xiadan_tiem = Utils.findRequiredViewAsType(source, R.id.nb_order_xiadan_tiem, "field 'nb_order_xiadan_tiem'", TextView.class);
    target.nb_order_toubao_tiem = Utils.findRequiredViewAsType(source, R.id.nb_order_toubao_tiem, "field 'nb_order_toubao_tiem'", TextView.class);
    target.nb_order_price = Utils.findRequiredViewAsType(source, R.id.nb_order_price, "field 'nb_order_price'", TextView.class);
    target.nb_order_mushu = Utils.findRequiredViewAsType(source, R.id.nb_order_mushu, "field 'nb_order_mushu'", TextView.class);
    target.nb_order_nongyuzhi = Utils.findRequiredViewAsType(source, R.id.nb_order_nongyuzhi, "field 'nb_order_nongyuzhi'", TextView.class);
    target.nb_order_heji = Utils.findRequiredViewAsType(source, R.id.nb_order_heji, "field 'nb_order_heji'", TextView.class);
    target.nb_order_shengxiao = Utils.findRequiredViewAsType(source, R.id.nb_order_shengxiao, "field 'nb_order_shengxiao'", TextView.class);
    target.nb_order_toubao_tiem_ll = Utils.findRequiredViewAsType(source, R.id.nb_order_toubao_tiem_ll, "field 'nb_order_toubao_tiem_ll'", LinearLayout.class);
    target.nb_order_shengxiao_ll = Utils.findRequiredViewAsType(source, R.id.nb_order_shengxiao_ll, "field 'nb_order_shengxiao_ll'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.bank = null;
    target.title = null;
    target.nb_order_tu_tv = null;
    target.nb_order_address = null;
    target.nb_order_user_name = null;
    target.nb_order_user_phone = null;
    target.nb_order_number = null;
    target.nb_order_canpay_name = null;
    target.nb_order_name = null;
    target.nb_order_jine = null;
    target.nb_order_zeren = null;
    target.nb_order_img = null;
    target.nb_order_tu = null;
    target.nb_order_dingbainhao = null;
    target.nb_order_xiadan_tiem = null;
    target.nb_order_toubao_tiem = null;
    target.nb_order_price = null;
    target.nb_order_mushu = null;
    target.nb_order_nongyuzhi = null;
    target.nb_order_heji = null;
    target.nb_order_shengxiao = null;
    target.nb_order_toubao_tiem_ll = null;
    target.nb_order_shengxiao_ll = null;

    this.target = null;
  }
}
