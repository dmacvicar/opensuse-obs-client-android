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

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.opensuse.android.obs.data.Request;

public class RequestListActivity extends ListActivity {
	private ProgressDialog progressDialog = null; 
    private List<Request> requests = null;
    private ArrayAdapter<Request> adapter;
    private Runnable viewRequests;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requests = new ArrayList<Request>();
        this.adapter = new RequestListAdapter(this, android.R.layout.simple_list_item_1, requests);
        setListAdapter(this.adapter);

        viewRequests = new Runnable(){
            @Override
            public void run() {
                getRequests();
            }
        };
        Thread thread =  new Thread(null, viewRequests, "MagentoBackground");
        thread.start();
        progressDialog = ProgressDialog.show(RequestListActivity.this,    
              "Please wait...", "Retrieving data ...", true);
              
    }
    
    private Runnable returnRes = new Runnable() {

        @Override
        public void run() {
            if(requests != null && requests.size() > 0){
                adapter.notifyDataSetChanged();
                for(int i=0;i<requests.size();i++)
                adapter.add(requests.get(i));
            }
            progressDialog.dismiss();
            adapter.notifyDataSetChanged();
        }
    };
    
	private void getRequests(){
        try{
            requests = new ArrayList<Request>();
            Client client = new Client(this);
            requests = client.getMyRequests();
            Log.i("ARRAY", ""+ requests.size());
          } catch (Exception e) { 
            Log.e("BACKGROUND_PROC", e.getMessage());
          }
          runOnUiThread(returnRes);
      }
	
	  
}
