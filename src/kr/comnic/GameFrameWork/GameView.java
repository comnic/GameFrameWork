package kr.comnic.GameFrameWork;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class GameView extends SurfaceView implements SurfaceHolder.Callback {

	private GameViewThread m_thread;
	
	public GameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		getHolder().addCallback(this);
		m_thread = new GameViewThread(getHolder(), this);
		
	}
	
	public void OnDraw(Canvas canvas){
		Bitmap _scratch = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
		canvas.drawColor(Color.BLACK);
		canvas.drawBitmap(_scratch, 10, 10, null);
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		m_thread.setRunnung(true);
		m_thread.start();
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		boolean retry = true;
		m_thread.setRunnung(false);
		while(retry){
			try{
				m_thread.join();
				retry = false;
			}catch(InterruptedException e){
			}
		}
		
	}
	
	public void Update(){
		
	}

}
