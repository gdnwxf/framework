package com.wch.test.disruptor.counter;

import com.wch.test.disruptor.event.TestEvent;

public class TestHandler {
    
    private CounterTracer tracer;
    
    public TestHandler(CounterTracer tracer) {
        this.tracer = tracer;
    }
    
    /**
     * 如果返回 true，则表示处理已经全部完成，不再处理后续事件；
     * 
     * @param event
     * @return
     */
    public boolean process(TestEvent event){
        return tracer.count();
    }
}