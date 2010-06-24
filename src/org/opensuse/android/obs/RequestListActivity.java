package org.opensuse.android.obs;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class RequestListActivity extends ListActivity {
	private ProgressDialog progressDialog = null; 
    private List<Request> requests = null;
    private ArrayAdapter<Request> adapter;
    private Runnable viewRequests;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requests = new ArrayList<Request>();
        this.adapter = new RequestAdapter(this, android.R.layout.simple_list_item_1, requests);
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
	
	  private class RequestAdapter extends ArrayAdapter<Request> {

        private List<Request> items;

        public RequestAdapter(Context context, int textViewResourceId, List<Request> items) {
                super(context, textViewResourceId, items);
                this.items = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
                View v = convertView;
                if (v == null) {
                    LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    v = vi.inflate(android.R.layout.simple_list_item_1, null);
                }
                Request req = items.get(position);
                if (req != null) {
                        TextView tt = (TextView) v.findViewById(android.R.id.text1);
                        if (tt != null) {
                              tt.setText(req.getDescription());
                        }
                }
                return v;
        }
}
}
