// Generated code from Butter Knife. Do not modify!
package com.azhyun.massxj.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.azhyun.massxj.R;
import com.azhyun.massxj.view.MarqueeView;
import com.youth.banner.Banner;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeFragment2_ViewBinding<T extends HomeFragment2> implements Unbinder {
  protected T target;

  @UiThread
  public HomeFragment2_ViewBinding(T target, View source) {
    this.target = target;

    target.titleAddress = Utils.findRequiredViewAsType(source, R.id.title_address, "field 'titleAddress'", TextView.class);
    target.homeBanner = Utils.findRequiredViewAsType(source, R.id.home_banner, "field 'homeBanner'", Banner.class);
    target.homeTrusteeship = Utils.findRequiredViewAsType(source, R.id.home_trusteeship, "field 'homeTrusteeship'", LinearLayout.class);
    target.homeFarming = Utils.findRequiredViewAsType(source, R.id.home_farming, "field 'homeFarming'", LinearLayout.class);
    target.homeLobby = Utils.findRequiredViewAsType(source, R.id.home_lobby, "field 'homeLobby'", LinearLayout.class);
    target.homeMessage = Utils.findRequiredViewAsType(source, R.id.home_message, "field 'homeMessage'", LinearLayout.class);
    target.marqueeView = Utils.findRequiredViewAsType(source, R.id.marquee_view, "field 'marqueeView'", MarqueeView.class);
    target.homeCombo = Utils.findRequiredViewAsType(source, R.id.home_combo, "field 'homeCombo'", LinearLayout.class);
    target.homeTrusteeship2 = Utils.findRequiredViewAsType(source, R.id.home_trusteeship2, "field 'homeTrusteeship2'", LinearLayout.class);
    target.homeSow = Utils.findRequiredViewAsType(source, R.id.home_sow, "field 'homeSow'", LinearLayout.class);
    target.homePesticide = Utils.findRequiredViewAsType(source, R.id.home_pesticide, "field 'homePesticide'", LinearLayout.class);
    target.homeHarvest = Utils.findRequiredViewAsType(source, R.id.home_harvest, "field 'homeHarvest'", LinearLayout.class);
    target.homeMyTrusteeship = Utils.findRequiredViewAsType(source, R.id.home_my_trusteeship, "field 'homeMyTrusteeship'", LinearLayout.class);
    target.homeRelease = Utils.findRequiredViewAsType(source, R.id.home_release, "field 'homeRelease'", LinearLayout.class);
    target.imgSow = Utils.findRequiredViewAsType(source, R.id.img_sow, "field 'imgSow'", ImageView.class);
    target.tvSowName = Utils.findRequiredViewAsType(source, R.id.tv_sow_name, "field 'tvSowName'", TextView.class);
    target.tvSowFullname = Utils.findRequiredViewAsType(source, R.id.tv_sow_fullname, "field 'tvSowFullname'", TextView.class);
    target.imgPesticide = Utils.findRequiredViewAsType(source, R.id.img_pesticide, "field 'imgPesticide'", ImageView.class);
    target.tvPesticideName = Utils.findRequiredViewAsType(source, R.id.tv_pesticide_name, "field 'tvPesticideName'", TextView.class);
    target.tvPesticideFullname = Utils.findRequiredViewAsType(source, R.id.tv_pesticide_fullname, "field 'tvPesticideFullname'", TextView.class);
    target.imgHarvest = Utils.findRequiredViewAsType(source, R.id.img_harvest, "field 'imgHarvest'", ImageView.class);
    target.tvHarvestName = Utils.findRequiredViewAsType(source, R.id.tv_harvest_name, "field 'tvHarvestName'", TextView.class);
    target.tvHarvestFullname = Utils.findRequiredViewAsType(source, R.id.tv_harvest_fullname, "field 'tvHarvestFullname'", TextView.class);
    target.tvComboName = Utils.findRequiredViewAsType(source, R.id.tv_combo_name, "field 'tvComboName'", TextView.class);
    target.tvComboFullname = Utils.findRequiredViewAsType(source, R.id.tv_combo_fullname, "field 'tvComboFullname'", TextView.class);
    target.imgMessage = Utils.findRequiredViewAsType(source, R.id.img_message, "field 'imgMessage'", ImageView.class);
    target.tvMore = Utils.findRequiredViewAsType(source, R.id.tv_more, "field 'tvMore'", TextView.class);
    target.titleMessage = Utils.findRequiredViewAsType(source, R.id.title_message, "field 'titleMessage'", ImageView.class);
    target.mswipeRefreshLayout = Utils.findRequiredViewAsType(source, R.id.mswipeRefreshLayout, "field 'mswipeRefreshLayout'", SwipeRefreshLayout.class);
    target.home_nb_zx = Utils.findRequiredViewAsType(source, R.id.home_nb_zx, "field 'home_nb_zx'", LinearLayout.class);
    target.tuijianll = Utils.findRequiredViewAsType(source, R.id.tuijianll, "field 'tuijianll'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.titleAddress = null;
    target.homeBanner = null;
    target.homeTrusteeship = null;
    target.homeFarming = null;
    target.homeLobby = null;
    target.homeMessage = null;
    target.marqueeView = null;
    target.homeCombo = null;
    target.homeTrusteeship2 = null;
    target.homeSow = null;
    target.homePesticide = null;
    target.homeHarvest = null;
    target.homeMyTrusteeship = null;
    target.homeRelease = null;
    target.imgSow = null;
    target.tvSowName = null;
    target.tvSowFullname = null;
    target.imgPesticide = null;
    target.tvPesticideName = null;
    target.tvPesticideFullname = null;
    target.imgHarvest = null;
    target.tvHarvestName = null;
    target.tvHarvestFullname = null;
    target.tvComboName = null;
    target.tvComboFullname = null;
    target.imgMessage = null;
    target.tvMore = null;
    target.titleMessage = null;
    target.mswipeRefreshLayout = null;
    target.home_nb_zx = null;
    target.tuijianll = null;

    this.target = null;
  }
}
