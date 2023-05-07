package junit_test;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import static org.junit.Assert.assertEquals;


import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import gui.GUI;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import static org.junit.Assert.assertEquals;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import static org.junit.Assert.assertEquals;


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

	void testMakeMove() {
	    // Create a new game board with a size of 3
	    State board = new State(3);
	    JPanel grid = new JPanel();
	    JButton tic = new JButton();
	    
	    // Make a valid move
	    makeMove(0, 0, 'S', grid, tic);
	    assertEquals("Red - S", tic.getText());
	    assertEquals(Color.CYAN, tic.getBackground());
	    assertEquals("Current player: Blue", currentPlayer.getText());
	    
	    // Make an invalid move
	    makeMove(0, 0, 'O', grid, tic);
	    assertEquals("Red - S", tic.getText());
	    assertEquals(Color.CYAN, tic.getBackground());
	    assertEquals("Current player: Blue", currentPlayer.getText());
	    
	    // Make a winning move
	    makeMove(1, 1, 'O', grid, tic);
	    makeMove(0, 1, 'S', grid, tic);
	    makeMove(2, 2, 'O', grid, tic);
	    makeMove(0, 2, 'S', grid, tic);
	    makeMove(2, 0, 'O', grid, tic);
	    makeMove(1, 0, 'S', grid, tic);
	    makeMove(2, 1, 'O', grid, tic);
	    assertEquals(State.PLAYER_2_WINS, board.getState(isGameSimple()));
	    JOptionPane.showMessageDialog(grid, "Blue won!");
	    
	    // Make a draw
	    makeMove(1, 2, 'S', grid, tic);
	    makeMove(0, 1, 'O', grid, tic);
	    makeMove(0, 2, 'S', grid, tic);
	    makeMove(1, 1, 'O', grid, tic);
	    makeMove(2, 0, 'S', grid, tic);
	    makeMove(2, 1, 'O', grid, tic);
	    makeMove(1, 0, 'S', grid, tic);
	    makeMove(2, 2, 'O', grid, tic);
	    makeMove(0, 0, 'S', grid, tic);
	    assertEquals(State.DRAW, board.getState(isGameSimple()));
	    JOptionPane.showMessageDialog(grid, "It's a draw!");
	}

	    @Test
	    void testGetBestMove() {
	        State state = new State(3);
	        state.move(0, 0, 'O');
	        state.move(1, 1, 'S');
	        state.move(2, 2, 'O');
	        Move move = AI.getBestMove(state);
	        Assertions.assertNotNull(move);
	        Assertions.assertTrue(move.getPiece() == 'O' || move.getPiece() == 'S');
	        Assertions.assertTrue(move.getSpot().get(0) == 1 || move.getSpot().get(0) == 2);
	        Assertions.assertTrue(move.getSpot().get(1) == 0 || move.getSpot().get(1) == 1);
	    }
	   
	    
	    public void testToString() {
	        MoveRecord move = new MoveRecord(2, 3, "A", "black");
	        String expected = "{\"x\":2,\"y\":3,\"letter\":\"A\",\"color\":\"black\"}";
	        assertEquals(expected, move.toString());
	    }
	    
	    @Rule
	    public TemporaryFolder folder = new TemporaryFolder();

	    @Test
	    public void testWrite() throws IOException {
	        String path = folder.getRoot().getPath() + "/test.txt";
	        String content = "Hello, world!";
	        IOUtils.write(path, content);
	        String actualContent = new String(Files.readAllBytes(new File(path).toPath()));
	        assertEquals(content, actualContent);
	    }
	    
	    
	    
	    
	    
	    
	}
