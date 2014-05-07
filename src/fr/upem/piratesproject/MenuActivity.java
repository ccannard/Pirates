package fr.upem.piratesproject;


import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MenuActivity extends Activity{

	private static boolean sound = true;
	private MediaPlayer media;
	
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
		ImageButton bSound = (ImageButton) findViewById(R.id.main_menu_sound);
		if(extras!=null){
			Log.d("PiratesMadness","sound state : "+extras.getBoolean("sound"));
			if(extras.getBoolean("sound")){
				bSound.setImageResource(R.drawable.sound_on);
				bSound.setActivated(true);
			}else{
				bSound.setImageResource(R.drawable.sound_off);
				bSound.setActivated(false);
			}
		}else{
			if(bSound.isActivated()){
				bSound.setImageResource(R.drawable.sound_on);
//				bSound.setActivated(true);
			}else{
				bSound.setImageResource(R.drawable.sound_off);
//				bSound.setActivated(false);
			}
		}
		setButtonOnListener(extras);
		media = MediaPlayer.create(this, R.raw.neantitude_robin);
		media.start();
		media.setLooping(true);
		
	}

	private void setButtonOnListener(final Bundle savedInstanceState){
		Button bPlay = (Button) findViewById(R.id.main_menu_play);
		bPlay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Toast.makeText(getApplicationContext(), "play", Toast.LENGTH_SHORT).show();
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
//				Toast.makeText(getApplicationContext(), "Settings", Toast.LENGTH_SHORT).show();
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
//				Toast.makeText(getApplicationContext(), "Score Board", Toast.LENGTH_SHORT).show();
				Log.d("PiratesMadness","Score Board selected");
				Intent score = new Intent(getApplication(), ScoreBoardActivity.class);
				score.putExtras(saveParam(savedInstanceState));
				startActivity(score);
			}
		});
		final ImageButton bSound = (ImageButton) findViewById(R.id.main_menu_sound);
		bScore.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				boolean sound;
				if(bSound.isActivated()){
					sound=true;
					bSound.setImageResource(R.drawable.sound_on);
					media.start();
				}else{
					sound=false;
					bSound.setImageResource(R.drawable.sound_off);
					media.pause();
//					bSound.setBackgroundResource(R.drawable.sound_off);
				}
//				bSound.invalidate();
//				Log.d("PiratesMadness","Sound "+sound);
				//				savedInstanceState.putBoolean("sound", sound);
//				getLayoutInflater().inflate(R.layout.activity_menu, null).invalidate();
			}
		});
	}

	public Bundle saveParam(Bundle data){
		if(data==null){
			data = new Bundle();
		}
		ImageButton bSound = (ImageButton) findViewById(R.id.main_menu_sound);
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
		setButtonOnListener(getIntent().getExtras());
//		Button bSound = (Button) findViewById(R.id.main_menu_sound);
//		if(sound){
//			bSound.setBackgroundResource(R.drawable.sound_on);
//		}else{
//			bSound.setBackgroundResource(R.drawable.sound_off);
//		}
	}
	
}
