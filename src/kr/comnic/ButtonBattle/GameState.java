package kr.comnic.ButtonBattle;

import java.util.Random;

import kr.comnic.GameFrameWork.AppManager;
import kr.comnic.GameFrameWork.GraphicObject;
import kr.comnic.GameFrameWork.IState;
import kr.comnic.GameFrameWork.R;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class GameState implements IState {
	private int m_score;
	private int m_limitTime;
	private int m_life;
	private long m_startGameTime;
	private long m_curGameTime;
	private boolean m_isClear;
	
	private GraphicObject m_BtnGreen;
	private GraphicObject m_BtnRed;
	private GraphicObject m_BtnSpc1;
	private GraphicObject m_BtnSpc2;
	private GraphicObject m_BtnSpc3;
	private GraphicObject m_BtnSpc4;
	private GraphicObject m_BtnSpc5;
	private GraphicObject m_BtnStar;
	private GraphicObject m_BtnTime;
	
	private GraphicObject m_numBlue[];
	private GraphicObject m_numRed[];
	private GraphicObject m_numYellow[];
	private GraphicObject m_numGreen[];


	private GraphicObject m_background;
	private ButtonItem m_bi[][];
	
	@Override
	public void Destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void Init() {
		// TODO Auto-generated method stub
		m_isClear = false;
		
		m_score = 0;
		m_limitTime = 30;
		m_life = 15;
		
		m_startGameTime = System.currentTimeMillis();
		
		m_background = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.background));
		
		m_BtnGreen = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.btn_green));
		m_BtnRed = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.btn_red));
		m_BtnSpc1 = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.btn_special001));
		m_BtnSpc2 = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.btn_special002));
		m_BtnSpc3 = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.btn_special003));
		m_BtnSpc4 = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.btn_special004));
		m_BtnSpc5 = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.btn_special005));
		m_BtnStar = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.btn_star));
		m_BtnTime = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.btn_time));
		
		m_numBlue = new GraphicObject[10];
		m_numBlue[0] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.blue_0));
		m_numBlue[1] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.blue_1));
		m_numBlue[2] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.blue_2));
		m_numBlue[3] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.blue_3));
		m_numBlue[4] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.blue_4));
		m_numBlue[5] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.blue_5));
		m_numBlue[6] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.blue_6));
		m_numBlue[7] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.blue_7));
		m_numBlue[8] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.blue_8));
		m_numBlue[9] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.blue_9));

		m_numRed = new GraphicObject[10];
		m_numRed[0] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.org_0));
		m_numRed[1] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.org_1));
		m_numRed[2] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.org_2));
		m_numRed[3] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.org_3));
		m_numRed[4] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.org_4));
		m_numRed[5] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.org_5));
		m_numRed[6] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.org_6));
		m_numRed[7] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.org_7));
		m_numRed[8] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.org_8));
		m_numRed[9] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.org_9));

		m_numYellow = new GraphicObject[10];
		m_numYellow[0] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.ye_0));
		m_numYellow[1] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.ye_1));
		m_numYellow[2] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.ye_2));
		m_numYellow[3] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.ye_3));
		m_numYellow[4] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.ye_4));
		m_numYellow[5] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.ye_5));
		m_numYellow[6] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.ye_6));
		m_numYellow[7] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.ye_7));
		m_numYellow[8] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.ye_8));
		m_numYellow[9] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.ye_9));

		m_numGreen = new GraphicObject[10];
		m_numGreen[0] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.green_0));
		m_numGreen[1] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.green_1));
		m_numGreen[2] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.green_2));
		m_numGreen[3] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.green_3));
		m_numGreen[4] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.green_4));
		m_numGreen[5] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.green_5));
		m_numGreen[6] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.green_6));
		m_numGreen[7] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.green_7));
		m_numGreen[8] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.green_8));
		m_numGreen[9] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.green_9));
		
		m_bi = new ButtonItem[9][7];
		
		initButtonItem();
	}

	@Override
	public void Render(Canvas canvas) {
		// TODO Auto-generated method stub
		m_background.Draw(canvas);
		drawInfo(canvas);
		
		int __x, __y;
		__x = __y = 0;
		int __x2, __y2;
		
		for(int y = 0 ; y < 9 ; y++){
			for(int x = 0 ; x < 7 ; x++){
				//버튼 그릴 좌표
				__x = 15 + 64*x;
				__y = 185 + 64*y;
				
				//숫자 그려줄 좌표
				__x2 = __x + 44;
				__y2 = __y + 44;
				
				if(m_bi[y][x].getKind() == ButtonItem.BUTTON_KIND_GREEN){
					m_BtnGreen.setPosition(__x, __y);
					m_BtnGreen.Draw(canvas);
				}
				else if(m_bi[y][x].getKind() == ButtonItem.BUTTON_KIND_RED){
					m_BtnRed.setPosition(__x, __y);
					m_BtnRed.Draw(canvas);
					
					m_numRed[m_bi[y][x].getClickCount()].setPosition(__x2, __y2);
					m_numRed[m_bi[y][x].getClickCount()].Draw(canvas);
				}
				else if(m_bi[y][x].getKind() == ButtonItem.BUTTON_KIND_SPECIAL1){
					m_BtnSpc1.setPosition(__x, __y);
					m_BtnSpc1.Draw(canvas);
					
					m_numBlue[m_bi[y][x].getClickCount()].setPosition(__x2, __y2);
					m_numBlue[m_bi[y][x].getClickCount()].Draw(canvas);
				}
				else if(m_bi[y][x].getKind() == ButtonItem.BUTTON_KIND_SPECIAL2){
					m_BtnSpc2.setPosition(__x, __y);
					m_BtnSpc2.Draw(canvas);

					m_numBlue[m_bi[y][x].getClickCount()].setPosition(__x2, __y2);
					m_numBlue[m_bi[y][x].getClickCount()].Draw(canvas);
				}
				else if(m_bi[y][x].getKind() == ButtonItem.BUTTON_KIND_SPECIAL3){
					m_BtnSpc3.setPosition(__x, __y);
					m_BtnSpc3.Draw(canvas);

					m_numYellow[m_bi[y][x].getClickCount()].setPosition(__x2, __y2);
					m_numYellow[m_bi[y][x].getClickCount()].Draw(canvas);
				}
				else if(m_bi[y][x].getKind() == ButtonItem.BUTTON_KIND_SPECIAL4){
					m_BtnSpc4.setPosition(__x, __y);
					m_BtnSpc4.Draw(canvas);

					m_numGreen[m_bi[y][x].getClickCount()].setPosition(__x2, __y2);
					m_numGreen[m_bi[y][x].getClickCount()].Draw(canvas);
				}
				else if(m_bi[y][x].getKind() == ButtonItem.BUTTON_KIND_SPECIAL5){
					m_BtnSpc5.setPosition(__x, __y);
					m_BtnSpc5.Draw(canvas);

					m_numRed[m_bi[y][x].getClickCount()].setPosition(__x2, __y2);
					m_numRed[m_bi[y][x].getClickCount()].Draw(canvas);
				}
				else if(m_bi[y][x].getKind() == ButtonItem.BUTTON_KIND_STAR){
					m_BtnStar.setPosition(__x, __y);
					m_BtnStar.Draw(canvas);

					m_numBlue[m_bi[y][x].getClickCount()].setPosition(__x2, __y2);
					m_numBlue[m_bi[y][x].getClickCount()].Draw(canvas);
				}
				else if(m_bi[y][x].getKind() == ButtonItem.BUTTON_KIND_TIME){
					m_BtnTime.setPosition(__x, __y);
					m_BtnTime.Draw(canvas);

					m_numBlue[m_bi[y][x].getClickCount()].setPosition(__x2, __y2);
					m_numBlue[m_bi[y][x].getClickCount()].Draw(canvas);

				}				
				else continue;
			}
		}
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		if(!m_isClear){
			isClear();
			m_curGameTime = System.currentTimeMillis();
		}


		//Log.i("Call", "Update");
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if(event.getPointerCount() > 1){
			float _x = event.getX(0);
			float _y = event.getY(0);
			clickProcess(_x, _y);

			_x = event.getX(1);
			_y = event.getY(1);
			clickProcess(_x, _y);			
		}else{
			float _x = event.getX();
			float _y = event.getY();
			
			clickProcess(_x, _y);
		}
		return false;
	}
	
	private void clickProcess(float _x, float _y){
		if(_x < 15 || _x > 462) return ;
		if(_y < 185 || _y > 760) return ;
		if(m_isClear) return;

		int _indexX = (int)((_x-15)/64.0);
		int _indexY = (int)((_y-185)/64.0);

		//Log.i("Touch", String.format("[%d, %d] - (%f, %f)", _indexX, _indexY, _x, _y));
		
		try{
			if(m_bi[_indexY][_indexX].getKind() == ButtonItem.BUTTON_KIND_GREEN){
				m_life -= 1;
			}else
				m_bi[_indexY][_indexX].click();
		}catch (Exception e) {
			;// TODO: handle exception
		}

	}
	
	private boolean isClear(){
		for(int y = 0 ; y < 9 ; y++)
			for(int x = 0 ; x < 7 ; x++)
				if(m_bi[y][x].getKind() != ButtonItem.BUTTON_KIND_GREEN)
					return false;		
		m_isClear = true;
		return true;
	}
	
	private void initButtonItem(){
		Random rand = new Random();
		
		for(int y = 0 ; y < 9 ; y++){
			for(int x = 0 ; x < 7 ; x++){				
				m_bi[y][x] = new ButtonItem(rand.nextInt(8));
			}
		}		
	}
	
	private void drawInfo(Canvas canvas){
		Paint p = new Paint();
		p.setTextSize(30);
		p.setColor(Color.WHITE);
		canvas.drawText(String.format("Left Time : %d", m_limitTime - (int)((m_curGameTime - m_startGameTime)/1000)), 15, 60, p);
		canvas.drawText(String.format("Left Life : %d", m_life), 15, 90, p);
		canvas.drawText(String.format("Score : %d", m_score), 15, 120, p);
	}
	
	public void addScore(int n){
		m_score += n;
	}

}
