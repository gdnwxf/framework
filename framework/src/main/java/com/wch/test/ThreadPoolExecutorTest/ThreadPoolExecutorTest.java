package com.wch.test.ThreadPoolExecutorTest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * 	3, 核心线程数
	3, 最大线程数
	5000, 线程活动的最大时间 
	TimeUnit.SECONDS, 活动的单位
	workQueue 任务队列   
				当队列深度达到最大的时候才会新起线程去处理  
				当线程达到最大时则走RejectedExecutionHandler
 * </pre>
 * @author wch
 *
 */
public class ThreadPoolExecutorTest {

	public static void main(String[] args) {
		
		final BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(100);
		ThreadPoolExecutor threadPoolExecutor = new  ThreadPoolExecutor(2, 10, 5000, TimeUnit.SECONDS, workQueue); 
		
//		new Timer() {
//			
//		}.schedule(new TimerTask() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				System.out.println(" 队列大小是多少 " + workQueue.size());
//			}
//		}, 0, 10);;
		
//		threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//		for (int i = 0; i < 10; i++) {
//			workQueue.add(new Runnable() {
//				
//				public void run() {	
//					System.out.println(  Thread.currentThread().getName());
//				}
//			});
//		}
		
		for (int i = 0; i < 1000; i++) {
			threadPoolExecutor.execute(new Runnable() {
				
				public void run() {	
					System.out.println(Thread.currentThread().getName() + "队列深度" + workQueue.size());
				}
			});
		}
	}
}
