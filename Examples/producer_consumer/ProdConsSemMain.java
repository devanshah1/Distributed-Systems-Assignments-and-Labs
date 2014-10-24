import java.util.concurrent.Semaphore;


public class ProdConsSemMain {
	
	private Semaphore semProd, semCons;
	static private Consumer cons;
	static private Producer prod;

	public static void main(String[] args)
	{
		System.out.println("Creating producer and consumer semaphores");

		Semaphore semProd = new Semaphore(1, true);
		Semaphore semCons = new Semaphore(1, true);

		// Acquire both semaphores to replicate P(v)
		
		try {
			semProd.acquire();
			semCons.acquire();	
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		prod = new Producer(semProd,semCons);
		cons = new Consumer(semProd,semCons);
		
		prod.start();
		cons.start();
	}
}