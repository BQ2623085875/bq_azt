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

public class ApplyForInfoActivity_ViewBinding<T extends ApplyForInfoActivity> implements Unbinder {
  protected T target;

  @UiThread
  public ApplyForInfoActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.bank = Utils.findRequiredViewAsType(source, R.id.bank, "field 'bank'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.mBanner = Utils.findRequiredViewAsType(source, R.id.banner, "field 'mBanner'", Banner.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.tvAddress = Utils.findRequiredViewAsType(source, R.id.tv_address, "field 'tvAddress'", TextView.class);
    target.tvArea = Utils.findRequiredViewAsType(source, R.id.tv_area, "field 'tvArea'", TextView.class);
    target.tvInfo = Utils.findRequiredViewAsType(source, R.id.tv_info, "field 'tvInfo'", TextView.class);
    target.tvType = Utils.findRequiredViewAsType(source, R.id.tv_type, "field 'tvType'", TextView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvPhone = Utils.findRequiredViewAsType(source, R.id.tv_phone, "field 'tvPhone'", TextView.class);
    target.btnPass = Utils.findRequiredViewAsType(source, R.id.btn_pass, "field 'btnPass'", Button.class);
    target.btnReject = Utils.findRequiredViewAsType(source, R.id.btn_reject, "field 'btnReject'", Button.class);
    target.btnUploadImg = Utils.findRequiredViewAsType(source, R.id.btn_upload_img, "field 'btnUploadImg'", Button.class);
    target.lineBottomBtn = Utils.findRequiredViewAsType(source, R.id.line_bottom_btn, "field 'lineBottomBtn'", AutoLinearLayout.class);
    target.lineImg = Utils.findRequiredViewAsType(source, R.id.line_img, "field 'lineImg'", AutoLinearLayout.class);
    target.lineCustomer = Utils.findRequiredViewAsType(source, R.id.line_customer, "field 'lineCustomer'", AutoLinearLayout.class);
    target.tvReason = Utils.findRequiredViewAsType(source, R.id.tv_reason, "field 'tvReason'", TextView.class);
    target.lineRefuse = Utils.findRequiredViewAsType(source, R.id.line_refuse, "field 'lineRefuse'", AutoLinearLayout.class);
    target.imgAgreement = Utils.findRequiredViewAsType(source, R.id.img_agreement, "field 'imgAgreement'", ImageView.class);
    target.imgCertificate = Utils.findRequiredViewAsType(source, R.id.img_certificate, "field 'imgCertificate'", ImageView.class);
    target.imgIdCard = Utils.findRequiredViewAsType(source, R.id.img_id_card, "field 'imgIdCard'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.bank = null;
    target.title = null;
    target.mBanner = null;
    target.tvTitle = null;
    target.tvAddress = null;
    target.tvArea = null;
    target.tvInfo = null;
    target.tvType = null;
    target.tvName = null;
    target.tvPhone = null;
    target.btnPass = null;
    target.btnReject = null;
    target.btnUploadImg = null;
    target.lineBottomBtn = null;
    target.lineImg = null;
    target.lineCustomer = null;
    target.tvReason = null;
    target.lineRefuse = null;
    target.imgAgreement = null;
    target.imgCertificate = null;
    target.imgIdCard = null;

    this.target = null;
  }
}
