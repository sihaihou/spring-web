package com.reyco.kn.core.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Task1 implements Runnable{
	
	@Override
	public void run() {
		System.out.println("first DynamicTask，" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}

}
