/*
    ADD YOUR HEADER HERE
 */

package assignment4;

import java.io.*;
import java.util.*;


import java.io.BufferedReader;
import java.io.FileReader;


// do not change class name or interface it implements
public class WordLadderSolver implements Assignment4Interface
{
	static ArrayList<String> Dictionary = new ArrayList<String>();
	static List<String> SolutionList = new ArrayList<String>();
	static List<String> RemovedList = new ArrayList<String>();
	private Scanner x;

    // add a constructor for this object. HINT: it would be a good idea to set up the dictionary there
	public WordLadderSolver()
	{
		//constructor
	
	}
	
	
	
    // do not change signature of the method implemented from the interface
    @Override
    public List<String> computeLadder(String startWord, String endWord) throws NoSuchLadderException 
    {
    	
        // implement this method
    	makeDictionary();
    	
    	if(Dictionary.contains(startWord)==false)
    	{
    		throw new NoSuchLadderException("Invalid input start word");
    	}
    	if(Dictionary.contains(endWord)==false)
    	{
    		throw new NoSuchLadderException("Invalid input end word");
    	}
    	
    	int ladderExist = MakeLadder(startWord,endWord,0);
    	if (ladderExist == 0){throw new NoSuchLadderException("Ladder Does Not Exist");}
    	
    	return SolutionList;
    	
        
    	
    }

    @Override
    public boolean validateResult(String startWord, String endWord, List<String> wordLadder) 
    {
    	System.out.print("Validating Word Ladder...");
    	  //check to make sure start and end are right words
    		if(wordLadder.get(0).equals(startWord)==false){return false;}
    		if(wordLadder.get(wordLadder.size()-1).equals(endWord)==false){return false;}

    	 //check to make sure each word in ladder exists in dictionary
    		for (int i =0; i<wordLadder.size(); i++)
    		{
    			if(Dictionary.contains(wordLadder.get(i))==false){return false;}
    		}
    		
    	 //check to make sure each word differs by one letter
    		for(int i =0; i<wordLadder.size()-1; i++)
    		{
    			char[] word1 = wordLadder.get(i).toCharArray();
    			char[] word2 = wordLadder.get(i+1).toCharArray();
    			
    			int count = 0;
    	    	for(int j = 0; j<5; j++)
    	    	{
    	    		if(word1[j] == word2[j])
    	    		{
    	    			count = count+1;
    	    		}
    	    	}
    	    	
    	    	if(count<4){return false;}
    		}
    		
    		return true;
    }
    // add additional methods here
    public void makeDictionary(){
		try{
			x = new Scanner(new File("A4-words.txt"));
		}
		catch(Exception e)
		{
			System.out.println("Could not open dictionary");
		}
		while(x.hasNext())
		{
			String temp = x.nextLine();
			if (temp.startsWith("*")==true)
			{
				//do nothing
			}
			else
			{
				//store word in dictionary object
				String word = temp.substring(0, 5);
				Dictionary.add(word);
			}
		}
	}
    
    public int MakeLadder(String w1,String w2,int ind)
    {
    	if(w1.equals(w2)==true)
    	{
    		SolutionList.add(w1);
    		SolutionList.add(w2);
    		return 1;
    	}
    	if((SolutionList.contains(w1))==true)
    	{
    		return 0;
    	}
    	
    	if((RemovedList.contains(w1))==true)
    	{
    		return 0;
    	}
    	
    	SolutionList.add(w1);
    	
    	//check to see if w1 and w2 are one letter away
    	char[] word1 = w1.toLowerCase().toCharArray();
    	char[] word2 = w2.toLowerCase().toCharArray();
    	
    	int count = 0;
    	for(int i = 0; i<5; i++)
    	{
    		if(word1[i] == word2[i])
    		{
    			count = count+1;
    		}
    	}
    	if(count == 4){
    		SolutionList.add(w2);
    		return 1;
    		}
    	
    	int t = 0;
    	for(int i=0; i<5; i++)
    	{
    		List<String> currWordSet = new ArrayList<String>();
    		if((i==ind)&&(SolutionList.size()>1)){} //if you just changed this index, then do nothing
    		else if(word1[i] == word2[i]){} //if this index is the correct letter as end word, then do nothing
    		else
    		{
    			currWordSet = findDictWord(w1, w2, i);
    		}
    		
    		if(currWordSet.isEmpty()==true)
    		{
    			
    		}
    		else
    		{
    			for(int j = 0; j<currWordSet.size(); j++)
    			{
    				t = MakeLadder(currWordSet.get(j), w2, i);
    				if(t==1){return t;}
    			}
    		}
    		
    		
    		
    	}
    	
    	
    	SolutionList.remove(w1);
    	RemovedList.add(w1);
    	return t;
    		
    	
    }
    
    //method: findDictWord
    //returns a list of words that are exactly like input "word_str" except differ in the letter at index "ind"
    //also takes into consideration the "endword_str", 
    //     if there is a word that has the same letter in index "ind" as "endword_str", 
    //	   then that word gets sorted into the beginning of the returned list.
    public List<String> findDictWord(String word_str, String endword_str, int ind)
    {
    	char[] word = word_str.toCharArray();
    	char[] endword = endword_str.toCharArray();
    	List<String> candidateWords = new ArrayList<String>();
    
    	
    	char x = 'a';
    	for(int i = 0; i<26; i++)
    	{
    		if(word[ind] == x){} //do nothing
    		else if(endword[ind] == x)
    		{
    			char[] testWord = word;
    			testWord[ind] = x;
    			String testWord_str = new String(testWord);
    			boolean check = Dictionary.contains(testWord_str);
    			if(check == true)
    			{
    				candidateWords.add(0, testWord_str);
    			}
    		}
    		else
    		{
    			char[] testWord = word;
    			testWord[ind] = x;
    			String testWord_str = new String(testWord);
    			boolean check = Dictionary.contains(testWord_str);
    			if(check == true)
    			{
    				candidateWords.add(testWord_str);
    			}
    		}
    		
    		x = (char) (x+1);
    	}
    	
    	return candidateWords;
    	
    }
	
}
