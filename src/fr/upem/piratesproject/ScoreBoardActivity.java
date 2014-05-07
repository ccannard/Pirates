package fr.upem.piratesproject;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class ScoreBoardActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score_board);
//		Log.d("PiratesMadness","page : score board");
		ListView lv = (ListView) findViewById(R.id.id_score_board);
		lv.setAdapter(new ScoreBoardAdapter(
				getApplicationContext(), R.layout.line_score_board, R.id.id_prototype_text_name_winner,
				createListOfLineScoreBoard()));
		Bundle extras = getIntent().getExtras();
		setButtonOnListener(extras);
	}
	
	private LineScoreBoard[] createListOfLineScoreBoard(){
		LineScoreBoard[] listLineScoreBoard=null;
		Scanner scan=null;
		try {
			scan = new Scanner(getAssets().open("score"));
			if(!scan.hasNext()) {
				Log.e("PiratesMadness - ScoreBoardActivity - createListOfLineScoreBoard", "Error with the scanner");
				System.exit(-1);
			}
//			int size = Integer.parseInt(scan.nextLine());
			LinkedList<LineScoreBoard> list = new LinkedList<LineScoreBoard>();
			while(scan.hasNextLine()){
				String[] tmp = scan.nextLine().split(" ");
				list.addLast(new LineScoreBoard(tmp[0], tmp[1], tmp[2], tmp[3]));
			}

			int size = list.size();
			listLineScoreBoard = new LineScoreBoard[size];
			for(int i=0; i<size; i++){
				listLineScoreBoard[i] = list.remove();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(scan!=null){
				scan.close();
			}
		}
		return listLineScoreBoard;
	}
	
	private void setButtonOnListener(final Bundle savedInstanceState){
		Button bPlay = (Button) findViewById(R.id.button_play);
		bPlay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent play = new Intent(getApplication(), MainActivity.class);
				if(savedInstanceState!=null) play.putExtras(savedInstanceState);
				startActivity(play);
			}
		});
		Button bMenu = (Button) findViewById(R.id.button_menu);
		bMenu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent menu = new Intent(getApplication(), MenuActivity.class);
				if(savedInstanceState!=null) menu.putExtras(savedInstanceState);
				startActivity(menu);
			}
		});
		Button bSettings = (Button) findViewById(R.id.button_settings);
		bSettings.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent settings = new Intent(getApplication(), SettingsActivity.class);
				if(savedInstanceState!=null) settings.putExtras(savedInstanceState);
				startActivity(settings);
			}
		});
	}

}
