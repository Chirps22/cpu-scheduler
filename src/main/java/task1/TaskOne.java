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
    		break;
    	case "wc":
    		if (subInput[1] == "-l") {
    			lWc(subInput[2]);
    		} else {
    			wc(subInput[1]);
    		}
    		break;
    	case "sort":
    		sort(subInput[1]);
    		break;
    	case "uniq":
    		uniq(subInput[1]);
    	default:
    		//actual error or just print message?
    	}
    }

    // more methods can be added 
    
    public void cat(String catInput) {
    	if (new File(catInput).exists()) {
    		try (BufferedReader reader = new BufferedReader(new FileReader(catInput))) {
    			String line;
    			while ((line = reader.readLine()) != null) {
    				bufferOutput.add(line);
    			}
    			reader.close();
    		} catch (IOException e) {
				e.printStackTrace();
			}
    	} else {
    		throw new IllegalArgumentException("Invalid file " + catInput);
    	}
    }
    
    public void wc(String wcInput) {
    	if (new File(wcInput).exists()) {
    		try (BufferedReader reader = new BufferedReader(new FileReader(wcInput))) {
    			String line;
    			int newLine = 0;
    			int words = 0;
    			int bytes = 0;
    			while ((line = reader.readLine()) != null) {
    				newLine += 1;
    				String[] splitLine = line.split(" ");
    				if (splitLine[0] != " ") {
    					words += splitLine.length;
    					bytes += line.getBytes("UTF-8").length;
    				}
    			}
    			reader.close();
    			String result = newLine + " " + words + " " + bytes;
    			bufferOutput.add(result);
    		} catch (IOException e) {
				e.printStackTrace();
			}
    	} else {
    		throw new IllegalArgumentException("Invalid file " + wcInput);
    	}
    }
    
    public void lWc(String lwcInput) {    //does not work!!!!!
    	if (new File(lwcInput).exists()) {
    		try (BufferedReader reader = new BufferedReader(new FileReader(lwcInput))) {
    			String line;
    			int newLine = 0;
    			while ((line = reader.readLine()) != null) {
    				newLine += 1;
    			}
    			reader.close();
    			String lineCount = Integer.toString(newLine);
    			bufferOutput.add(lineCount);
    		} catch (IOException e) {
				e.printStackTrace();
			}
    	} else {
    		throw new IllegalArgumentException("Invalid file " + lwcInput);
    	}
    }
    
    public void sort(String sortInput) {
    	if (new File(sortInput).exists()) {
    		try (BufferedReader reader = new BufferedReader(new FileReader(sortInput))) {
    			String line;
    			List<String> sortFileLines = new ArrayList<>();
    			while ((line = reader.readLine()) != null) {
    				sortFileLines.add(line);
    			}
    			reader.close();
    			//can I use a prebuilt library to sort???
    			bufferOutput.add(null);
    		} catch (IOException e) {
				e.printStackTrace();
			}
    	} else {
    		throw new IllegalArgumentException("Invalid file " + sortInput);
    	}
    }
    
    public void uniq(String uniqInput) {  //what if there are 3 duplicates? if one compared to 2 gets rid of 2 and 3 takes its place, do i compare 1 to 3 or move on and compare 3 to 4?
    	if (new File(uniqInput).exists()) {
    		try (BufferedReader reader = new BufferedReader(new FileReader(uniqInput))) {
    			String line;
    			List<String> uniqFileLines = new ArrayList<>();
    			while ((line = reader.readLine()) != null) {
    				uniqFileLines.add(line);
    			}
    			reader.close();
    			for (int i = 0; i < (uniqFileLines.size() - 1); i++) {
    				if (uniqFileLines.get(i) == uniqFileLines.get(i+1)) {
    					uniqFileLines.remove(i);
    				}
    			}
    			//how to get this outputted when working in pipe? Temp file???
    		} catch (IOException e) {
				e.printStackTrace();
			}
    	} else {
    		throw new IllegalArgumentException("Invalid file " + uniqInput);
    	}
    }

    
    public List<String> getCommandOutput() {
        return new ArrayList<>(bufferOutput);
    }
}
