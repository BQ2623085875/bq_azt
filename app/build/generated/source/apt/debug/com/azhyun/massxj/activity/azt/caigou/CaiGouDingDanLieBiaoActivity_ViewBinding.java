// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.activity.azt.caigou;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CaiGouDingDanLieBiaoActivity_ViewBinding<T extends CaiGouDingDanLieBiaoActivity> implements Unbinder {
  protected T target;

  @UiThread
  public CaiGouDingDanLieBiaoActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.bank = Utils.findRequiredViewAsType(source, R.id.bank, "field 'bank'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.xrv = Utils.findRequiredViewAsType(source, R.id.xrv, "field 'xrv'", RecyclerView.class);
    target.quanbu_l = Utils.findRequiredViewAsType(source, R.id.quanbu_l, "field 'quanbu_l'", LinearLayout.class);
    target.quanbu_t = Utils.findRequiredViewAsType(source, R.id.quanbu_t, "field 'quanbu_t'", TextView.class);
    target.daichuli_l = Utils.findRequiredViewAsType(source, R.id.daichuli_l, "field 'daichuli_l'", LinearLayout.class);
    target.daichuli_t = Utils.findRequiredViewAsType(source, R.id.daichuli_t, "field 'daichuli_t'", TextView.class);
    target.yiwancheng_l = Utils.findRequiredViewAsType(source, R.id.yiwancheng_l, "field 'yiwancheng_l'", LinearLayout.class);
    target.yiwancheng_t = Utils.findRequiredViewAsType(source, R.id.yiwancheng_t, "field 'yiwancheng_t'", TextView.class);
    target.a = Utils.findRequiredViewAsType(source, R.id.a, "field 'a'", TextView.class);
    target.b = Utils.findRequiredViewAsType(source, R.id.b, "field 'b'", TextView.class);
    target.c = Utils.findRequiredViewAsType(source, R.id.c, "field 'c'", TextView.class);
    target.wsj = Utils.findRequiredViewAsType(source, R.id.wsj, "field 'wsj'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.bank = null;
    target.title = null;
    target.xrv = null;
    target.quanbu_l = null;
    target.quanbu_t = null;
    target.daichuli_l = null;
    target.daichuli_t = null;
    target.yiwancheng_l = null;
    target.yiwancheng_t = null;
    target.a = null;
    target.b = null;
    target.c = null;
    target.wsj = null;

    this.target = null;
  }
}
