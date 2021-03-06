package fr.upem.piratesproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		GameArea ga = (GameArea)findViewById(R.id.gameArea);
		ga.pause();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		final GameArea ga = (GameArea)findViewById(R.id.gameArea);
//		ga.setEasy(false);
		ga.resume();
	}
	
}
