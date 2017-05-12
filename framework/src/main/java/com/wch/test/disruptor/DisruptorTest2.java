package com.wch.test.disruptor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import org.apache.commons.lang.math.RandomUtils;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.EventTranslatorVararg;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.wch.test.disruptor.disruptor.LongEvent;
import com.wch.test.disruptor.disruptor.LongEventFactory;
import com.wch.test.disruptor.disruptor.LongEventHandler;

public class DisruptorTest2 {

	public static void main(String[] args) {
		EventFactory<LongEvent> eventFactory = new LongEventFactory();
		ExecutorService executor = Executors.newFixedThreadPool(3);
		int ringBufferSize = 1024 * 1024; // RingBuffer 大小，必须是 2 的 N 次方；
		        
		final Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(eventFactory,
													ringBufferSize, 
													executor, 
													ProducerType.SINGLE, 
													new YieldingWaitStrategy()) ;
				
//				new Disruptor<LongEvent>(eventFactory,
//		                ringBufferSize, executor, ProducerType.SINGLE,
//		                new YieldingWaitStrategy());
		        
//		RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
		 
		
		WorkHandler<LongEvent> workHandlers1 = new WorkHandler<LongEvent>() {
			@Override
			public void onEvent(LongEvent event) throws Exception {
				System.out.println( event.getName() +" " + Thread.currentThread().getName());
			}
		};
		WorkHandler<LongEvent> workHandlers2 = new WorkHandler<LongEvent>() {
			@Override
			public void onEvent(LongEvent event) throws Exception {
				System.out.println( event.getName() +" " + Thread.currentThread().getName());
			}
		};
		 
//		disruptor.handleEventsWithWorkerPool(workHandlers);
//		disruptor.handleEventsWith( new EventHandler[] { new LongEventHandler("1") ,new LongEventHandler("2") });
		
		disruptor.handleEventsWithWorkerPool(workHandlers1 ,workHandlers2 );
		
		new Thread() {
			public void run() {
				for (int i = 0; i < 100; i++) {
				    publishEvent2(disruptor, i);
				}
			}

			private long getEventData() {
				return RandomUtils.nextLong();
			};
			
		} .start();
	
		disruptor.start();
	}
	
	
	static class Translator implements EventTranslatorVararg<Object>{

		@Override
		public void translateTo(Object event, long sequence, Object... args) {
			// TODO Auto-generated method stub
			LongEvent event2 = (LongEvent) event;
			event2.set((Long) args[0]);
			event2.setName("第" + (Integer) args[1] + " 个事件");
			
		}    
	}
	    
	public static EventTranslatorVararg TRANSLATOR = new Translator();
	    
	public static void publishEvent2(Disruptor<LongEvent> disruptor, int index) {
	    // 发布事件；
	    RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
	    long data = getEventData();//获取要通过事件传递的业务数据；
	    ringBuffer.publishEvent((EventTranslatorVararg)TRANSLATOR, data ,index);
	}

	private static long getEventData() {
		return RandomUtils.nextLong();
	}
}
