package ProcessScheduler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.InputStream;
import java.io.IOException;

/**
 * This class reads a text file, and create PCB objects to be added to the JobQueue
 */
public class JobQueue {

	private Queue<ProcessControlBlock> queue = null;

	public JobQueue() throws FileNotFoundException {
		this.queue = new LinkedList<ProcessControlBlock>();
	}

	/** Reads the InputScript file, Ignores line if it begins with a hash.
	 * @param filePath
	 * @throws FileNotFoundException
	 */
	public void readFile(String filePath) throws FileNotFoundException {
		File file = new File(filePath);
		// TODO
		// Read the file...
		// Check if the file exists
		// Reads the InputScript file
		// adds the process from the file to the queue (use addToQueue())
	   /*  if (!file.exists()) {
	        throw new FileNotFoundException("The file at path " + filePath + " does not exist.");
	    }*/
		if (file.exists()) {
			try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
				String line;
				while ((line = reader.readLine()) != null) {
					if (line.startsWith("#")) {	
						continue;
					} else {
						String[] splitScript = line.split(",");
						int priority = Integer.parseInt(splitScript[1]);
						addToQueue(splitScript[0],priority,splitScript[2]);
					}
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			throw new FileNotFoundException("The file at path " + filePath + " does not exist.");
		}
	}

	/** Creates a PCB object and adds it to the jobQueue. 
	 * @param PID - process id
	 * @param priority - int priority
	 * @param processPathFile - specific path of the python file.
	 */
	public void addToQueue(String PID, int priority, String processPathFile) {
		// get the process CPU burst's time, then add to the queue
		// TODO
		long burst = generateBurst(processPathFile);
		ProcessControlBlock process = new ProcessControlBlock(PID,priority,burst,processPathFile);
		queue.add(process);
	}

	/** 
	 * @param PythonPathFile - path of python script
	 * @return - generated burst time
	 */
	private long generateBurst(String PythonPathFile) {
		double generatedBurst = 0;
		long scriptSize = new File(PythonPathFile).length();
		generatedBurst = Math.round((Main.getBurstSpeed() * scriptSize)/10) * 10;
		return (long) generatedBurst;
	}

	public Queue<ProcessControlBlock> getQueue() {
		return this.queue;
	}

}
