package com.wch.test.disruptor.disruptor;
import java.util.concurrent.TimeUnit;

import com.lmax.disruptor.EventHandler;

public class LongEventHandler implements EventHandler<LongEvent>
{
	
	private String name ;
	
	
    public LongEventHandler(String name) {
		super();
		this.name = name;
	}



	public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws InterruptedException
    {
    	if(sequence == 5) {
    		TimeUnit.SECONDS.sleep(5);
    	} 
        System.out.println("Event: " + event);
    }
}