package fr.upem.piratesproject;

import java.io.File;
import java.util.Scanner;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ScoreBoardActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score_board);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		TableLayout tableLayout = (TableLayout) findViewById(R.id.id_score_board);
		Scanner scText = new Scanner(new File("assets/score"));
		tableLayout.addView(child);
		setContentView(tableLayout);
	}
}
