package org.opensuse.android.obs;

import java.util.ArrayList;
import java.util.List;

import org.opensuse.android.HttpCoreConnection;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import zipwire.rest.ApacheRestConnection;
import zipwire.text.CamelCase;
import zipwire.xml.XmlNamingRule;

public class Client extends HttpCoreConnection {

	public static final String PREFERENCES = "preferences";
	
	private static final String DEFAULT_API_HOST = "api.opensuse.org";
	private static final String DEFAULT_USERNAME = "";
	private static final String DEFAULT_PASSWORD = "";
	
	static XmlNamingRule xmlNamingRule = new XmlNamingRule(){
		public String transform(String method) {
	      return new CamelCase(method).separateWith("-").toLowerCase();
	    }
	  };
	  
	public Client(Context context) {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		// then you use
		String username = prefs.getString("username", DEFAULT_USERNAME);
		String password = prefs.getString("password", DEFAULT_PASSWORD);
		setProtocol("https://");
		setUsername(username);
		setPassword(password);
		setHost(DEFAULT_API_HOST);
		setXmlNamingRule(xmlNamingRule);
	}
	  
	public List<Project> getProjects() {
		return get("source").asList(Project.class, "entry");
	}
	
	public List<Distribution> getDistributions() {
		return get("distributions").asList(Distribution.class, "distribution");
	}
	
}
