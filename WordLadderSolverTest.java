package assignment4;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class WordLadderSolverTest {

	@Test
	public void testOneLetter() throws NoSuchLadderException {
		
		//change w1 and w2 to strings you would like to test
		String w1 = "stone";
		String w2 = "money";
		
		WordLadderSolver test = new WordLadderSolver();
		List<String> wordLadder = test.computeLadder(w1, w2); 
		boolean result = test.oneLetterApart(wordLadder);
		
		assertEquals(true,result);
		
		
		
	}
	
	public void testInDictionary() throws NoSuchLadderException
	{
		//change w1 and w2 to strings you would like to test
				String w1 = "stone";
				String w2 = "money";
				
				WordLadderSolver test = new WordLadderSolver();
				List<String> wordLadder = test.computeLadder(w1, w2); 
				boolean result = test.inDictionary(wordLadder);
		
				assertEquals(true,result);
	}

}
