// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import com.azhyun.massxj.view.NoSrcollViewPage;
import com.zhy.autolayout.AutoLinearLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UserLogingActivity_ViewBinding<T extends UserLogingActivity> implements Unbinder {
  protected T target;

  @UiThread
  public UserLogingActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.textLogin = Utils.findRequiredViewAsType(source, R.id.text_login, "field 'textLogin'", TextView.class);
    target.imgLogin = Utils.findRequiredViewAsType(source, R.id.img_login, "field 'imgLogin'", ImageView.class);
    target.lineLogin = Utils.findRequiredViewAsType(source, R.id.line_login, "field 'lineLogin'", AutoLinearLayout.class);
    target.textRegister = Utils.findRequiredViewAsType(source, R.id.text_register, "field 'textRegister'", TextView.class);
    target.imgRegister = Utils.findRequiredViewAsType(source, R.id.img_register, "field 'imgRegister'", ImageView.class);
    target.lineRegister = Utils.findRequiredViewAsType(source, R.id.line_register, "field 'lineRegister'", AutoLinearLayout.class);
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.view_pager, "field 'viewPager'", NoSrcollViewPage.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.textLogin = null;
    target.imgLogin = null;
    target.lineLogin = null;
    target.textRegister = null;
    target.imgRegister = null;
    target.lineRegister = null;
    target.viewPager = null;

    this.target = null;
  }
}
