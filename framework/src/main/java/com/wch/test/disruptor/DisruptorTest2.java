package com.wch.test.disruptor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.math.RandomUtils;

import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.EventTranslatorVararg;
import com.lmax.disruptor.ExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
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
		                ringBufferSize, executor, ProducerType.SINGLE,
		                new YieldingWaitStrategy());
		        
		
		
		EventHandler<LongEvent> eventHandler = new LongEventHandler(null);
		SequenceBarrier sequenceBarrier  = disruptor.getBarrierFor(eventHandler);
		ExceptionHandler<LongEvent> exceptionHandler = new ExceptionHandler<LongEvent>() {

			@Override
			public void handleEventException(Throwable ex, long sequence,LongEvent event) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void handleOnStartException(Throwable ex) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void handleOnShutdownException(Throwable ex) {
				// TODO Auto-generated method stub
				
			}
			
		};
		WorkHandler<LongEvent> workHandlers = new WorkHandler<LongEvent>() {

			@Override
			public void onEvent(LongEvent event) throws Exception {
				// TODO Auto-generated method stub
				System.out.println(" event : " + event);
			}
			
		};
		
		final LongEventHandler[] handlers = new LongEventHandler[2];
		{
			handlers[0] = new LongEventHandler("handler1");
			handlers[1] = new LongEventHandler("handler2");
		}
		        
		final RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
		BatchEventProcessor<LongEvent> [] batchEventProcessors = new BatchEventProcessor[2]; 
		{
			batchEventProcessors[0] =  new BatchEventProcessor<LongEvent>(ringBuffer, sequenceBarrier, handlers[0]);
			batchEventProcessors[1] =  new BatchEventProcessor<LongEvent>(ringBuffer, sequenceBarrier, handlers[1]);
			ringBuffer.addGatingSequences(batchEventProcessors[0].getSequence(),batchEventProcessors[1].getSequence());
		}

		executor.submit(batchEventProcessors[0]);
		executor.submit(batchEventProcessors[1]);
	    
		new Thread() {
			public void run() {
				for (int i = 0; i < 10; i++) {
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
