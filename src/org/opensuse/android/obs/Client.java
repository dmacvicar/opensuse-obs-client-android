package org.opensuse.android.obs;

import java.util.ArrayList;
import java.util.List;

import org.opensuse.android.HttpCoreConnection;

import zipwire.rest.ApacheRestConnection;
import zipwire.text.CamelCase;
import zipwire.xml.XmlNamingRule;

public class Client extends HttpCoreConnection {

	private static final String HOSTNAME = "api.opensuse.org";
	private static final String USERNAME = "";
	private static final String PASSWORD = "";
	
	static XmlNamingRule xmlNamingRule = new XmlNamingRule(){
		public String transform(String method) {
	      return new CamelCase(method).separateWith("-").toLowerCase();
	    }
	  };
	
	public Client() {
		super(HOSTNAME, USERNAME, PASSWORD, xmlNamingRule);
	}
	  
	public Client(String host, String user, String password, XmlNamingRule arg3) {
		super(host, user, password, xmlNamingRule);
		// TODO Auto-generated constructor stub
	}

	public List<Project> getProjects() {
		return get("source").asList(Project.class, "entry");
	}
	
	public List<Distribution> getDistributions() {
		return get("distributions").asList(Distribution.class, "distribution");
	}
	
}
