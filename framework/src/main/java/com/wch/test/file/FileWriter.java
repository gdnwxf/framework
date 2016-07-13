package com.wch.test.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FileWriter {

	public static void main(String[] args) throws IOException, InterruptedException {
		final java.io.FileWriter fileWriter = new java.io.FileWriter(new File("d:\\1.txt"), true);
		final BufferedWriter boStream =  new BufferedWriter(fileWriter);
		
		ScheduledThreadPoolExecutor executorService = new ScheduledThreadPoolExecutor(1);
		executorService.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				try {
					boStream.write("你好啊!");
					System.out.println("你好啊!");
					boStream.flush();
					fileWriter.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} , 3,3, TimeUnit.SECONDS); 
		TimeUnit.SECONDS.sleep(100);
		boStream.close();
		fileWriter.close();
		
	}
	
}
