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

package org.opensuse.android;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.simpleframework.xml.transform.Transform;

/*
 * Transform for the serializer framework that supports reading the
 * date format the build service uses
 * 
 * @author Duncan Mac-Vicar P. <dmacvicar@suse.de>
 */
public class DateTransform implements Transform<Date> {
	public static final String OBS_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
	
	@Override
    public Date read(String value) throws Exception {
       SimpleDateFormat format = new SimpleDateFormat(OBS_TIME_FORMAT);
       return format.parse(value);
    }

	@Override
    public String write(Date value) throws Exception {
    	SimpleDateFormat format = new SimpleDateFormat(OBS_TIME_FORMAT);
    	return format.format(value);
    }
}
