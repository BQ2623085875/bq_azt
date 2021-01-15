// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.activity.azt;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import com.azhyun.massxj.utils.azt.PhotoViewPager;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ProductInfoImagesActivity_ViewBinding<T extends ProductInfoImagesActivity> implements Unbinder {
  protected T target;

  @UiThread
  public ProductInfoImagesActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.mViewPager = Utils.findRequiredViewAsType(source, R.id.view_pager, "field 'mViewPager'", PhotoViewPager.class);
    target.mBackTextView = Utils.findRequiredViewAsType(source, R.id.back_text_view, "field 'mBackTextView'", TextView.class);
    target.mDeleteImageView = Utils.findRequiredViewAsType(source, R.id.delete_image_view, "field 'mDeleteImageView'", ImageView.class);
    target.mTitleTextView = Utils.findRequiredViewAsType(source, R.id.title_text_view, "field 'mTitleTextView'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mViewPager = null;
    target.mBackTextView = null;
    target.mDeleteImageView = null;
    target.mTitleTextView = null;

    this.target = null;
  }
}
