package com.dreamwin.xunlei.controller;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import com.dreamwin.cclib.naga.annotation.Mapping;
import com.dreamwin.cclib.naga.view.Naga;
import com.dreamwin.cclib.naga.view.View;
import com.dreamwin.xunlei.proccess.TaskDownload;
import com.dreamwin.xunlei.proccess.TaskQueue;


public class DisplayCurrentTaskController {
	
	@Mapping(uri = "/servlet/currentTask")
	public View process(HttpServletRequest request) {
		Map<String, List<String[]>> map = new HashMap<String, List<String[]>>();
		List<TaskDownload> list = TaskQueue.getInstance().getDownloadThreadList();
		List<String[]> info = new ArrayList<String[]>();
		info.add(new String[]{"Address", "Speed(Kb/s)", "Percent", "FileSize(M)"});
		for(TaskDownload taskDownload : list){
			if(taskDownload.getCurTask()== null )continue;	
			String src = taskDownload.getCurTask().getSrc();
			String speed = String.valueOf(taskDownload.getSpeed());
			String percent = String.valueOf(taskDownload.getPercent());
			String size = "";
			if(taskDownload.getFileSize() != null &&  taskDownload.getFileSize() < 1000000){
				size = "小于1M";
			}else{
				if(taskDownload.getFileSize() != null &&  taskDownload.getFileSize() > 1000000){
					size = String.valueOf(taskDownload.getFileSize()/1000000);	
				}
			}
			info.add(new String[]{src, speed, percent, size});
		} 
		map.put("info", info);
		return Naga.createJstlView("displaycurtask.jsp", map);
	}
}
