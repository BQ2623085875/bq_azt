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

public class MyParticipationActivity_ViewBinding<T extends MyParticipationActivity> implements Unbinder {
  protected T target;

  @UiThread
  public MyParticipationActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.bank = Utils.findRequiredViewAsType(source, R.id.bank, "field 'bank'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.view_pager, "field 'viewPager'", NoSrcollViewPage.class);
    target.tabParticipation = Utils.findRequiredViewAsType(source, R.id.tab_participation, "field 'tabParticipation'", TabLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.bank = null;
    target.title = null;
    target.viewPager = null;
    target.tabParticipation = null;

    this.target = null;
  }
}
