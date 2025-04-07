package task1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
    	switch(subInput[0]) {
    	case "cat":
    		cat(subInput[1]);
    	case "wc":
    		if (subInput[1] == "-l") {
    			lWc(subInput[1]);
    		} else {
    			wc(subInput[1]);
    		}
    	case "sort":
    		sort(subInput[1]);
    	}
    }

    // more methods can be added 
    
    public void cat(String catInput) {
    	if (new File(catInput).exists()) {
    		try (BufferedReader reader = new BufferedReader(new FileReader(catInput))) {
    			String line;
    			while ((line = reader.readLine()) != null) {
    				bufferOutput.add(line);
    			reader.close();
    			}
    		} catch (IOException e) {
				e.printStackTrace();
			}
    	} else {
    		throw new IllegalArgumentException("Error: Invalid file " + catInput);  //do I need file name as well?
    	}
    	
    }
    
    public void wc(String wcInput) {
    	
    }
    
    public void lWc(String lwcInput) {
    	
    }
    
    public void sort(String sortInput) {
    	
    }

    
    public List<String> getCommandOutput() {
        return new ArrayList<>(bufferOutput);
    }
}
