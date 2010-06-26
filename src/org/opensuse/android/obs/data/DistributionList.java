package org.opensuse.android.obs.data;
import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "distributions")
public class DistributionList {

	@ElementList(entry="distribution", inline=true)
	private List<Distribution> distributions;

	public List<Distribution> getDistributions() {
		return distributions;
	}
}