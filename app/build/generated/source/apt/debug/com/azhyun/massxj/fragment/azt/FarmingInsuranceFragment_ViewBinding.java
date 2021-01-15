// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.fragment.azt;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import com.azhyun.massxj.fragment.azt.farmingInsurance.FarmingInsuranceFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FarmingInsuranceFragment_ViewBinding<T extends FarmingInsuranceFragment> implements Unbinder {
  protected T target;

  @UiThread
  public FarmingInsuranceFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.mXr = Utils.findRequiredViewAsType(source, R.id.xr, "field 'mXr'", XRecyclerView.class);
    target.wsj = Utils.findRequiredViewAsType(source, R.id.wsj, "field 'wsj'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mXr = null;
    target.wsj = null;

    this.target = null;
  }
}
