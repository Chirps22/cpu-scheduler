package ProcessScheduler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * CPU Thread. Runs a python script from a given PCB.
 *
 */
public class CPU extends Thread {

	/**
	 * Instance fields used in constructor for parsing in other objects
	 */
	private boolean running = true; // if CPU is actively executing a process
	private ProcessControlBlock PCB = null;
	private EventLog log = null;

	public CPU(ProcessControlBlock PCB, EventLog log) {
		this.PCB = PCB;
		this.log = log;
	}

	/**
	 * Run method - Runs a python process when called.
	 * Puts the current CPU thread to sleep for the time the python script
	 * should run for.
	 * 
	 */
	public void run() {
		// TODO
		try {
			ProcessBuilder pb = new ProcessBuilder("python3", PCB.getProcessPath());
			PCB.setArrivalTime(System.currentTimeMillis());
			System.out.println(PCB.getPID() + ": Running");
			Process p = pb.start();
			Thread.sleep(PCB.getCPUBurstTime());
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			StringBuffer outputBuffer = new StringBuffer();
			String line;
			while ((line = reader.readLine()) != null) {
				outputBuffer.append(line);
				outputBuffer.append("\n");
			}
			String finalOutput = outputBuffer.toString().trim();
			PCB.setPCBResult(finalOutput);
			PCB.setExecutionTime();
			PCB.setState("Terminated");
			System.out.println(PCB.getPID() + ": Complete, Context Switches: " + PCB.getContextSwitches() + ", Output: " + finalOutput + ", Execution Time: " + PCB.getExecutionTime() + "ms");
			log.add(PCB.getPID() + ": Complete, Context Switches: " + PCB.getContextSwitches() + ", Output: " + finalOutput);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public ProcessControlBlock getPCB() {
		return this.PCB;
	}

	public boolean getRunning() {
		return this.running;
	}

}
