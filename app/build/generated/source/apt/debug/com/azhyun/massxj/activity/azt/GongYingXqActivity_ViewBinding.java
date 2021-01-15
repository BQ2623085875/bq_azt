// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.activity.azt;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class GongYingXqActivity_ViewBinding<T extends GongYingXqActivity> implements Unbinder {
  protected T target;

  @UiThread
  public GongYingXqActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.mViewPager = Utils.findRequiredViewAsType(source, R.id.view_pager, "field 'mViewPager'", ViewPager.class);
    target.mIndicatorLayout = Utils.findRequiredViewAsType(source, R.id.indicator_layout, "field 'mIndicatorLayout'", LinearLayout.class);
    target.name = Utils.findRequiredViewAsType(source, R.id.name, "field 'name'", TextView.class);
    target.price = Utils.findRequiredViewAsType(source, R.id.price, "field 'price'", TextView.class);
    target.danwei = Utils.findRequiredViewAsType(source, R.id.danwei, "field 'danwei'", TextView.class);
    target.ghdz = Utils.findRequiredViewAsType(source, R.id.ghdz, "field 'ghdz'", TextView.class);
    target.miaoshu = Utils.findRequiredViewAsType(source, R.id.miaoshu, "field 'miaoshu'", TextView.class);
    target.phone = Utils.findRequiredViewAsType(source, R.id.phone, "field 'phone'", TextView.class);
    target.tiem = Utils.findRequiredViewAsType(source, R.id.tiem, "field 'tiem'", TextView.class);
    target.img = Utils.findRequiredViewAsType(source, R.id.img, "field 'img'", ImageView.class);
    target.bendi_gy_lianxi_ll = Utils.findRequiredViewAsType(source, R.id.bendi_gy_lianxi_ll, "field 'bendi_gy_lianxi_ll'", LinearLayout.class);
    target.bd_lianxiren = Utils.findRequiredViewAsType(source, R.id.bd_lianxiren, "field 'bd_lianxiren'", TextView.class);
    target.bd_lianxi = Utils.findRequiredViewAsType(source, R.id.bd_lianxi, "field 'bd_lianxi'", TextView.class);
    target.shuliang = Utils.findRequiredViewAsType(source, R.id.shuliang, "field 'shuliang'", TextView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.bank = Utils.findRequiredViewAsType(source, R.id.bank, "field 'bank'", ImageView.class);
    target.xiaxian_anniu_tv = Utils.findRequiredViewAsType(source, R.id.xiaxian_anniu_tv, "field 'xiaxian_anniu_tv'", TextView.class);
    target.qg_name = Utils.findRequiredViewAsType(source, R.id.qg_name, "field 'qg_name'", TextView.class);
    target.qg_pinzhong = Utils.findRequiredViewAsType(source, R.id.qg_pinzhong, "field 'qg_pinzhong'", TextView.class);
    target.qg_shuliang = Utils.findRequiredViewAsType(source, R.id.qg_shuliang, "field 'qg_shuliang'", TextView.class);
    target.qg_huoyuandi = Utils.findRequiredViewAsType(source, R.id.qg_huoyuandi, "field 'qg_huoyuandi'", TextView.class);
    target.qg_pinzhi = Utils.findRequiredViewAsType(source, R.id.qg_pinzhi, "field 'qg_pinzhi'", TextView.class);
    target.qg_img = Utils.findRequiredViewAsType(source, R.id.qg_img, "field 'qg_img'", ImageView.class);
    target.qg_phone = Utils.findRequiredViewAsType(source, R.id.qg_phone, "field 'qg_phone'", TextView.class);
    target.qg_tiem = Utils.findRequiredViewAsType(source, R.id.qg_tiem, "field 'qg_tiem'", TextView.class);
    target.qg_xq_ll = Utils.findRequiredViewAsType(source, R.id.qg_xq_ll, "field 'qg_xq_ll'", LinearLayout.class);
    target.gy_xq_ll = Utils.findRequiredViewAsType(source, R.id.gy_xq_ll, "field 'gy_xq_ll'", LinearLayout.class);
    target.gy_xiaxian_ll = Utils.findRequiredViewAsType(source, R.id.gy_xiaxian_ll, "field 'gy_xiaxian_ll'", LinearLayout.class);
    target.gy_xiaxian_tiem = Utils.findRequiredViewAsType(source, R.id.gy_xiaxian_tiem, "field 'gy_xiaxian_tiem'", TextView.class);
    target.qg_xiaxian_ll = Utils.findRequiredViewAsType(source, R.id.qg_xiaxian_ll, "field 'qg_xiaxian_ll'", LinearLayout.class);
    target.qg_xiaxian_tiem = Utils.findRequiredViewAsType(source, R.id.qg_xiaxian_tiem, "field 'qg_xiaxian_tiem'", TextView.class);
    target.gy_zhanshitu_rv = Utils.findRequiredViewAsType(source, R.id.gy_zhanshitu_rv, "field 'gy_zhanshitu_rv'", XRecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mViewPager = null;
    target.mIndicatorLayout = null;
    target.name = null;
    target.price = null;
    target.danwei = null;
    target.ghdz = null;
    target.miaoshu = null;
    target.phone = null;
    target.tiem = null;
    target.img = null;
    target.bendi_gy_lianxi_ll = null;
    target.bd_lianxiren = null;
    target.bd_lianxi = null;
    target.shuliang = null;
    target.title = null;
    target.bank = null;
    target.xiaxian_anniu_tv = null;
    target.qg_name = null;
    target.qg_pinzhong = null;
    target.qg_shuliang = null;
    target.qg_huoyuandi = null;
    target.qg_pinzhi = null;
    target.qg_img = null;
    target.qg_phone = null;
    target.qg_tiem = null;
    target.qg_xq_ll = null;
    target.gy_xq_ll = null;
    target.gy_xiaxian_ll = null;
    target.gy_xiaxian_tiem = null;
    target.qg_xiaxian_ll = null;
    target.qg_xiaxian_tiem = null;
    target.gy_zhanshitu_rv = null;

    this.target = null;
  }
}
