// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PublishedActivity_ViewBinding<T extends PublishedActivity> implements Unbinder {
  protected T target;

  @UiThread
  public PublishedActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.linearlayoutReleaseTheSupply = Utils.findRequiredViewAsType(source, R.id.linearlayout_release_the_supply, "field 'linearlayoutReleaseTheSupply'", RelativeLayout.class);
    target.linearlayoutReleaseFor = Utils.findRequiredViewAsType(source, R.id.linearlayout_release_for, "field 'linearlayoutReleaseFor'", RelativeLayout.class);
    target.bank = Utils.findRequiredViewAsType(source, R.id.bank, "field 'bank'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.linearlayoutReleaseTheSupply = null;
    target.linearlayoutReleaseFor = null;
    target.bank = null;
    target.title = null;

    this.target = null;
  }
}
