package kr.comnic.GameFrameWork;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class GameView extends SurfaceView implements SurfaceHolder.Callback {

	private GameViewThread m_thread;
	
	private IState m_state;
	
	public GameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		setFocusable(true);
		
		AppManager.getInstance().setGameView(this);
		AppManager.getInstance().setResources(getResources());
		
		getHolder().addCallback(this);
		m_thread = new GameViewThread(getHolder(), this);
		
		ChangeGameState(new IntroState());
		
	}
	
	public void OnDraw(Canvas canvas){
		//Bitmap _scratch = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
		canvas.drawColor(Color.BLACK);
		//canvas.drawBitmap(_scratch, 10, 10, null);
		
		m_state.Render(canvas);		
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
		m_state.Update();
	}
	
	public void ChangeGameState(IState _state){
		if(m_state != null)
			m_state.Destroy();
		_state.Init();
		m_state = _state;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		m_state.onKeyDown(keyCode, event);
		
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		//return super.onTouchEvent(event);
		m_state.onTouchEvent(event);
		
		return true;
	}
	


}
