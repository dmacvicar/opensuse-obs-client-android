package org.opensuse.android.obs;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DistributionListActivity extends ListActivity {
	private ProgressDialog progressDialog = null; 
    private List<Distribution> Distributions = null;
    private ArrayAdapter<Distribution> adapter;
    private Runnable viewDistributions;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Distributions = new ArrayList<Distribution>();
        this.adapter = new DistributionAdapter(this, android.R.layout.simple_list_item_1, Distributions);
        setListAdapter(this.adapter);

        viewDistributions = new Runnable(){
            @Override
            public void run() {
                getDistributions();
            }
        };
        Thread thread =  new Thread(null, viewDistributions, "MagentoBackground");
        thread.start();
        progressDialog = ProgressDialog.show(DistributionListActivity.this,    
              "Please wait...", "Retrieving data ...", true);
              
    }
    private Runnable returnRes = new Runnable() {

        @Override
        public void run() {
            if(Distributions != null && Distributions.size() > 0){
                adapter.notifyDataSetChanged();
                for(int i=0;i<Distributions.size();i++)
                adapter.add(Distributions.get(i));
            }
            progressDialog.dismiss();
            adapter.notifyDataSetChanged();
        }
    };
    
	private void getDistributions(){
        try{
            Distributions = new ArrayList<Distribution>();
            Client client = new Client();
            Distributions = client.getDistributions();
            Log.i("ARRAY", ""+ Distributions.size());
          } catch (Exception e) { 
            Log.e("BACKGROUND_PROC", e.getMessage());
          }
          runOnUiThread(returnRes);
      }
	
	  private class DistributionAdapter extends ArrayAdapter<Distribution> {

        private List<Distribution> items;

        public DistributionAdapter(Context context, int textViewResourceId, List<Distribution> items) {
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
                Distribution p = items.get(position);
                if (p != null) {
                        TextView tt = (TextView) v.findViewById(android.R.id.text1);
                        if (tt != null) {
                              tt.setText(p.getName());                            
                        }
                }
                return v;
        }
}
}
