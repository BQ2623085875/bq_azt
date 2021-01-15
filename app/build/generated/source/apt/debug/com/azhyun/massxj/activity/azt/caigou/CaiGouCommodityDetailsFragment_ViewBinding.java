// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.activity.azt.caigou;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CaiGouCommodityDetailsFragment_ViewBinding<T extends CaiGouCommodityDetailsFragment> implements Unbinder {
  protected T target;

  @UiThread
  public CaiGouCommodityDetailsFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.mCommodity_jianhao = Utils.findRequiredViewAsType(source, R.id.commodity_jianhao, "field 'mCommodity_jianhao'", LinearLayout.class);
    target.mCommodity_jiahao = Utils.findRequiredViewAsType(source, R.id.commodity_jiahao, "field 'mCommodity_jiahao'", LinearLayout.class);
    target.mCommodity_shuliang = Utils.findRequiredViewAsType(source, R.id.commodity_shuliang, "field 'mCommodity_shuliang'", EditText.class);
    target.mViewPager = Utils.findRequiredViewAsType(source, R.id.view_pager, "field 'mViewPager'", ViewPager.class);
    target.mIndicatorLayout = Utils.findRequiredViewAsType(source, R.id.indicator_layout, "field 'mIndicatorLayout'", LinearLayout.class);
    target.product_name_text_view = Utils.findRequiredViewAsType(source, R.id.product_name_text_view, "field 'product_name_text_view'", TextView.class);
    target.adapt_field_text_view = Utils.findRequiredViewAsType(source, R.id.adapt_field_text_view, "field 'adapt_field_text_view'", TextView.class);
    target.product_price_text_view = Utils.findRequiredViewAsType(source, R.id.product_price_text_view, "field 'product_price_text_view'", TextView.class);
    target.mall_commodity_fuwushang = Utils.findRequiredViewAsType(source, R.id.mall_commodity_fuwushang, "field 'mall_commodity_fuwushang'", TextView.class);
    target.product_guige_text = Utils.findRequiredViewAsType(source, R.id.product_guige_text, "field 'product_guige_text'", TextView.class);
    target.product_guige_rl = Utils.findRequiredViewAsType(source, R.id.product_guige_rl, "field 'product_guige_rl'", RelativeLayout.class);
    target.danwei = Utils.findRequiredViewAsType(source, R.id.danwei, "field 'danwei'", TextView.class);
    target.img = Utils.findRequiredViewAsType(source, R.id.img, "field 'img'", ImageView.class);
    target.img_rv = Utils.findRequiredViewAsType(source, R.id.img_rv, "field 'img_rv'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mCommodity_jianhao = null;
    target.mCommodity_jiahao = null;
    target.mCommodity_shuliang = null;
    target.mViewPager = null;
    target.mIndicatorLayout = null;
    target.product_name_text_view = null;
    target.adapt_field_text_view = null;
    target.product_price_text_view = null;
    target.mall_commodity_fuwushang = null;
    target.product_guige_text = null;
    target.product_guige_rl = null;
    target.danwei = null;
    target.img = null;
    target.img_rv = null;

    this.target = null;
  }
}
