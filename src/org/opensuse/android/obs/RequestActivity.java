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
  along with QuiteSleep.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.opensuse.android.obs;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.opensuse.android.XmlPersister;
import org.opensuse.android.obs.data.Request;
import org.opensuse.android.obs.data.Request.Action;
import org.opensuse.android.obs.data.Request.Action.Type;
import org.simpleframework.xml.Serializer;

public class RequestActivity extends Activity {
	private ProgressDialog progressDialog = null; 
    private Request request = null;
    private Runnable viewRequest;
    private TextView tv;
    private String diff;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        this.tv = new TextView(this);
        setContentView(this.tv);
        
        viewRequest = new Runnable(){
            @Override
            public void run() {
                getRequest();
            }
        };
        Thread thread =  new Thread(null, viewRequest, "MagentoBackground");
        thread.start();
        progressDialog = ProgressDialog.show(RequestActivity.this,    
              "Please wait...", "Retrieving data ...", true);
    }
    
    private Runnable returnRes = new Runnable() {
        @Override
        public void run() {
            if(request != null) {
            	
            	
            	tv.setText(request.getDescription() + "\n" + diff);
            }
            progressDialog.dismiss();
        }
    };
    
	private void getRequest(){
        try {
        	// see if we got the data as xml
        	Bundle extras = getIntent().getExtras();
        	if (extras.containsKey("xml")) {
        		String xml = extras.getString("xml");
        		Serializer serializer = new XmlPersister();
        		request = serializer.read(Request.class, xml);        		
        	}
        	else
        	{
        		String id = extras.getString("id");        		
        		Client client = new Client(this);
                request = client.getRequest(id);                
        	}
            
        	// get the diff
        	diff = "";
        	// set the diff of the request
        	for (Action action : request.getActions() ) {
        		if (action.getType() == Type.SUBMIT &&
        		    action.getTarget() != null) {
        			Client client = new Client(this);
        			diff += client.getDiff(action.getSource().getProject(),
        					action.getSource().getPackage(),
        					action.getTarget().getProject(),
        					action.getTarget().getPackage(),
        					action.getSource().getRev());
        		}
        	}
        	
          } catch (Exception e) { 
            Log.e("BACKGROUND_PROC", e.getMessage());
          }
          runOnUiThread(returnRes);
      }
	
	  
}
