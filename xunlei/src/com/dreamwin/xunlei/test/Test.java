package com.dreamwin.xunlei.test;

import com.dreamwin.cclib.db.sober.SoberException;
import com.dreamwin.xunlei.db.dao.SoberFactory;
import com.dreamwin.xunlei.db.dao.TaskDao;

public class Test {

	public static void main(String[] args) throws SoberException {
		if (!SoberFactory.initailize("com.dreamwin.xunlei.orm", "download", true)) {
			System.out.println("init error");
			System.exit(1);
		}else{
			System.out.println("Init sober is success !");
		}
		System.out.println(TaskDao.count());
	}

}
