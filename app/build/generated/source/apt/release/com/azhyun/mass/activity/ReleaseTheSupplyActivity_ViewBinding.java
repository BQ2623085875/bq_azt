// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import com.zhy.autolayout.AutoLinearLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ReleaseTheSupplyActivity_ViewBinding<T extends ReleaseTheSupplyActivity> implements Unbinder {
  protected T target;

  @UiThread
  public ReleaseTheSupplyActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.textNextStep = Utils.findRequiredViewAsType(source, R.id.text_next_step, "field 'textNextStep'", TextView.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
    target.layoutBeforeUploading = Utils.findRequiredViewAsType(source, R.id.layout_before_uploading, "field 'layoutBeforeUploading'", LinearLayout.class);
    target.bank = Utils.findRequiredViewAsType(source, R.id.bank, "field 'bank'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.edtTitle = Utils.findRequiredViewAsType(source, R.id.edt_title, "field 'edtTitle'", TextInputEditText.class);
    target.textSelected = Utils.findRequiredViewAsType(source, R.id.text_selected, "field 'textSelected'", TextView.class);
    target.lineSelected = Utils.findRequiredViewAsType(source, R.id.line_selected, "field 'lineSelected'", AutoLinearLayout.class);
    target.edtNorms = Utils.findRequiredViewAsType(source, R.id.edt_norms, "field 'edtNorms'", TextInputEditText.class);
    target.edtPrice = Utils.findRequiredViewAsType(source, R.id.edt_price, "field 'edtPrice'", TextInputEditText.class);
    target.edtNum = Utils.findRequiredViewAsType(source, R.id.edt_num, "field 'edtNum'", TextInputEditText.class);
    target.edtDescribe = Utils.findRequiredViewAsType(source, R.id.edt_describe, "field 'edtDescribe'", TextInputEditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.textNextStep = null;
    target.recyclerView = null;
    target.layoutBeforeUploading = null;
    target.bank = null;
    target.title = null;
    target.edtTitle = null;
    target.textSelected = null;
    target.lineSelected = null;
    target.edtNorms = null;
    target.edtPrice = null;
    target.edtNum = null;
    target.edtDescribe = null;

    this.target = null;
  }
}
