package com.dreamwin.xunlei.util;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.log4j.Logger;

public class HttpUtil {
	private static Logger logger = Logger.getLogger(HttpUtil.class);	

	public static boolean judgeSrc(String src){
		if(src == null || src.length() <= 0){
			logger.info("src is error !");
			return false;
		}
		URL url = null;
		try {
			url = new URL(src);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			int state = conn.getResponseCode();
			logger.info("The connection status code is " + state);
			if(state == 200){
				return true;
			}
		} catch (MalformedURLException e) {
			logger.info("The address is not in conformity with the rules ");
			return false;
		} catch (IOException e) {
			logger.info("The address is not available ");
			return false;
		}
		return false;
	}
	
	public static boolean judgeDst(String dst){
		if(dst==null || dst.length() <= 0){
			logger.info("Dst is error !");
			return false;
		}
		File file = new File(dst);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				logger.info("The specified directory does not exist ");
				return false;
			}
		}
		return true;
	}
}
