/* 
  Copyright 2010 Duncan Mac-Vicar P. <dmacvicar@suse.de>
 
  This file is part of the openSUSE Build Service Android Client.

  openSUSE Build Service Android Client is free software: you
  can  redistribute it and/or modify it under the terms of the GNU
  General Public License as published by the Free Software Foundation,
  either version 3 of the License, or (at your option) any later
  version.

  openSUSE Build Service Android Client is distributed in the hope
  that it will be useful, but WITHOUT ANY WARRANTY; without even the
  implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
  See the GNU General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with QuiteSleep.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.opensuse.android.obs.data;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict=false)
public class Distribution {
	
	@Element
	private String name;	
	private String project;
	@Element
	private String reponame;
	@Element
	private String repository;
	@Attribute
	private String vendor;	
	@Attribute
	private String version;
	
	public String getName() {
		return name;
	}
	public String getProject() {
		return project;
	}
	public String getReponame() {
		return reponame;
	}
	public String getRepository() {
		return repository;
	}
	public String getVendor() {
		return vendor;
	}
	public String getVersion() {
		return version;
	}
}