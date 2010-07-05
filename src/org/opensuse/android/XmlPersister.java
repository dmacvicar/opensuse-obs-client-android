package org.opensuse.android;

import org.simpleframework.xml.core.Persister;

public class XmlPersister extends Persister {

	public XmlPersister() {
		super(new TypesMatcher());
	}
	
}
