package org.opensuse.android.obs.data;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict=false)
public class Distribution {
	
	@Element
	private String name;	
	private String project;
	@Element
	private String reponame;
	@Element
	private String repository;
	@Attribute
	private String vendor;	
	@Attribute
	private String version;
	
	public String getName() {
		return name;
	}
	public String getProject() {
		return project;
	}
	public String getReponame() {
		return reponame;
	}
	public String getRepository() {
		return repository;
	}
	public String getVendor() {
		return vendor;
	}
	public String getVersion() {
		return version;
	}
}
