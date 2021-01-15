// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.fragment.azt.licaityfragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AllFragment_ViewBinding<T extends AllFragment> implements Unbinder {
  protected T target;

  @UiThread
  public AllFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.loca_all_rv = Utils.findRequiredViewAsType(source, R.id.loca_all_rv, "field 'loca_all_rv'", XRecyclerView.class);
    target.wsj = Utils.findRequiredViewAsType(source, R.id.wsj, "field 'wsj'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.loca_all_rv = null;
    target.wsj = null;

    this.target = null;
  }
}
