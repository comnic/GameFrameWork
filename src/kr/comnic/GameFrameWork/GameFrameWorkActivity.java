package kr.comnic.GameFrameWork;

import net.daum.mobilead.AdHttpListener;
import net.daum.mobilead.MobileAdView;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.cauly.android.ad.AdInfo;
import com.cauly.android.ad.AdListener;
import com.cauly.android.ad.AdView;

public class GameFrameWorkActivity extends Activity implements AdListener, AdHttpListener{
	private RelativeLayout m_adLayout = null;
	private AdView m_caulyADView = null;
	private MobileAdView m_adamADView = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	Log.i("==================", "onCreate");
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.main);
        LinearLayout game_layout = (LinearLayout)findViewById(R.id.game_layout);
        game_layout.addView(new GameView(this));

        m_adLayout = (RelativeLayout)findViewById(R.id.ad_layout);        
        AdInfo ads_info = new AdInfo();
        //ads_info.initData("", adtype, gender, age, gps, effect, allowcall, reloadInterval)
        //irmWTe609U
        ads_info.initData("irmWTe609U", "CPC", "all", "all", "off", "circle", "no", 30);
       
        m_caulyADView = new AdView(this);
        m_caulyADView.setAdListener(this);
        
        m_adLayout.addView(m_caulyADView);
        
    }

    @Override
    protected void onStart() {
    	// TODO Auto-generated method stub
    	Log.i("==================", "onStart");
    	super.onStart();
    }
    
    @Override
    protected void onStop() {
    	// TODO Auto-generated method stub
    	Log.i("==================", "onStop");
    	super.onStop();
    }
    
    @Override
    protected void onPause() {
    	// TODO Auto-generated method stub
    	Log.i("==================", "onPause");
    	super.onPause();
    }
    
    @Override
    protected void onDestroy() {
    	// TODO Auto-generated method stub
    	Log.i("==================", "onDestroy");
    	finish();
    	super.onDestroy();
    }
    
    @Override
    protected void onRestart() {
    	// TODO Auto-generated method stub
    	Log.i("==================", "onRestart");
    	super.onRestart();
    }
    
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	Log.i("==================", "onResume");
    	super.onResume();
    }
    
	@Override
	public void onFailedToReceiveAd(boolean arg0) {
		net.daum.mobilead.AdConfig.setClientId("a1fZ08T130b7a176e6");
		m_adamADView = new MobileAdView(this);
		
		m_adamADView.setAdListener(this);
		m_adamADView.setVisibility(View.VISIBLE);
		
		m_caulyADView.setVisibility(View.GONE);
		m_adLayout.addView(m_adamADView);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReceiveAd() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void didDownloadAd_AdListener() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void failedDownloadAd_AdListener(int arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}
}