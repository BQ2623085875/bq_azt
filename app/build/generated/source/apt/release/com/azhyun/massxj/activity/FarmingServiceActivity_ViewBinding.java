// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import com.azhyun.massxj.view.NoSrcollViewPage;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FarmingServiceActivity_ViewBinding<T extends FarmingServiceActivity> implements Unbinder {
  protected T target;

  @UiThread
  public FarmingServiceActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.bank = Utils.findRequiredViewAsType(source, R.id.bank, "field 'bank'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.layoutTab = Utils.findRequiredViewAsType(source, R.id.layout_tab, "field 'layoutTab'", TabLayout.class);
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.view_pager, "field 'viewPager'", NoSrcollViewPage.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.bank = null;
    target.title = null;
    target.layoutTab = null;
    target.viewPager = null;

    this.target = null;
  }
}
