package com.reyco.kn.core.task;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;


public class Test {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ReycoFutureTask<Integer> task = new ReycoFutureTask<Integer>(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				System.out.println(Thread.currentThread().getName());
				Integer a = 1+1;
				return a;
			}
		});
		new Thread(task).start();
		Integer a = task.get();
		System.out.println(a);
	}
}
