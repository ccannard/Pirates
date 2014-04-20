package fr.upem.piratesproject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;


public class BattleGround {

	private int height = 0;
	private int width = 0;
	private Bitmap texture;
	private SparseArray<ArrayList<Integer>> map;
	private boolean landscape;
	private Pirates[] pirates;
	private boolean easy = true;

	public static BattleGround createBattleground(InputStream level, Activity daddy, boolean isEasy, int DrawablePirate1, int DrawablePirate2){
		try {
			BattleGround bg = new BattleGround();
			bg.easy = isEasy;
			int height = 0;
			SparseArray<ArrayList<Integer>> map = new SparseArray<ArrayList<Integer>>();
			Pirates[] pirates = new Pirates[2];
			GameArea ga =((GameArea)daddy.findViewById(R.id.gameArea));
			Point pirate1 = new Point();
			Point pirate2 = new Point();
			Scanner s = new Scanner(level);
			boolean firstCircle = true;
			String line;
			while (s.hasNextLine()) {
				line = s.nextLine();
				int x = 0;
				for (char c : line.toCharArray()) {
					ArrayList<Integer> current = map.get(height,
						new ArrayList<Integer>());
					if (c == 'x'){
						current.add(x);
					}else if(c == '1' || c == '2'){
						if(Integer.parseInt(Character.toString(c))==1)
							pirate1 = new Point(x, height);
						else
							pirate2 = new Point(x, height);
					}
					map.put(height, current);
					x++;
				}
				height++;
				if (firstCircle)
					bg.width = x;
				firstCircle = false;
			}
			bg.map = map;
			bg.height = height;
			if (bg.width == 0 || height == 0)
				throw new IllegalStateException();
			float new_width;
			float new_height;
			if(bg.landscape = bg.width>height)
				daddy.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
			else
				daddy.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				
			new_width = (float)ga.getWidth() / (float)(bg.width);
			new_height = (float)ga.getHeight() / (float)(height);
			bg.texture = rescaledBitmap(ga, new_width, new_height, R.drawable.wall);
			pirates[0] = new Pirates(new Point((int)(pirate1.x*new_width), (int)(pirate1.y*new_height)), ga, 0, rescaledBitmap(ga, new_width, new_height, DrawablePirate1));
			pirates[1] = new Pirates(new Point((int)(pirate2.x*new_width), (int)(pirate2.y*new_height)), ga, 0, rescaledBitmap(ga, new_width, new_height, DrawablePirate2));
			bg.pirates = pirates;
			return bg;
		} catch (IllegalStateException ise) {
			Log.e("pirate", "Can't parse level file!");
			System.exit(-1);
		}
		Log.e("pirate", "Can't create BattleGround! Abord activity!");
		System.exit(-1);
		return null;
	}

	public static Bitmap rescaledBitmap(GameArea ga, float new_width, float new_height, int drawable){
		Bitmap basic = BitmapFactory.decodeResource(ga.getResources(),
				drawable);
		Matrix matrix = new Matrix();
		matrix.postScale(new_width / basic.getWidth(),
			new_height / basic.getHeight());
		return Bitmap.createBitmap(basic, 0, 0, basic.getWidth(),
				basic.getHeight(), matrix, true);
	}
	
	public Pirates getPirate(int id) {
		return this.pirates[id];
	}

	public boolean isEasy() {
		return easy;
	}

	public Bitmap getTexture() {
		return texture;
	}

	public SparseArray<ArrayList<Integer>> getMap() {
		return map;
	}

	public boolean isLandscape() {
		return landscape;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}