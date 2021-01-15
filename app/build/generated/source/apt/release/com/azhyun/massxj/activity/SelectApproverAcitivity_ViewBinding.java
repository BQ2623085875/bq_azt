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
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.autolayout.AutoRelativeLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SelectApproverAcitivity_ViewBinding<T extends SelectApproverAcitivity> implements Unbinder {
  protected T target;

  @UiThread
  public SelectApproverAcitivity_ViewBinding(T target, View source) {
    this.target = target;

    target.bank = Utils.findRequiredViewAsType(source, R.id.bank, "field 'bank'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.relationTitle = Utils.findRequiredViewAsType(source, R.id.relation_title, "field 'relationTitle'", AutoRelativeLayout.class);
    target.tvType = Utils.findRequiredViewAsType(source, R.id.tv_type, "field 'tvType'", TextView.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.xrecycler_view, "field 'recyclerView'", XRecyclerView.class);
    target.emptyLayout = Utils.findRequiredViewAsType(source, R.id.empty_layout, "field 'emptyLayout'", RelativeLayout.class);
    target.tvConfirm = Utils.findRequiredViewAsType(source, R.id.tv_confirm, "field 'tvConfirm'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.bank = null;
    target.title = null;
    target.relationTitle = null;
    target.tvType = null;
    target.recyclerView = null;
    target.emptyLayout = null;
    target.tvConfirm = null;

    this.target = null;
  }
}
