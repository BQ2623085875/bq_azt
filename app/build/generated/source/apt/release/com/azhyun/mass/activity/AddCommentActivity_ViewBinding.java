// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import com.example.xlhratingbar_lib.XLHRatingBar;
import com.zhy.autolayout.AutoLinearLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddCommentActivity_ViewBinding<T extends AddCommentActivity> implements Unbinder {
  protected T target;

  @UiThread
  public AddCommentActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.ratingBar = Utils.findRequiredViewAsType(source, R.id.ratingBar, "field 'ratingBar'", XLHRatingBar.class);
    target.ratingBar2 = Utils.findRequiredViewAsType(source, R.id.ratingBar2, "field 'ratingBar2'", XLHRatingBar.class);
    target.bank = Utils.findRequiredViewAsType(source, R.id.bank, "field 'bank'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.edtContent = Utils.findRequiredViewAsType(source, R.id.edt_content, "field 'edtContent'", EditText.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
    target.btnRelease = Utils.findRequiredViewAsType(source, R.id.btn_release, "field 'btnRelease'", Button.class);
    target.img = Utils.findRequiredViewAsType(source, R.id.img, "field 'img'", ImageView.class);
    target.tvClassify = Utils.findRequiredViewAsType(source, R.id.tv_classify, "field 'tvClassify'", TextView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvPrice = Utils.findRequiredViewAsType(source, R.id.tv_price, "field 'tvPrice'", TextView.class);
    target.imgGood = Utils.findRequiredViewAsType(source, R.id.img_good, "field 'imgGood'", ImageView.class);
    target.tvGood = Utils.findRequiredViewAsType(source, R.id.tv_good, "field 'tvGood'", TextView.class);
    target.tvDescribeGood = Utils.findRequiredViewAsType(source, R.id.tv_describe_good, "field 'tvDescribeGood'", TextView.class);
    target.imgOrdinary = Utils.findRequiredViewAsType(source, R.id.img_ordinary, "field 'imgOrdinary'", ImageView.class);
    target.tvOrdinary = Utils.findRequiredViewAsType(source, R.id.tv_ordinary, "field 'tvOrdinary'", TextView.class);
    target.tvDescribeOrdinary = Utils.findRequiredViewAsType(source, R.id.tv_describe_ordinary, "field 'tvDescribeOrdinary'", TextView.class);
    target.imgPoor = Utils.findRequiredViewAsType(source, R.id.img_poor, "field 'imgPoor'", ImageView.class);
    target.tvPoor = Utils.findRequiredViewAsType(source, R.id.tv_poor, "field 'tvPoor'", TextView.class);
    target.tvDescribePoor = Utils.findRequiredViewAsType(source, R.id.tv_describe_poor, "field 'tvDescribePoor'", TextView.class);
    target.lineGood = Utils.findRequiredViewAsType(source, R.id.line_good, "field 'lineGood'", AutoLinearLayout.class);
    target.lineOrdinary = Utils.findRequiredViewAsType(source, R.id.line_ordinary, "field 'lineOrdinary'", AutoLinearLayout.class);
    target.linePoor = Utils.findRequiredViewAsType(source, R.id.line_poor, "field 'linePoor'", AutoLinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ratingBar = null;
    target.ratingBar2 = null;
    target.bank = null;
    target.title = null;
    target.edtContent = null;
    target.recyclerView = null;
    target.btnRelease = null;
    target.img = null;
    target.tvClassify = null;
    target.tvName = null;
    target.tvPrice = null;
    target.imgGood = null;
    target.tvGood = null;
    target.tvDescribeGood = null;
    target.imgOrdinary = null;
    target.tvOrdinary = null;
    target.tvDescribeOrdinary = null;
    target.imgPoor = null;
    target.tvPoor = null;
    target.tvDescribePoor = null;
    target.lineGood = null;
    target.lineOrdinary = null;
    target.linePoor = null;

    this.target = null;
  }
}
