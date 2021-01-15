// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.activity.azt;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class QiuGouXqActivity_ViewBinding<T extends QiuGouXqActivity> implements Unbinder {
  protected T target;

  @UiThread
  public QiuGouXqActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.view_pager = Utils.findRequiredViewAsType(source, R.id.view_pager, "field 'view_pager'", ViewPager.class);
    target.indicator_layout = Utils.findRequiredViewAsType(source, R.id.indicator_layout, "field 'indicator_layout'", LinearLayout.class);
    target.name = Utils.findRequiredViewAsType(source, R.id.name, "field 'name'", TextView.class);
    target.pinzhong = Utils.findRequiredViewAsType(source, R.id.pinzhong, "field 'pinzhong'", TextView.class);
    target.shuliang = Utils.findRequiredViewAsType(source, R.id.shuliang, "field 'shuliang'", TextView.class);
    target.huoyuandi = Utils.findRequiredViewAsType(source, R.id.huoyuandi, "field 'huoyuandi'", TextView.class);
    target.pinzhi = Utils.findRequiredViewAsType(source, R.id.pinzhi, "field 'pinzhi'", TextView.class);
    target.phone = Utils.findRequiredViewAsType(source, R.id.phone, "field 'phone'", TextView.class);
    target.tiem = Utils.findRequiredViewAsType(source, R.id.tiem, "field 'tiem'", TextView.class);
    target.xiaxian_tiem = Utils.findRequiredViewAsType(source, R.id.xiaxian_tiem, "field 'xiaxian_tiem'", TextView.class);
    target.img = Utils.findRequiredViewAsType(source, R.id.img, "field 'img'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.bank = Utils.findRequiredViewAsType(source, R.id.bank, "field 'bank'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.view_pager = null;
    target.indicator_layout = null;
    target.name = null;
    target.pinzhong = null;
    target.shuliang = null;
    target.huoyuandi = null;
    target.pinzhi = null;
    target.phone = null;
    target.tiem = null;
    target.xiaxian_tiem = null;
    target.img = null;
    target.title = null;
    target.bank = null;

    this.target = null;
  }
}
