package org.opensuse.android.obs;

import org.opensuse.android.obs.request.State;

import zipwire.xml.XmlClump;

public interface Request {
	String getDescription();
	State getState();
}
