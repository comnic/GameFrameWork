package kr.comnic.ButtonBattle;

import kr.comnic.GameFrameWork.AppManager;
import kr.comnic.GameFrameWork.GraphicObject;
import kr.comnic.GameFrameWork.IState;
import kr.comnic.GameFrameWork.R;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class ClearState implements IState {
	private int m_stage, m_time, m_life, m_point;
	
	private GraphicObject m_background; 

	public ClearState(int _s, int _t, int _l, int _p){
		m_stage = _s;
		m_time = _t;
		m_life = _l;
		m_point = _p;
	}
	
	@Override
	public void Destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Init() {
		// TODO Auto-generated method stub
		m_background = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.clear_stage));
	}

	@Override
	public void Render(Canvas canvas) {
		// TODO Auto-generated method stub
		m_background.Draw(canvas);
		
		//TableLayout m_rankLayout = (TableLayout) AppManager.getInstance().getGameView().findViewById(R.id.rankLayout);
		//m_rankLayout.setVisibility(View.VISIBLE);
		
		Paint p = new Paint();
		p.setTextSize(35);
		p.setColor(Color.BLACK);
		//canvas.drawText(String.format("%ds", m_time), 141, 347, p);
		//canvas.drawText(String.valueOf(m_life), 253, 347, p);
		canvas.drawText(String.valueOf(m_point), 280, 310, p);
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
		
		if((_x >= 110 && _x <= 350) && (_y >= 600 && _y <= 680))
			AppManager.getInstance().getGameView().ChangeGameState(new GameState(++m_stage, m_point));
		return true;
	}

}
