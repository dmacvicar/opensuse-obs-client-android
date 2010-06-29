package org.opensuse.android.obs.data;

import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

public class User {
	
	@Element
	private String login;
	@Element
	private String email;
	@Element
	private String realname;
	@Element
	private String globalrole;
	@ElementList(name="watchlist", entry="project")
	private List<Project> watchlist;
	
	public String getLogin() {
		return login;
	}
	public String getEmail() {
		return email;
	}
	public String getRealname() {
		return realname;
	}
	public String getGlobalrole() {
		return globalrole;
	}
	public List<Project> getWatchlist() {
		return watchlist;
	}
}
