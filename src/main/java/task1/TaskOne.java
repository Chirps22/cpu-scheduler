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

    public void executeCommands(String inputString) {
    	String[] subInput = inputString.split(" ");
    	switch(subInput[0]) {
    	case "cat":
    		cat(subInput[1]);
    		break;
    	case "wc":
    		if (subInput[1].equals("-l")) {
    			lWc(subInput[2]);
    			break;
    		} else {
    			wc(subInput[1]);
    			break;
    		}
    	case "sort":
    		sort(subInput[1]);
    		break;
    	case "uniq":
    		uniq(subInput[1]);
    		break;
    	case "":
    		break;
    	default:
    		throw new IllegalArgumentException("Invalid command " + subInput[0]);
    	}
    }
    
    public void cat(String catInput) {
    	if (new File(catInput).exists() && new File(catInput).isFile()) {
    		try (BufferedReader reader = new BufferedReader(new FileReader(catInput))) {
    			String line;
    			while ((line = reader.readLine()) != null) {
    				bufferOutput.add(line);
    			}
    			reader.close();
    		} catch (IOException e) {
				e.printStackTrace();
			}
    	} else if (new File(catInput).exists() && new File(catInput).isDirectory()) {
    		throw new IllegalArgumentException(catInput + "is a directory");
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
    
    public void lWc(String lwcInput) {
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
    			Collections.sort(sortFileLines);
    			for (String i : sortFileLines) {
    				bufferOutput.add(i);
    			}
    		} catch (IOException e) {
				e.printStackTrace();
			}
    	} else if (new File(sortInput).length() == 0){
    		throw new IllegalArgumentException("Cannot sort empty file " + sortInput);    //do I need this???
    	} else {
    		throw new IllegalArgumentException("Invalid file " + sortInput);
    	}
    }
    
    public void uniq(String uniqInput) {
    	if (new File(uniqInput).exists()) {
    		try (BufferedReader reader = new BufferedReader(new FileReader(uniqInput))) {
    			String line;
    			List<String> uniqFileLines = new ArrayList<>();
    			while ((line = reader.readLine()) != null) {
    				uniqFileLines.add(line);
    			}
    			reader.close();
    			for (int i = 0; i < (uniqFileLines.size() - 1); i++) {
    				while (uniqFileLines.get(i).equals(uniqFileLines.get(i+1))) {
    					uniqFileLines.remove(i+1);
    				}
    			}
    			for (String i : uniqFileLines) {
    				bufferOutput.add(i);
    			}
    		} catch (IOException e) {
				e.printStackTrace();
			}
    	} else {
    		throw new IllegalArgumentException("Invalid file " + uniqInput);
    	}
    }
    
    public void pipe() {
    	
    }
    
    public List<String> getCommandOutput() {
        return new ArrayList<>(bufferOutput);
    }
}
