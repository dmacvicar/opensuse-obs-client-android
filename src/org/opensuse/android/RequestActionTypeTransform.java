package org.opensuse.android;

import org.opensuse.android.obs.data.Request;
import org.simpleframework.xml.transform.Transform;

public class RequestActionTypeTransform implements Transform<Request.Action.Type> {
	@Override
	public Request.Action.Type read(String value) throws Exception {
		return Request.Action.Type.valueOf(value.toUpperCase());
	}

	@Override
	public String write(Request.Action.Type value) throws Exception {
		return value.name();
	}
}
