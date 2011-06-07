package kr.comnic.GameFrameWork;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class GraphicObject {
	protected Bitmap	m_bitmap;
	protected int		m_x;
	protected int		m_y;
	
	public GraphicObject(Bitmap bitmap){
		m_bitmap = bitmap;
		m_x = 0;
		m_y = 0;
	}
	
	public void Draw(Canvas canvas){
		canvas.drawBitmap(m_bitmap, m_x, m_y, null);
	}
	
	public void setPosition(int x, int y){
		m_x = x;
		m_y = y;
	}
	
	public int GetX(){
		return m_x;
	}
	
	public int GetY(){
		return m_y;
	}
}
