package fr.upem.piratesproject;

import java.io.IOException;
import java.util.ArrayList;


import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class GameArea extends SurfaceView implements Runnable {

	private BattleGround bg = null;
	private Thread mainThread = null;
	private final SurfaceHolder sh;
	private boolean active = false;

	public GameArea(Context context, AttributeSet as) {
		super(context, as);
		sh = this.getHolder();
	}
	
	@Override
	public void run() {
		while (active) {
			if (!sh.getSurface().isValid()){
				continue;
			}
			try{
				if(bg==null)
					bg = BattleGround.createBattleground(getContext().getAssets().open("1"), ((Activity)this.getContext()), true, R.drawable.pirate1, R.drawable.pirate1);
			}catch(IOException ioe){
				Log.e("pirate", "Can't parse level!");
				System.exit(1);
			}
			Canvas canvas = null;
			try {
				canvas = sh.lockCanvas();
				canvas.drawARGB(255, 255, 255, 255);
				for (int i = 0; i < ((bg.isLandscape())?bg.getWidth():bg.getHeight()); i++) {
					int size = bg.getMap().get(i, new ArrayList<Integer>()).size();
					for (int j = 0; j < size; j++) {
						int y = i;
						int x = bg.getMap().get(i).get(j);
						canvas.drawBitmap(bg.getTexture(), x * bg.getTexture().getWidth(), y * bg.getTexture().getHeight(), null);
					}
				}
				for(int i = 0; i<2; i++){
					Pirates pirate = bg.getPirate(i);
					canvas.drawBitmap(pirate.getPirateBitmap(), (float)pirate.getCoodinate().x, (float)pirate.getCoodinate().y, null);
				}
			} finally {
				sh.unlockCanvasAndPost(canvas);
			}
			if(true){
				OnTouchListener otl = new OnTouchListener() {
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						int id = -1;
						if(bg.getPirate(0).getPiratePadBuffer().contains((int)event.getX(), (int)event.getY())){
							if(!bg.getPirate(1).getPiratePadBuffer().contains((int)event.getX(), (int)event.getY()))
								id = 1;
						} else if(bg.getPirate(1).getPiratePadBuffer().contains((int)event.getX(), (int)event.getY())){
							if(!bg.getPirate(0).getPiratePadBuffer().contains((int)event.getX(), (int)event.getY()))
								id = 2;
						}
						if(id!=-1)
							bg.getPirate(id).jump();
						return true;
					}
				};
				this.setOnTouchListener(otl);
			}
		}
	}
	
	public BattleGround getBattleGround(){
		return this.bg;
	}

	public void pause() {
		active = false;
		while (true) {
			try {
				mainThread.join();
			} catch (InterruptedException ie) {
				Log.e("PirateMadness", "Program fail to paused!");
			}
			break;
		}
		mainThread = null;
	}

	public void resume() {
		active = true;
		mainThread = new Thread(this);
		mainThread.start();
	}

}
