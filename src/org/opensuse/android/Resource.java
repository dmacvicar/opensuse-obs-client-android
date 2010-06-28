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
