package com.dreamwin.xunlei.proccess;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import com.dreamwin.xunlei.config.Config;
import com.dreamwin.xunlei.orm.Task;

public class TaskQueue {

	private static TaskQueue instance = null;
	
	private ConcurrentLinkedQueue<Task> queue = new ConcurrentLinkedQueue<Task>();
	private List<TaskDownload> curDownloadThread = new ArrayList<TaskDownload>(); 

	public static TaskQueue getInstance(){
		if(instance == null){
			instance = new TaskQueue();
		}
		return instance;
	}
	
	public void initDownloadThread(){
		for(int i=0; i < Config.TASK_MAX; i++){
			TaskDownload td = new TaskDownload();
			td.start();
			curDownloadThread.add(td);
		}
	}
	public void addTaskDownload(TaskDownload td){
		curDownloadThread.add(td);
	}
	public List<TaskDownload> getDownloadThreadList(){
		return curDownloadThread;
	}
	public Task getTask(){
		return queue.poll();
	}
	public void addTask(Task task) {
		queue.offer(task);
	}	
}
