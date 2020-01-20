package com.reyco.kn.core.task;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ReycoFutureTask<V> implements Runnable,Future<V>{
		
	final Callable<V> callable;
	
	V result;
	
	public ReycoFutureTask(Callable<V> callable) {
		super();
		this.callable = callable;
	}
	public V get() throws InterruptedException, ExecutionException {
		synchronized (this) {
			this.wait();
		}
		return result;
	}
	@Override
	public void run() {
		try {
			result = callable.call();
			synchronized (this) {
				this.notifyAll();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		return false;
	}
	@Override
	public boolean isCancelled() {
		return false;
	}
	@Override
	public boolean isDone() {
		return false;
	}
	@Override
	public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		return null;
	}
}
