package junit_test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import gui.GUI;


class GUITests {

    @Test
    public void testReversePosString() {
        String posString = "abcdefghijkl";
        String expectedReverse = "kljihgfedcba";
        String actualReverse = ReversePosString.reversePosString(posString);
        assertEquals(expectedReverse, actualReverse);
    }
    
    @Test
     public void testUpdateScores() {
     // Create a new game board with a size of 5
        int gridSize = 5;
      	String[][] board = new String[gridSize][gridSize];
      	board[1][1] = "S";
        board[1][2] = "O";
        board[1][3] = "S";
            
            // Call the updateScores method
        updateScores(gridSize, board);
            
            // Check that the expected score is returned for each word
         assertEquals(1, getScore("SOS001001002001003"));
         assertEquals(1, getScore("SOS001001002001003".substring(3, 18)));
         assertEquals(1, getScore("SOS001001002001003".substring(6)));
         assertEquals(0, getScore("SOS000001002001003"));
         assertEquals(0, getScore("SOS000001002001003".substring(3, 18)));
         assertEquals(0, getScore("SOS000001002001003".substring(6)));
        }
        
        private int getScore(String posString) {
        // This method would retrieve the score for the given posString from the game's scoring system
        // In this sample test, it just returns a fixed value for testing purposes
        return posString.contains("SOS") ? 1 : 0;
        }
    

	
	@Test
	public void testInBounds() {
		// Test for gridSize = 5
		assertTrue(inBounds(0, 0, 5)); 
		assertTrue(inBounds(2, 3, 5)); 
		assertTrue(inBounds(4, 4, 5)); 
		assertFalse(inBounds(-1, 3, 5)); 
		assertFalse(inBounds(2, 5, 5)); 
		assertFalse(inBounds(5, 5, 5)); 
		assertFalse(inBounds(2, -1, 5)); 
	}

	  
	  @Test
	  public void testScoreWordSOS() {
	    // Test when the word is "SOS" and the position string has not been used before
	    ScoreWord.scoreWord("SOS", "A1B2C3");
	    assertEquals(1, ScoreWord.getRedScore());
	    
	    // Test when the word is "SOS" and the position string has already been used
	    ScoreWord.scoreWord("SOS", "A1B2C3");
	    assertEquals(1, ScoreWord.getRedScore());
	  }
	  
	  
	  	@Test
	  	public void testIsGameOver() {
	  		// Test for simple game with only one SOS combination
	  		Map<String, Boolean> sosCombinationPositionString = new HashMap<String, Boolean>();
	  		sosCombinationPositionString.put("A1B1C1", true);
	  		assertTrue(Game.isGameOver(3, 9, sosCombinationPositionString));

	  		// Test for simple game with multiple SOS combinations
	  		sosCombinationPositionString = new HashMap<String, Boolean>();
	  		sosCombinationPositionString.put("A1B1C1", true);
	  		sosCombinationPositionString.put("A2B2C2", true);
	  		assertFalse(Game.isGameOver(3, 9, sosCombinationPositionString));

	  		// Test for non-simple game with only one SOS combination
	  		sosCombinationPositionString = new HashMap<String, Boolean>();
	  		sosCombinationPositionString.put("A1B1C1", true);
	  		assertFalse(Game.isGameOver(4, 16, sosCombinationPositionString));

	  		// Test for non-simple game with all letters entered
	  		sosCombinationPositionString = new HashMap<String, Boolean>();
	  		assertFalse(Game.isGameOver(4, 16, sosCombinationPositionString));

	  		// Test for non-simple game with some letters remaining
	  		sosCombinationPositionString = new HashMap<String, Boolean>();
	  		assertFalse(Game.isGameOver(4, 12, sosCombinationPositionString));
	  	}
	  	
	  	@Test
	  	public void testUpdateBoard() {
	  		String[][] board = new String[][] {
	  			{"", "", ""},
	  			{"", "", ""},
	  			{"", "", ""}
	  		};
	  		int row = 1;
	  		int col = 2;
	  		String value = "X";
	  			
	  		updateBoard("RED", 0, row, col, value, board);
	  			
	  		assertEquals(board[row][col], value);
	  		assertEquals(1, lettersEnteredCount);
	  	} 

	  }

	  	
	  	

	  

	  
	








    
    
    



