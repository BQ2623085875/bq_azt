// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.fragment.azt;

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

public class AgriculturalMaterialsMallFragment_ViewBinding<T extends AgriculturalMaterialsMallFragment> implements Unbinder {
  protected T target;

  @UiThread
  public AgriculturalMaterialsMallFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.mMall_rv = Utils.findRequiredViewAsType(source, R.id.mall_rv, "field 'mMall_rv'", XRecyclerView.class);
    target.wushuju = Utils.findRequiredViewAsType(source, R.id.wushuju, "field 'wushuju'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mMall_rv = null;
    target.wushuju = null;

    this.target = null;
  }
}
