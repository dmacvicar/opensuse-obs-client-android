package org.opensuse.android.obs.data;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(strict=false)
public class State {
	
	@Attribute
	private String name;
	@Attribute
	private String who;
	@Attribute
	private String when;
	
	String getName() {
		return name;
	}
	
	String getWho() {
		return who;
	}
	
	String getWhen() {
		return when;
	}
}
