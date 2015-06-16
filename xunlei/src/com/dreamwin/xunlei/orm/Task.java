package com.dreamwin.xunlei.orm;

import java.util.Date;

import com.dreamwin.cclib.db.sober.annotation.Entity;
import com.dreamwin.cclib.db.sober.annotation.Table;

@Entity
@Table(name = "task", engine = "InnoDB")
public class Task {
	/*
	 * 任务状态 0,5,10
	 * 0 初始码表示当前任务在数据库中
	 * 5 表示当前任务在下载队列中
	 * 10 表示任务已经下载完成
	 * */
	public static final int ORIGINAL_CODE = 0;
	public static final int DOWNLOAD_CODE = 5;
	public static final int FINISHED_CODE = 10;
	
	
	private Integer id;
	private String src;
	private String dst;
	private String md5;
	private Date time;
	private Integer status;
	
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getDst() {
		return dst;
	}
	public void setDst(String dst) {
		this.dst = dst;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
}
