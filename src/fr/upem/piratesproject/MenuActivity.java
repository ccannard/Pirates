package fr.upem.piratesproject;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Bundle extras = getIntent().getExtras();
		/*if(extras==null){
			Log.d("PiratesMadness","savedInstanceState : is null");
		}else{
			for(String k : extras.keySet()){
				Log.d("PiratesMadness","Key : "+k);
			}
		}*/

		setContentView(R.layout.activity_menu);
		Button sound = (Button) findViewById(R.id.main_menu_sound);
		if(extras!=null){
			Log.d("PiratesMadness","sound state : "+extras.getBoolean("sound"));
			if(extras.getBoolean("sound")){
				sound.setBackgroundResource(R.drawable.sound_on);
				sound.setActivated(true);
			}else{
				sound.setBackgroundResource(R.drawable.sound_off);
				sound.setActivated(false);
			}
		}else{
			if(sound.isActivated()){
				sound.setBackgroundResource(R.drawable.sound_on);
				sound.setActivated(true);
			}else{
				sound.setBackgroundResource(R.drawable.sound_off);
				sound.setActivated(false);
			}
		}
		setButtonOnListener(extras);
	}

	private void setButtonOnListener(final Bundle savedInstanceState){
		Button bPlay = (Button) findViewById(R.id.main_menu_play);
		bPlay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "play", Toast.LENGTH_SHORT).show();
				Intent play = new Intent(getApplication(), MainActivity.class);
				play.putExtras(saveParam(savedInstanceState));
				startActivity(play);
			}
		});
		Button bSettings = (Button) findViewById(R.id.main_menu_settings);
		bSettings.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Settings", Toast.LENGTH_SHORT).show();
				Intent menu = new Intent(getApplication(), SettingsActivity.class);
				menu.putExtras(saveParam(savedInstanceState));
				startActivity(menu);
			}
		});
		Button bScore = (Button) findViewById(R.id.main_menu_score_board);
		bScore.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Score Board", Toast.LENGTH_SHORT).show();
				Intent score = new Intent(getApplication(), ScoreBoardActivity.class);
				score.putExtras(saveParam(savedInstanceState));
				startActivity(score);
			}
		});
		final Button bSound = (Button) findViewById(R.id.main_menu_sound);
		bScore.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				boolean sound;
				if(bSound.isActivated()){
//					sound=true;
					bSound.setBackgroundResource(R.drawable.sound_on);
				}else{
//					sound=false;
					bSound.setBackgroundResource(R.drawable.sound_off);
				}
//				Log.d("PiratesMadness","Sound "+sound);
				//				savedInstanceState.putBoolean("sound", sound);
			}
		});
	}

	public Bundle saveParam(Bundle data){
		if(data==null){
			data = new Bundle();
		}
		Button bSound = (Button) findViewById(R.id.main_menu_sound);
		if(bSound.isActivated()){
			data.putBoolean("sound", true);
		}else{
			data.putBoolean("sound", false);
		}
//		for(String k : data.keySet()){
//			Log.d("PiratesMadness","key : "+k);
//		}	
		Log.d("PiratesMadness","Sound state menu : "+data.getBoolean("sound"));
		return data;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//		setButtonOnListener(null);
	}

}
