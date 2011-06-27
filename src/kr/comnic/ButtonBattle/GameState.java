package kr.comnic.ButtonBattle;

import java.util.Random;

import kr.comnic.GameFrameWork.AppManager;
import kr.comnic.GameFrameWork.GraphicObject;
import kr.comnic.GameFrameWork.GraphicSetObject;
import kr.comnic.GameFrameWork.IState;
import kr.comnic.GameFrameWork.R;
import android.graphics.Canvas;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class GameState implements IState {
	public static final int GAME_LEVEL_EASY = 1;
	public static final int GAME_LEVEL_HARD = 3;
	
	private final int GAME_BUTTON_ROW = 8;
	private final int GAME_BUTTON_COL = 7;

	private final int GAME_FIRST_BUTTON_X = 15;
	private final int GAME_FIRST_BUTTON_Y = 165;
	
	private final int GAME_BUTTON_SIZE_WIDTH = 64;
	private final int GAME_BUTTON_SIZE_HEIGHT = 64;

	private final int GAME_INIT_MODE_ALL = 0;
	private final int GAME_INIT_MODE_GREEN = 1;
	
	private int m_stage = 1;
	private int m_score = 0;		//점수
	private int m_limitTime = 30;	//게임에 주어진 시간
	private int m_curTime = 0;		//남은 시간
	private int m_life = 15;		//생명 수
	private int m_clickCnt = 0;
	
	private long m_startGameTime;	//시작시간
	private long m_curGameTime;		//현재시간
	
	private boolean m_isInitGame = false;
	private boolean m_isClear = false;	//게임을 클리어 했는지 유무
	private boolean m_isFail = false;	//게임을 실패 했는지 유무
	private boolean m_isGameOver = false;
	
	private GraphicSetObject m_btnSet001;
	
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

	private GraphicObject m_numBlack[];
	private GraphicObject m_numMinus;

	private GraphicObject m_background;
	private GraphicObject m_easy_background;
	private GraphicObject m_inner_background;
	
	//ButtonItem배열
	private ButtonItem m_bi[][];
	
	@Override
	public void Destroy() {
		// TODO Auto-generated method stub
	}

	public GameState(){
		//Log.i("Game Info", "GameState GameState()");
	}
	public GameState(int _stage, int _baseScore){
		//Log.i("Game Info", "GameState GameState(int)");
		setStage(_stage);
		m_score = _baseScore;
	}
	
	@Override
	public void Init() {
		//Log.i("Game Info", "GameState Init()");
		// TODO Auto-generated method stub
		/*
		m_isClear = false;
		m_isFail = false;
		
		m_score = 0;
		m_limitTime = 30;
		m_life = 15;
		*/
		
		m_isInitGame = false;
		m_isClear = false;	//게임을 클리어 했는지 유무
		m_isFail = false;	//게임을 실패 했는지 유무
		m_isGameOver = false;

		
		/* ButtonItem배열
		[0,0,0,0,0,0,0]
		[0,0,0,0,0,0,0]
		[0,0,0,0,0,0,0]
		[0,0,0,0,0,0,0]
		[0,0,0,0,0,0,0]
		[0,0,0,0,0,0,0]
		[0,0,0,0,0,0,0]
		[0,0,0,0,0,0,0]
		[0,0,0,0,0,0,0]
	 * 
	 */
		m_bi = new ButtonItem[GAME_BUTTON_ROW][GAME_BUTTON_COL];
		
		//ButtonItem배열을 초기화 한다.
		//해당위치에 표시될 버튼으르 랜덤으로 설정한다.
		initButtonItem(GAME_INIT_MODE_ALL);
		
		//게임 시작 시간을 저장한다. 현재시간을 밀리초로 기록.
		m_curGameTime = m_startGameTime = System.currentTimeMillis();
		
		//배경으로 사용될 이미지 
		m_background = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.background));
		m_easy_background = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.easy_background));
		m_inner_background = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.inner_background));
		
		//정보(시간, 점수 등)표시할 숫자와 마이너스 기호
		m_numBlack = new GraphicObject[10];
		m_numBlack[0] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.n0));
		m_numBlack[1] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.n1));
		m_numBlack[2] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.n2));
		m_numBlack[3] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.n3));
		m_numBlack[4] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.n4));
		m_numBlack[5] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.n5));
		m_numBlack[6] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.n6));
		m_numBlack[7] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.n7));
		m_numBlack[8] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.n8));
		m_numBlack[9] = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.n9));		

		m_numMinus = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.minus));
		
		//게임에 사용될 버튼 세트.
		m_btnSet001 = new GraphicSetObject(AppManager.getInstance().getBitmap(R.drawable.btn_list_001));
		
		//게임에 사용될 버튼들.
		m_BtnGreen = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.btn_green));
		m_BtnRed = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.btn_red));
		m_BtnSpc1 = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.btn_special001));
		m_BtnSpc2 = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.btn_special002));
		m_BtnSpc3 = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.btn_special003));
		m_BtnSpc4 = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.btn_special004));
		m_BtnSpc5 = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.btn_special005));
		m_BtnStar = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.btn_star));
		m_BtnTime = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.btn_time));
		
		//버튼 아래게 표시될 누름 횟수 표시용.
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
		
	}

	@Override
	public void Render(Canvas canvas) {
		// TODO Auto-generated method stub
		
		//배경을 먼저 그린다.
		if(m_stage < GAME_LEVEL_HARD)
			m_easy_background.Draw(canvas);
		else
			m_background.Draw(canvas);
		
		//정보를 그린다.
		drawInfo(canvas);
		
		//버튼을 그린다.
		drawButton(canvas);

	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		if(m_isInitGame){
			int progressTime = (int)((m_curGameTime - m_startGameTime)/1000);
			m_curTime = m_limitTime - progressTime;
	
			//특정시간마다 그린색을 다시 다른 색으로 바꿔 준다.
			int chkTime = progressTime % 10;
			if(m_isInitGame && chkTime == 0)
				initButtonItem(GAME_INIT_MODE_GREEN);
	
			if(m_isGameOver){
				GameOver();
			}else if(m_isClear){
				//Log.i("Game Info", "Clear!!");
				AppManager.getInstance().getGameView().ChangeGameState(new ClearState(m_stage, m_curTime, m_life, m_score));
			}else if(m_isFail){
				//Log.i("Game Info", "Game Over!!");
				//
				AppManager.getInstance().getGameView().ChangeGameState(new GameOverState(m_score));

				/*
				Message msg = new Message();
				msg.what = 0;
				AppManager.getInstance().getHandler().sendMessageAtFrontOfQueue(msg);
				*/
			}else{
				//Log.i("Game Info", "Continue!!");
				m_isClear = isClear();
	
				if(m_curTime <= 0 || m_life <= 0)
					m_isFail = true;
	
				m_curGameTime = System.currentTimeMillis();
			}
		}
		//Log.i("Call", "Update");
	}

	public void GameOver(){
		//ranking대상이면
		if(false){
	        //Intent clsIntent = new Intent(AppManager.getInstance().getContext(), ClearActivity.class );
	        //AppManager.getInstance().getContext().startActivity(clsIntent);
			final LinearLayout linear = (LinearLayout)View.inflate(AppManager.getInstance().getContext(), R.layout.rank_input, null);
			linear.setVisibility(View.VISIBLE);
			;
		}else{
			
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		
		/*
		 * 멀티터치용으로 만들었는데 잘 안 되는 듯.
		 * Test가 필요함.
		 */
		if(event.getPointerCount() > 1){	//1보다 크면 멀티터치로 인식
			//첫번째 터치 처리
			float _x = event.getX(0);
			float _y = event.getY(0);
			clickProcess(_x, _y);

			//두번째 터치 처리
			_x = event.getX(1);
			_y = event.getY(1);
			clickProcess(_x, _y);			
		}else{								//멀티터치가 아닐때
			//터치 처리
			float _x = event.getX();
			float _y = event.getY();
			
			clickProcess(_x, _y);
		}
		return false;
	}
	
	/*
	 * 클릭된 좌표를 값으로 버튼 클릭을 처리한다.
	 */
	private void clickProcess(float _x, float _y){
		if(_x < GAME_FIRST_BUTTON_X || _x > 462) return ;
		if(_y < GAME_FIRST_BUTTON_Y || _y > 760) return ;
		if(m_isClear) return;

		int _indexX = (int)((_x-GAME_FIRST_BUTTON_X)/GAME_BUTTON_SIZE_WIDTH);
		int _indexY = (int)((_y-GAME_FIRST_BUTTON_Y)/GAME_BUTTON_SIZE_HEIGHT);

		//Log.i("Touch", String.format("[%d, %d] - (%f, %f)", _indexX, _indexY, _x, _y));
		
		try{
			if(m_bi[_indexY][_indexX].getKind() == ButtonItem.BUTTON_KIND_GREEN){
				AppManager.getInstance().getGameView().Vibrate(100);
				if(m_life > 0)	//마지막에 -1로 표시되지 않게 하기 위해.
					m_life -= 1;
			}else{
				m_bi[_indexY][_indexX].click();
			}
		}catch (Exception e) {
			;// TODO: handle exception
		}

	}
	
	private boolean isClear(){
		if(m_isInitGame){
			/*이거 이제 현실적으로 불가능하게 됐당...ㅋㅋ
			 * 
			for(int y = 0 ; y < GAME_BUTTON_ROW ; y++)
				for(int x = 0 ; x < GAME_BUTTON_COL ; x++)
					if(m_bi[y][x] != null && m_bi[y][x].getKind() != ButtonItem.BUTTON_KIND_GREEN)
						return false;		
			m_isClear = true;
			 */
			if(m_clickCnt < (m_stage * 15))
				return false;
		}
		return true;
	}
	
	private void initButtonItem(int _mode){
		//Log.i("Game Info", "initButton");
		Random rand = new Random();
		int nRand = 0, randKind = 0;
		
		for(int y = 0 ; y < GAME_BUTTON_ROW ; y++){
			for(int x = 0 ; x < GAME_BUTTON_COL ; x++){
				if(m_stage < GAME_LEVEL_HARD){
					if((y < 2 || y > GAME_BUTTON_ROW - 2) || (x < 1 || x > GAME_BUTTON_COL - 2)){
						m_bi[y][x] = new ButtonItem(ButtonItem.BUTTON_KIND_BLOCK);
						continue;
					}
				}
				
				if(_mode == GAME_INIT_MODE_GREEN){
					if(m_bi[y][x] != null)
						if(m_bi[y][x].getKind() == ButtonItem.BUTTON_KIND_GREEN){													
							nRand = rand.nextInt(100);
							//Log.i("Random!!!!!!!!", String.valueOf(nRand));
							if(nRand >= 0 && nRand <= 5)
								randKind = ButtonItem.BUTTON_KIND_SPECIAL1;
							else if(nRand >= 6 && nRand <= 10)
								randKind = ButtonItem.BUTTON_KIND_SPECIAL2;
							else if(nRand >= 11 && nRand <= 15)
								randKind = ButtonItem.BUTTON_KIND_SPECIAL3;
							else if(nRand >= 16 && nRand <= 20)
								randKind = ButtonItem.BUTTON_KIND_SPECIAL4;
							else if(nRand >= 21 && nRand <= 25)
								randKind = ButtonItem.BUTTON_KIND_SPECIAL5;
							else if(nRand >= 26 && nRand <= 30)
								randKind = ButtonItem.BUTTON_KIND_STAR;
							else if(nRand >= 31 && nRand <= 35)
								randKind = ButtonItem.BUTTON_KIND_TIME;
							else if(nRand >= 36 && nRand <= 60)
								randKind = ButtonItem.BUTTON_KIND_RED;
							else 
								randKind = ButtonItem.BUTTON_KIND_GREEN;
							m_bi[y][x] = new ButtonItem(randKind);
						}
				}else if(_mode == GAME_INIT_MODE_ALL){
					m_bi[y][x] = new ButtonItem(rand.nextInt(9));
				}
			}
		}
		//button이 다 만들어 졌다.
		m_isInitGame = true;
	}

	private void drawInfo(Canvas canvas){
		////////////////////////////////////////////////////////////
		//stage을 표시한다.
		////////////////////////////////////////////////////////////
		String _strStage = String.valueOf(m_stage); //일단 String형으로 바꾼다.
		drawStringNumber(canvas, _strStage, 427, 31, 20);

		////////////////////////////////////////////////////////////
		//시간을 표시한다.
		////////////////////////////////////////////////////////////
		String _strLeftTime = String.valueOf(m_curTime); //일단 String형으로 바꾼다.
		drawStringNumber(canvas, _strLeftTime, 125, 86, -20);
		
		////////////////////////////////////////////////////////////
		//Life를 표시한다.
		////////////////////////////////////////////////////////////
		drawStringNumber(canvas, String.valueOf(m_life), 263, 86, 20);
		
		////////////////////////////////////////////////////////////
		//Point를 표시한다.
		////////////////////////////////////////////////////////////		
		drawStringNumber(canvas, String.valueOf(m_score), 380, 86, 20);

		/*
		Paint p = new Paint();
		p.setTextSize(30);
		p.setColor(Color.WHITE);
		canvas.drawText(String.format("Left Time : %d", m_limitTime - (int)((m_curGameTime - m_startGameTime)/1000)), 15, 60, p);
		canvas.drawText(String.format("Left Life : %d", m_life), 15, 90, p);
		canvas.drawText(String.format("Score : %d", m_score), 15, 120, p);
		*/
	}
	
	private void drawButton(Canvas canvas){
		/*
		 * 배열 크기만큼 버튼들을 속성에 맞게 그린다.
		 */
		int __x, __y;
		__x = __y = 0;
		int __x2, __y2;
		
		for(int y = 0 ; y < GAME_BUTTON_ROW ; y++){
			for(int x = 0 ; x < GAME_BUTTON_COL ; x++){
				//버튼 그릴 좌표
				__x = GAME_FIRST_BUTTON_X + GAME_BUTTON_SIZE_WIDTH * x;
				__y = GAME_FIRST_BUTTON_Y + GAME_BUTTON_SIZE_WIDTH * y;
				
				//숫자 그려줄 좌표
				__x2 = __x + 44;
				__y2 = __y + 44;
				
		try{
				if(m_bi[y][x].getKind() == ButtonItem.BUTTON_KIND_BLOCK){
					continue;
				}
				else if(m_bi[y][x].getKind() == ButtonItem.BUTTON_KIND_GREEN){
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
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
			}
		}
	}
	
	public void drawStringNumber(Canvas _canvas, String _strNumber, int _offsetX, int _offsetY, int _numValue){
		int _index;
		int n = 0;
		int _x, _y=_offsetY;
		if(_numValue > 0){
			for(int i = 0 ; i < _strNumber.length() ; i++){
				_x = _offsetX + (_numValue*i);
				if(String.valueOf(_strNumber.charAt(i)).equals("-")){
					m_numMinus.setPosition(_x, _y);
					m_numMinus.Draw(_canvas);
				}else{
					_index = Integer.valueOf(String.valueOf(_strNumber.charAt(i)));
					m_numBlack[_index].setPosition(_x, _y);
					m_numBlack[_index].Draw(_canvas);
				}
			}
		}else{
			for(int i = _strNumber.length() ; i > 0 ; i--, n++){
				_x = _offsetX + (_numValue*n);
				if(String.valueOf(_strNumber.charAt(i-1)).equals("-")){
					m_numMinus.setPosition(_x, _y);
					m_numMinus.Draw(_canvas);
				}else{
					_index = Integer.valueOf(String.valueOf(_strNumber.charAt(i-1)));
					m_numBlack[_index].setPosition(_x, _y);
					m_numBlack[_index].Draw(_canvas);
				}
			}
		}
	}
	
	public void addScore(int n){
		m_score += n;
		
		//버튼당 클릭수가 완료되면 호출되므로 여기다 카운트 한다.
		m_clickCnt++;
	}

	public void addLife(int n){
		m_life += n;
		//Log.i("Game Info", String.format("m_life : %d", m_life));
	}

	public void addTime(int n){
		m_limitTime += n;
		//Log.i("Game Info", String.format("m_limitTime : %d", m_limitTime));
	}

	public void setStage(int _stage) {
		if(_stage <= 0)
			this.m_stage = 1;
		else
			this.m_stage = _stage;
	}

	public int getStage() {
		return m_stage;
	}
}
