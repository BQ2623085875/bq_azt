// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import com.zhy.autolayout.AutoLinearLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ReleaseForActivity_ViewBinding<T extends ReleaseForActivity> implements Unbinder {
  protected T target;

  @UiThread
  public ReleaseForActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.bank = Utils.findRequiredViewAsType(source, R.id.bank, "field 'bank'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.edtTitle = Utils.findRequiredViewAsType(source, R.id.edt_title, "field 'edtTitle'", TextInputEditText.class);
    target.lineClassify = Utils.findRequiredViewAsType(source, R.id.line_classify, "field 'lineClassify'", AutoLinearLayout.class);
    target.edtNum = Utils.findRequiredViewAsType(source, R.id.edt_num, "field 'edtNum'", TextInputEditText.class);
    target.edtNorms = Utils.findRequiredViewAsType(source, R.id.edt_norms, "field 'edtNorms'", TextInputEditText.class);
    target.btnPublish = Utils.findRequiredViewAsType(source, R.id.btn_publish, "field 'btnPublish'", Button.class);
    target.tvClassify = Utils.findRequiredViewAsType(source, R.id.tv_classify, "field 'tvClassify'", TextView.class);
    target.tvArea = Utils.findRequiredViewAsType(source, R.id.tv_area, "field 'tvArea'", TextView.class);
    target.line_area = Utils.findRequiredViewAsType(source, R.id.line_area, "field 'line_area'", AutoLinearLayout.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
    target.layout_before_uploading = Utils.findRequiredViewAsType(source, R.id.layout_before_uploading, "field 'layout_before_uploading'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.bank = null;
    target.title = null;
    target.edtTitle = null;
    target.lineClassify = null;
    target.edtNum = null;
    target.edtNorms = null;
    target.btnPublish = null;
    target.tvClassify = null;
    target.tvArea = null;
    target.line_area = null;
    target.recyclerView = null;
    target.layout_before_uploading = null;

    this.target = null;
  }
}
