package assignment4;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class findDictWordTest {

	@Test
	public void test() {
		//change w1 and w2 to strings you would like to test
				String w1 = "stone";
				String w2 = "money";
				
				WordLadderSolver test = new WordLadderSolver();
				test.makeDictionary();
				List<String> candidateWords = test.findDictWord(w1,w2,0);
				
				System.out.print(candidateWords); //should be atone and stone
				assertEquals(candidateWords.size(),2);
			
	}

}
