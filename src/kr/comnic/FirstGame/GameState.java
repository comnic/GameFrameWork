package kr.comnic.FirstGame;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;
import kr.comnic.GameFrameWork.AppManager;
import kr.comnic.GameFrameWork.GraphicObject;
import kr.comnic.GameFrameWork.IState;
import kr.comnic.GameFrameWork.R;

public class GameState implements IState {

	private static final int WALK_SPEED = 5;

	private GraphicObject m_background;
	//private Player m_player;
	
	@Override
	public void Destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void Init() {
		// TODO Auto-generated method stub
		m_background = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.background));
		//m_player = new Player(AppManager.getInstance().getBitmap(R.drawable.walk));
	}

	@Override
	public void Render(Canvas canvas) {
		// TODO Auto-generated method stub
		m_background.Draw(canvas);
		//m_player.Draw(canvas);
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		long GameTime = System.currentTimeMillis();
		//m_player.Update(GameTime);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		/*
		switch(keyCode){
		case KeyEvent.KEYCODE_DPAD_UP:
			m_player.setPosition(m_player.GetX(), m_player.GetY() - WALK_SPEED);
			break;
		case KeyEvent.KEYCODE_DPAD_DOWN:
			m_player.setPosition(m_player.GetX(), m_player.GetY() + WALK_SPEED);
			break;
		case KeyEvent.KEYCODE_DPAD_LEFT:
			m_player.setPosition(m_player.GetX() - WALK_SPEED, m_player.GetY());
			break;
		case KeyEvent.KEYCODE_DPAD_RIGHT:
			m_player.setPosition(m_player.GetX() + WALK_SPEED, m_player.GetY());
			break;
		}
		m_player.Update(System.currentTimeMillis());
		*/		
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

}
