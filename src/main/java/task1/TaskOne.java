package task1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class TaskOne {
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
    	String[] commands = inputString.split("\\|");
        File tempFile = new File("temp.txt");
        for (int i = 0; i < commands.length; i++) {
            String command = commands[i].trim();
            String[] subCommands = command.split(" ");
            String cmd = subCommands[0];
            String arg = null;
            if (subCommands.length > 1) {
                arg = subCommands[1];
            }
            String fileInput;
            if (i == 0) {
                fileInput = arg;
            } else {
                fileInput = "temp.txt";
            }
            if (cmd.equals("cat")) {
                cat(fileInput);
            } else if (cmd.equals("sort")) {
                sort(fileInput);
            } else if (cmd.equals("uniq")) {
                uniq(fileInput);
            } else if (cmd.equals("wc")) {
                if (arg != null && arg.equals("-l")) {
                    String nextArg = null;
                    if (subCommands.length > 2) {
                        nextArg = subCommands[2];
                    }

                    if (i == 0) {
                        lWc(nextArg);
                    } else {
                        lWc("temp.txt");
                    }
                } else {
                    wc(fileInput);
                }
            } else {
                if (!cmd.trim().isEmpty()) {
                	throw new IllegalArgumentException("Invalid command " + cmd);
                }
            }
            if (i < commands.length - 1) {
                try {
                	FileWriter writer = new FileWriter(tempFile);
                
                	for (String line : bufferOutput) {
                		writer.write(line + "\n");
                	}
                	writer.close();
                	bufferOutput.clear();
                } catch (IOException e) {
					e.printStackTrace();
                }
            }
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
    			int newLine = 0;
    			while ((reader.readLine()) != null) {
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
    	if (new File(sortInput).length() == 0 && !new File(sortInput).exists()) {
    	} else if (new File(sortInput).length() == 0) {
    		 System.out.println("File is empty");
    	}
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
    	} else {
    		throw new IllegalArgumentException("Invalid file " + sortInput);
    	}
    }
    
    public void uniq(String uniqInput) {
    	if (new File(uniqInput).length() == 0 && !new File(uniqInput).exists()) {
    	} else if (new File(uniqInput).length() == 0) {
    		 System.out.println("File is empty");
    	}
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
    
    public List<String> getCommandOutput() {
        return new ArrayList<>(bufferOutput);
    }
}
