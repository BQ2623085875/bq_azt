// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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

    target.imgPortrait = Utils.findRequiredViewAsType(source, R.id.img_portrait, "field 'imgPortrait'", CircleImageView.class);
    target.tvPhone = Utils.findRequiredViewAsType(source, R.id.tv_phone, "field 'tvPhone'", TextView.class);
    target.tvStatus = Utils.findRequiredViewAsType(source, R.id.tv_status, "field 'tvStatus'", TextView.class);
    target.userInfo = Utils.findRequiredViewAsType(source, R.id.user_info, "field 'userInfo'", AutoLinearLayout.class);
    target.mLogin_no_ll = Utils.findRequiredViewAsType(source, R.id.login_no_ll, "field 'mLogin_no_ll'", LinearLayout.class);
    target.mLogin_yes_ll = Utils.findRequiredViewAsType(source, R.id.login_yes_ll, "field 'mLogin_yes_ll'", LinearLayout.class);
    target.mMe_login_no_tv = Utils.findRequiredViewAsType(source, R.id.me_login_no_tv, "field 'mMe_login_no_tv'", TextView.class);
    target.mMe_daifukuan = Utils.findRequiredViewAsType(source, R.id.me_daifukuan, "field 'mMe_daifukuan'", TextView.class);
    target.mMe_daifahuo = Utils.findRequiredViewAsType(source, R.id.me_daifahuo, "field 'mMe_daifahuo'", TextView.class);
    target.mMe_daishouhuo = Utils.findRequiredViewAsType(source, R.id.me_daishouhuo, "field 'mMe_daishouhuo'", TextView.class);
    target.mMe_yiwancheng = Utils.findRequiredViewAsType(source, R.id.me_yiwancheng, "field 'mMe_yiwancheng'", TextView.class);
    target.mMe_quanbu = Utils.findRequiredViewAsType(source, R.id.me_quanbu, "field 'mMe_quanbu'", TextView.class);
    target.mMe_shouhuofuwu = Utils.findRequiredViewAsType(source, R.id.me_shouhuofuwu, "field 'mMe_shouhuofuwu'", RelativeLayout.class);
    target.mMe_gongying = Utils.findRequiredViewAsType(source, R.id.me_gongying, "field 'mMe_gongying'", RelativeLayout.class);
    target.mMe_nongyuzhi = Utils.findRequiredViewAsType(source, R.id.me_nongyuzhi, "field 'mMe_nongyuzhi'", RelativeLayout.class);
    target.mMe_jifen = Utils.findRequiredViewAsType(source, R.id.me_jifen, "field 'mMe_jifen'", RelativeLayout.class);
    target.mMe_outlogin = Utils.findRequiredViewAsType(source, R.id.me_outlogin, "field 'mMe_outlogin'", RelativeLayout.class);
    target.mMe_nongbaodingdan = Utils.findRequiredViewAsType(source, R.id.me_nongbaodingdan, "field 'mMe_nongbaodingdan'", RelativeLayout.class);
    target.caigou_rv = Utils.findRequiredViewAsType(source, R.id.caigou_rv, "field 'caigou_rv'", RelativeLayout.class);
    target.jf = Utils.findRequiredViewAsType(source, R.id.jf, "field 'jf'", TextView.class);
    target.nyz = Utils.findRequiredViewAsType(source, R.id.nyz, "field 'nyz'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.imgPortrait = null;
    target.tvPhone = null;
    target.tvStatus = null;
    target.userInfo = null;
    target.mLogin_no_ll = null;
    target.mLogin_yes_ll = null;
    target.mMe_login_no_tv = null;
    target.mMe_daifukuan = null;
    target.mMe_daifahuo = null;
    target.mMe_daishouhuo = null;
    target.mMe_yiwancheng = null;
    target.mMe_quanbu = null;
    target.mMe_shouhuofuwu = null;
    target.mMe_gongying = null;
    target.mMe_nongyuzhi = null;
    target.mMe_jifen = null;
    target.mMe_outlogin = null;
    target.mMe_nongbaodingdan = null;
    target.caigou_rv = null;
    target.jf = null;
    target.nyz = null;

    this.target = null;
  }
}
