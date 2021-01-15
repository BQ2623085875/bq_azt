// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ReviewFragment_ViewBinding<T extends ReviewFragment> implements Unbinder {
  protected T target;

  @UiThread
  public ReviewFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.titleRecyclerView = Utils.findRequiredViewAsType(source, R.id.title_recycler_view, "field 'titleRecyclerView'", RecyclerView.class);
    target.reviewRecyclerView = Utils.findRequiredViewAsType(source, R.id.review_recycler_view, "field 'reviewRecyclerView'", XRecyclerView.class);
    target.empty = Utils.findRequiredViewAsType(source, R.id.empty_text, "field 'empty'", TextView.class);
    target.btnOrder = Utils.findRequiredViewAsType(source, R.id.btn_order, "field 'btnOrder'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.titleRecyclerView = null;
    target.reviewRecyclerView = null;
    target.empty = null;
    target.btnOrder = null;

    this.target = null;
  }
}
