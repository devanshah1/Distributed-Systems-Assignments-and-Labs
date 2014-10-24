import java.util.concurrent.Semaphore;

public class Consumer extends Thread {
	Semaphore prod, cons;

	public Consumer(Semaphore prod, Semaphore cons) {
		this.prod=prod;
		this.cons=cons;
	}

	public void run()
	{
		while(true) {
			try {
				System.out.println("Waiting for Producer ...");
				prod.acquire();
				System.out.println("Consuming ...");
				cons.release();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}