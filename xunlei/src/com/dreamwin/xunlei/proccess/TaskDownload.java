package com.dreamwin.xunlei.proccess;

import java.io.File;
import org.apache.log4j.Logger;
import com.dreamwin.cclib.db.sober.SoberException;
import com.dreamwin.cclib.httplib.wget.WgetWrapper;
import com.dreamwin.xunlei.config.Config;
import com.dreamwin.xunlei.db.dao.TaskDao;
import com.dreamwin.xunlei.orm.Task;
import com.dreamwin.xunlei.util.Md5Util;

public class TaskDownload extends Thread {

	private static Logger logger = Logger.getLogger(TaskDownload.class);

	private Task curTask;
	private WgetWrapper wgetWrapper;

	@Override
	public void run() {
		while (true) {
			doDownloadAction();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				logger.error("", e);
			}
		}
	}

	private void doDownloadAction() {
		curTask = TaskQueue.getInstance().getTask();
		if (curTask == null) {
			return;
		}
		download();
		curTask = null;
	}

	private void download() {
		logger.info("begin download: src is " + curTask.getSrc());
		wgetWrapper = new WgetWrapper(curTask.getSrc(), curTask.getDst());
		wgetWrapper.download();
		if (!wgetWrapper.isSuccessDownload()) {
			try {
				TaskDao.updateStatusToInit(curTask);
				logger.error("download is FAIL. Retry time for at least "+ Config.RETRY_TIME + " seconds ");
			} catch (SoberException e) {
				logger.error("UpdateTask is error . The task will be add to queue .");
				TaskQueue.getInstance().addTask(curTask);
			}
		} else {
			if (Md5Util.compareMd5(curTask)) {
				logger.info("Md5 authentication successly . ");
				logger.info("Download is success. The src is "+ curTask.getSrc());
				try {
					TaskDao.updateStatusToSuccess(curTask);
				} catch (SoberException e) {
					logger.error("update status is FAIL from database !");
					TaskQueue.getInstance().addTask(curTask);
				}
			} else {
				logger.info("Md5 authentication failure ");
				if (new File(curTask.getDst()).delete()) {
					logger.info("The file deleted successly .");
				} else {
					logger.info("The file deleted failure .");
				}
				TaskQueue.getInstance().addTask(curTask);
			}

		}
	}

	public Task getCurTask() {
		return curTask;
	}

	public float getSpeed() {
		return wgetWrapper.getSpeed();
	}

	public Integer getPercent() {
		return wgetWrapper.getPercent();
	}

	public Long getFileSize() {
		return wgetWrapper.getFileSize();
	}
}
