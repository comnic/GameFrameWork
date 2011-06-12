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
import android.view.View;
import android.widget.TableLayout;

public class ClearState implements IState {
	
	private int m_time, m_life, m_point;
	
	private GraphicObject m_background; 

	public ClearState(int t, int l, int p){
		m_time = t;
		m_life = l;
		m_point = p;
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
		p.setTextSize(30);
		p.setColor(Color.BLACK);
		canvas.drawText(String.format("%ds", m_time), 141, 347, p);
		canvas.drawText(String.valueOf(m_life), 253, 347, p);
		canvas.drawText(String.valueOf(m_point), 366, 347, p);
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
