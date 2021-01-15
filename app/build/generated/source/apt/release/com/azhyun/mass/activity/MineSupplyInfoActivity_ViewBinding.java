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
import com.youth.banner.Banner;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MineSupplyInfoActivity_ViewBinding<T extends MineSupplyInfoActivity> implements Unbinder {
  protected T target;

  @UiThread
  public MineSupplyInfoActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.bank = Utils.findRequiredViewAsType(source, R.id.bank, "field 'bank'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.mBanner = Utils.findRequiredViewAsType(source, R.id.mBanner, "field 'mBanner'", Banner.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.tvPrice = Utils.findRequiredViewAsType(source, R.id.tv_price, "field 'tvPrice'", TextView.class);
    target.tvNorms = Utils.findRequiredViewAsType(source, R.id.tv_norms, "field 'tvNorms'", TextView.class);
    target.tvNumber = Utils.findRequiredViewAsType(source, R.id.tv_number, "field 'tvNumber'", TextView.class);
    target.tvDescribe = Utils.findRequiredViewAsType(source, R.id.tv_describe, "field 'tvDescribe'", TextView.class);
    target.userImg = Utils.findRequiredViewAsType(source, R.id.user_img, "field 'userImg'", CircleImageView.class);
    target.tvPhone = Utils.findRequiredViewAsType(source, R.id.tv_phone, "field 'tvPhone'", TextView.class);
    target.tvTime = Utils.findRequiredViewAsType(source, R.id.tv_time, "field 'tvTime'", TextView.class);
    target.tvStatus = Utils.findRequiredViewAsType(source, R.id.tv_status, "field 'tvStatus'", TextView.class);
    target.tvArea = Utils.findRequiredViewAsType(source, R.id.tv_area, "field 'tvArea'", TextView.class);
    target.tvOffline = Utils.findRequiredViewAsType(source, R.id.tv_offline, "field 'tvOffline'", TextView.class);
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
    target.tvPrice = null;
    target.tvNorms = null;
    target.tvNumber = null;
    target.tvDescribe = null;
    target.userImg = null;
    target.tvPhone = null;
    target.tvTime = null;
    target.tvStatus = null;
    target.tvArea = null;
    target.tvOffline = null;

    this.target = null;
  }
}
