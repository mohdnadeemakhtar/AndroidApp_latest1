package de.seco.bloxxapp.activities;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import de.seco.bloxxapp.general.WallFragment;
import de.seco.bloxxapp.generated.R;

@SuppressLint("NewApi")
public class HomeWallActivity extends Activity {
	
	
	  	private DrawerLayout mDrawerLayout;
	    private ListView mDrawerList;
	    private ActionBarDrawerToggle mDrawerToggle;

	    private CharSequence mDrawerTitle;
	    private CharSequence mTitle;
	    private String[] mPlanetTitles;
	    Context context;
	    
	
	@SuppressLint("NewApi")
	@Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.home_wall_activity);
	        HomeWallActivity homeWallActivity = HomeWallActivity.this;
	        context = homeWallActivity;

	        mTitle = mDrawerTitle = getTitle();
	        mPlanetTitles = getResources().getStringArray(R.array.navigation_array);
	        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
	        mDrawerList = (ListView) findViewById(R.id.left_drawer);

	        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
	        mDrawerList.setAdapter(new ArrayAdapter<String>(this,R.layout.drawer_list_item, mPlanetTitles));
	        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
     //	    getActionBar().setDisplayHomeAsUpEnabled(true);
//	        getActionBar().setHomeButtonEnabled(true);
            getActionBar().hide();
            
	        mDrawerToggle = new ActionBarDrawerToggle( this, mDrawerLayout,  R.drawable.ic_drawer,   R.string.drawer_open,   R.string.drawer_close) {
	            public void onDrawerClosed(View view) {
	                getActionBar().setTitle(mTitle);
	                invalidateOptionsMenu(); 
	            }

	            public void onDrawerOpened(View drawerView) {
	                getActionBar().setTitle(mDrawerTitle);
	                invalidateOptionsMenu(); 
	            }
	        };
	        mDrawerLayout.setDrawerListener(mDrawerToggle);

	        if (savedInstanceState == null) {
	            selectItem(0,context);
	        }
	    }
	 
	 
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        MenuInflater inflater = getMenuInflater();
	        inflater.inflate(R.menu.main, menu);
	        return super.onCreateOptionsMenu(menu);
	    }

	    /* Called whenever we call invalidateOptionsMenu() */
	    @Override
	    public boolean onPrepareOptionsMenu(Menu menu) {
	        // If the nav drawer is open, hide action items related to the content view
	        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
	        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
	        return super.onPrepareOptionsMenu(menu);
	    }

	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {	       
	        if (mDrawerToggle.onOptionsItemSelected(item)) {
	            return true;
	        }
	        // Handle action buttons
	        switch(item.getItemId()) {
	        case R.id.action_websearch:
	            // create intent to perform web search for this planet
	            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
	            intent.putExtra(SearchManager.QUERY, getActionBar().getTitle());	           
	            if (intent.resolveActivity(getPackageManager()) != null) {
	                startActivity(intent);
	            } else {
	                Toast.makeText(this, R.string.app_not_available, Toast.LENGTH_LONG).show();
	            }
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	        }
	    }

	    /* The click listner for ListView in the navigation drawer */
	    private class DrawerItemClickListener implements ListView.OnItemClickListener {
	        @Override
	        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	        	
	            selectItem(position,context);
	        }
	    }

	    private void selectItem(int position,Context context) {
	        // update the main content by replacing fragments
	        Fragment fragment = new WallFragment(context, mDrawerLayout,mDrawerList);
	        Bundle args = new Bundle();
	        args.putInt(WallFragment.ARG_PLANET_NUMBER, position);
	        fragment.setArguments(args);

	        FragmentManager fragmentManager = getFragmentManager();
	        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

	        // update selected item and title, then close the drawer
	        mDrawerList.setItemChecked(position, true);
	        setTitle(mPlanetTitles[position]);
	        mDrawerLayout.closeDrawer(mDrawerList);
	        
	        
	    }

	    @Override
	    public void setTitle(CharSequence title) {
	        mTitle = title;
	        getActionBar().setTitle(mTitle);
	    }

	    /**
	     * When using the ActionBarDrawerToggle, you must call it during
	     * onPostCreate() and onConfigurationChanged()...
	     */

	    @Override
	    protected void onPostCreate(Bundle savedInstanceState) {
	        super.onPostCreate(savedInstanceState);
	        // Sync the toggle state after onRestoreInstanceState has occurred.
	        mDrawerToggle.syncState();
	    }

	    @Override
	    public void onConfigurationChanged(Configuration newConfig) {
	        super.onConfigurationChanged(newConfig);
	        // Pass any configuration change to the drawer toggls
	        mDrawerToggle.onConfigurationChanged(newConfig);
	    }

	    /**
	     * Fragment that appears in the "content_frame", shows a planet
	     */
	   
}
