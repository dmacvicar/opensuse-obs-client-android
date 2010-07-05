package org.opensuse.android;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.opensuse.android.obs.data.Request;
import org.simpleframework.xml.transform.Matcher;
import org.simpleframework.xml.transform.Transform;

import android.util.Log;

/*
 * matcher for the serializer framework that supports our custom
 * types
 * 
 * @author Duncan Mac-Vicar P. <dmacvicar@suse.de>
 */
public class TypesMatcher implements Matcher {
	@Override
	public Transform match(Class type) throws Exception {
       if(type == Date.class) {
          return new DateTransform();
       }
       else if (type == Request.Action.Type.class) {
    	   return new RequestActionTypeTransform();
       }
       return null;
    }
}
