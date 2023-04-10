package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import javax.swing.*;

public class GUI {
	// Class Variables
	static JButton button = new JButton("Submit");
	static JRadioButton sbutton = new JRadioButton("S");
	static JRadioButton obutton = new JRadioButton("O");
	static JRadioButton redbutton = new JRadioButton("Red");
	static JRadioButton bluebutton = new JRadioButton("Blue");
	static JRadioButton simplebutton = new JRadioButton("Simple game");
	static JRadioButton genbutton = new JRadioButton("General game");
	static JTextField field = new JTextField("", 3);
	// static JPanel panel_1 = new JPanel();
	static JLabel currentPlayer = new JLabel("Current player");
	static JLabel titleLabel = new JLabel("SOS Game");
	static int redScore = 0;
	static int blueScore = 0;

	static String[][] board;
	static int lettersEnteredCount = 0;

	static HashMap<String, Boolean> sosCombinationPositionString = new HashMap<>();

	// Constructor

	/*
	 * public GUI() { // Local Variables
	 * 
	 * JFrame jfrSOS = null; Container c = null;
	 * 
	 * //integer z = 0;
	 * 
	 * // Instantiate the frame
	 * 
	 * jfrSOS = new JFrame( "SOS" );
	 * 
	 * c = jfrSOS.getContentPane();
	 * 
	 * buildGUI( c );
	 * 
	 * }
	 * 
	 * 
	 * 
	 * private JPanel createGridInGridPanel() {
	 * 
	 * panel_1.add(panel_1);
	 * 
	 * return panel_1; }
	 * 
	 */

	private static void buildGUI(Container c) {
		// Local Variables

		JPanel jpaHeader = createHeaderPanel();
		JPanel jpaColor = createColorPanel();
		JPanel jpaGrid = createGridPanel();
		JPanel jpaLetter = createLetterPanel();
		// JPanel jpaTurn = createTurnPanel();
		JPanel jpaType = createGameTypePanel(jpaHeader);
		// JPanel jpaGridinGrid = new JPanel();

		// Set layout for container c

		c.setLayout(new BorderLayout());

		// Instantiate the Grid Panel

		// jpaGridinGrid = createGridInGridPanel();

		// Add GUI Components

		c.add(jpaHeader, BorderLayout.NORTH);
		c.add(jpaColor, BorderLayout.WEST);
		c.add(jpaGrid, BorderLayout.CENTER);
		// c.add( jpaGridinGrid, BorderLayout.CENTER);
		c.add(jpaLetter, BorderLayout.EAST);

		// c.add( jpaTurn, BorderLayout.SOUTH );

		buttonAction(jpaGrid);

	}

	private static JPanel createHeaderPanel() {
		// Local Variables

		JPanel jpaHeader = new JPanel();

		JPanel jpaSOS = createSOSPanel();
		JPanel jpaGameType = createGameTypePanel(jpaHeader);
		JPanel jpaBoardSize = createBoardSizePanel();

		JLabel jlLabel = titleLabel;

		// Set layout for header panel

		jpaHeader.setLayout(new BorderLayout());

		// Instantiate the Grid Panel

		// Add GUI Components

		jpaHeader.add(jpaSOS, BorderLayout.NORTH);
		jpaHeader.add(jpaGameType, BorderLayout.CENTER);
		jpaHeader.add(jpaBoardSize, BorderLayout.EAST);
		jpaHeader.add(jlLabel, BorderLayout.WEST);
		jpaHeader.add(field, BorderLayout.EAST);
		jpaHeader.add(currentPlayer, BorderLayout.SOUTH);
		jpaHeader.add(button, BorderLayout.NORTH);

		// Return the panel

		return jpaHeader;
	}

	private static JPanel createSOSPanel() {
		// Local Variables

		JPanel jpaSOS = new JPanel();

		// Instantiate GUI components

		// Add GUI components to panel

		jpaSOS.add(titleLabel);

		// Return panel

		return jpaSOS;
	}

	private static JPanel createGameTypePanel(JPanel header) {
		JPanel jpaType = new JPanel();

		JRadioButton jrbSimple = simplebutton;
		JRadioButton jrbGen = genbutton;

		ButtonGroup bgGameType = new ButtonGroup();

		// Instantiate GUI Components

		// Add radio buttons to button group

		bgGameType.add(jrbSimple);
		bgGameType.add(jrbGen);

		// Add GUI components

		jpaType.add(jrbSimple);
		jpaType.add(jrbGen);

		header.add(jpaType);

		// Return the JPanel

		return jpaType;

	}

	private static JPanel createBoardSizePanel() {

		JPanel jpaBoard = null;
		jpaBoard = new JPanel();

		jpaBoard.setLayout(new GridLayout());

		return jpaBoard;
	}

	public static JPanel createColorPanel() {
		// Local Variables

		JPanel jpaColor = null;

		JRadioButton jrbRed = null;
		JRadioButton jrbBlue = null;

		ButtonGroup bgColor = null;

		// Instantiate GUI Components

		jpaColor = new JPanel();

		JLabel label = titleLabel;

		jrbRed = redbutton;
		jrbBlue = bluebutton;

		bgColor = new ButtonGroup();

		// Add radio buttons to button group

		bgColor.add(jrbRed);
		bgColor.add(jrbBlue);

		// Add GUI components

		jpaColor.add(jrbRed);
		jpaColor.add(jrbBlue);
		jpaColor.add(label);

		// Return the JPanel

		return jpaColor;
	}

	private static JPanel createGridPanel() {
		JPanel jpaGridPanel = null;
		jpaGridPanel = new JPanel();

		jpaGridPanel.setLayout(new GridLayout());

		return jpaGridPanel;

	}

	private static JPanel createLetterPanel() {
		// Local Variables

		JPanel jpaLetter = null;

		JRadioButton jrbS = null;
		JRadioButton jrbO = null;

		ButtonGroup bgLetter = null;

		// Instantiate GUI Components

		jpaLetter = new JPanel();

		jrbS = sbutton;
		jrbO = obutton;

		bgLetter = new ButtonGroup();

		// Add radio buttons to button group

		bgLetter.add(jrbS);
		bgLetter.add(jrbO);

		// Add GUI components

		jpaLetter.add(jrbS);
		jpaLetter.add(jrbO);

		// Return the JPanel

		return jpaLetter;
	}

	private static JPanel createTurnPanel() {
		JPanel jpaTurn = new JPanel();

		JLabel jlCurrentPlayer = null;

		jlCurrentPlayer = new JLabel(" ");

		jpaTurn.add(jlCurrentPlayer);

		return jpaTurn;
	}

	private static void updateBoard(String currentPlayerText, int index, int row, int col, String value) {

		if (isPlayerRed(redbutton, bluebutton)) {
			board[row][col] = value;
		} else {
			board[row][col] = value;
		}
		lettersEnteredCount++;
	}

	private static boolean isGameOver(int gridSize) {
		
		if(isGameSimple(simplebutton, genbutton) && sosCombinationPositionString.size() == 1) {
			return true;
		}
		else if (lettersEnteredCount == gridSize * gridSize) {
			return true;
		}
		return false; 
	}

	private static String getWordFromStack(Stack<String> stack) {
		Stack<String> newStack = (Stack<String>) stack.clone();
		String backwardsS = "";
		while (!newStack.isEmpty()) {
			backwardsS += newStack.pop();
		}
		String reversed = "";

		for (int i = 0; i < backwardsS.length(); i++) {
			reversed += backwardsS.charAt(i);

		}
		return reversed;

	}

	private static void scoreWord(String word, String posString) {

		if (word.equals("SOS") && !sosCombinationPositionString.containsKey(posString)
				&& !sosCombinationPositionString.containsKey(reversePosString(posString))) {

			if (isPlayerRed(redbutton, bluebutton)) {
				redScore++;
				System.out.println("red " + posString);
			}

			else {
				blueScore++;
				System.out.println("blue " + posString);
			}
			// TODO store the combination of positions for that SOS
			sosCombinationPositionString.put(posString, true);

		}

	}
	
	private static String reversePosString(String posString) {
		Stack<String> stack = new Stack<>();
		
		String currString = "";
		for(int i = 0; i < posString.length(); i++) {
			currString += posString.charAt(i);
			if(currString.length() == 2) {
				stack.push(currString);
				currString = "";
			}
		}
		
		String reverse = "";
		
		while(!stack.empty()) {
			reverse += stack.pop();
		}
		return reverse;
	}

	private static void updateScores(int gridSize) {

		for (int row = 0; row < gridSize; row++) {
			for (int col = 0; col < gridSize; col++) {

				String currentChar = board[row][col];
				if (currentChar != null) {
					String word = "";
					String posString = "";
					// character in middle of sos
					if (inBounds(row + 1, col, gridSize) && inBounds(row - 1, col, gridSize)) {
						word = board[row + 1][col] + currentChar + board[row - 1][col];
						posString = "" + (row + 1) + col + row + col + (row - 1) + col;
						scoreWord(word, posString);

					}

					if (inBounds(row, col - 1, gridSize) && inBounds(row, col + 1, gridSize)) {
						word = board[row][col - 1] + currentChar + board[row][col + 1];
						posString = "" + row + (col - 1) + row + col + (row) + (col + 1);
						scoreWord(word, posString);
					}

					// in the middle of left and right
					if (inBounds(row, col - 1, gridSize) && inBounds(row, col + 1, gridSize)) {
						word = board[row][col - 1] + currentChar + board[row][col + 1];
						posString = "" + row + (col - 1) + row + col + row + (col + 1);
						scoreWord(word, posString);
					}

					if (inBounds(row - 1, col - 1, gridSize) && inBounds(row + 1, col + 1, gridSize)) {
						word = board[row - 1][col - 1] + currentChar + board[row + 1][col + 1];
						posString = "" + (row - 1) + (col - 1) + row + col + (row + 1) + (col + 1);
						scoreWord(word, posString);
					}

					if (inBounds(row + 1, col + 1, gridSize) && inBounds(row - 1, col - 1, gridSize)) {
						word = board[row + 1][col + 1] + currentChar + board[row - 1][col - 1];
						posString = "" + (row + 1) + (col + 1) + row + col + (row - 1) + (col - 1);
						scoreWord(word, posString);
					}

					// on the edge cases of the grid

					// two to the right
					if (inBounds(row, col + 1, gridSize) && inBounds(row, col + 2, gridSize)) {
						word = currentChar + board[row][col + 1] + board[row][col + 2];
						posString = "" + row + col + row + (col + 1) + (row) + (col + 2);
						scoreWord(word, posString);
					}

					// two to the left
					if (inBounds(row, col - 2, gridSize) && inBounds(row, col - 1, gridSize)) {
						word = board[row][col - 2] + board[row][col - 1] + currentChar;
						posString = "" + row + (col - 2) + row + (col - 1) + row + col;
						scoreWord(word, posString);
					}

					// two below
					if (inBounds(row + 1, col, gridSize) && inBounds(row + 2, col, gridSize)) {
						word = board[row + 2][col] + board[row + 1][col] + currentChar;
						posString = "" + row + col + (row + 1) + col + (row + 2) + col;
						scoreWord(word, posString);
					}

					// two above
					if (inBounds(row - 1, col, gridSize) && inBounds(row - 2, col, gridSize)) {
						word = board[row - 1][col] + board[row - 2][col] + currentChar;
						posString = "" + (row - 2) + col + (row - 1) + col + row + col;
						scoreWord(word, posString);
					}

					// two left up diagonal
					if (inBounds(row - 1, col - 1, gridSize) && inBounds(row - 2, col - 2, gridSize)) {
						word = currentChar + board[row - 1][col - 1] + board[row - 2][col - 2];
						posString = "" + row + col + (row - 1) + (col - 1) + (row - 2) + (col - 2);
						scoreWord(word, posString);
					}

					// two left down diagonal
					if (inBounds(row + 1, col-1, gridSize) && inBounds(row + 2, col-2, gridSize)) {
						word = currentChar + board[row+1][col-1] + board[row +2][col-2]; 
						posString = "" + row + col + (row + 1) + (col-1) + (row + 2) + (col-2);
						scoreWord(word, posString);
					}

					// two right up diagonal
					if (inBounds(row - 1, col+1, gridSize) && inBounds(row - 2, col+2, gridSize)) {
						word = currentChar + board[row - 1][col+1] + board[row - 2][col+2];
						posString = "" + row + col + (row - 1) +(col+1)+ (row-2) + (col+2);
						scoreWord(word, posString);
					}

					// two right down diagonal
					if (inBounds(row +1, col + 1, gridSize) && inBounds(row + 2, col + 2, gridSize)) {
						word = currentChar + board[row + 1][col + 1] + board[row + 2][col + 2];
						posString = "" + row + col + (row+1) + (col+1) + (row+2) + (col+2);
						scoreWord(word, posString);
					}			

				}

			}

		}

	}

	private static boolean inBounds(int row, int col, int gridSize) {
		return row >= 0 && row < gridSize && col >= 0 && col < gridSize;

	}

	static void buttonAction(JPanel grid) {

		// JFrame jfrSOS = new JFrame();

		// JPanel panel_1 = new JPanel();

		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {

					int n = Integer.parseInt(field.getText());

					setGrid(grid, n);
					for (int i = 0; i < n * n; i++) {

						board = new String[n][n];

						JButton tic = new JButton(i % 2 == 0 ? " " : " ");

						final int index = i;
						int col = index % n;
						int row = index / n;

						tic.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent f) {
								if (isLetterS(sbutton, obutton)) {
									tic.setText("S");
									tic.setEnabled(false);
									updateBoard(currentPlayer.getText(), index, row, col, "S");

								} else {
									tic.setText("O");
									tic.setEnabled(false);
									updateBoard(currentPlayer.getText(), index, row, col, "O");

								}


								updateScores(n);
								if (isGameOver(n)) {
									JOptionPane.showMessageDialog(null, "Game over!      " + "blue:" + blueScore + "    red: " + redScore, null, JOptionPane.PLAIN_MESSAGE);
								}

								if (isPlayerRed(redbutton, bluebutton)) {
									currentPlayer.setText("Current player: blue, points: " + blueScore);

								}

								else {
									currentPlayer.setText("Current player: red, points: " + redScore);
								}

							}
						});

						grid.add(tic);

					}

					if (isPlayerRed(redbutton, bluebutton)) {
						currentPlayer.setText("Current player: blue ");

					}

					else {
						currentPlayer.setText("Current player: red ");
					}

					grid.revalidate();

					// frame.revalidate();
				}

				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		// setFrameAttributes( frame );

		// frame.revalidate();
	}

	// Class helper methods

	/*
	 * 
	 * private static void setFrameAttributes( JFrame myGUI ) { myGUI.setSize( 500,
	 * 500 ); myGUI.setLocationRelativeTo(null);
	 * myGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); myGUI.setVisible(true);
	 * }
	 */

	private static void setGrid(JPanel panel, int n) {

		panel.removeAll();

		panel.setLayout(new GridLayout(n, n));

	}

	private static Boolean isPlayerRed(JRadioButton red, JRadioButton blue) {
		if (red.isSelected()) {
			return true;
		}

		else {
			return false;
		}
	}

	private static Boolean isLetterS(JRadioButton s, JRadioButton o) {
		if (s.isSelected()) {
			return true;
		}

		else {
			return false;
		}
	}

	private static Boolean isGameSimple(JRadioButton simple, JRadioButton gen) {
		if (simple.isSelected()) {
			return true;
		} else {
			return false;
		}
	}

	public static JFrame createFrame(int width, int height) {

		JFrame frame = new JFrame();

		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		return frame;
	}

	public static void main(String[] args) {
		JFrame frame = createFrame(500, 500);
		buildGUI(frame.getContentPane());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/*
		 * JPanel panel = new JPanel(new BorderLayout());
		 * frame.getContentPane().add(panel); JPanel grid = new JPanel();
		 * panel.add(grid, BorderLayout.NORTH); grid.setLayout(new GridLayout(3, 3, 0,
		 * 0));
		 * 
		 * JPanel header = new JPanel(new FlowLayout()); grid.add(header);
		 * 
		 * 
		 * JPanel panel_1 = new JPanel();
		 * 
		 * 
		 * grid.add(panel_1);
		 * 
		 * 
		 */

		frame.revalidate();

	}

}
