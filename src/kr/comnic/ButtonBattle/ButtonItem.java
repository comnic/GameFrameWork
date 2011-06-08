package kr.comnic.ButtonBattle;

import kr.comnic.GameFrameWork.AppManager;

public class ButtonItem {
	public static final int BUTTON_KIND_GREEN 		= 0;
	public static final int BUTTON_KIND_RED 		= 1;
	public static final int BUTTON_KIND_SPECIAL1 	= 2;
	public static final int BUTTON_KIND_SPECIAL2 	= 3;
	public static final int BUTTON_KIND_SPECIAL3 	= 4;
	public static final int BUTTON_KIND_SPECIAL4 	= 5;
	public static final int BUTTON_KIND_SPECIAL5 	= 6;
	public static final int BUTTON_KIND_STAR 		= 7;
	public static final int BUTTON_KIND_TIME 		= 8;
	
	private boolean m_alive = true;
	private int m_kind;
	private int m_clickCount;
	private int m_point;
	
	public ButtonItem(int _kind){
		switch(_kind){
		//빨간 버튼, 기본적인 버튼으로 1번 클릭에 1점.
		case BUTTON_KIND_RED:
			m_kind = BUTTON_KIND_RED;
			m_clickCount = 1;
			m_point = 1;			
			break;
		//에너지를 5올려준다.
		case BUTTON_KIND_SPECIAL1:
			m_kind = BUTTON_KIND_SPECIAL1;
			m_clickCount = 0;
			m_point = 0;
			break;
		//2번 클릭에 3점
		case BUTTON_KIND_SPECIAL2:
			m_kind = BUTTON_KIND_SPECIAL2;
			m_clickCount = 2;
			m_point = 3;
			break;
		//3번 클릭에 5점
		case BUTTON_KIND_SPECIAL3:
			m_kind = BUTTON_KIND_SPECIAL3;
			m_clickCount = 3;
			m_point = 5;
			break;
		//4번 클릭에 7점
		case BUTTON_KIND_SPECIAL4:
			m_kind = BUTTON_KIND_SPECIAL4;
			m_clickCount = 4;
			m_point = 7;
			break;
		//5번 클릭에 10점
		case BUTTON_KIND_SPECIAL5:
			m_kind = BUTTON_KIND_SPECIAL5;
			m_clickCount = 5;
			m_point = 10;
			break;
		//1번 클릭에 3점
		case BUTTON_KIND_STAR:
			m_kind = BUTTON_KIND_STAR;
			m_clickCount = 1;
			m_point = 3;
			break;
		//시간을 10초 올려준다.
		case BUTTON_KIND_TIME:
			m_kind = BUTTON_KIND_TIME;
			m_clickCount = 5;
			m_point = 10;
			break;
		}
	}
	
	public void clickBtn(){
		if(--m_clickCount <= 0){
			m_alive = false;
		}
	}
	
	public int getKind(){
		return m_kind;
	}

	public int getClickCount(){
		return m_clickCount;
	}

	public void setAlive(boolean _b){
		m_alive = _b;
	}
	
	public void setKind(int _kind){
		m_kind = _kind;
	}

	public void click(){
		if(m_kind == BUTTON_KIND_GREEN) return;
		
		if(--m_clickCount <= 0){
			setKind(BUTTON_KIND_GREEN);
			((GameState)AppManager.getInstance().getGameView().getGameState()).addScore(m_point);
		}
	}

}
