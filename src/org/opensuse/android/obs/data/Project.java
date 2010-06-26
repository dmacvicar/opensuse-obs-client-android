package org.opensuse.android.obs.data;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(strict=false)
public class Project {
	@Attribute
	private String name;
	
	public String getName() {
		return name;
	}
}
