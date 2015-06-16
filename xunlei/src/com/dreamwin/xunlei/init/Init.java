package com.dreamwin.xunlei.init;

import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.dreamwin.cclib.db.sober.SoberException;
import com.dreamwin.cclib.util.ConfigUtil;
import com.dreamwin.xunlei.config.Config;
import com.dreamwin.xunlei.db.dao.SoberFactory;
import com.dreamwin.xunlei.db.dao.TaskDao;
import com.dreamwin.xunlei.proccess.TaskQueue;
import com.dreamwin.xunlei.proccess.TaskScannerThread;


@SuppressWarnings("serial")
public class Init extends HttpServlet{
	
	private Logger logger = Logger.getLogger(Init.class);
	
	@Override
	public void init() {
		PropertyConfigurator.configure(Thread.currentThread().getContextClassLoader().getResource("log4j.properties"));
		
		if (!SoberFactory.initailize("com.dreamwin.xunlei.orm", "download", true)) {
			logger.error("Init sober error!");
			System.exit(1);
		}else{
			logger.info("Init sober is success !");
		}
		
		if(!ConfigUtil.readFromFile(Config.class, "config.properties")){
			logger.error("Init config.properties is error !");
			System.exit(1);
		}else{
			logger.info("Init config is success !");
		}
		ConfigUtil.print(Config.class);
		
		try {
			TaskDao.resetStatus();
		} catch (SoberException e) {
			logger.error("Init status from DB is error !");
			System.exit(1);
		}

		TaskQueue.getInstance().initDownloadThread();
		logger.info("Start The count of download thread is " + Config.TASK_MAX);
		
		TaskScannerThread ts = new TaskScannerThread();
		ts.start();
		logger.info("Start the thread real-time monitoring ");
	}
}
