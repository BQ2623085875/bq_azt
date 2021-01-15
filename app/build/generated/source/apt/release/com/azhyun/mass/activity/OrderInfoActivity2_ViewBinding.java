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
import com.zhy.autolayout.AutoLinearLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OrderInfoActivity2_ViewBinding<T extends OrderInfoActivity2> implements Unbinder {
  protected T target;

  @UiThread
  public OrderInfoActivity2_ViewBinding(T target, View source) {
    this.target = target;

    target.bank = Utils.findRequiredViewAsType(source, R.id.bank, "field 'bank'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.imageSubmit = Utils.findRequiredViewAsType(source, R.id.image_submit, "field 'imageSubmit'", ImageView.class);
    target.tvSubmit = Utils.findRequiredViewAsType(source, R.id.tv_submit, "field 'tvSubmit'", TextView.class);
    target.text1 = Utils.findRequiredViewAsType(source, R.id.text1, "field 'text1'", TextView.class);
    target.imgConfirm = Utils.findRequiredViewAsType(source, R.id.img_confirm, "field 'imgConfirm'", ImageView.class);
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
    target.img = Utils.findRequiredViewAsType(source, R.id.img, "field 'img'", ImageView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvIntroduction = Utils.findRequiredViewAsType(source, R.id.tv_introduction, "field 'tvIntroduction'", TextView.class);
    target.tvDescribe = Utils.findRequiredViewAsType(source, R.id.tv_describe, "field 'tvDescribe'", TextView.class);
    target.tvPrice = Utils.findRequiredViewAsType(source, R.id.tv_price, "field 'tvPrice'", TextView.class);
    target.tvBookingName = Utils.findRequiredViewAsType(source, R.id.tv_booking_name, "field 'tvBookingName'", TextView.class);
    target.tvUserPhone = Utils.findRequiredViewAsType(source, R.id.tv_user_phone, "field 'tvUserPhone'", TextView.class);
    target.tvArea = Utils.findRequiredViewAsType(source, R.id.tv_area, "field 'tvArea'", TextView.class);
    target.tvNum = Utils.findRequiredViewAsType(source, R.id.tv_num, "field 'tvNum'", TextView.class);
    target.tvAllPrice = Utils.findRequiredViewAsType(source, R.id.tv_all_price, "field 'tvAllPrice'", TextView.class);
    target.tvCodeId = Utils.findRequiredViewAsType(source, R.id.tv_code_id, "field 'tvCodeId'", TextView.class);
    target.lineCodeId = Utils.findRequiredViewAsType(source, R.id.line_code_id, "field 'lineCodeId'", AutoLinearLayout.class);
    target.tvSubmitTime = Utils.findRequiredViewAsType(source, R.id.tv_submit_time, "field 'tvSubmitTime'", TextView.class);
    target.lineSubmitTime = Utils.findRequiredViewAsType(source, R.id.line_submit_time, "field 'lineSubmitTime'", AutoLinearLayout.class);
    target.tvConfirmTime = Utils.findRequiredViewAsType(source, R.id.tv_confirm_time, "field 'tvConfirmTime'", TextView.class);
    target.lineConfirmTime = Utils.findRequiredViewAsType(source, R.id.line_confirm_time, "field 'lineConfirmTime'", AutoLinearLayout.class);
    target.tvConfirmService = Utils.findRequiredViewAsType(source, R.id.tv_confirm_service, "field 'tvConfirmService'", TextView.class);
    target.lineConfirmService = Utils.findRequiredViewAsType(source, R.id.line_confirm_service, "field 'lineConfirmService'", AutoLinearLayout.class);
    target.tvPayTime = Utils.findRequiredViewAsType(source, R.id.tv_pay_time, "field 'tvPayTime'", TextView.class);
    target.tvRemark = Utils.findRequiredViewAsType(source, R.id.tv_remark, "field 'tvRemark'", TextView.class);
    target.lineBottm = Utils.findRequiredViewAsType(source, R.id.line_bottm, "field 'lineBottm'", AutoLinearLayout.class);
    target.linePayTime = Utils.findRequiredViewAsType(source, R.id.line_pay_time, "field 'linePayTime'", AutoLinearLayout.class);
    target.btn = Utils.findRequiredViewAsType(source, R.id.btn, "field 'btn'", Button.class);
    target.btn1 = Utils.findRequiredViewAsType(source, R.id.btn1, "field 'btn1'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.bank = null;
    target.title = null;
    target.imageSubmit = null;
    target.tvSubmit = null;
    target.text1 = null;
    target.imgConfirm = null;
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
    target.img = null;
    target.tvName = null;
    target.tvIntroduction = null;
    target.tvDescribe = null;
    target.tvPrice = null;
    target.tvBookingName = null;
    target.tvUserPhone = null;
    target.tvArea = null;
    target.tvNum = null;
    target.tvAllPrice = null;
    target.tvCodeId = null;
    target.lineCodeId = null;
    target.tvSubmitTime = null;
    target.lineSubmitTime = null;
    target.tvConfirmTime = null;
    target.lineConfirmTime = null;
    target.tvConfirmService = null;
    target.lineConfirmService = null;
    target.tvPayTime = null;
    target.tvRemark = null;
    target.lineBottm = null;
    target.linePayTime = null;
    target.btn = null;
    target.btn1 = null;

    this.target = null;
  }
}
