package kr.comnic.ButtonBattle;

import kr.comnic.GameFrameWork.AppManager;
import kr.comnic.GameFrameWork.IState;
import kr.comnic.GameFrameWork.R;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.TableLayout;

public class IntroState implements IState {
	private Bitmap m_background;
	
	int x;
	int y;
	
	@Override
	public void Destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void Init() {
		// TODO Auto-generated method stub
		m_background = AppManager.getInstance().getBitmap(R.drawable.intro);
/*		
        AdInfo ads_info = new AdInfo();
        ads_info.initData("irmWTe609U", "cpc", "all", "all", "off", "circle", "no", 30);

        m_adview = new AdView(AppManager.getInstance().getContext());
        m_adview.setAdListener(this);
        m_adview.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        m_adview.layout(0, 720, 480, 800);
        m_adview.invalidate();
*/		
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		Log.i("Game Info", "onTouchEvent!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		AppManager.getInstance().getGameView().ChangeGameState(new GameState());
		return true;
	}

	@Override
	public void Render(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawBitmap(m_background, x, y, null);

		//TableLayout m_rankLayout = (TableLayout) AppManager.getInstance().getGameView().findViewById(R.id.rankLayout);
		//m_rankLayout.setVisibility(View.VISIBLE);
		
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		
	}
}
