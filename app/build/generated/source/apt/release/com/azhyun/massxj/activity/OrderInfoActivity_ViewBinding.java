// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import com.youth.banner.Banner;
import com.zhy.autolayout.AutoLinearLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OrderInfoActivity_ViewBinding<T extends OrderInfoActivity> implements Unbinder {
  protected T target;

  @UiThread
  public OrderInfoActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.bank = Utils.findRequiredViewAsType(source, R.id.bank, "field 'bank'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.imgSubit = Utils.findRequiredViewAsType(source, R.id.image_submit, "field 'imgSubit'", ImageView.class);
    target.imgConfirm = Utils.findRequiredViewAsType(source, R.id.img_confirm, "field 'imgConfirm'", ImageView.class);
    target.text1 = Utils.findRequiredViewAsType(source, R.id.text1, "field 'text1'", TextView.class);
    target.btn = Utils.findRequiredViewAsType(source, R.id.btn, "field 'btn'", Button.class);
    target.mBanner = Utils.findRequiredViewAsType(source, R.id.mbanner, "field 'mBanner'", Banner.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvPrice = Utils.findRequiredViewAsType(source, R.id.tv_price, "field 'tvPrice'", TextView.class);
    target.tvNorms = Utils.findRequiredViewAsType(source, R.id.tv_norms, "field 'tvNorms'", TextView.class);
    target.tvSubmit = Utils.findRequiredViewAsType(source, R.id.tv_submit, "field 'tvSubmit'", TextView.class);
    target.tvConfirm = Utils.findRequiredViewAsType(source, R.id.tv_confirm, "field 'tvConfirm'", TextView.class);
    target.text2 = Utils.findRequiredViewAsType(source, R.id.text2, "field 'text2'", TextView.class);
    target.imgFukuan = Utils.findRequiredViewAsType(source, R.id.img_fukuan, "field 'imgFukuan'", ImageView.class);
    target.tvFukuan = Utils.findRequiredViewAsType(source, R.id.tv_fukuan, "field 'tvFukuan'", TextView.class);
    target.text3 = Utils.findRequiredViewAsType(source, R.id.text3, "field 'text3'", TextView.class);
    target.imgService = Utils.findRequiredViewAsType(source, R.id.img_service, "field 'imgService'", ImageView.class);
    target.tvService = Utils.findRequiredViewAsType(source, R.id.tv_service, "field 'tvService'", TextView.class);
    target.text4 = Utils.findRequiredViewAsType(source, R.id.text4, "field 'text4'", TextView.class);
    target.imgReview = Utils.findRequiredViewAsType(source, R.id.img_review, "field 'imgReview'", ImageView.class);
    target.tvReview = Utils.findRequiredViewAsType(source, R.id.tv_review, "field 'tvReview'", TextView.class);
    target.tvNumber = Utils.findRequiredViewAsType(source, R.id.tv_number, "field 'tvNumber'", TextView.class);
    target.tvArea = Utils.findRequiredViewAsType(source, R.id.tv_area, "field 'tvArea'", TextView.class);
    target.tvDescribe = Utils.findRequiredViewAsType(source, R.id.tv_describe, "field 'tvDescribe'", TextView.class);
    target.tvTime = Utils.findRequiredViewAsType(source, R.id.tv_time, "field 'tvTime'", TextView.class);
    target.tvOrderPrice = Utils.findRequiredViewAsType(source, R.id.tv_order_price, "field 'tvOrderPrice'", TextView.class);
    target.tvOrderName = Utils.findRequiredViewAsType(source, R.id.tv_order_name, "field 'tvOrderName'", TextView.class);
    target.lineOrderName = Utils.findRequiredViewAsType(source, R.id.line_order_name, "field 'lineOrderName'", AutoLinearLayout.class);
    target.tvPhone = Utils.findRequiredViewAsType(source, R.id.tv_phone, "field 'tvPhone'", TextView.class);
    target.linePhone = Utils.findRequiredViewAsType(source, R.id.line_phone, "field 'linePhone'", AutoLinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.bank = null;
    target.title = null;
    target.imgSubit = null;
    target.imgConfirm = null;
    target.text1 = null;
    target.btn = null;
    target.mBanner = null;
    target.tvName = null;
    target.tvPrice = null;
    target.tvNorms = null;
    target.tvSubmit = null;
    target.tvConfirm = null;
    target.text2 = null;
    target.imgFukuan = null;
    target.tvFukuan = null;
    target.text3 = null;
    target.imgService = null;
    target.tvService = null;
    target.text4 = null;
    target.imgReview = null;
    target.tvReview = null;
    target.tvNumber = null;
    target.tvArea = null;
    target.tvDescribe = null;
    target.tvTime = null;
    target.tvOrderPrice = null;
    target.tvOrderName = null;
    target.lineOrderName = null;
    target.tvPhone = null;
    target.linePhone = null;

    this.target = null;
  }
}
