package org.opensuse.android.obs;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class HomeActivity extends ListActivity {
	
	public static final String[] MAIN_MENU = new String[] {
		"Projects",
		"Distributions"
	};

	SharedPreferences preferences;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Log.i("FOO", "start!");
        super.onCreate(savedInstanceState);
        
        // Initialize preferences
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		String username = preferences.getString("username", "n/a");
		String password = preferences.getString("password", "n/a");
		
        //setContentView(R.layout.main);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, MAIN_MENU));
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.menu, menu);
    	return true;
    }
    
 // This method is called once the menu is selected
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// We have only one menu option
		case R.id.preferences:
			// Launch Preference activity
			Intent i = new Intent(HomeActivity.this, BuildServiceSettingsActivity.class);
			startActivity(i);
			// A toast is a view containing a quick little message for the user.
			Toast.makeText(HomeActivity.this,
					"Here you can maintain your user credentials.",
					Toast.LENGTH_LONG).show();
			break;

		}
		return true;
	}
	
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	AlertDialog alertDialog = new AlertDialog.Builder(this).create();
    	alertDialog.setTitle("Item Selected");  
    	alertDialog.setMessage("You just clicked an  item position #" + String.valueOf(position));
    	alertDialog.setButton("OK",new DialogInterface.OnClickListener(){
    	    public void onClick(DialogInterface dialog, int which) {
    	    	return;
    	}}); 
    	alertDialog.show();
    	super.onListItemClick(l, v, position, id);
    	  
    	switch (position)
    	{
    		case 0:    			 
    			startActivity(new Intent(HomeActivity.this, ProjectListActivity.class));
    			break;
    		case 1:    			 
    			startActivity(new Intent(HomeActivity.this, DistributionListActivity.class));
    			break;
    	}
    }
}