package ProcessScheduler;

import java.util.Queue;

/**
 * Dispatcher is a thread which checks the readyQueue for a process and
 * dispatches one at a time.
 */
public class Dispatcher implements Runnable {
	/**
	 * Instance fields for accessing ready queue and scheduler object
	 */
	private ProcessControlBlock PCB = null;
	private Queue<ProcessControlBlock> readyQueue = null;
	private Scheduler scheduler = null;
	private boolean dispatched = false; // ensures only one process is dispatched at a time.

	/**Constructor to assign input parameters to instance fields.
	 * @param readyQueue
	 * @param scheduler
	 */
	public Dispatcher(Queue<ProcessControlBlock> readyQueue, Scheduler scheduler) {
		this.readyQueue = readyQueue;
		this.scheduler = scheduler;
	}

	public void run() {
		// TODO
		synchronized (readyQueue) {
            if (!readyQueue.isEmpty()) {
                scheduler.runAlgorithm();
            }
        }
	}

	public ProcessControlBlock getPCB() {
		return this.PCB;
	}

	public void setDispatched(boolean cpuBusy) {
		this.dispatched = cpuBusy;
	}

	public boolean getDispatched() {
		return this.dispatched;
	}

}
