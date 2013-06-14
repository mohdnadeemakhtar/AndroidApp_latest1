package de.seco.bloxxapp.general;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import de.seco.bloxxapp.generated.R;

public class BinderData extends BaseAdapter {

	LayoutInflater inflater;
	ImageView thumb_image;
	List<HashMap<String,String>> dataCollection;
	ViewHolder holder;
	
	public BinderData() {
	}
	
	public BinderData(Activity act, List<HashMap<String,String>> map) {
		
		this.dataCollection = map;
		
		inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	

	public int getCount() {
		return dataCollection.size();
	}

	public Object getItem(int arg0) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		 
		View vi=convertView;
	    if(convertView==null){
	     
	      vi = inflater.inflate(R.layout.list_row, null);
	      holder = new ViewHolder();	     
	      holder.firstName = (TextView)vi.findViewById(R.id.firstName); // city name
	      holder.lastName = (TextView)vi.findViewById(R.id.lastName); // city weather overview
	      holder.email = (TextView)vi.findViewById(R.id.email); // city weather overview
	      holder.image = (ImageView) vi.findViewById(R.id.image);
	 
	      vi.setTag(holder);
	    }
	    else{
	    	
	    	holder = (ViewHolder)vi.getTag();
	    }

	      holder.firstName.setText(dataCollection.get(position).get("firstName"));
	      holder.lastName.setText(dataCollection.get(position).get("lastName"));
	      holder.email.setText(dataCollection.get(position).get("email"));
	      byte img[] = BloxxResources.myBloxxUser.getImage();
	      if(null != img){
	    	  holder.image.setImageBitmap(BitmapFactory.decodeByteArray(img, 0, img.length));  
	      }
	      
	      
	      return vi;
	}
	
	/*
	 * 
	 * */
	static class ViewHolder{
		
		TextView firstName;
		TextView lastName;
		TextView email;
		ImageView image;
	}
	
}



