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

import java.util.HashMap;
import java.util.List;

import org.opensuse.android.obs.data.Request;
import org.opensuse.android.obs.data.Request.Action;
import org.opensuse.android.util.HumanDate;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RequestListAdapter extends ArrayAdapter<Request> {

	private Context context;
    private List<Request> items;
    private LayoutInflater inflater;
    private HashMap<String, Bitmap> gravatars;
    private Client client;
    
    public RequestListAdapter(Context context, int textViewResourceId, List<Request> items) {
            super(context, textViewResourceId, items);
            this.context = context;
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
    			gravatars.put(login, client.getGravatar(client.getGravatarID(login), 30));
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
            		ImageView iv = (ImageView) v.findViewById(R.id.req_who_icon);
            		String login = req.getState().getWho();
        			iv.setImageBitmap(client.getGravatar(client.getGravatarID(login), 30));
        			
                    iv = (ImageView) v.findViewById(R.id.req_state_icon);
                    Log.i(getClass().getSimpleName(), "state: " + req.getState().getName());
                    if (req.getState().getName() == "new") {
                    	Bitmap bMap = BitmapFactory.decodeResource(context.getResources(), R.drawable.accept);
                    	iv.setImageBitmap(bMap);
                    }
                    
                    if (! req.getActions().isEmpty()) {
                    	String actiondesc = "";
                    	Action action = req.getActions().get(0);
                    	switch (action.getType()) {
                    	case SUBMIT:
                    		actiondesc = action.getSource().getProject() + "/" + action.getSource().getPackage() +
                    					 " \u2192 " +
                    					 action.getTarget().getProject() + "/" + action.getTarget().getPackage();
                    		break;
                    	}
                    	TextView tt = (TextView) v.findViewById(R.id.req_summary);                    
                        if (tt != null && actiondesc != null) tt.setText(actiondesc);
                    }
                    TextView tt = (TextView) v.findViewById(R.id.req_description);
                    if (tt != null) tt.setText(req.getDescription());
                    
                    tt = (TextView) v.findViewById(R.id.req_header);
                    if (tt != null) tt.setText((new HumanDate(req.getState().getWhen()).toHumanString()));
                    
                    tt = (TextView) v.findViewById(R.id.req_state_who);
                    if (tt != null) tt.setText(req.getState().getWho());                    
                    
            }
            return v;
    }
}