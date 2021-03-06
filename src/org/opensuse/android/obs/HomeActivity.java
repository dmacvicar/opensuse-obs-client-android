/* 
  Copyright 2010 Duncan Mac-Vicar P. <dmacvicar@suse.de>
 
  This file is part of the openSUSE Build Service Android Client.

  openSUSE Build Service Android Client is free software: you
  can  redistribute it and/or modify it under the terms of the GNU
  General Public License as published by the Free Software Foundation,
  either version 3 of the License, or (at your option) any later
  version.

  openSUSE Build Service Android Client is distributed in the hope
  that it will be useful, but WITHOUT ANY WARRANTY; without even the
  implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
  See the GNU General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.opensuse.android.obs;

import android.app.ListActivity;
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
		"Pending Requests",
		"Projects",
		"Distributions"
	};

	SharedPreferences preferences;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Log.i("FOO", "start!");
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, MAIN_MENU));
        if (!isConfigured()) configure();
    }
    
    private boolean isConfigured() {
 		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		String username = preferences.getString("username", "");
		return (username != "");
    }
    
    private void configure() {    	
    	Intent i = new Intent(HomeActivity.this, BuildServiceSettingsActivity.class);
    	startActivity(i);
    	// A toast is a view containing a quick little message for the user.
    	Toast.makeText(HomeActivity.this,
    			"Please configure your build service credentials",
    			Toast.LENGTH_LONG).show();
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
    	
    	super.onListItemClick(l, v, position, id);
    	
    	if (!isConfigured()) {
    		configure();
    		return;
    	}
    	
    	switch (position)
    	{
    		case 0:    			 
    			startActivity(new Intent(HomeActivity.this, RequestListActivity.class));
    			break;
    		case 1:    			 
    			startActivity(new Intent(HomeActivity.this, ProjectListActivity.class));
    			break;
    		case 2:    			 
    			startActivity(new Intent(HomeActivity.this, DistributionListActivity.class));
    			break;
    	}
    }
}