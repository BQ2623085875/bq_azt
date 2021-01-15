// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.activity.azt;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyIntegralActivity_ViewBinding<T extends MyIntegralActivity> implements Unbinder {
  protected T target;

  @UiThread
  public MyIntegralActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.bank = Utils.findRequiredViewAsType(source, R.id.bank, "field 'bank'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.integral_nyz_tv = Utils.findRequiredViewAsType(source, R.id.integral_nyz_tv, "field 'integral_nyz_tv'", TextView.class);
    target.integral_dqjf_tv = Utils.findRequiredViewAsType(source, R.id.integral_dqjf_tv, "field 'integral_dqjf_tv'", TextView.class);
    target.integral_dhnyz_tv = Utils.findRequiredViewAsType(source, R.id.integral_dhnyz_tv, "field 'integral_dhnyz_tv'", TextView.class);
    target.integral_xr = Utils.findRequiredViewAsType(source, R.id.integral_xr, "field 'integral_xr'", XRecyclerView.class);
    target.integral_dqjfxm_tv = Utils.findRequiredViewAsType(source, R.id.integral_dqjfxm_tv, "field 'integral_dqjfxm_tv'", TextView.class);
    target.integral_jfjs_tv = Utils.findRequiredViewAsType(source, R.id.integral_jfjs_tv, "field 'integral_jfjs_tv'", TextView.class);
    target.integral_nyzjf_tv = Utils.findRequiredViewAsType(source, R.id.integral_nyzjf_tv, "field 'integral_nyzjf_tv'", TextView.class);
    target.dq_tv = Utils.findRequiredViewAsType(source, R.id.dq_tv, "field 'dq_tv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.bank = null;
    target.title = null;
    target.integral_nyz_tv = null;
    target.integral_dqjf_tv = null;
    target.integral_dhnyz_tv = null;
    target.integral_xr = null;
    target.integral_dqjfxm_tv = null;
    target.integral_jfjs_tv = null;
    target.integral_nyzjf_tv = null;
    target.dq_tv = null;

    this.target = null;
  }
}
