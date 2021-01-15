// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import com.azhyun.massxj.utils.AlignTextView;
import com.zhy.autolayout.AutoLinearLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LeagueFragment_ViewBinding<T extends LeagueFragment> implements Unbinder {
  protected T target;

  @UiThread
  public LeagueFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.bank = Utils.findRequiredViewAsType(source, R.id.bank, "field 'bank'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.radio1 = Utils.findRequiredViewAsType(source, R.id.radio1, "field 'radio1'", RadioButton.class);
    target.radio2 = Utils.findRequiredViewAsType(source, R.id.radio2, "field 'radio2'", RadioButton.class);
    target.radio3 = Utils.findRequiredViewAsType(source, R.id.radio3, "field 'radio3'", RadioButton.class);
    target.textName = Utils.findRequiredViewAsType(source, R.id.text_name, "field 'textName'", AlignTextView.class);
    target.editName = Utils.findRequiredViewAsType(source, R.id.edit_name, "field 'editName'", EditText.class);
    target.textGender = Utils.findRequiredViewAsType(source, R.id.text_gender, "field 'textGender'", AlignTextView.class);
    target.editGender = Utils.findRequiredViewAsType(source, R.id.edit_gender, "field 'editGender'", EditText.class);
    target.textPhone = Utils.findRequiredViewAsType(source, R.id.text_phone, "field 'textPhone'", AlignTextView.class);
    target.editPhone = Utils.findRequiredViewAsType(source, R.id.edit_phone, "field 'editPhone'", EditText.class);
    target.textArea = Utils.findRequiredViewAsType(source, R.id.text_area, "field 'textArea'", EditText.class);
    target.textAddress = Utils.findRequiredViewAsType(source, R.id.text_address, "field 'textAddress'", AlignTextView.class);
    target.editAddress = Utils.findRequiredViewAsType(source, R.id.edit_address, "field 'editAddress'", EditText.class);
    target.textSoil = Utils.findRequiredViewAsType(source, R.id.text_soil, "field 'textSoil'", AlignTextView.class);
    target.editSoil = Utils.findRequiredViewAsType(source, R.id.edit_soil, "field 'editSoil'", EditText.class);
    target.textSynopsis = Utils.findRequiredViewAsType(source, R.id.text_synopsis, "field 'textSynopsis'", AlignTextView.class);
    target.editSynopsis = Utils.findRequiredViewAsType(source, R.id.edit_synopsis, "field 'editSynopsis'", EditText.class);
    target.submit = Utils.findRequiredViewAsType(source, R.id.submit, "field 'submit'", Button.class);
    target.textServiceName = Utils.findRequiredViewAsType(source, R.id.text_service_name, "field 'textServiceName'", AlignTextView.class);
    target.editServiceName = Utils.findRequiredViewAsType(source, R.id.edit_service_name, "field 'editServiceName'", EditText.class);
    target.lineServiceName = Utils.findRequiredViewAsType(source, R.id.line_service_name, "field 'lineServiceName'", AutoLinearLayout.class);
    target.lineAddress = Utils.findRequiredViewAsType(source, R.id.line_address, "field 'lineAddress'", AutoLinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.bank = null;
    target.title = null;
    target.radio1 = null;
    target.radio2 = null;
    target.radio3 = null;
    target.textName = null;
    target.editName = null;
    target.textGender = null;
    target.editGender = null;
    target.textPhone = null;
    target.editPhone = null;
    target.textArea = null;
    target.textAddress = null;
    target.editAddress = null;
    target.textSoil = null;
    target.editSoil = null;
    target.textSynopsis = null;
    target.editSynopsis = null;
    target.submit = null;
    target.textServiceName = null;
    target.editServiceName = null;
    target.lineServiceName = null;
    target.lineAddress = null;

    this.target = null;
  }
}
