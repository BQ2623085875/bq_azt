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

public class BuyFragment_ViewBinding<T extends BuyFragment> implements Unbinder {
  protected T target;

  @UiThread
  public BuyFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.chooseClassify = Utils.findRequiredViewAsType(source, R.id.choose_classify, "field 'chooseClassify'", AutoLinearLayout.class);
    target.chooseArea = Utils.findRequiredViewAsType(source, R.id.choose_area, "field 'chooseArea'", AutoLinearLayout.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'recyclerView'", XRecyclerView.class);
    target.emptyLayout = Utils.findRequiredViewAsType(source, R.id.empty_layout, "field 'emptyLayout'", RelativeLayout.class);
    target.tvCancel = Utils.findRequiredViewAsType(source, R.id.tv_cancel, "field 'tvCancel'", TextView.class);
    target.tvArea = Utils.findRequiredViewAsType(source, R.id.tv_area, "field 'tvArea'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.chooseClassify = null;
    target.chooseArea = null;
    target.recyclerView = null;
    target.emptyLayout = null;
    target.tvCancel = null;
    target.tvArea = null;

    this.target = null;
  }
}
