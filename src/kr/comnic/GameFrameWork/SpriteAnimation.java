package kr.comnic.GameFrameWork;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class SpriteAnimation extends GraphicObject {
	private Rect m_SRect;
	private int m_fps;
	private int m_noOfFrames;
	
	private long m_frameTimer;
	private int m_currentFrame;
	private int m_spriteWidth;
	private int m_spriteHeight;
	
	public SpriteAnimation(Bitmap bitmap){
		super(bitmap);
		
		m_SRect = new Rect(0, 0, 0, 0);
		m_frameTimer = 0;
		m_currentFrame = 0;
	}
	
	public void InitSprite(int height, int width, int theFPS, int theFrameCount){
		m_spriteHeight = height;
		m_spriteWidth = width;
		m_SRect.top = 0;
		m_SRect.bottom = m_spriteHeight;
		m_SRect.left = 0;
		m_SRect.right = m_spriteWidth;
		m_fps = 1000 / theFPS;
		m_noOfFrames = theFrameCount;
	}

	@Override
	public void Draw(Canvas canvas) {
		// TODO Auto-generated method stub
		Rect dest = new Rect(m_x, m_y, m_x + m_spriteWidth, m_y + m_spriteHeight);
		canvas.drawBitmap(m_bitmap, m_SRect, dest, null);
	}
	
	public void Update(long GameTime){
		if(GameTime > m_frameTimer + m_fps){
			m_frameTimer = GameTime;
			m_currentFrame += 1;
			if(m_currentFrame >= m_noOfFrames){
				m_currentFrame = 0;
			}
		}
		m_SRect.left = m_currentFrame * m_spriteWidth;
		m_SRect.right = m_SRect.left + m_spriteWidth;
	}
}
