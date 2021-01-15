// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.activity.azt;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SubmitOrderActivity_ViewBinding<T extends SubmitOrderActivity> implements Unbinder {
  protected T target;

  @UiThread
  public SubmitOrderActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.bank = Utils.findRequiredViewAsType(source, R.id.bank, "field 'bank'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.submit_shuliang = Utils.findRequiredViewAsType(source, R.id.submit_shuliang, "field 'submit_shuliang'", EditText.class);
    target.submit_address = Utils.findRequiredViewAsType(source, R.id.submit_address, "field 'submit_address'", TextView.class);
    target.submit_user_name = Utils.findRequiredViewAsType(source, R.id.submit_user_name, "field 'submit_user_name'", TextView.class);
    target.submit_user_phone = Utils.findRequiredViewAsType(source, R.id.submit_user_phone, "field 'submit_user_phone'", TextView.class);
    target.submit_compaye = Utils.findRequiredViewAsType(source, R.id.submit_compaye, "field 'submit_compaye'", TextView.class);
    target.submit_commodity_name = Utils.findRequiredViewAsType(source, R.id.submit_commodity_name, "field 'submit_commodity_name'", TextView.class);
    target.submit_commodity_guige = Utils.findRequiredViewAsType(source, R.id.submit_commodity_guige, "field 'submit_commodity_guige'", TextView.class);
    target.submit_commodity_price = Utils.findRequiredViewAsType(source, R.id.submit_commodity_price, "field 'submit_commodity_price'", TextView.class);
    target.submit_commodity_jine = Utils.findRequiredViewAsType(source, R.id.submit_commodity_jine, "field 'submit_commodity_jine'", TextView.class);
    target.submit_commodity_yunfei = Utils.findRequiredViewAsType(source, R.id.submit_commodity_yunfei, "field 'submit_commodity_yunfei'", TextView.class);
    target.submit_heji = Utils.findRequiredViewAsType(source, R.id.submit_heji, "field 'submit_heji'", TextView.class);
    target.submit_bott_heji = Utils.findRequiredViewAsType(source, R.id.submit_bott_heji, "field 'submit_bott_heji'", TextView.class);
    target.submit_tijiao = Utils.findRequiredViewAsType(source, R.id.submit_tijiao, "field 'submit_tijiao'", RelativeLayout.class);
    target.submit_commodity_jianhao = Utils.findRequiredViewAsType(source, R.id.submit_commodity_jianhao, "field 'submit_commodity_jianhao'", LinearLayout.class);
    target.submit_commodity_jiahao = Utils.findRequiredViewAsType(source, R.id.submit_commodity_jiahao, "field 'submit_commodity_jiahao'", LinearLayout.class);
    target.submit_commodity_img = Utils.findRequiredViewAsType(source, R.id.submit_commodity_img, "field 'submit_commodity_img'", ImageView.class);
    target.gongjan = Utils.findRequiredViewAsType(source, R.id.gongjan, "field 'gongjan'", TextView.class);
    target.danwei = Utils.findRequiredViewAsType(source, R.id.danwei, "field 'danwei'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.bank = null;
    target.title = null;
    target.submit_shuliang = null;
    target.submit_address = null;
    target.submit_user_name = null;
    target.submit_user_phone = null;
    target.submit_compaye = null;
    target.submit_commodity_name = null;
    target.submit_commodity_guige = null;
    target.submit_commodity_price = null;
    target.submit_commodity_jine = null;
    target.submit_commodity_yunfei = null;
    target.submit_heji = null;
    target.submit_bott_heji = null;
    target.submit_tijiao = null;
    target.submit_commodity_jianhao = null;
    target.submit_commodity_jiahao = null;
    target.submit_commodity_img = null;
    target.gongjan = null;
    target.danwei = null;

    this.target = null;
  }
}
