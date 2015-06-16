package com.dreamwin.xunlei.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import com.dreamwin.xunlei.orm.Task;

public class Md5Util {
	private static Logger logger = Logger.getLogger(Md5Util.class);
	
	public static boolean compareMd5(Task task){
		if(!task.getMd5().equals(getFileMd5(task))){
			return false;
		}
		return true;
	}
	
	private static String getFileMd5(Task task) {
		String md5 = null;
		try {
			md5=DigestUtils.md5Hex(new FileInputStream(
					new File(task.getDst())));
			logger.info("The system generate md5 is " + md5);
			logger.info("The file md5 is " + task.getMd5());
			return md5;
		} catch (FileNotFoundException e) {
			return "";
		} catch (IOException e) {
			return "";
		}
	}
}
