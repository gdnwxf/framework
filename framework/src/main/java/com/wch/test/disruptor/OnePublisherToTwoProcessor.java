package com.wch.test.disruptor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.wch.test.disruptor.disruptor.LongEvent;
import com.wch.test.disruptor.disruptor.LongEventFactory;
import com.wch.test.disruptor.disruptor.LongEventHandler;

public class OnePublisherToTwoProcessor {
	private static final int BUFFER_SIZE = 1024;
	private final ExecutorService EXECUTOR = Executors.newFixedThreadPool(2);
	private final Disruptor<LongEvent> ringBuffer = new Disruptor<LongEvent>(
			new LongEventFactory(), BUFFER_SIZE, EXECUTOR ,ProducerType.SINGLE , new YieldingWaitStrategy());
	private final SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();
	private final LongEventHandler[] handlers = new LongEventHandler[2];
	{
		handlers[0] = new LongEventHandler("handler1");
		handlers[1] = new LongEventHandler("handler2");
	}
	private final BatchEventProcessor<?>[] batchEventProcessors = new BatchEventProcessor[2];
	{
		batchEventProcessors[0] =  new BatchEventProcessor<LongEvent>(ringBuffer, sequenceBarrier, handlers[0]);
		batchEventProcessors[1] =  new BatchEventProcessor<LongEvent>(ringBuffer, sequenceBarrier, handlers[1]);
		ringBuffer.addGatingSequences(batchEventProcessors[0].getSequence(),batchEventProcessors[1].getSequence());
	}

	public OnePublisherToTwoProcessor() {
		startCon();
		startPro();
	}

	public void startCon() {
		EXECUTOR.submit(batchEventProcessors[0]);
		EXECUTOR.submit(batchEventProcessors[1]);
	}

	void startPro() {
		new Thread(new Runnable() {
			public void run() {
				int number = 1;
				long price = 10;
				while (true) {
					// 获取下一个可用的slot
					long pos = ringBuffer.next();
					// 获得该slot
					ringBuffer.get(pos).setStockInfo(stockInfo);
					// 提交修改
					ringBuffer.publish(pos);
					try {
						double interval = Math.random();
						Thread.sleep((long) (1000 * interval));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	public static void main(String[] args) {
		new OnePublisherToTwoProcessor();
	}
}