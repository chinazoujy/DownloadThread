package com.dreamwin.xunlei.db.dao;

import java.util.List;
import com.dreamwin.cclib.db.sober.SoberException;
import com.dreamwin.xunlei.config.Config;
import com.dreamwin.xunlei.orm.Task;

public class TaskDao {
	public static void resetStatus() throws SoberException{
		String sql = "UPDATE task SET status=? WHERE status = ?";
		SoberFactory.getSober().write(sql, Task.ORIGINAL_CODE, Task.DOWNLOAD_CODE);
	}
	public static List<Task> getAllInfo() throws SoberException {
		String query2 = "SELECT * FROM task";
		return SoberFactory.getSober().read(query2).toObjects(Task.class);	
	}
	public static int count() throws SoberException{
		String sql="SELECT * FROM task";
		return SoberFactory.getSober().read(sql).toObjects(Task.class).size();
	}
	public static void insert(Task task) throws SoberException{
		SoberFactory.getSober().insert(task);
	}	
	public static List<Task> selectTask() throws SoberException{
		
		String sql = "SELECT * From task WHERE NOW() -time >= ? AND status=?";
		return SoberFactory.getSober().read(sql, Config.RETRY_TIME, Task.ORIGINAL_CODE).toObjects(Task.class);
	}
	public static void updateStatusToInit(Task task) throws SoberException{
		String sql = "UPDATE task SET time = NOW(), status=?  WHERE id = ?";
		SoberFactory.getSober().write(sql, Task.ORIGINAL_CODE, task.getId());
	}	
	public static void updateStatusToSuccess(Task task) throws SoberException{
		String sql = "UPDATE task SET status=?  WHERE id = ?";
		SoberFactory.getSober().write(sql,Task.FINISHED_CODE, task.getId());
	}
	public static void changeStatus(Task task) throws SoberException{
		String sql = "UPDATE task SET status=? WHERE id = ?";
		SoberFactory.getSober().write(sql,Task.DOWNLOAD_CODE, task.getId());
	}
}
