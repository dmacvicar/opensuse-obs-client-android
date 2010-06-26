package org.opensuse.android.obs.data;

import org.opensuse.android.obs.data.State;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict=false)
public class Request {
	
	@Element
	private String description;
	@Element
	private State state;	
	
	public String getDescription() {
		return description;	
	}
	
	public State getState() {
		return state;
	}
}
