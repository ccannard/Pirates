package fr.upem.piratesproject;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

public class Pirates {
	private int live;
	private Point coordinate;
	private float speed;
	private final GameArea ga;
	private final int id;
	private final Bitmap face;
	
	public Pirates(Point initialCoordinate, GameArea ga, int id, Bitmap face) {
		this.face = face;
		this.ga = ga;
		coordinate = initialCoordinate;
		this.id = id;
	}
	
	public Point getCoodinate(){
		return this.coordinate;
	}
	
	public Bitmap getPirateBitmap(){
		return this.face;
	}
	
	public void jump(){
		Log.d("pirate", "Pirate " + id + " jump!");
	}
	
	public Rect getPiratePadBuffer(){
		Rect area;
		if(ga.getBattleGround().isEasy()){
			final int middleX;
			final int middleY;
			if(ga.getWidth()<ga.getHeight()){
				middleX = ga.getWidth();
				middleY = ga.getHeight()/2;
			}else{
				middleX = ga.getWidth()/2;
				middleY = ga.getHeight();
			}
			area = new Rect(0, 0, middleX, middleY);
		} else {
			area = new Rect(coordinate.x+50, coordinate.y-50, coordinate.x-50, coordinate.y+50);
		}
		return area;
	}
}