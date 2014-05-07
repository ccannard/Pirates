package fr.upem.piratesproject;

import java.io.IOException;
import java.util.Scanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;

public class PersonnageActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_choose_character);
		GridView gv = (GridView) findViewById(R.id.grid_layout_choose_character);
		CharacterItem[] list = createListOfCharacter();
		if(list==null){
			Log.e("PiratesMadness - PersonnageActivity - onCreate", "list is null");
			System.exit(-1);
		}
//		for(CharacterItem c : list){
//			Log.d("PiratesMadness - PersonnageActivity - onCreate",c.toString());
//		}
//		if(gv==null){
//			Log.e("PiratesMadness - PersonnageActivity - onCreate", "gv is null");
//			System.exit(-1);
//		}
		
		gv.setAdapter(new CharacterAdapter(getApplicationContext(),
				R.layout.layout_prototype_image_text, R.id.prototype_text, list));
		setButtonOnClick(getIntent().getExtras());
	}
	
	private void setButtonOnClick(final Bundle extras) {
		// TODO Auto-generated method stub
		Button bBack = (Button) findViewById(R.id.back);
		bBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), MenuActivity.class);
				i.putExtras(extras);
				startActivity(i);
			}
		});
	}

	private CharacterItem[] createListOfCharacter(){
		Scanner scan;
		CharacterItem[] listofCharacter;
		try {
			scan = new Scanner(getAssets().open("character_name"));
		if(!scan.hasNext()){
			Log.e("PiratesMadness - PersonnageActivity - createListOfCharacter", "le fichier est incorrect");
			System.exit(-1);
		}
		int size = Integer.parseInt(scan.nextLine().trim());
		listofCharacter = new CharacterItem[size];
		int i=0;
		while(scan.hasNextLine()){
			String line = scan.nextLine();
			int id = getResources().getIdentifier(line.substring(0, line.indexOf(" ")), "drawable", getPackageName());
			String name = line.substring(line.indexOf(" "));	
			listofCharacter[i] = new CharacterItem(id, name);
			i++;
		}
		scan.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e("PiratesMadness - PersonnageActivity - createListOfCharacter", "error with the file");
			e.printStackTrace();
			listofCharacter=null;
		}
		return listofCharacter;
	}

}
