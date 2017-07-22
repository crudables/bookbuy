package com.ables.bookbuy.util;

import org.springframework.stereotype.Component;

@Component
public class FileUtils {
	
	public String getFileExtension(String filename){
		if (filename == null) {
            return null;
        }
        int extensionPos = filename.lastIndexOf(".");
        int lastUnixPos = filename.lastIndexOf("/");
        int lastWindowsPos = filename.lastIndexOf("/");
        int lastSeparator = Math.max(lastUnixPos, lastWindowsPos);

        int index = lastSeparator > extensionPos ? -1 : extensionPos;
        if (index == -1) {
            return "";
        } else {
            return filename.substring(index + 1);
        }	
	}

}
