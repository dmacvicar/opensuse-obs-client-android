package org.opensuse.android;

public interface RestClient {
	public <T> T get(String path, Class<T> returnType);
}
