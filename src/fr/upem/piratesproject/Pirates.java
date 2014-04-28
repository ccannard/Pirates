package fr.upem.piratesproject;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

public class Pirates {
	int live;
	Point coordinate;
	float speed;
	final GameArea ga;
	final int id;
	final Bitmap face;
	final Rect padBuffer;
	boolean noOrientation;
	Orientation orientation;
	
	public Pirates(Point initialCoordinate, GameArea ga, int id, Bitmap face) {
		this.face = face;
		this.ga = ga;
		coordinate = initialCoordinate;
		this.id = id;
		final int middleX;
		final int middleY;
		if(ga.getWidth()<ga.getHeight()){
			middleX = ga.getWidth();
			middleY = ga.getHeight()/2;
		}else{
			middleX = ga.getWidth()/2;
			middleY = ga.getHeight();
		}
		this.padBuffer = new Rect(0, 0, middleX, middleY);
	}
	
	public void jump(){
		Log.d("pirate", "Pirate " + id + " jump!");
	}
	
	public Rect getPiratePadBuffer(){
		if(ga.bg.easy){
			return this.padBuffer;
		} else {
			return new Rect(coordinate.x+50, coordinate.y-50, coordinate.x-50, coordinate.y+50);
		}
	}

	public Rect getPirateBuffer() {
		Rect area = new Rect(coordinate.x+(face.getWidth()/2),coordinate.y-(face.getHeight()/2), coordinate.x-(face.getWidth()/2), coordinate.y+(face.getHeight()/2));
		
		return area;
	}

	public void reverse() {
		Orientation newOne;
		switch(orientation){
			case NORTH : newOne = Orientation.SOUTH; break;
			case SOUTH : newOne = Orientation.NORTH; break;
			case EAST : newOne = Orientation.WEST; break;
			default : newOne = Orientation.EAST; break;
		}
		this.orientation = newOne;
	}

	public void changeOrientation(Rect rect) {
		float xDelta = coordinate.x-rect.exactCenterX();
		float yDelta = coordinate.y-rect.exactCenterY();
		if(xDelta>=0.5 && xDelta<=-0.5){
			if(yDelta<=-1 && yDelta>=-2){
				orientation = Orientation.NORTH;
			} else {
				orientation = Orientation.SOUTH;
			}
		}else{
			if(xDelta<=-1 && xDelta>=-2){
				orientation = Orientation.EAST;
			} else {
				orientation = Orientation.WEST;
			}
		}
	}
}