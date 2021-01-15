// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import com.youth.banner.Banner;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FarmingServiceInfoFragment_ViewBinding<T extends FarmingServiceInfoFragment> implements Unbinder {
  protected T target;

  @UiThread
  public FarmingServiceInfoFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.tvAllReview = Utils.findRequiredViewAsType(source, R.id.tv_all_review, "field 'tvAllReview'", TextView.class);
    target.btnOrder = Utils.findRequiredViewAsType(source, R.id.btn_order, "field 'btnOrder'", Button.class);
    target.mBanner = Utils.findRequiredViewAsType(source, R.id.mBanner, "field 'mBanner'", Banner.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.tvIntroduction = Utils.findRequiredViewAsType(source, R.id.tv_introduction, "field 'tvIntroduction'", TextView.class);
    target.tvPrice = Utils.findRequiredViewAsType(source, R.id.tv_price, "field 'tvPrice'", TextView.class);
    target.tvNorms = Utils.findRequiredViewAsType(source, R.id.tv_norms, "field 'tvNorms'", TextView.class);
    target.tvNumber = Utils.findRequiredViewAsType(source, R.id.tv_number, "field 'tvNumber'", TextView.class);
    target.tvArea = Utils.findRequiredViewAsType(source, R.id.tv_area, "field 'tvArea'", TextView.class);
    target.tvDescribe = Utils.findRequiredViewAsType(source, R.id.tv_describe, "field 'tvDescribe'", TextView.class);
    target.tvEvaluationsNumber = Utils.findRequiredViewAsType(source, R.id.tv_evaluations_number, "field 'tvEvaluationsNumber'", TextView.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.tvAllReview = null;
    target.btnOrder = null;
    target.mBanner = null;
    target.tvTitle = null;
    target.tvIntroduction = null;
    target.tvPrice = null;
    target.tvNorms = null;
    target.tvNumber = null;
    target.tvArea = null;
    target.tvDescribe = null;
    target.tvEvaluationsNumber = null;
    target.recyclerView = null;

    this.target = null;
  }
}
