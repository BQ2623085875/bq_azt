// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.activity.azt;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CaiPurchaseActivity_ViewBinding<T extends CaiPurchaseActivity> implements Unbinder {
  protected T target;

  @UiThread
  public CaiPurchaseActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.bank = Utils.findRequiredViewAsType(source, R.id.bank, "field 'bank'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.mLayout_tab = Utils.findRequiredViewAsType(source, R.id.layout_tab, "field 'mLayout_tab'", TabLayout.class);
    target.mView_pager = Utils.findRequiredViewAsType(source, R.id.view_pager, "field 'mView_pager'", ViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.bank = null;
    target.title = null;
    target.mLayout_tab = null;
    target.mView_pager = null;

    this.target = null;
  }
}
