package com.dreamwin.xunlei.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import com.dreamwin.cclib.db.sober.SoberException;
import com.dreamwin.cclib.naga.annotation.Mapping;
import com.dreamwin.cclib.naga.view.Naga;
import com.dreamwin.cclib.naga.view.View;
import com.dreamwin.xunlei.db.dao.TaskDao;
import com.dreamwin.xunlei.orm.Task;
import com.dreamwin.xunlei.util.HttpUtil;

public class AddQueueControll {

	@Mapping(uri = "/servlet/addTask")
	public View process(HttpServletRequest request) {
		String uri = request.getParameter("uriName");
		Task task = getTask(uri);
		if (task != null) {
			try {
				TaskDao.insert(task);
				return Naga.createJstlView("finished.jsp", null);
			} catch (SoberException e) {
				Map<String, String> errorMap = new HashMap<String, String>();
				String errorInfo = "Database error !";
				errorMap.put("info", errorInfo);
				return Naga.createJstlView("error.jsp", errorMap);	
			}
			
		} else {
			Map<String, String> errorMap = new HashMap<String, String>();
			String errorInfo = "The wrong information !";
			errorMap.put("info", errorInfo);
			return Naga.createJstlView("error.jsp", errorMap);
		}

	}

	private Task getTask(String uri) {
		Matcher m = Pattern.compile("src=(.*?)&dst=(.*?)&md5=(.*)")
				.matcher(uri);
		String src = null, dst = null, md5 = null;
		while (m.find()) {
			src = m.group(1);
			dst = m.group(2);
			md5 = m.group(3);
		}
		if (src == null || dst == null || md5 == null) {
			return null;
		}
		if (!HttpUtil.judgeSrc(src)) {
			return null;
		}
		if (!HttpUtil.judgeDst(dst)) {
			return null;
		}
		Task task = new Task();
		task.setId(null);
		task.setSrc(src);
		task.setDst(dst);
		task.setMd5(md5);
		task.setStatus(Task.ORIGINAL_CODE);
		long curTime = System.currentTimeMillis();
		curTime -= 30 * 1000;
		task.setTime(new Date(curTime));
		return task;
	}
}
