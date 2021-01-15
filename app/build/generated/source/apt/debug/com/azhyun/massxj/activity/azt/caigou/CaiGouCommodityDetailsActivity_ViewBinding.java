// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.activity.azt.caigou;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CaiGouCommodityDetailsActivity_ViewBinding<T extends CaiGouCommodityDetailsActivity> implements Unbinder {
  protected T target;

  @UiThread
  public CaiGouCommodityDetailsActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.mTitle = Utils.findRequiredViewAsType(source, R.id.title, "field 'mTitle'", TextView.class);
    target.mBank = Utils.findRequiredViewAsType(source, R.id.bank, "field 'mBank'", ImageView.class);
    target.mInfo_frame_layout = Utils.findRequiredViewAsType(source, R.id.info_frame_layout, "field 'mInfo_frame_layout'", FrameLayout.class);
    target.mPromptly_ll = Utils.findRequiredViewAsType(source, R.id.promptly_ll, "field 'mPromptly_ll'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mTitle = null;
    target.mBank = null;
    target.mInfo_frame_layout = null;
    target.mPromptly_ll = null;

    this.target = null;
  }
}
