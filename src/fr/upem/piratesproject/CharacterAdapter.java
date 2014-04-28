package fr.upem.piratesproject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class CharacterAdapter extends ArrayAdapter<CharacterItem> {

	public CharacterAdapter(Context context, int textViewResourceId,
			CharacterItem[] objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return super.getView(position, convertView, parent);
	}

}
