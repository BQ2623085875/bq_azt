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

public class AgentInfoActivity_ViewBinding<T extends AgentInfoActivity> implements Unbinder {
  protected T target;

  @UiThread
  public AgentInfoActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.bank = Utils.findRequiredViewAsType(source, R.id.bank, "field 'bank'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.tvRole = Utils.findRequiredViewAsType(source, R.id.tv_role, "field 'tvRole'", TextView.class);
    target.tvStatus = Utils.findRequiredViewAsType(source, R.id.tv_status, "field 'tvStatus'", TextView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvSex = Utils.findRequiredViewAsType(source, R.id.tv_sex, "field 'tvSex'", TextView.class);
    target.tvPhone = Utils.findRequiredViewAsType(source, R.id.tv_phone, "field 'tvPhone'", TextView.class);
    target.tvAddress = Utils.findRequiredViewAsType(source, R.id.tv_address, "field 'tvAddress'", TextView.class);
    target.tvRegion = Utils.findRequiredViewAsType(source, R.id.tv_region, "field 'tvRegion'", TextView.class);
    target.tvArea = Utils.findRequiredViewAsType(source, R.id.tv_area, "field 'tvArea'", TextView.class);
    target.tvPersonalProfile = Utils.findRequiredViewAsType(source, R.id.tv_personal_profile, "field 'tvPersonalProfile'", TextView.class);
    target.lineNote = Utils.findRequiredViewAsType(source, R.id.line_note, "field 'lineNote'", AutoLinearLayout.class);
    target.tvNote = Utils.findRequiredViewAsType(source, R.id.tv_note, "field 'tvNote'", TextView.class);
    target.btnPass = Utils.findRequiredViewAsType(source, R.id.btn_pass, "field 'btnPass'", Button.class);
    target.btnReject = Utils.findRequiredViewAsType(source, R.id.btn_reject, "field 'btnReject'", Button.class);
    target.lineBottomBtn = Utils.findRequiredViewAsType(source, R.id.line_bottom_btn, "field 'lineBottomBtn'", AutoLinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.bank = null;
    target.title = null;
    target.tvRole = null;
    target.tvStatus = null;
    target.tvName = null;
    target.tvSex = null;
    target.tvPhone = null;
    target.tvAddress = null;
    target.tvRegion = null;
    target.tvArea = null;
    target.tvPersonalProfile = null;
    target.lineNote = null;
    target.tvNote = null;
    target.btnPass = null;
    target.btnReject = null;
    target.lineBottomBtn = null;

    this.target = null;
  }
}
