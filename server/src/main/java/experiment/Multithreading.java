package experiment;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Multithreading {

	public static void main(String[] args) {
		ExecutorService e = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		final List <String> list = new Vector();
		e.execute(new Runnable() {
			
			@Override
			public void run() {
				list.add("test");
			}
		});
		e.isShutdown();
		while (!e.isTerminated()) {
		}
	}
	
	public static void main2(String[] args) {
		
		long start = System.currentTimeMillis();
		final List <String> list = new Vector();
		int countcpus = Runtime.getRuntime().availableProcessors();
		System.out.println("cpus: "+countcpus);
		for (int i = 0; i < countcpus; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						synchronized (list) {
							
						
						if(list.size() > 0)
							list.remove(0);
						//Thread.sleep(1000);
						list.add("hallo");
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName());
				}
			}).start();
		}
	
	System.out.println((System.currentTimeMillis() - start) + " ms");
	}
}
