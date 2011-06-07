package kr.comnic.GameFrameWork;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class AppManager {
	private static AppManager s_instance;
	
	private GameView m_gameview;
	private Resources m_resources;
	
	public static AppManager getInstance(){
		if(s_instance == null)
			s_instance = new AppManager();
		return s_instance;
	}
	
	void setGameView(GameView _gameview){
		m_gameview = _gameview;
	}
	
	void setResources(Resources _resource){
		m_resources = _resource;
	}
	
	public GameView getGameView(){
		return m_gameview;
	}
	
	public Resources getResources(){
		return m_resources;
	}
	
	public Bitmap getBitmap(int r){
		return BitmapFactory.decodeResource(m_resources, r);
	}
}