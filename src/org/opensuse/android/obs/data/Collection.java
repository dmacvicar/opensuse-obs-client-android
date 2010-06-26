package org.opensuse.android.obs.data;
import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "collection", strict=false)
public class Collection {

	@ElementList(entry="request", inline=true, required=false)
	private List<Request> requests;

	@ElementList(entry="package", inline=true, required=false)
	private List<Package> packages;

	@ElementList(entry="project", inline=true, required=false)
	private List<Project> projects;

	public List<Request> getRequests() {
		return requests;
	}

	public List<Package> getPackages() {
		return packages;
	}

	public List<Project> getProjects() {
		return projects;
	}
}