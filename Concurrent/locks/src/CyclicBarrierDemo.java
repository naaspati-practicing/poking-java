import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

// https://www.baeldung.com/java-cyclic-barrier
public class CyclicBarrierDemo implements Runnable {

	public static void main(String[] args) {
		new CyclicBarrierDemo(5, 3).run();
	}
	
	private final CyclicBarrier barrier;
	private final List<List<Integer>> partialResults = Collections.synchronizedList(new ArrayList<>());
	private final int NUM_WORKERS;
	private final int NUM_PARTIAL_RESULTS;

	public CyclicBarrierDemo(int num_workers, int num_partial_results) {
		super();
		NUM_WORKERS = num_workers;
		NUM_PARTIAL_RESULTS = num_partial_results;
		
		barrier = new CyclicBarrier(num_workers, new AggregatorThread());
	}
	
	@Override
	public void run() {
		ExecutorService ex = Executors.newCachedThreadPool();
		System.out.println("start CyclicBarrierDemo in thread: "+Thread.currentThread().getName());
		
		for (int i = 0; i < NUM_WORKERS; i++) 
			ex.execute(new NumberCruncherThread());
		
		ex.shutdown();
		try {
			ex.awaitTermination(3, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	class NumberCruncherThread implements Runnable {
		@Override
		public void run() {
			String tname = Thread.currentThread().getName();
			
			List<Integer> list = new ArrayList<>();
			ThreadLocalRandom r = ThreadLocalRandom.current();
			
			for (int i = 0; i < NUM_PARTIAL_RESULTS; i++) {
				Integer n = Integer.valueOf(r.nextInt(10));
				System.out.printf("%s: Crunching some numbers! Final result - %s\n", tname, n);
				list.add(n);
			}
			partialResults.add(list);
			
			try {
				System.out.println(tname.concat(" waiting for others to reach barrier."));
				barrier.await();
				System.out.println(tname.concat(" wait released"));
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}
	
	class AggregatorThread implements Runnable {

		@Override
		public void run() {
		  String tname = Thread.currentThread().getName();
		  System.out.println( tname + ": Computing sum of " + NUM_WORKERS + " workers, having " + NUM_PARTIAL_RESULTS + " results each.");
		  
		  int sum = 0;
		  for (List<Integer> threadResult : partialResults) {
              System.out.print("Adding ");
              
              for (Integer partialResult : threadResult) {
                  System.out.print(partialResult+" ");
                  sum += partialResult;
              }
              System.out.println();
          }
		  
		 System.out.println(tname + ": Final result = " + sum);

		}
		
	}

}
