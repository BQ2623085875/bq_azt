// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputEditText;
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
    target.lineArea = Utils.findRequiredViewAsType(source, R.id.line_area, "field 'lineArea'", AutoLinearLayout.class);
    target.edtNorms = Utils.findRequiredViewAsType(source, R.id.edt_norms, "field 'edtNorms'", TextInputEditText.class);
    target.edtName = Utils.findRequiredViewAsType(source, R.id.edt_name, "field 'edtName'", TextInputEditText.class);
    target.edtPhone = Utils.findRequiredViewAsType(source, R.id.edt_phone, "field 'edtPhone'", TextInputEditText.class);
    target.btnPublish = Utils.findRequiredViewAsType(source, R.id.btn_publish, "field 'btnPublish'", Button.class);
    target.tvClassify = Utils.findRequiredViewAsType(source, R.id.tv_classify, "field 'tvClassify'", TextView.class);
    target.tvArea = Utils.findRequiredViewAsType(source, R.id.tv_area, "field 'tvArea'", TextView.class);
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
    target.lineArea = null;
    target.edtNorms = null;
    target.edtName = null;
    target.edtPhone = null;
    target.btnPublish = null;
    target.tvClassify = null;
    target.tvArea = null;

    this.target = null;
  }
}
