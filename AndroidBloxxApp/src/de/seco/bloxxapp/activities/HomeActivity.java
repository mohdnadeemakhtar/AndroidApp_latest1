package de.seco.bloxxapp.activities;

import de.seco.bloxxapp.general.BloxxResources;
import de.seco.bloxxapp.general.ImageAdapter;
import de.seco.bloxxapp.generated.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Activity {
	private GridView gridView;
	 
	static final String[] MOBILE_OS = new String[] { 
		"Nachrichten", "Freunde", "Gruppen", "Termine" };
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		
		gridView = (GridView) findViewById(R.id.gridView1);
		gridView.setAdapter(new ImageAdapter(this, MOBILE_OS));
		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Toast.makeText(
				   getApplicationContext(),
				   ((TextView) v.findViewById(R.id.grid_item_label))
				   .getText(), Toast.LENGTH_SHORT).show();
 
			}
		});
		TextView resultText = (TextView) findViewById(R.id.welcomeText);
		resultText.setText("Willkommen " + BloxxResources.myBloxxUser.getFirstName());
	}
}
