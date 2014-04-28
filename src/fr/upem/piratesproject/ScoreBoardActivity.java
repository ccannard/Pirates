package fr.upem.piratesproject;

import java.io.IOException;
import java.util.Scanner;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScoreBoardActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score_board);
//		Log.d("PiratesMadness","page : score board");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.id_score_board);
		int width = 100;
		int height = 30;
		TextView firstLine = new TextView(getApplicationContext());
		firstLine.setText("Score Board :");
		firstLine.setGravity(Gravity.CENTER);
		firstLine.setBackgroundColor(getResources().getColor(R.color.white));
		linearLayout.addView(firstLine);
		try{
			Scanner scText = new Scanner(getApplicationContext().getAssets().open("score"));
			int compteur=0;
			while(scText!=null && scText.hasNextLine() && compteur<10){
				LinearLayout linearLayout2 = new LinearLayout(getApplicationContext());
				linearLayout2.setOrientation(LinearLayout.HORIZONTAL);
				linearLayout2.setBackgroundColor(getResources().getColor(R.color.green));
//				linearLayout2.setPadding(10, 10, 10, 10);
				String tmp = scText.nextLine();
				Log.d("PiratesMadness", "ligne : "+tmp);
				String[] ressources = tmp.split(" ");
				for(String tmpRess : ressources){
					TextView textView = new TextView(getApplicationContext());
					textView.setText(tmpRess);
					textView.setTextColor(getResources().getColor(R.color.Black));
					textView.setGravity(Gravity.CENTER);
					textView.setPadding(10, 10, 10, 0);
					linearLayout2.addView(textView);
					
				}
				linearLayout.addView(linearLayout2);
			}
		}
		catch (IOException ioe){
			System.out.println("Error for reading the file");
		}
		setContentView(linearLayout);
	}
}
