package org.opensuse.android;

import java.io.InputStream;

public class Resource {

	private InputStream content;
	private int status;
	
	Resource(int status, InputStream content) {
		this.status = status;
		this.content = content;
	}

	public InputStream getContent() {
		return content;
	}

	public int getStatus() {
		return status;
	}
}
