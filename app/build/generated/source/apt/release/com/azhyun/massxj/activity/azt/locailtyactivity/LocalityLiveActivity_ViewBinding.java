// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.activity.azt.locailtyactivity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import com.azhyun.massxj.view.NoSrcollViewPage;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LocalityLiveActivity_ViewBinding<T extends LocalityLiveActivity> implements Unbinder {
  protected T target;

  @UiThread
  public LocalityLiveActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.mBank = Utils.findRequiredViewAsType(source, R.id.bank, "field 'mBank'", ImageView.class);
    target.mLocality_sou = Utils.findRequiredViewAsType(source, R.id.locality_sou, "field 'mLocality_sou'", ImageView.class);
    target.mLocality_pager = Utils.findRequiredViewAsType(source, R.id.locality_pager, "field 'mLocality_pager'", NoSrcollViewPage.class);
    target.mLicality_sou_rl = Utils.findRequiredViewAsType(source, R.id.licality_sou_rl, "field 'mLicality_sou_rl'", RelativeLayout.class);
    target.mLicality_biaoti_rl = Utils.findRequiredViewAsType(source, R.id.licality_biaoti_rl, "field 'mLicality_biaoti_rl'", RelativeLayout.class);
    target.radioGroup = Utils.findRequiredViewAsType(source, R.id.radio_group, "field 'radioGroup'", RadioGroup.class);
    target.quan = Utils.findRequiredViewAsType(source, R.id.quan, "field 'quan'", RadioButton.class);
    target.gongying = Utils.findRequiredViewAsType(source, R.id.gongying, "field 'gongying'", RadioButton.class);
    target.qiugou = Utils.findRequiredViewAsType(source, R.id.qiugou, "field 'qiugou'", RadioButton.class);
    target.fenlie = Utils.findRequiredViewAsType(source, R.id.fenlie, "field 'fenlie'", TextView.class);
    target.fen_rl = Utils.findRequiredViewAsType(source, R.id.fen_rl, "field 'fen_rl'", RelativeLayout.class);
    target.sou_dele = Utils.findRequiredViewAsType(source, R.id.sou_dele, "field 'sou_dele'", ImageView.class);
    target.sou_ed = Utils.findRequiredViewAsType(source, R.id.sou_ed, "field 'sou_ed'", EditText.class);
    target.sou_rl = Utils.findRequiredViewAsType(source, R.id.sou_rl, "field 'sou_rl'", RelativeLayout.class);
    target.loca_rv = Utils.findRequiredViewAsType(source, R.id.loca_rv, "field 'loca_rv'", RecyclerView.class);
    target.drawerlayout = Utils.findRequiredViewAsType(source, R.id.drawerlayout, "field 'drawerlayout'", DrawerLayout.class);
    target.fenlei_chongzhi = Utils.findRequiredViewAsType(source, R.id.fenlei_chongzhi, "field 'fenlei_chongzhi'", TextView.class);
    target.fenlei_yes = Utils.findRequiredViewAsType(source, R.id.fenlei_yes, "field 'fenlei_yes'", TextView.class);
    target.linterHistoryConfirm = Utils.findRequiredViewAsType(source, R.id.linter_history, "field 'linterHistoryConfirm'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mBank = null;
    target.mLocality_sou = null;
    target.mLocality_pager = null;
    target.mLicality_sou_rl = null;
    target.mLicality_biaoti_rl = null;
    target.radioGroup = null;
    target.quan = null;
    target.gongying = null;
    target.qiugou = null;
    target.fenlie = null;
    target.fen_rl = null;
    target.sou_dele = null;
    target.sou_ed = null;
    target.sou_rl = null;
    target.loca_rv = null;
    target.drawerlayout = null;
    target.fenlei_chongzhi = null;
    target.fenlei_yes = null;
    target.linterHistoryConfirm = null;

    this.target = null;
  }
}
