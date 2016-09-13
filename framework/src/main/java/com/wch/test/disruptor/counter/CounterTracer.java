package com.wch.test.disruptor.counter;

public interface CounterTracer {
	    public void start()  ;

	    public long getMilliTimeSpan()  ;
	    public boolean count();
	    public void waitForReached()throws InterruptedException ;
}
