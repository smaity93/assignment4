package assignment4; 

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List; 

public class Assign4Driver
{     
	
	public static void main(String[] args)     
	{        
			if (args.length != 1) {
				System.err.println("Error: Document Empty");
				System.exit(-1);
			}
			processLinesInFile(args[0]);
				
	} 
	
	public static void solver(String w1, String w2)
	{
		//NEED TO CHANGE INPUTS TO READ FROM DOCUMENT
		
		// Create a word ladder solver object         
		Assignment4Interface wordLadderSolver = new WordLadderSolver();
		
		try         
		{             
			List<String> result = wordLadderSolver.computeLadder("stone", "money");             
			//boolean correct = wordLadderSolver.validateResult("cones", "money", result);         
		}         
			
		catch (NoSuchLadderException e)         
			
		{             
				e.printStackTrace();         
		}  
		
	}
	
	public static void processLinesInFile(String filename) {

		// Translator translator = new Translator();
		try {
			FileReader freader = new FileReader(filename);
			BufferedReader reader = new BufferedReader(freader);

			for (String s = reader.readLine(); s != null; s = reader.readLine()) {
				String words[] = s.split(" ");
				String word1 = words[0];
				String word2 = words[1];
				solver(word1,word2);
			}
		} catch (FileNotFoundException e) {
			System.err.println("Error: File not found. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) {
			System.err.println("Error: IO exception. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
}
