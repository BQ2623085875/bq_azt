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
import com.zhy.autolayout.AutoLinearLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ManagementActivity_ViewBinding<T extends ManagementActivity> implements Unbinder {
  protected T target;

  @UiThread
  public ManagementActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.bank = Utils.findRequiredViewAsType(source, R.id.bank, "field 'bank'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.tvArea = Utils.findRequiredViewAsType(source, R.id.tv_area, "field 'tvArea'", TextView.class);
    target.tvCounty = Utils.findRequiredViewAsType(source, R.id.tv_county, "field 'tvCounty'", TextView.class);
    target.lineCounty = Utils.findRequiredViewAsType(source, R.id.line_county, "field 'lineCounty'", AutoLinearLayout.class);
    target.tvTownship = Utils.findRequiredViewAsType(source, R.id.tv_township, "field 'tvTownship'", TextView.class);
    target.lineTownship = Utils.findRequiredViewAsType(source, R.id.line_township, "field 'lineTownship'", AutoLinearLayout.class);
    target.tvVillage = Utils.findRequiredViewAsType(source, R.id.tv_village, "field 'tvVillage'", TextView.class);
    target.lineVillage = Utils.findRequiredViewAsType(source, R.id.line_village, "field 'lineVillage'", AutoLinearLayout.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'recyclerView'", XRecyclerView.class);
    target.mEmpty = Utils.findRequiredViewAsType(source, R.id.empty_layout, "field 'mEmpty'", RelativeLayout.class);
    target.tvAgentNum = Utils.findRequiredViewAsType(source, R.id.tv_agent_num, "field 'tvAgentNum'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.bank = null;
    target.title = null;
    target.tvArea = null;
    target.tvCounty = null;
    target.lineCounty = null;
    target.tvTownship = null;
    target.lineTownship = null;
    target.tvVillage = null;
    target.lineVillage = null;
    target.recyclerView = null;
    target.mEmpty = null;
    target.tvAgentNum = null;

    this.target = null;
  }
}
