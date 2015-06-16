package com.dreamwin.xunlei.proccess;

import java.util.List;

import org.apache.log4j.Logger;
import com.dreamwin.cclib.db.sober.SoberException;
import com.dreamwin.xunlei.db.dao.SoberFactory;
import com.dreamwin.xunlei.db.dao.TaskDao;
import com.dreamwin.xunlei.orm.Task;

public class TaskScannerThread extends Thread {
	private static Logger logger = Logger.getLogger(SoberFactory.class);

	public void run() {
		while (true) {
			getTask();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				logger.error("", e);
			}
		}
	}

	private void getTask() {
		List<Task> list;
		try {
			list = TaskDao.selectTask();
		} catch (SoberException e1) {
			logger.error("", e1);
			return;
		}
		for (Task task : list) {
			try {
				TaskDao.changeStatus(task);
				TaskQueue.getInstance().addTask(task);
			} catch (SoberException e) {
				logger.error("", e);
			}
		}
	}
}
