package org.opensuse.android.obs;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HomeActivity extends ListActivity {
	
	public static final String[] MAIN_MENU = new String[] {
		"Projects",
		"Distributions"
	};

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Log.i("FOO", "start!");
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, MAIN_MENU));
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
    		case 1:    			 
    			startActivity(new Intent(HomeActivity.this, DistributionListActivity.class));
    	    break;
    	}
    }	
}