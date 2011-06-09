package kr.comnic.ButtonBattle;

import kr.comnic.GameFrameWork.AppManager;
import kr.comnic.GameFrameWork.GraphicObject;
import kr.comnic.GameFrameWork.IState;
import kr.comnic.GameFrameWork.R;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class ClearState implements IState {
	
	private GraphicObject m_background; 

	@Override
	public void Destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Init() {
		// TODO Auto-generated method stub
		m_background = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.clear_state));
	}

	@Override
	public void Render(Canvas canvas) {
		// TODO Auto-generated method stub
		m_background.Draw(canvas);
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		AppManager.getInstance().getGameView().ChangeGameState(new GameState());
		return true;
	}

}
