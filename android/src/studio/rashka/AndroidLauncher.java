package studio.rashka;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.flurry.android.FlurryAgent;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.util.HashMap;
import java.util.Map;

public class AndroidLauncher extends AndroidApplication implements AdHandler, RewardedVideoAdListener {

	private Map<String, RewardedVideoAd> videoAd;
	private int loadVideo = 4;
	private Preferences setting;
	private DisplayRatio displayRatio;

	protected Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case 0: // BonusXP
					if (videoAd.get("bonusXP").isLoaded()) {
						videoAd.get("bonusXP").show();
						loadVideo = 0;
					}
					break;
				case 1: // BonusHam
					if (videoAd.get("bonusHam").isLoaded()) {
						videoAd.get("bonusHam").show();
						loadVideo = 1;
					}
					break;
				case 2: // XP
					if (videoAd.get("upXP").isLoaded()) {
						videoAd.get("upXP").show();
						loadVideo = 2;
					}
					break;
				case 3: // Ham
					if (videoAd.get("upHam").isLoaded()) {
						videoAd.get("upHam").show();
						loadVideo = 3;
					}
					break;
				case 4: // Воскрешение
					if (videoAd.get("Angel").isLoaded()) {
						videoAd.get("Angel").show();
						loadVideo = 5;
					}
					break;
				case 404:
					if (!videoAd.get("bonusXP").isLoaded()) loadVideoAd();
					break;
			}
		}
	};

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // не даёт тухнуть экрану
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

		super.onCreate(savedInstanceState);

		RelativeLayout layout = new RelativeLayout(this);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;

		View gameView = initializeForView(new RuDragonGame(this), config);
		layout.addView(gameView);
		setContentView(layout);

		new FlurryAgent.Builder()
                .withLogEnabled(true)
                .build(this, "XX3MMGFDBMHFH94NGBN4");

		setting = Gdx.app.getPreferences("Pref_app");
		displayRatio = new DisplayRatio(getWindowManager(), setting);
		videoAd = new HashMap<>();

		MobileAds.initialize(this, "ca-app-pub-4502192705853513~4533777353");

		videoAd.put("bonusXP", MobileAds.getRewardedVideoAdInstance(this));
		videoAd.get("bonusXP").setRewardedVideoAdListener(this);

		videoAd.put("bonusHam", MobileAds.getRewardedVideoAdInstance(this));
		videoAd.get("bonusHam").setRewardedVideoAdListener(this);

		videoAd.put("upXP", MobileAds.getRewardedVideoAdInstance(this));
		videoAd.get("upXP").setRewardedVideoAdListener(this);

		videoAd.put("upHam", MobileAds.getRewardedVideoAdInstance(this));
		videoAd.get("upHam").setRewardedVideoAdListener(this);

		videoAd.put("Angel", MobileAds.getRewardedVideoAdInstance(this));
		videoAd.get("Angel").setRewardedVideoAdListener(this);
	}

	private void loadVideoAd() {
		if (loadVideo == 4) {
			videoAd.get("bonusXP").loadAd("ca-app-pub-4502192705853513/2549121152", new AdRequest.Builder().build());
			videoAd.get("bonusHam").loadAd("ca-app-pub-4502192705853513/6174162081", new AdRequest.Builder().build());
			videoAd.get("upXP").loadAd("ca-app-pub-4502192705853513/9278181038", new AdRequest.Builder().build());
			videoAd.get("upHam").loadAd("ca-app-pub-4502192705853513/8169770033", new AdRequest.Builder().build());
			videoAd.get("Angel").loadAd("ca-app-pub-4502192705853513/3628451180", new AdRequest.Builder().build());
		}
		else if (loadVideo == 0)
			videoAd.get("bonusXP").loadAd("ca-app-pub-4502192705853513/2549121152", new AdRequest.Builder().build());
		else if (loadVideo == 1)
			videoAd.get("bonusHam").loadAd("ca-app-pub-4502192705853513/6174162081", new AdRequest.Builder().build());
		else if (loadVideo == 2)
			videoAd.get("upXP").loadAd("ca-app-pub-4502192705853513/9278181038", new AdRequest.Builder().build());
		else if (loadVideo == 3)
			videoAd.get("upHam").loadAd("ca-app-pub-4502192705853513/8169770033", new AdRequest.Builder().build());
		else if (loadVideo == 5)
			videoAd.get("Angel").loadAd("ca-app-pub-4502192705853513/3628451180", new AdRequest.Builder().build());
	}

	@Override
	public void onBackPressed() { // кнопка назад

	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) { // скрываем панель навигации
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus) {
			getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
					| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
					| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
					| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
					| View.SYSTEM_UI_FLAG_FULLSCREEN
					| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
					| View.KEEP_SCREEN_ON);
		}
	}

	@Override
	public void onPointerCaptureChanged(boolean hasCapture) {

	}

	@Override
	public void showAds(final int show) {
		handler.sendEmptyMessage(show);
	}

	@Override
	public void onResume() {
		videoAd.get("bonusXP").resume(this);
		videoAd.get("bonusHam").resume(this);
		videoAd.get("upXP").resume(this);
		videoAd.get("upHam").resume(this);
		videoAd.get("Angel").resume(this);
		super.onResume();
	}

	@Override
	public void onPause() {
		videoAd.get("bonusXP").pause(this);
		videoAd.get("bonusHam").pause(this);
		videoAd.get("upXP").pause(this);
		videoAd.get("upHam").pause(this);
		videoAd.get("Angel").pause(this);
		super.onPause();
	}

	@Override
	public void onDestroy() {
		videoAd.get("bonusXP").destroy(this);
		videoAd.get("bonusHam").destroy(this);
		videoAd.get("upXP").destroy(this);
		videoAd.get("upHam").destroy(this);
		videoAd.get("Angel").destroy(this);
		super.onDestroy();
	}

	@Override
	public void onRewardedVideoAdLoaded() {
	}

	@Override
	public void onRewardedVideoAdOpened() {
	}

	@Override
	public void onRewardedVideoStarted() {
	}

	@Override
	public void onRewardedVideoAdClosed() {
		loadVideoAd();
	}

	@Override
	public void onRewarded(RewardItem rewardItem) {
		if (loadVideo == 0) { // бонус % ХР
			setting.putInteger("SuperBonusXP", setting.getInteger("SuperBonusXP") + 1);
			setting.flush();
		}
		else if (loadVideo == 1) { // бонус % мяса
			setting.putInteger("SuperBonusHam", setting.getInteger("SuperBonusHam") + 1);
			setting.flush();
		}
		else if (loadVideo == 2) { // +150xp
			float i = setting.getFloat("XP") + 500;
			setting.putFloat("XP", i);
			if (i >= 2500 && i < 5814) setting.putInteger("Level", 1);
			else if (i >= 5814 && i < 11625) setting.putInteger("Level", 2);
			else if (i >= 11625 && i < 18600) setting.putInteger("Level", 3);
			else if (i >= 18600 && i < 29760) setting.putInteger("Level", 4);
			else if (i >= 29760 && i < 47615) setting.putInteger("Level", 5);
			else if (i >= 47615 && i < 76185) setting.putInteger("Level", 6);
			else if (i >= 76185 && i < 121900) setting.putInteger("Level", 7);
			else if (i >= 121900 && i < 195035) setting.putInteger("Level", 8);
			else if (i >= 195035 && i < 312056) setting.putInteger("Level", 9);
			else if (i >= 312056) setting.putInteger("Level", 10);
			setting.flush();
		}
		else if (loadVideo == 3) { // + ham
			setting.putInteger("Bank", setting.getInteger("Bank") + 250);
			if (setting.getInteger("Bank") >= 500 && setting.getInteger("Bank500") == 0) setting.putInteger("Bank500", 1);
			else if (setting.getInteger("Bank") >= 1000 && setting.getInteger("Bank1000") == 0) setting.putInteger("Bank1000", 1);
			else if (setting.getInteger("Bank") >= 1500 && setting.getInteger("Bank1500") == 0) setting.putInteger("Bank1500", 1);
			else if (setting.getInteger("Bank") >= 3000 && setting.getInteger("Bank3000") == 0) setting.putInteger("Bank3000", 1);
			else if (setting.getInteger("Bank") >= 5500 && setting.getInteger("Bank5500") == 0) setting.putInteger("Bank5500", 1);
			else if (setting.getInteger("Bank") >= 8350 && setting.getInteger("Bank8350") == 0) setting.putInteger("Bank8350", 1);
			setting.flush();
		}
		else if (loadVideo == 5) { // воскрешение
			setting.putInteger("AngelBonus", setting.getInteger("AngelBonus") + 1);
			setting.flush();
		}
	}

	@Override
	public void onRewardedVideoAdLeftApplication() {
	}

	@Override
	public void onRewardedVideoAdFailedToLoad(int i) {
	}

	@Override
	public void onRewardedVideoCompleted() {

	}
}