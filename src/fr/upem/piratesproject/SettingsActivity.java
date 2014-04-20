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

public class SettingsActivity extends Activity {

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		setButtonOnListener(savedInstanceState);

	}

	private void setButtonOnListener(final Bundle savedInstanceState){
		Button bPersonnage = (Button) findViewById(R.id.choose_your_character);
		bPersonnage.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent character = new Intent(getApplication(), PersonnageActivity.class);
				//Est ce qu'il faut prendre le bundle précédent et le compléter ?
				//Ou en créer un nouveau ?
				//On prend le précédent parce que certaines valeurs ont pu être sauvegardé déjà
				character.putExtras(saveParam(savedInstanceState));
				startActivity(character);
			}
		});
		Button bMap = (Button) findViewById(R.id.choose_your_map);
		bMap.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent map = new Intent(getApplication(), MapActivity.class);
				map.putExtras(saveParam(savedInstanceState));
				startActivity(map);
			}
		});
		Button bPlay = (Button) findViewById(R.id.button_play);
		bPlay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent play = new Intent(getApplication(), GameArea.class);
				play.putExtras(saveParam(savedInstanceState));
				startActivity(play);
			}
		});
		Button bMenu = (Button) findViewById(R.id.button_menu);
		bMenu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent menu = new Intent(getApplication(), MenuActivity.class);
				menu.putExtras(saveParam(savedInstanceState));
				startActivity(menu);
			}
		});
		Button bScore = (Button) findViewById(R.id.button_score_board);
		bScore.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent score = new Intent(getApplication(), ScoreBoardActivity.class);
				score.putExtras(saveParam(savedInstanceState));
				startActivity(score);
			}
		});
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//Comment récupérer les données de l'application à ce niveau la ?
		setButtonOnListener(null);
	}

	public Bundle saveParam(Bundle data){
		if(data==null){
			data = new Bundle();
		}
		EditText edit1 = (EditText)findViewById(R.id.name_player1);
		String text = edit1.getText().toString();
		//tester si le text vaut null lorsque l'utilisateur ne renseigne rien
		Log.d("PiratesMadness - SettingsActivity", "text vaut : "+text);
		data.putString("namePlayerOne", text);
		EditText edit2 = (EditText)findViewById(R.id.name_player2);
		String text2 = edit2.getText().toString();
		//tester si le text vaut null lorsque l'utilisateur ne renseigne rien
		Log.d("PiratesMadness - SettingsActivity", "text vaut : "+text2);
		data.putString("namePlayerTwo", text2);
		RadioButton modeEasy = (RadioButton) findViewById(R.id.easy_mode);
		String textMode=null;
		if(modeEasy.isChecked()){
			textMode = "easy";
		}else{
			textMode = "hard";
		}
		data.putString("mode", textMode);
		RadioButton soundOn = (RadioButton) findViewById(R.id.button_sound_on);
		boolean sound=false;
		if(soundOn.isChecked()){
			sound=true;
		}
		data.putBoolean("sound", sound);
		return data;
	}

}
