package kr.comnic.ButtonBattle;

import kr.comnic.GameFrameWork.AppManager;
import kr.comnic.GameFrameWork.GameFrameWorkActivity;
import kr.comnic.GameFrameWork.GraphicObject;
import kr.comnic.GameFrameWork.IState;
import kr.comnic.GameFrameWork.R;
import android.content.Intent;
import android.graphics.Canvas;
import android.sax.StartElementListener;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class MainMenuState implements IState {
	private GraphicObject m_background; 

	@Override
	public void Destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Init() {
		// TODO Auto-generated method stub
		m_background = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.main_menu));

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
		float _x = event.getX();
		float _y = event.getY();
		
		if((_x >= 95 && _x <= 380) && (_y >= 310 && _y <= 360)){
			Intent intent = new Intent(AppManager.getInstance().getContext(), HowToActivity.class);
			AppManager.getInstance().getContext().startActivity(intent);
		}else if((_x >= 115 && _x <= 360) && (_y >= 400 && _y <= 450)){
			AppManager.getInstance().getGameView().ChangeGameState(new GameState(GameState.GAME_LEVEL_EASY, 0));
		}else if((_x >= 110 && _x <= 360) && (_y >= 485 && _y <= 530)){
			AppManager.getInstance().getGameView().ChangeGameState(new GameState(GameState.GAME_LEVEL_HARD, 0));
		}
		
		return false;
	}

}
