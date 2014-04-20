package fr.upem.piratesproject;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MenuActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		Button sound = (Button) findViewById(R.id.main_menu_sound);
		if(sound.isActivated()){
			sound.setBackgroundResource(R.drawable.sound_on);
		}else{
			sound.setBackgroundResource(R.drawable.sound_off);
		}
		setButtonOnListener(savedInstanceState);
	}
	
	private void setButtonOnListener(final Bundle savedInstanceState){
		Button bPlay = (Button) findViewById(R.id.main_menu_play);
		bPlay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "play", Toast.LENGTH_SHORT).show();
				Intent play = new Intent(getApplication(), GameArea.class);
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
				Intent menu = new Intent(getApplication(), MenuActivity.class);
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
	}

	public Bundle saveParam(Bundle data){
		if(data==null){
			data = new Bundle();
		}
		Button bSound = (Button) findViewById(R.id.main_menu_sound);
		if(bSound.isActivated()){
			data.putBoolean("Sound", true);
		}else{
			data.putBoolean("Sound", false);
		}
		return data;
		}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		setButtonOnListener(null);
	}
	
}
