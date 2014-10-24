import java.util.concurrent.Semaphore;


public class Producer extends Thread {
	Semaphore prod, cons;
	
	public Producer(Semaphore prod, Semaphore cons) {
		this.prod=prod;
		this.cons=cons;
	}
	
	public void run()
	{
		while(true) {
			try {
				System.out.println("Producing ...");
				prod.release();
				System.out.println("Waiting for Consumer ...");
				cons.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
	}
}