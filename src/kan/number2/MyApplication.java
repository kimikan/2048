package kan.number2;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

public class MyApplication extends AndroidApplication {

	public static MyApplication context;
	
	public Handler handler;
	
	AdView adView;

	public String getResById(int res) {
		return this.getResources().getString(res);
	}
	
	public void setAdVisible(final boolean show) {
		handler.post(new Runnable() {

			@Override
			public void run() {
				adView.setVisibility(show ? View.VISIBLE : View.GONE);
				if(show) {
					adView.loadAd(new AdRequest());
				}
			}
			
		});
		
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				adView.setVisibility(View.GONE);
			}
			
		}, 80000);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		context = this;
		handler = new Handler();
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration localAndroidApplicationConfiguration = new AndroidApplicationConfiguration();
	    localAndroidApplicationConfiguration.useAccelerometer = false;
	    localAndroidApplicationConfiguration.useCompass = false;
	    localAndroidApplicationConfiguration.useWakelock = true;
	    localAndroidApplicationConfiguration.useGL20 = false;
	    localAndroidApplicationConfiguration.useAccelerometer = true;
	    
	    adView = new AdView(this, AdSize.SMART_BANNER, "a152a05f7a4c6c5");
	    
	    RelativeLayout layout = new RelativeLayout(this);
	    /*
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        */
	    RelativeLayout.LayoutParams adParams = 
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 
                                RelativeLayout.LayoutParams.WRAP_CONTENT);
        adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        adParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
	    adView.loadAd(new AdRequest());
		View gameView = initializeForView(new RapidCalcGame(), localAndroidApplicationConfiguration);
		layout.addView(gameView);
		layout.addView(adView, adParams);
		setAdVisible(false); 
		this.setContentView(layout);
	}
}
