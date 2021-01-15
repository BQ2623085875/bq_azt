// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UploadImageActivity_ViewBinding<T extends UploadImageActivity> implements Unbinder {
  protected T target;

  @UiThread
  public UploadImageActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.bank = Utils.findRequiredViewAsType(source, R.id.bank, "field 'bank'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.cardRecyclerView = Utils.findRequiredViewAsType(source, R.id.card_recycler_view, "field 'cardRecyclerView'", RecyclerView.class);
    target.certificateRecyclerView = Utils.findRequiredViewAsType(source, R.id.certificate_recycler_view, "field 'certificateRecyclerView'", RecyclerView.class);
    target.pactRecyclerView = Utils.findRequiredViewAsType(source, R.id.pact_recycler_view, "field 'pactRecyclerView'", RecyclerView.class);
    target.btnAdd = Utils.findRequiredViewAsType(source, R.id.btn_add, "field 'btnAdd'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.bank = null;
    target.title = null;
    target.cardRecyclerView = null;
    target.certificateRecyclerView = null;
    target.pactRecyclerView = null;
    target.btnAdd = null;

    this.target = null;
  }
}
