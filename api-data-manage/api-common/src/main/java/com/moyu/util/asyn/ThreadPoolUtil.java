package com.moyu.util.asyn;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolUtil {

	private ThreadPoolUtil() {
		
	}
	private static final ThreadPoolUtil INSTANCE = new ThreadPoolUtil();

	public static final ThreadPoolUtil getInstance() {
		return INSTANCE;
	}
	
	private volatile ExecutorService asynService;
	/**
	 * 异步执行线程池
	 * 
	 * @return
	 */
	public ExecutorService getAsynThread() {
		if (asynService == null) {
			synchronized (ExecutorService.class) {
				if (asynService == null) {
					asynService = Executors.newFixedThreadPool(50);
				}
			}
		}
		return asynService;
	}
	
	public static void execute(Runnable run){
		ThreadPoolUtil.getInstance().getAsynThread().execute(run);
	}
}
