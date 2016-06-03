package com.snaptube.ndktest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;

public class MainActivity extends AppCompatActivity {

  public static InterstitialAd duExitInterstitialAd = null;
  public static InterstitialAd duStartInterstitialAd = null;
  public Context context;

//  public native String helloWorld();

  static {
//    System.loadLibrary("ndk_load");
//    System.loadLibrary("ijkffmpeg");
//    System.loadLibrary("ijksdl");
//    System.loadLibrary("ijkplayer");
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
          .setAction("Action", null).show();
      }
    });

//    Toast.makeText(this, helloWorld(), Toast.LENGTH_SHORT).show();

    // preload ads
    TestApplication.getInstance().initMobvistaAdWall();
    TestApplication.getInstance().preloadVideoInfoAd();

    if (duStartInterstitialAd != null) {
      duStartInterstitialAd.show();
      duStartInterstitialAd = null;
    }

    (new Handler()).postDelayed(new Runnable() {
      @Override
      public void run() {
        fetchNewExitInterstitial();
      }
    }, 30 * 10);

    context = this;
    findViewById(R.id.interstitial_ad).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent();
        intent.setClass(context, ExitInterstitialActivity.class);
        context.startActivity(intent);
      }
    });

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  private static void fetchNewExitInterstitial() {
    duExitInterstitialAd = new InterstitialAd(
      TestApplication.getInstance().getApplicationContext(),
      AdsConfig.MOBVISTA_EXIT_INTERTITIAL_FB_ID);
    duExitInterstitialAd.loadAd();
    duExitInterstitialAd.setAdListener(new InterstitialAdListener() {
      @Override
      public void onInterstitialDisplayed(Ad ad) {

      }

      @Override
      public void onInterstitialDismissed(Ad ad) {

      }

      @Override
      public void onError(Ad ad, AdError adError) {

      }

      @Override
      public void onAdLoaded(Ad ad) {

      }

      @Override
      public void onAdClicked(Ad ad) {

      }
    });
  }

  private static void fetchNewStartInterstitial() {
    if (duStartInterstitialAd != null) {
      duStartInterstitialAd.destroy();
    }
    duStartInterstitialAd = new InterstitialAd(
      TestApplication.getInstance().getApplicationContext(),
      AdsConfig.MOBVISTA_START_INTERSTITIAL_FB_ID);
    duStartInterstitialAd.loadAd();
  }

  @Override
  public void onBackPressed() {
    fetchNewStartInterstitial();
    if (duExitInterstitialAd != null) {
      duExitInterstitialAd.show();
      duExitInterstitialAd = null;
    }

    super.onBackPressed();
  }
}
