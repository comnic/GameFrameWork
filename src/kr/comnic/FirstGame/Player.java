package kr.comnic.FirstGame;

import android.graphics.Bitmap;
import kr.comnic.GameFrameWork.SpriteAnimation;

public class Player extends SpriteAnimation {

	public Player(Bitmap bitmap) {
		super(bitmap);
		// TODO Auto-generated constructor stub
		this.InitSprite(45, 26, 4, 2);
		this.setPosition(100, 100);
	}

}
