package com.dreamwin.xunlei.test;

import com.dreamwin.cclib.httplib.wget.WgetWrapper;

public class Test1 {
	public static void main(String[] args) throws InterruptedException {
		final WgetWrapper wget = new WgetWrapper("http://192.168.1.248/software/eclipse-jee-helios-SR2-linux-gtk.tar.gz", "/home/zoujy/Desktop/testDownload/eclipse.tar.gz");
		new Thread(){
			public void run() {
				wget.download();
			};
		}.start();
		System.out.println(wget.getFileSize());
	}
}
