package org.opensuse.android.obs.data;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict=false)
public class Package {
	@Element(required=false)
	private String title;
	@Element(required=false)
	private String description;
	
	@Attribute
	private String project;
	@Attribute
	private String name;
	
	public String getProject() {
		return project;
	}
	public String getName() {
		return name;
	}
	
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
}
