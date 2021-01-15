// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MineAuditActivity_ViewBinding<T extends MineAuditActivity> implements Unbinder {
  protected T target;

  @UiThread
  public MineAuditActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.layoutTab = Utils.findRequiredViewAsType(source, R.id.layout_tab, "field 'layoutTab'", TabLayout.class);
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.view_pager, "field 'viewPager'", ViewPager.class);
    target.back = Utils.findRequiredViewAsType(source, R.id.back, "field 'back'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.layoutTab = null;
    target.viewPager = null;
    target.back = null;

    this.target = null;
  }
}
