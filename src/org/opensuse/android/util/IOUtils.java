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
