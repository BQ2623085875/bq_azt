// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.autolayout.AutoLinearLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LandHostingFragment_ViewBinding<T extends LandHostingFragment> implements Unbinder {
  protected T target;

  @UiThread
  public LandHostingFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.tvNotAudit = Utils.findRequiredViewAsType(source, R.id.tv_not_audit, "field 'tvNotAudit'", TextView.class);
    target.viewNotAudit = Utils.findRequiredView(source, R.id.view_not_audit, "field 'viewNotAudit'");
    target.lineNotAudit = Utils.findRequiredViewAsType(source, R.id.line_not_audit, "field 'lineNotAudit'", AutoLinearLayout.class);
    target.tvApproved = Utils.findRequiredViewAsType(source, R.id.tv_approved, "field 'tvApproved'", TextView.class);
    target.viewApproved = Utils.findRequiredView(source, R.id.view_approved, "field 'viewApproved'");
    target.lineApproved = Utils.findRequiredViewAsType(source, R.id.line_approved, "field 'lineApproved'", AutoLinearLayout.class);
    target.tvReject = Utils.findRequiredViewAsType(source, R.id.tv_reject, "field 'tvReject'", TextView.class);
    target.viewReject = Utils.findRequiredView(source, R.id.view_reject, "field 'viewReject'");
    target.lineReject = Utils.findRequiredViewAsType(source, R.id.line_reject, "field 'lineReject'", AutoLinearLayout.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'recyclerView'", XRecyclerView.class);
    target.emptyLayout = Utils.findRequiredViewAsType(source, R.id.empty_layout, "field 'emptyLayout'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.tvNotAudit = null;
    target.viewNotAudit = null;
    target.lineNotAudit = null;
    target.tvApproved = null;
    target.viewApproved = null;
    target.lineApproved = null;
    target.tvReject = null;
    target.viewReject = null;
    target.lineReject = null;
    target.recyclerView = null;
    target.emptyLayout = null;

    this.target = null;
  }
}
