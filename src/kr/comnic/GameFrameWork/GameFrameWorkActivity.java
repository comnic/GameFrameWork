package kr.comnic.GameFrameWork;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.cauly.android.ad.AdInfo;
import com.cauly.android.ad.AdListener;
import com.cauly.android.ad.AdView;

public class GameFrameWorkActivity extends Activity implements AdListener{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.main);
        RelativeLayout main_layout = (RelativeLayout)findViewById(R.id.main_layout);
        main_layout.addView(new GameView(this));
        
        AdInfo ads_info = new AdInfo();
        //ads_info.initData("", adtype, gender, age, gps, effect, allowcall, reloadInterval)
        ads_info.initData("irmWTe609U", "CPC", "all", "all", "off", "circle", "no", 30);
        
        AdView adview = new AdView(this);
        adview.setAdListener(this);
        main_layout.addView(adview);
        
    }

	@Override
	public void onFailedToReceiveAd(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReceiveAd() {
		// TODO Auto-generated method stub
		
	}
}