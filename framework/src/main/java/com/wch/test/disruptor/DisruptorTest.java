package com.wch.test.disruptor;

import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.math.RandomUtils;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.wch.test.disruptor.disruptor.LongEvent;
import com.wch.test.disruptor.disruptor.LongEventFactory;
import com.wch.test.disruptor.disruptor.LongEventHandler;

public class DisruptorTest {

	public static void main(String[] args) {
		EventFactory<LongEvent> eventFactory = new LongEventFactory();
		ExecutorService executor = Executors.newSingleThreadExecutor();
		int ringBufferSize = 2 * 2; // RingBuffer 大小，必须是 2 的 N 次方；
		        
		final Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(eventFactory,
		                ringBufferSize, executor, ProducerType.SINGLE,
		                new YieldingWaitStrategy());
		        
		EventHandler<LongEvent> eventHandler = new LongEventHandler();
		
		disruptor.handleEventsWith(eventHandler);
		        
		final RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
	    
		new Thread() {
			public void run() {
				for (int i = 0; i < 10; i++) {
					long sequence = ringBuffer.next();//请求下一个事件序号；
					try {
						LongEvent event = ringBuffer.get(sequence);//获取该序号对应的事件对象；
						long data = getEventData();//获取要通过事件传递的业务数据；
						event.set(data);
						event.setName("第" + i  + "个事件");
					} finally{
						ringBuffer.publish(sequence);//发布事件；
					}
				}
			}

			private long getEventData() {
				return RandomUtils.nextLong();
			};
			
		} .start();
	
		disruptor.start();
	}
}
