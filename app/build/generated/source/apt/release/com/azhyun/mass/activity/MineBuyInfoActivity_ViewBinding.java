// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MineBuyInfoActivity_ViewBinding<T extends MineBuyInfoActivity> implements Unbinder {
  protected T target;

  @UiThread
  public MineBuyInfoActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.bank = Utils.findRequiredViewAsType(source, R.id.bank, "field 'bank'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.img = Utils.findRequiredViewAsType(source, R.id.img, "field 'img'", CircleImageView.class);
    target.tvPhone = Utils.findRequiredViewAsType(source, R.id.tv_phone, "field 'tvPhone'", TextView.class);
    target.tvTime = Utils.findRequiredViewAsType(source, R.id.tv_time, "field 'tvTime'", TextView.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.tvClassify = Utils.findRequiredViewAsType(source, R.id.tv_classify, "field 'tvClassify'", TextView.class);
    target.tvNumber = Utils.findRequiredViewAsType(source, R.id.tv_number, "field 'tvNumber'", TextView.class);
    target.tvArea = Utils.findRequiredViewAsType(source, R.id.tv_area, "field 'tvArea'", TextView.class);
    target.tvDescribe = Utils.findRequiredViewAsType(source, R.id.tv_describe, "field 'tvDescribe'", TextView.class);
    target.tvAddTime = Utils.findRequiredViewAsType(source, R.id.tv_add_time, "field 'tvAddTime'", TextView.class);
    target.btnOff = Utils.findRequiredViewAsType(source, R.id.btn_off, "field 'btnOff'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.bank = null;
    target.title = null;
    target.img = null;
    target.tvPhone = null;
    target.tvTime = null;
    target.tvTitle = null;
    target.tvClassify = null;
    target.tvNumber = null;
    target.tvArea = null;
    target.tvDescribe = null;
    target.tvAddTime = null;
    target.btnOff = null;

    this.target = null;
  }
}
