package kr.comnic.ButtonBattle;

import kr.comnic.GameFrameWork.AppManager;
import kr.comnic.GameFrameWork.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;

public class HowToActivity extends Activity implements OnTouchListener{

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

	    requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    
	    setContentView(R.layout.howto);
	    findViewById(R.id.ivHowTo).setOnTouchListener(this);
	
	    // TODO Auto-generated method stub
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		float _x = event.getX();
		float _y = event.getY();
		
		if((_x >= 270 && _x <= 455) && (_y >= 725 && _y <= 765))
			finish();
		
		return false;
	}

}
