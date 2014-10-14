package semaphore_example;
import java.util.concurrent.Semaphore;
import java.util.Random;

//Solving the mutual exclusion problem using Semaphore class
// Try switching the number of semaphores used. When you switch to 1
// semaphore you see that the other threads will not enter the
// critical region until one has left.

class SemaphoreExample extends Thread
{
	private static final Random rand = new Random();

	private int id;

	private Semaphore sem;

	public SemaphoreExample(int i, Semaphore s)
	{
		id = i;
		sem = s;
	}

	private void busy()
	{
		try
		{
			sleep(rand.nextInt(500));
		} catch (InterruptedException e)
		{
		}
	}

	private void noncritical()
	{
		System.out.println("Thread " + id + " is NON critical");
		busy();
	}

	private void critical()
	{
		System.out.println("Thread " + id + " entering critical section");
		busy();
		System.out.println("Thread " + id + " leaving critical section");
	}

	public void run()
	{
		for (int i = 0; i < 2; ++i)
		{
			noncritical();
			try
			{
				sem.acquire();
			} catch (InterruptedException e)
			{
				// ...
			}
			critical();
			sem.release();
		} 
	}

	public static void main(String[] args)
	{
		final int N = 4;

		System.out.println("Busy waiting...");

		//Semaphore(int permits, boolean fair) 
		Semaphore sem = new Semaphore(2, true);

		SemaphoreExample[] p = new SemaphoreExample[N];

		for (int i = 0; i < N; i++)
		{
			p[i] = new SemaphoreExample(i, sem);
			p[i].start();
		}
	}
}
