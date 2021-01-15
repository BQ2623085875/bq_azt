// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import com.azhyun.massxj.view.NoSrcollViewPage;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity2_ViewBinding<T extends MainActivity2> implements Unbinder {
  protected T target;

  @UiThread
  public MainActivity2_ViewBinding(T target, View source) {
    this.target = target;

    target.viewPager = Utils.findRequiredViewAsType(source, R.id.view_pager, "field 'viewPager'", NoSrcollViewPage.class);
    target.nihao = Utils.findRequiredView(source, R.id.nihao, "field 'nihao'");
    target.radioGroup = Utils.findRequiredViewAsType(source, R.id.radio_group, "field 'radioGroup'", RadioGroup.class);
    target.rdHome = Utils.findRequiredViewAsType(source, R.id.rd_home, "field 'rdHome'", RadioButton.class);
    target.rdDaily = Utils.findRequiredViewAsType(source, R.id.rd_daily, "field 'rdDaily'", RadioButton.class);
    target.rdMy = Utils.findRequiredViewAsType(source, R.id.rd_my, "field 'rdMy'", RadioButton.class);
    target.rgOper = Utils.findRequiredViewAsType(source, R.id.rg_oper, "field 'rgOper'", LinearLayout.class);
    target.imgLeague = Utils.findRequiredViewAsType(source, R.id.img_league, "field 'imgLeague'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.viewPager = null;
    target.nihao = null;
    target.radioGroup = null;
    target.rdHome = null;
    target.rdDaily = null;
    target.rdMy = null;
    target.rgOper = null;
    target.imgLeague = null;

    this.target = null;
  }
}
