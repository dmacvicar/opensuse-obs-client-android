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

public class ProjectListActivity extends ListActivity {
	private ProgressDialog progressDialog = null; 
    private List<Project> projects = null;
    private ArrayAdapter<Project> adapter;
    private Runnable viewProjects;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        projects = new ArrayList<Project>();
        this.adapter = new ProjectAdapter(this, android.R.layout.simple_list_item_1, projects);
        setListAdapter(this.adapter);

        viewProjects = new Runnable(){
            @Override
            public void run() {
                getProjects();
            }
        };
        Thread thread =  new Thread(null, viewProjects, "MagentoBackground");
        thread.start();
        progressDialog = ProgressDialog.show(ProjectListActivity.this,    
              "Please wait...", "Retrieving data ...", true);
              
    }
    private Runnable returnRes = new Runnable() {

        @Override
        public void run() {
            if(projects != null && projects.size() > 0){
                adapter.notifyDataSetChanged();
                for(int i=0;i<projects.size();i++)
                adapter.add(projects.get(i));
            }
            progressDialog.dismiss();
            adapter.notifyDataSetChanged();
        }
    };
    
	private void getProjects(){
        try{
            projects = new ArrayList<Project>();
            Client client = new Client();
            projects = client.getProjects();
            Log.i("ARRAY", ""+ projects.size());
          } catch (Exception e) { 
            Log.e("BACKGROUND_PROC", e.getMessage());
          }
          runOnUiThread(returnRes);
      }
	
	  private class ProjectAdapter extends ArrayAdapter<Project> {

        private List<Project> items;

        public ProjectAdapter(Context context, int textViewResourceId, List<Project> items) {
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
                Project p = items.get(position);
                if (p != null) {
                        TextView tt = (TextView) v.findViewById(android.R.id.text1);
                        if (tt != null) {
                              tt.setText("Name: "+"foo");                            
                        }
                }
                return v;
        }
}
}
