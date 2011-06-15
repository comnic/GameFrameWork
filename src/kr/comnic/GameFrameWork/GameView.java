package kr.comnic.GameFrameWork;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.LinearLayout;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
	private Vibrator m_vibrator;
	private GameViewThread m_thread;	
	private GraphicObject m_Image;	
	private IState m_state;
	
	public GameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		setFocusable(true);
		m_vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
		
		AppManager.getInstance().setContext(context);
		AppManager.getInstance().setGameView(this);
		AppManager.getInstance().setResources(getResources());
		AppManager.getInstance().setHandler(m_handler);

		getHolder().addCallback(this);
		m_thread = new GameViewThread(getHolder(), this);
		
		ChangeGameState(new kr.comnic.ButtonBattle.IntroState());
		
	}
	
	public void OnDraw(Canvas canvas){
		canvas.drawColor(Color.BLACK);
		if(m_state != null)
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
		m_thread.setRunning(true);
		m_thread.start();
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Log.i("Surface", "Destroyed");
		
		boolean retry = true;
		m_thread.setRunning(false);
		while(retry){
			try{
				m_thread.join();
				retry = false;
			}catch(InterruptedException e){
			}
		}
		Log.i("Surface", "Destroyed Success!!");
	}
	
	public void Vibrate(long milliseconds){
		m_vibrator.vibrate(milliseconds);
	}
	
	public void Update(){
		if(m_state != null)
			m_state.Update();
	}
	
	public void ChangeGameState(IState _state){
		if(m_state != null){
			m_state.Destroy();
			m_state = null;
		}
		_state.Init();
		m_state = _state;
	}
	
	public IState getGameState(){		
		return m_state;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		m_state.onKeyDown(keyCode, event);

		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		//return super.onTouchEvent(event);
		m_state.onTouchEvent(event);
		
		return false;
	}
	
	public void setRankInfoAlertDialog(){
		final LinearLayout linear = (LinearLayout)View.inflate(AppManager.getInstance().getContext(), R.layout.rank_input, null);
		linear.setVisibility(View.VISIBLE);
		
		new AlertDialog.Builder(AppManager.getInstance().getContext())
		.setTitle("Game Over!")
		.setIcon(R.drawable.icon)
		.setView(linear)
		.setPositiveButton("Save", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				ChangeGameState(new kr.comnic.ButtonBattle.IntroState());
				m_thread.setUpdate(true);
			}
			
		
		})
		.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		})
		.show();
	}

	Handler m_handler = new Handler() {
		public void handleMessage(Message msg) {
			if(msg.what == 0){
				Log.i("Game Info", "GameOver Message Recieve!!");
				ChangeGameState(new kr.comnic.ButtonBattle.IntroState());
				/*일단 Intro로 가 버린당. */
				//m_thread.setUpdate(false);
				//setRankInfoAlertDialog();
				
			}
		}
	};
}
