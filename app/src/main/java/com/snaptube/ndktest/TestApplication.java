package com.snaptube.ndktest;

import android.app.Application;
import android.content.Context;

import com.mobvista.msdk.MobVistaConstans;
import com.mobvista.msdk.MobVistaSDK;
import com.mobvista.msdk.out.MobVistaSDKFactory;
import com.mobvista.msdk.out.MvNativeHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xgao on 1/5/2016.
 */
public class TestApplication extends Application {
  private static Context context;

  @Override
  public void onCreate() {
    super.onCreate();
    context = this;

    MobVistaSDK mobVistaSDK = MobVistaSDKFactory.getMobVistaSDK();
    if (mobVistaSDK != null) {
      Map<String, String> map =
        mobVistaSDK.getMVConfigurationMap(AdsConfig.MOBVISTA_APP_ID,
          AdsConfig.MOBVISTA_APP_KEY);
      map.put(MobVistaConstans.PACKAGE_NAME_MANIFEST, "com.snaptube.ndktest");
      mobVistaSDK.init(map, this.getApplicationContext());
    }
  }

  public static TestApplication getInstance() {
    return (TestApplication) context;
  }

  public void initMobvistaAdWall() {
    MobVistaSDK sdk = MobVistaSDKFactory.getMobVistaSDK();
    if (sdk != null) {
      Map<String,Object> preloadMap = new HashMap<>();
      preloadMap.put(MobVistaConstans.PROPERTIES_LAYOUT_TYPE, MobVistaConstans.LAYOUT_APPWALL);
      preloadMap.put(MobVistaConstans.PROPERTIES_UNIT_ID, AdsConfig.MOBVISTA_APPWALL_UINT_ID);
      sdk.preload(preloadMap);
    }
  }

  public void preloadVideoInfoAd() {
    MobVistaSDK sdk = MobVistaSDKFactory.getMobVistaSDK();
    if (sdk != null) {
      Map<String, Object> preloadMap = new HashMap<>();

      preloadMap.put(MobVistaConstans.PROPERTIES_LAYOUT_TYPE, MobVistaConstans.LAYOUT_NATIVE);
      preloadMap.put(MobVistaConstans.PROPERTIES_UNIT_ID, AdsConfig.MOBVISTA_VIDEO_INFO_UINT_ID);
      preloadMap.put(MobVistaConstans.ID_FACE_BOOK_PLACEMENT, AdsConfig.MOBVISTA_VIDEO_INFO_FB_ID);
      preloadMap.put(MobVistaConstans.PREIMAGE, true);

      List<MvNativeHandler.Template> list = new ArrayList<>();
      list.add(new MvNativeHandler.Template(MobVistaConstans.TEMPLATE_BIG_IMG, 0));
      list.add(new MvNativeHandler.Template(MobVistaConstans.TEMPLATE_MULTIPLE_IMG, 0));
      String nativeInfo = MvNativeHandler.getTemplateString(list);
      preloadMap.put(MobVistaConstans.NATIVE_INFO, nativeInfo);

      sdk.preload(preloadMap);
    }
  }
}
