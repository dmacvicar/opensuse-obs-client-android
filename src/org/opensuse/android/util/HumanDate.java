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