package org.opensuse.android.obs;

import java.util.HashMap;
import java.util.List;

import org.opensuse.android.obs.data.Request;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RequestListAdapter extends ArrayAdapter<Request> {

    private List<Request> items;
    private LayoutInflater inflater;
    private HashMap<String, Bitmap> gravatars;
    private Client client;
    
    public RequestListAdapter(Context context, int textViewResourceId, List<Request> items) {
            super(context, textViewResourceId, items);
            this.inflater = LayoutInflater.from(context);
            this.items = items;
            this.client = new Client(context);
            this.gravatars = new HashMap<String, Bitmap>();            
    }

    public void loadGravatars()
	{
    	for (Request req : items ) {
    		String login = req.getState().getWho();
    		if (!gravatars.containsKey(login)) {
    			gravatars.put(login, Client.getGravatar(client.getGravatarID(login), 30));
			}
    	}
	}
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                v = inflater.inflate(R.layout.request_row, null);
            }
            Request req = items.get(position);
            if (req != null) {            	
            		ImageView iv = (ImageView) v.findViewById(R.id.request_who_icon);
            		String login = req.getState().getWho();
        			iv.setImageBitmap(Client.getGravatar(client.getGravatarID(login), 30));
                    TextView tt = (TextView) v.findViewById(R.id.req_state);
                    if (tt != null) tt.setText(req.getState().getName());
                    tt = (TextView) v.findViewById(R.id.req_state_when);
                    if (tt != null) tt.setText(req.getState().getWhen());
                    tt = (TextView) v.findViewById(R.id.req_state_who);
                    if (tt != null) tt.setText(req.getState().getWho());
                    
                    if (! req.getActions().isEmpty()) {
                    	tt = (TextView) v.findViewById(R.id.req_submit_source);
                    	if (tt != null) tt.setText(req.getActions().get(0).getSource().getProject() +
                    			"/" +
                    			req.getActions().get(0).getSource().getPackage());
              
                    	tt = (TextView) v.findViewById(R.id.req_submit_target);
                    	if (tt != null) tt.setText(req.getActions().get(0).getTarget().getProject() +
                    			"/" +
                    			req.getActions().get(0).getTarget().getPackage());
                    }
                    tt = (TextView) v.findViewById(R.id.req_description);
                    if (tt != null) tt.setText(req.getDescription());                        
            }
            return v;
    }
}