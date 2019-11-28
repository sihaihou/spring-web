package com.reyco.kn.core.utils;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.util.concurrent.RateLimiter;

public class RateLimiterUtils {

	public static void main(String[] args) throws InterruptedException {
		// test();
		// exec();
		atomic();
	}

	public static void test() throws InterruptedException {
		/**
		 * 根据指定的稳定吞吐率和预热期来创建RateLimiter，这里的吞吐率是指每秒多少许可数（通常是指QPS，每秒多少个请求量），
		 * 在这段预热时间内，RateLimiter每秒分配的许可数会平稳地增长直到预热期结束时达到其最大速率。（只要存在足够请求数来使其饱和）
		 * 
		 */
		// RateLimiter rateLimiter = RateLimiter.create(5, 3, TimeUnit.SECONDS);
		/**
		 * 根据指定的稳定吞吐率和预热期来创建RateLimiter，这里的吞吐率是指每秒多少许可数（通常是指QPS，每秒多少个请求量）
		 */
		RateLimiter rateLimiter = RateLimiter.create(50);
		/**
		 * getRate()返回RateLimiter 配置中的稳定速率，该速率单位是每秒多少许可数
		 */
		double rate = rateLimiter.getRate();
		System.out.println("rateLimiter稳定速率：" + rate);
		for (int i = 0; i < 20; i++) {
			/**
			 * 从RateLimiter 获取许可数，如果该许可数可以在无延迟下的情况下立即获取得到的话
			 */
			// System.out.println(rateLimiter.tryAcquire(1));
			/**
			 * 从RateLimiter 获取指定许可数如果该许可数可以在不超过timeout的时间内获取得到的话， 或者如果无法在timeout
			 * 过期之前获取得到许可数的话，那么立即返回false （无需等待）
			 */
			System.out.println(rateLimiter.tryAcquire(1, 100, TimeUnit.MILLISECONDS));
			// System.out.println(rateLimiter.acquire());
		}
	}

	public static void exec() throws InterruptedException {
		// 信号量
		Semaphore semphore = new Semaphore(2, true);
		for (int i = 0; i < 10; i++) {
			TimeUnit.SECONDS.sleep(2);
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						if (semphore.getQueueLength() > 2) {
							System.out.println("当前等待排队的任务数大于2，请稍候再试...");
						}
						if (semphore.tryAcquire(1, 1, TimeUnit.SECONDS)) {
							// 处理核心逻辑
							TimeUnit.SECONDS.sleep(3);
							System.out.println("--" + System.currentTimeMillis() / 1000);
						} else {
							System.out.println("没有令牌，请稍候再试...");
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						semphore.release(1);
					}
				}
			}).start();
		}
	}

	public static void atomic() {
		AtomicInteger count = new AtomicInteger(0);
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					if (count.get() >= 5) {
						System.out.println("请求用户过多，请稍后在试！" + System.currentTimeMillis() / 1000 + Thread.currentThread().getName());
					} else {
						try {
							count.incrementAndGet();
							// 处理核心逻辑
							TimeUnit.SECONDS.sleep(1);
							System.out.println("--" + System.currentTimeMillis() / 1000 + Thread.currentThread().getName());
						} catch (InterruptedException e) {
							e.printStackTrace();
						} finally {
							count.decrementAndGet();
						}
					}
				}
			}).start();
		}
	}

}
