package de.seco.bloxxapp.general;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import de.seco.bloxxapp.domain.BloxxUser;
import de.seco.bloxxapp.generated.R;

@SuppressLint({ "NewApi", "ValidFragment" })
public class WallFragment extends Fragment {
	
	private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    public static final String ARG_PLANET_NUMBER = "planet_number";
    private ArrayAdapter<String> listAdapter ;
    Context context;
    
    
    @SuppressLint("ValidFragment")
	public WallFragment(Context context,DrawerLayout mDrawerLayout,ListView mDrawerList) {
    	
       this.context = context;
       this.mDrawerLayout =mDrawerLayout;
       this.mDrawerList = mDrawerList;
    }

    @SuppressLint("NewApi")
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
    	   View rootView = inflater.inflate(R.layout.fragment_planet_test, container, false);
           int i = getArguments().getInt(ARG_PLANET_NUMBER);
           String navigationText = getResources().getStringArray(R.array.navigation_array)[i];          
           
           ImageView navigationIcon = (ImageView) rootView.findViewById(R.id.navigationIcon);
           navigationIcon.setOnClickListener(new OnClickListener() {
               public void onClick(View view) {
            	   mDrawerLayout.openDrawer(mDrawerList);
                 }
             });
           
           
        //Log.e("DATA :", BloxxResources.myBloxxUser.getImage().toString());
        
        

		List<HashMap<String, String>> dataCollection = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", BloxxResources.myBloxxUser.getId().toString());
		map.put("firstName", BloxxResources.myBloxxUser.getFirstName());
		map.put("lastName", BloxxResources.myBloxxUser.getLastName());
		map.put("email", BloxxResources.myBloxxUser.getEmail());
		
		dataCollection.add(map);

		BinderData bindingData = new BinderData((Activity) context,	dataCollection);
           switch (i) {
				case 0:
						Log.e("Home", "called");
					  
					  	ListView listView = (ListView) rootView.findViewById(R.id.wallList);

					    String[] planets = new String[] { "Aufwiedersehen ", "Heute sonner ist sehr gut", " Christian nicht gehen", "Christian ist da","Jupiter", "Saturn", "Uranus", "Neptune"};    
					    ArrayList<String> planetList = new ArrayList<String>();  
					    planetList.addAll( Arrays.asList(planets) );  
					    listAdapter = new ArrayAdapter<String>(context, R.layout.homewall_textview, planetList);  
					    listAdapter.add( "Ceres" );  
					    listAdapter.add( "Pluto" );  
					    listAdapter.add( "Haumea" );  
					    listAdapter.add( "Makemake" );  
					    listAdapter.add( "Eris" );  
					      
//					    listView.setAdapter(listAdapter);  
					    listView.setAdapter(bindingData);  
					  
					  
					
					break;
		
				default:
					break;
				}
           
           
           
           return rootView;
    }
}
