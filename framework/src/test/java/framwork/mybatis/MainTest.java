package framwork.mybatis;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class MainTest {
	public static void main(String[] args) {
		MyTask mt = new MyTask(1);

		ForkJoinPool forkJoinPool = new ForkJoinPool();
//		System.out.println(Runtime.getRuntime().availableProcessors()+" processess nums");

		Future<Integer> result = forkJoinPool.submit(mt);

		try {
			System.out.println(result.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		forkJoinPool.shutdown();
	}

	 
}