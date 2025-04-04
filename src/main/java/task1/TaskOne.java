package task1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
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
    	if (subInput[0] == "cat") {
    		cat(subInput[1]);
    	} 
    }

    // more methods can be added 
    
    public void cat(String catInput){
    	
    }

    
    public List<String> getCommandOutput() {
        return new ArrayList<>(bufferOutput);
    }
}
