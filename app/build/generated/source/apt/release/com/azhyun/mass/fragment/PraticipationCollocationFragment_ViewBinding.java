// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PraticipationCollocationFragment_ViewBinding<T extends PraticipationCollocationFragment> implements Unbinder {
  protected T target;

  @UiThread
  public PraticipationCollocationFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_collocationview, "field 'recyclerView'", XRecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.recyclerView = null;

    this.target = null;
  }
}
