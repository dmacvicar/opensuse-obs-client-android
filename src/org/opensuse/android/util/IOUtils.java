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
  along with this program.  If not, see <http://www.gnu.org/licenses/>.
  
*/
package org.opensuse.android.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class IOUtils {

	public static String readStream(InputStream is) {
		final char[] buffer = new char[0x10000];
		StringBuilder out = new StringBuilder();
		try {
			Reader in = new InputStreamReader(is, "UTF-8");
			int read;
			do {
				read = in.read(buffer, 0, buffer.length);
				if (read>0) {
					out.append(buffer, 0, read);
				}
			} while (read>=0);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return out.toString();
	}	
}
