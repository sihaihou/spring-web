package com.reyco.kn.core.service;

import com.reyco.kn.core.domain.ScheduledTaskEntity;

public interface ScheduledTaskService {
	
	/**
	 * 启动任务
	 * @param scheduledTaskEntity
	 * @return
	 */
	public Boolean startTask(ScheduledTaskEntity scheduledTaskEntity) throws Exception;
	/**
	 * 暂停任务
	 * @param scheduledTaskEntity
	 * @return
	 */
	public Boolean stopTask(ScheduledTaskEntity scheduledTaskEntity);
	/**
	 * 添加任务
	 * @param scheduledTaskEntity
	 * @return
	 */
	public boolean addTask(ScheduledTaskEntity scheduledTaskEntity);
	/**
	 * 移除任务
	 * @param scheduledTaskEntity
	 * @return
	 */
	public boolean removeTask(ScheduledTaskEntity scheduledTaskEntity);
	/**
	 * 更新任务
	 * @param scheduledTaskEntity
	 * @return
	 */
	public boolean updateTask(ScheduledTaskEntity scheduledTaskEntity);
}
