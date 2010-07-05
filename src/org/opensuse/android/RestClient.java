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

public interface RestClient {
	/*
	 * GET
	 * Gets a XML resource and builds the object of the specified class
	 */
	public <T> T get(String path, Class<T> returnType);
	
	/*
	 * POST
	 * POST to a resource and builds the object of the specified class
	 */
	public <T> T post(String path, String data, Class<T> returnType);
	
	/*
	 * GET
	 * Gets a XML resource and returns plain data
	 */
	public String getPlain(String path);
	
	/*
	 * POST
	 * POST a XML resource and returns plain data
	 */
	public String postPlain(String path, String data);
}
