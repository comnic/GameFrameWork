package kr.comnic.ButtonBattle;

import kr.comnic.GameFrameWork.AppManager;
import kr.comnic.GameFrameWork.IState;
import kr.comnic.GameFrameWork.R;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class IntroState implements IState {
	Bitmap m_background;
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
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		AppManager.getInstance().getGameView().ChangeGameState(new GameState());
		return true;
	}

	@Override
	public void Render(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawBitmap(m_background, x, y, null);
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		
	}

}
