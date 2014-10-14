package thread_example;
public class ThreadJoinSleep {

	public static void  main(String[] args){
		// Here I am trying to show how the join works
		// as opposed to a sleep.
		// join will end the sleep as soon as the main ends therefore
		// forcing all threads to end at the same time.
		
		Thread thread = new Thread() {
			public void run() {
				System.out.println("Sleeping...");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("End sleeping...");
			}
		};

		thread.start();
		try {
			thread.sleep(5000);
//	        thread.join(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("End");
	}
}
