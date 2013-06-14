package de.seco.bloxxapp.general;

import de.seco.bloxxapp.generated.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {
	private Context context;
	private final String[] mobileValues;
 
	public ImageAdapter(Context context, String[] mobileValues) {
		this.context = context;
		this.mobileValues = mobileValues;
	}
 
	public View getView(int position, View convertView, ViewGroup parent) {
 
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View gridView;
 
		if (convertView == null) {
			gridView = new View(context);
			gridView = inflater.inflate(R.layout.mobilegrid, null);
 
			// set value into textview
			TextView textView = (TextView) gridView.findViewById(R.id.grid_item_label);
			textView.setText(mobileValues[position]);
 
			// set image based on selected text
			ImageView imageView = (ImageView) gridView.findViewById(R.id.grid_item_image);
 
			String mobile = mobileValues[position];
 
			if (mobile.equals("Nachrichten")) {
				imageView.setImageResource(R.drawable.nachrichten_active);
			} else if (mobile.equals("Freunde")) {
				imageView.setImageResource(R.drawable.freunde_active);
			} else if (mobile.equals("Gruppen")) {
				imageView.setImageResource(R.drawable.gruppen_aktiv);
			} else if (mobile.equals("Termine")) {
				imageView.setImageResource(R.drawable.termine_active);
			} else {
				imageView.setImageResource(R.drawable.favicon);
			}
 
		} else {
			gridView = (View) convertView;
		}
 
		return gridView;
	}
 
	@Override
	public int getCount() {
		return mobileValues.length;
	}
 
	@Override
	public Object getItem(int position) {
		return null;
	}
 
	@Override
	public long getItemId(int position) {
		return 0;
	}
 
}
