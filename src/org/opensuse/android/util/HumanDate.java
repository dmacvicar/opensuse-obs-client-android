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

  Human date routines contain code from Hubdroid
  
  Copyright (c) 2010 Eddie Ringle <eddie@eringle.net>.
  All rights reserved.
 
  Redistribution and use in source and binary forms, with or
  without modification, are permitted provided that the following
  conditions are met:
   * Redistributions of source code must retain the above
     copyright notice, this list of conditions and the
     following disclaimer.
   * Redistributions in binary form must reproduce the above
     copyright notice, this list of conditions and the
     following disclaimer in the documentation and/or other
     materials provided with the distribution.
   * Neither the name of Idlesoft nor the names of
     its contributors may be used to endorse or promote
     products derived from this software without specific
     prior written permission.
  
  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND
  CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
  INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
  MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
  DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
  SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
  HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
  CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
  OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
*/

package org.opensuse.android.util;

import java.util.Date;

public class HumanDate {
	private Date date;
	
	public HumanDate(Date date) {
		this.date = date;
	}
	
	public String toHumanString() {
		String end;
		Date current_time = new Date();
		long ms = current_time.getTime() - date.getTime();
		long sec = ms / 1000;
		long min = sec / 60;
		long hour = min / 60;
		long day = hour / 24;
		if (day > 0) {
			if (day == 1) {
				end = " day ago";
			} else {
				end = " days ago";
			}
			return (day + end);
		} else if (hour > 0) {
			if (hour == 1) {
				end = " hour ago";
			} else {
				end = " hours ago";
			}
			return (hour + end);
		} else if (min > 0) {
			if (min == 1) {
				end = " minute ago";
			} else {
				end = " minutes ago";
			}
			return (min + end);
		} else {
			if (sec == 1) {
				end = " second ago";
			} else {
				end = " seconds ago";
			}
			return (sec + end);
		}
	}
}