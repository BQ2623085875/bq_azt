// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import com.zhy.autolayout.AutoLinearLayout;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MineFragment_ViewBinding<T extends MineFragment> implements Unbinder {
  protected T target;

  @UiThread
  public MineFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.titleMessage = Utils.findRequiredViewAsType(source, R.id.title_message, "field 'titleMessage'", ImageView.class);
    target.imgPortrait = Utils.findRequiredViewAsType(source, R.id.img_portrait, "field 'imgPortrait'", CircleImageView.class);
    target.tvPhone = Utils.findRequiredViewAsType(source, R.id.tv_phone, "field 'tvPhone'", TextView.class);
    target.tvStatus = Utils.findRequiredViewAsType(source, R.id.tv_status, "field 'tvStatus'", TextView.class);
    target.userInfo = Utils.findRequiredViewAsType(source, R.id.user_info, "field 'userInfo'", AutoLinearLayout.class);
    target.lineOperate = Utils.findRequiredViewAsType(source, R.id.line_operate, "field 'lineOperate'", AutoLinearLayout.class);
    target.lineManagement = Utils.findRequiredViewAsType(source, R.id.line_management, "field 'lineManagement'", AutoLinearLayout.class);
    target.lineRelease = Utils.findRequiredViewAsType(source, R.id.line_release, "field 'lineRelease'", AutoLinearLayout.class);
    target.lineAudit = Utils.findRequiredViewAsType(source, R.id.line_audit, "field 'lineAudit'", AutoLinearLayout.class);
    target.lineOrder = Utils.findRequiredViewAsType(source, R.id.line_order, "field 'lineOrder'", AutoLinearLayout.class);
    target.mineLineSupply = Utils.findRequiredViewAsType(source, R.id.mine_line_supply, "field 'mineLineSupply'", AutoLinearLayout.class);
    target.mineLineBuy = Utils.findRequiredViewAsType(source, R.id.mine_line_buy, "field 'mineLineBuy'", AutoLinearLayout.class);
    target.mineLineNotice = Utils.findRequiredViewAsType(source, R.id.mine_line_notice, "field 'mineLineNotice'", AutoLinearLayout.class);
    target.mineLineWork = Utils.findRequiredViewAsType(source, R.id.mine_line_work, "field 'mineLineWork'", AutoLinearLayout.class);
    target.mineLinePolicy = Utils.findRequiredViewAsType(source, R.id.mine_line_policy, "field 'mineLinePolicy'", AutoLinearLayout.class);
    target.mineLineHosting = Utils.findRequiredViewAsType(source, R.id.mine_line_hosting, "field 'mineLineHosting'", AutoLinearLayout.class);
    target.mineLineApply = Utils.findRequiredViewAsType(source, R.id.mine_line_apply, "field 'mineLineApply'", AutoLinearLayout.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.title = null;
    target.titleMessage = null;
    target.imgPortrait = null;
    target.tvPhone = null;
    target.tvStatus = null;
    target.userInfo = null;
    target.lineOperate = null;
    target.lineManagement = null;
    target.lineRelease = null;
    target.lineAudit = null;
    target.lineOrder = null;
    target.mineLineSupply = null;
    target.mineLineBuy = null;
    target.mineLineNotice = null;
    target.mineLineWork = null;
    target.mineLinePolicy = null;
    target.mineLineHosting = null;
    target.mineLineApply = null;
    target.recyclerView = null;

    this.target = null;
  }
}
