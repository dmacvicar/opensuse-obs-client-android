package org.opensuse.android;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.simpleframework.xml.transform.Matcher;
import org.simpleframework.xml.transform.Transform;

import android.util.Log;

/*
 * matcher for the serializer framework that supports reading the
 * date format the build service uses
 * 
 * @author Duncan Mac-Vicar P. <dmacvicar@suse.de>
 */
public class DateMatcher implements Matcher, Transform<Date> {
	
	public static final String OBS_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    
	@Override
	public Transform<Date> match(Class type) throws Exception {
       if(type == Date.class) {
          return this;
       }
       return null;
    }

	@Override
    public Date read(String value) throws Exception {
       SimpleDateFormat format = new SimpleDateFormat(OBS_TIME_FORMAT);
       return format.parse(value);
    }

	@Override
    public String write(Date value) throws Exception {
    	SimpleDateFormat format = new SimpleDateFormat(OBS_TIME_FORMAT);
    	return format.format(value);
    }
 }
