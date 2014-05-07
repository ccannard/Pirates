package fr.upem.piratesproject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


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

	BattleGround bg = null;
	Thread mainThread = null;
	final SurfaceHolder sh;
	boolean active = false;
	Observer impactObserver;

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
				for (int i = 0; i < ((bg.landscape)?bg.width:bg.height); i++) {
					int size = bg.map.get(i, new ArrayList<Integer>()).size();
					for (int j = 0; j < size; j++) {
						int y = i;
						int x = bg.map.get(i).get(j);
						canvas.drawBitmap(bg.texture, x * bg.texture.getWidth(), y * bg.texture.getHeight(), null);
					}
				}
				for(int i = 0; i<2; i++){
					Pirates pirate = bg.pirates[i];
					canvas.drawBitmap(pirate.face, (float)pirate.coordinate.x, (float)pirate.coordinate.y, null);
				}
			} finally {
				sh.unlockCanvasAndPost(canvas);
			}
			if(true){
				impactObserver = new Observer() {
					
					@Override
					public void update(Observable observable, Object data) {
						BattleGround bg = (BattleGround)data;
						if(bg.pirates[0].getPirateBuffer().intersect(bg.pirates[1].getPirateBuffer())){
							if(bg.pirates[0].speed>bg.pirates[1].speed){
								bg.pirates[1].life-=1;
							}else if(bg.pirates[1].speed>bg.pirates[0].speed){
								bg.pirates[0].life-=1;
							}
							bg.pirates[0].changeOrientation(bg.pirates[0].getPirateBuffer());
							bg.pirates[0].reverse(bg.pirates[0].direction);
							bg.pirates[1].changeOrientation(bg.pirates[1].getPirateBuffer());
							bg.pirates[1].reverse(bg.pirates[1].direction);
						}
						
						//Rebond ou changement de gravité
						if(bg.pirates[0].noOrientation || bg.pirates[1].noOrientation){
							for(int i = 0; i<bg.obstacles.size();i++){
								if(bg.obstacles.get(i).intersect(bg.pirates[0].getPirateBuffer())){
									if(bg.pirates[0].noOrientation){
										bg.pirates[0].changeOrientation(bg.obstacles.get(i));
										bg.pirates[0].noOrientation = false;
									}else{
										bg.pirates[0].reverse(bg.pirates[0].direction);
									}
								}
								if(bg.obstacles.get(i).intersect(bg.pirates[1].getPirateBuffer())){
									if(bg.pirates[1].noOrientation){
										bg.pirates[1].changeOrientation(bg.obstacles.get(i));
										bg.pirates[1].noOrientation = false;
									}else{
										bg.pirates[1].reverse(bg.pirates[1].direction);
									}
								}
							}
						}
						
					}
				};
				impactObserver.update(null, this.bg);
				OnTouchListener otl = new OnTouchListener() {
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						int id = -1;
						if(bg.pirates[0].getPiratePadBuffer().contains((int)event.getX(), (int)event.getY())){
							if(!bg.pirates[1].getPiratePadBuffer().contains((int)event.getX(), (int)event.getY()))
								id = 1;
						} else if(bg.pirates[1].getPiratePadBuffer().contains((int)event.getX(), (int)event.getY())){
							if(!bg.pirates[0].getPiratePadBuffer().contains((int)event.getX(), (int)event.getY()))
								id = 2;
						}
						if(id!=-1)
							bg.pirates[id].jump();
							bg.pirates[id].noOrientation = true;
						return true;
					}
				};
				this.setOnTouchListener(otl);
			}
		}
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
		impactObserver.update(null, this);
		mainThread = new Thread(this);
		mainThread.start();
	}

}
