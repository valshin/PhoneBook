package me.noip.valshin.tools;

import java.io.File;

public class FileTools {

	public static boolean exists(String filePath) {
		return new File(filePath).exists();
	}

}
