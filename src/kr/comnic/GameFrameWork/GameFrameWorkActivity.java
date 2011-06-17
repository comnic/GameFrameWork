package kr.comnic.GameFrameWork;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.cauly.android.ad.AdInfo;
import com.cauly.android.ad.AdListener;
import com.cauly.android.ad.AdView;

public class GameFrameWorkActivity extends Activity implements AdListener{
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

        RelativeLayout ad_layout = (RelativeLayout)findViewById(R.id.ad_layout);        
        AdInfo ads_info = new AdInfo();
        //ads_info.initData("", adtype, gender, age, gps, effect, allowcall, reloadInterval)
        //irmWTe609U
        ads_info.initData("irmWTe609U", "CPC", "all", "all", "off", "circle", "no", 30);
       
        AdView adview = new AdView(this);
        adview.setAdListener(this);
        ad_layout.addView(adview);
        
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReceiveAd() {
		// TODO Auto-generated method stub
		
	}
}