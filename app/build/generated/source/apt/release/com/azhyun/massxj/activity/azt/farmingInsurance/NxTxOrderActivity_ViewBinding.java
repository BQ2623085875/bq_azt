// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.activity.azt.farmingInsurance;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NxTxOrderActivity_ViewBinding<T extends NxTxOrderActivity> implements Unbinder {
  protected T target;

  @UiThread
  public NxTxOrderActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.bank = Utils.findRequiredViewAsType(source, R.id.bank, "field 'bank'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.nb_address = Utils.findRequiredViewAsType(source, R.id.nb_address, "field 'nb_address'", TextView.class);
    target.nb_user_name = Utils.findRequiredViewAsType(source, R.id.nb_user_name, "field 'nb_user_name'", TextView.class);
    target.nb_user_phone = Utils.findRequiredViewAsType(source, R.id.nb_user_phone, "field 'nb_user_phone'", TextView.class);
    target.nb_compaye = Utils.findRequiredViewAsType(source, R.id.nb_compaye, "field 'nb_compaye'", TextView.class);
    target.nb_name = Utils.findRequiredViewAsType(source, R.id.nb_name, "field 'nb_name'", TextView.class);
    target.nb_img = Utils.findRequiredViewAsType(source, R.id.nb_img, "field 'nb_img'", ImageView.class);
    target.nb_meimu = Utils.findRequiredViewAsType(source, R.id.nb_meimu, "field 'nb_meimu'", TextView.class);
    target.nb_zeren = Utils.findRequiredViewAsType(source, R.id.nb_zeren, "field 'nb_zeren'", TextView.class);
    target.nb_jin = Utils.findRequiredViewAsType(source, R.id.nb_jine, "field 'nb_jin'", TextView.class);
    target.nb_mushu = Utils.findRequiredViewAsType(source, R.id.nb_mushu, "field 'nb_mushu'", TextView.class);
    target.number = Utils.findRequiredViewAsType(source, R.id.number, "field 'number'", TextView.class);
    target.nb_heji = Utils.findRequiredViewAsType(source, R.id.nb_heji, "field 'nb_heji'", TextView.class);
    target.nb_xia_heji = Utils.findRequiredViewAsType(source, R.id.nb_xia_heji, "field 'nb_xia_heji'", TextView.class);
    target.tv_nongyuzhi = Utils.findRequiredViewAsType(source, R.id.tv_nongyuzhi, "field 'tv_nongyuzhi'", TextView.class);
    target.nb_tijiao = Utils.findRequiredViewAsType(source, R.id.nb_tijiao, "field 'nb_tijiao'", RelativeLayout.class);
    target.nongyuzhi_rl = Utils.findRequiredViewAsType(source, R.id.nongyuzhi_rl, "field 'nongyuzhi_rl'", RelativeLayout.class);
    target.tv_nongyuzhi_buman = Utils.findRequiredViewAsType(source, R.id.tv_nongyuzhi_buman, "field 'tv_nongyuzhi_buman'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.bank = null;
    target.title = null;
    target.nb_address = null;
    target.nb_user_name = null;
    target.nb_user_phone = null;
    target.nb_compaye = null;
    target.nb_name = null;
    target.nb_img = null;
    target.nb_meimu = null;
    target.nb_zeren = null;
    target.nb_jin = null;
    target.nb_mushu = null;
    target.number = null;
    target.nb_heji = null;
    target.nb_xia_heji = null;
    target.tv_nongyuzhi = null;
    target.nb_tijiao = null;
    target.nongyuzhi_rl = null;
    target.tv_nongyuzhi_buman = null;

    this.target = null;
  }
}
