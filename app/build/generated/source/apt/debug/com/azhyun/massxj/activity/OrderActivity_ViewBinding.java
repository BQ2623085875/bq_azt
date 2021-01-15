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
import com.azhyun.massxj.activity.azt.OrderActivity;
import com.azhyun.massxj.view.NoSrcollViewPage;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OrderActivity_ViewBinding<T extends OrderActivity> implements Unbinder {
  protected T target;

  @UiThread
  public OrderActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.mBack = Utils.findRequiredViewAsType(source, R.id.bank, "field 'mBack'", ImageView.class);
    target.mTitle = Utils.findRequiredViewAsType(source, R.id.title, "field 'mTitle'", TextView.class);
    target.mLayout_tab = Utils.findRequiredViewAsType(source, R.id.layout_tab, "field 'mLayout_tab'", TabLayout.class);
    target.mView_pager = Utils.findRequiredViewAsType(source, R.id.view_pager, "field 'mView_pager'", NoSrcollViewPage.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mBack = null;
    target.mTitle = null;
    target.mLayout_tab = null;
    target.mView_pager = null;

    this.target = null;
  }
}
