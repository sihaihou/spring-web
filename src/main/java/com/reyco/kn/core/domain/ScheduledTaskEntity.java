package com.reyco.kn.core.domain;

public class ScheduledTaskEntity {
	private Integer task_id;
	private String task_name;
	private String task_cron;
	private String task_desc;
	public Integer getTask_id() {
		return task_id;
	}
	public void setTask_id(Integer task_id) {
		this.task_id = task_id;
	}
	public String getTask_name() {
		return task_name;
	}
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}
	public String getTask_cron() {
		return task_cron;
	}
	public void setTask_cron(String task_cron) {
		this.task_cron = task_cron;
	}
	public String getTask_desc() {
		return task_desc;
	}
	public void setTask_desc(String task_desc) {
		this.task_desc = task_desc;
	}
}