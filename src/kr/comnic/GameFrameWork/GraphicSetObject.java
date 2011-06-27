package kr.comnic.GameFrameWork;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class GraphicSetObject extends GraphicObject {
	private Rect m_SRect;
	
	private int m_frameCount;
	private int m_curFrame;
	private int m_frameWidth;
	private int m_frameHeight;

	public GraphicSetObject(Bitmap bitmap) {
		super(bitmap);
		// TODO Auto-generated constructor stub
		m_SRect = new Rect(0, 0, 0, 0);
	}
	
	public void InitSprite(int height, int width, int theFrameCount){
		m_frameHeight = height;
		m_frameWidth = width;
		m_SRect.top = 0;
		m_SRect.bottom = m_frameHeight;
		m_SRect.left = 0;
		m_SRect.right = m_frameWidth;
		
		m_frameCount = theFrameCount;
		setCurFrame(0);
	}
	
	@Override
	public void Draw(Canvas canvas) {
		// TODO Auto-generated method stub
		if(m_curFrame >= m_frameCount){
			m_curFrame = 0;
		}
	
		m_SRect.left = m_curFrame * m_frameWidth;
		m_SRect.right = m_SRect.left + m_frameWidth;
		
		Rect dest = new Rect(m_x, m_y, m_x + m_frameWidth, m_y + m_frameHeight);
		canvas.drawBitmap(m_bitmap, m_SRect, dest, null);
	}
	
	public void Update(long GameTime){

	}

	public void setCurFrame(int _curFrame) {
		this.m_curFrame = _curFrame;
	}

	public int getCurFrame() {
		return m_curFrame;
	}

}
