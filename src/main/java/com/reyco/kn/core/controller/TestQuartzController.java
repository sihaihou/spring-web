package com.reyco.kn.core.controller;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reyco.kn.core.domain.ScheduledTaskEntity;
import com.reyco.kn.core.task.Task1;
import com.reyco.quartz.service.TaskQuartz;

@RestController
@RequestMapping("/api")
public class TestQuartzController {
	@Autowired
	private TaskQuartz TaskQuartz;
	
	@GetMapping("start")
	public String start(@RequestParam Map<String,String> map) throws InterruptedException, ExecutionException {
		if(null == map || map.isEmpty() ) {
			return " task start failed!";
		}
		String taskName = map.get("taskName");
		String taskCron = map.get("taskCron");
		Boolean startTaskFlag = false;
		if(StringUtils.isBlank(taskCron)) {
			taskCron = "0/2 * * * * ?";
		}
		if(StringUtils.isBlank(taskName)) {
			taskName = "task1_1";
		}
		startTaskFlag = TaskQuartz.startTask(new Task1(),taskCron, taskName);
		System.out.println("DynamicTask.startCron1()");
		if (!startTaskFlag) {
			return " task start failed!";
		}
		return " task start success!";
	}
	
	@GetMapping("stop")
	public String stop(@RequestBody(required=false) ScheduledTaskEntity scheduledTaskEntity) {
		if(null == scheduledTaskEntity) {
			return " task stop failed!";
		}
		String task_name = scheduledTaskEntity.getTask_name();
		if(StringUtils.isBlank(task_name)) {
			return " task stop failed!";
		}
		Boolean stopTaskFlag = TaskQuartz.stopTask(task_name);
		if (!stopTaskFlag) {
			return " task stop failed!";
		}
		return " task stop success!";
	}
	
	@GetMapping("update")
	public String updateCron(@RequestParam Map<String,String> map) throws InterruptedException, ExecutionException {
		if(null == map || map.isEmpty()) {
			return " task update failed!";
		}
		String taskName = map.get("taskName");
		String taskCron = map.get("taskCron");
		Boolean startTaskFlag = false;
		if(StringUtils.isBlank(taskCron)) {
			taskCron= "0/4 * * * * ?";
		}
		if(StringUtils.isBlank(taskName)) {
			taskName = "task1_1";
		}
		startTaskFlag = TaskQuartz.updateTask(new Task1(), taskCron, taskName);
		System.out.println("DynamicTask.updateCron1()");
		if (!startTaskFlag) {
			return " task update failed!";
		}
		return " task update success!";
	}
}
