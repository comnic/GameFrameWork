package kr.comnic.ButtonBattle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import kr.comnic.GameFrameWork.AppManager;
import kr.comnic.GameFrameWork.GraphicObject;
import kr.comnic.GameFrameWork.IState;
import kr.comnic.GameFrameWork.R;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class GameOverState implements IState {
	private DBHelper m_helper;
	private GraphicObject m_background;

	private int m_score;
	
	private ArrayList<RankItem> m_rankList;
	
	public GameOverState(int _score){
		
		m_score = _score;

		m_helper = new DBHelper(AppManager.getInstance().getGameView().getContext(), "rank.db", null, 1);
		SQLiteDatabase db = m_helper.getWritableDatabase();
		
		if(m_score >= 100){
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date(System.currentTimeMillis());
			String reg_date = dateFormat.format(date);
			
			ContentValues row = new ContentValues();
			row.put("name", "Noname");
			row.put("score", m_score);
			row.put("reg_date", reg_date);
			
			db.insert("Rank", null, row);
		}
		
		m_rankList = new ArrayList<RankItem>();
		
		db = m_helper.getReadableDatabase();
		
		Cursor cursor = db.query("Rank", new String[]{"score", "reg_date"}, null, null, null, null, "score desc");
		for(int i = 0 ; i < 5 ; i++){
			if(cursor.moveToNext() == false)
				break;
			m_rankList.add(new RankItem(null, cursor.getInt(0), cursor.getString(1)));
		}

		if(!m_rankList.isEmpty()){
			db = m_helper.getWritableDatabase();
			db.execSQL(String.format("DELETE FROM Rank WHERE score < %d;", m_rankList.get(m_rankList.size()-1).score));
		}
		
		db.close();
	}

	@Override
	public void Destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Init() {
		// TODO Auto-generated method stub
		m_background = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.game_over));
	}

	@Override
	public void Render(Canvas canvas) {
		// TODO Auto-generated method stub
		m_background.Draw(canvas);
		Paint p = new Paint();
		p.setColor(Color.rgb(30, 27, 85));
		p.setTextSize(35);
		
		canvas.drawText(String.valueOf(m_score), 290, 215, p);
		
		for(int i = 0 ; i < m_rankList.size() ; i++){
			//Log.i("Game Info",String.format("%s [%s]", m_rankList.get(i).score, m_rankList.get(i).reg_date));
			canvas.drawText(String.format("%s [%s]", m_rankList.get(i).score, m_rankList.get(i).reg_date.substring(0, 10)), 104, 389 + (39*i), p);
		}
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
			AppManager.getInstance().getGameView().ChangeGameState(new GameState());

		return false;
	}

	class RankItem{
		public String name;
		public int score;
		public String reg_date;
		
		public RankItem(String _n, int _s, String _r){
			name = _n;
			score = _s;
			reg_date = _r;
		}
	}
}
