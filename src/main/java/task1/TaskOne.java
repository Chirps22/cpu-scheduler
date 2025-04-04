package task1;

import java.io.BufferedReader; //I am allowed this right?
import java.io.File;  //I am allowed this right?
import java.io.FileReader; //I am allowed this right?
import java.util.*;

public class TaskOne {
    // store all the output in this ArrayList for testing purposes
    private static final List<String> bufferOutput = new ArrayList<>();
    
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            TaskOne taskA = new TaskOne();
            
            System.out.println("Enter commands: cat, sort, uniq, wc or | ");
            while (true) {
                System.out.print(">> ");
                String input = scanner.nextLine().trim();
                try {
                    taskA.executeCommands(input);
                    bufferOutput.forEach(System.out::println);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                } finally {
                    bufferOutput.clear();
                }
            }
        }
    }
    // To be completed
    public void executeCommands(String inputString) {
    	String[] subInput = inputString.split(" ");
    	if (subInput[0] == "cat") {
    		if (new File(subInput[1]).isFile()) {
    			BufferedReader buffer = new BufferedReader(new FileReader(subInput[1]));
    			String output = "";
    			String line = null;
    			while ((line = buffer.readLine()) != null) {
    				output += line;
    			}
    		} else {
    			throw new IllegalArgumentException("Error: Invalid file" + subInput[1]);   //do I need file name as well?
    		}
    	}

    }

    // more methods can be added 

    
    public List<String> getCommandOutput() {
        return new ArrayList<>(bufferOutput);
    }
}
