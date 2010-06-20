package org.opensuse.android;

import org.opensuse.android.util.Base64;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.*;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import android.util.Log;

import zipwire.rest.Resource;
import zipwire.exception.ZipwireException;
import zipwire.rest.RestConnection;
import zipwire.xml.XmlNamingRule;

public class HttpCoreConnection implements RestConnection {
	private String protocol, host, username, password;
	private XmlNamingRule xmlNamingRule;
	
	public HttpCoreConnection(String host, String username, String password, XmlNamingRule xmlNamingRule) {
		if (host == null) throw new ZipwireException("Need to specify a host, username, and password");
		this.protocol = "https://";
		this.host = host;
		this.username = username;
		this.password = password;
		this.xmlNamingRule = xmlNamingRule;
	}
	public Resource get(String uri) {
        return execute(new HttpGet(buildUri(uri)));
	}
	public Resource post(String uri, String xml){
		return execute(add(new HttpPost(buildUri(uri)), xml));
	}
	public Resource put(String uri, String xml) {
		return execute(add(new HttpPut(buildUri(uri)), xml));
	}
	public Resource delete(String uri) {
		return execute(new HttpDelete(buildUri(uri)));
	}
	
	private Resource execute(HttpUriRequest m) {
		try {
			m.addHeader("Authorization", "Basic "+ getEncodedCredentials());

			HttpResponse response = connect().execute(m);
			int status = response.getStatusLine().getStatusCode();
			Log.w("HTTP_STATUS", Integer.toString(status));
			//Log.i("HTTP_CONTENT", response.getEntity().getContent().);
            
	        return (new Resource(xmlNamingRule,
	        			status,
	        			convertStreamToString(response.getEntity().getContent())));
		}
		catch(Exception e){
			throw new ZipwireException("Couldn't execute request:", e);
		}
	}
	private HttpUriRequest add(HttpEntityEnclosingRequestBase m, String xml) {
		try {
			m.setEntity(new StringEntity(xml, "ISO-8859-1"));
			m.setHeader("Content-Type",  "application/xml");
			return m;
		}
		catch(Exception e){
			throw new ZipwireException(e);
		}
	}
	
	private static String convertStreamToString(InputStream is) {
        /*
         * To convert the InputStream to String we use the BufferedReader.readLine()
         * method. We iterate until the BufferedReader return null which means
         * there's no more data to read. Each line will appended to a StringBuilder
         * and returned as String.
         */
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
 
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
	
	private String getEncodedCredentials() {
		return(Base64.encodeBytes((this.username+":"+this.password).getBytes()));
	}
	
	private HttpClient connect() {
		DefaultHttpClient client = new DefaultHttpClient();
        client.getCredentialsProvider().setCredentials(
            new AuthScope(host, 80),            
            new UsernamePasswordCredentials(username, password)
        );        
		return client;
	}
	private String buildUri(String path) {
		return protocol + host + "/" + path;
	}
}