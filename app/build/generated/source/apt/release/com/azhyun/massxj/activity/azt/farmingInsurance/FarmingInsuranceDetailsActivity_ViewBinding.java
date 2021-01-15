// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.activity.azt.farmingInsurance;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FarmingInsuranceDetailsActivity_ViewBinding<T extends FarmingInsuranceDetailsActivity> implements Unbinder {
  protected T target;

  @UiThread
  public FarmingInsuranceDetailsActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.bank = Utils.findRequiredViewAsType(source, R.id.bank, "field 'bank'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.webView = Utils.findRequiredViewAsType(source, R.id.webview, "field 'webView'", WebView.class);
    target.prico = Utils.findRequiredViewAsType(source, R.id.prico, "field 'prico'", TextView.class);
    target.yuding = Utils.findRequiredViewAsType(source, R.id.yuding, "field 'yuding'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.bank = null;
    target.title = null;
    target.webView = null;
    target.prico = null;
    target.yuding = null;

    this.target = null;
  }
}
