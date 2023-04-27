package gui;
 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
 
import javax.swing.*;

class Spot {
	
	private int player = 1;
	private char piece = ' ';
	
	public Spot() {
		
	}
	
	public void setPlayer(int player) {
		this.player = player;
	}
	
	public void setPiece(char piece) {
		this.piece = piece;
	}
	
	public int getPlayer() {
		return player;
	}
	
	public char getPiece() {
		return piece;
	}
}

class State {
	
	public static final int ACTIVE = 0;
	public static final int PLAYER_1_WINS = 1;
	public static final int PLAYER_2_WINS = 2;
	public static final int DRAW = 3;
	
	private Spot[][] board;
	private int player = 1;
	
	public State(int size) {
		
		board = new Spot[size][size];
		
		for(int row = 0; row < size; row++) {
			for(int col = 0; col < size; col++) {
				board[row][col] = new Spot();
			}
		}
	}
	
	public Spot[][] getBoard() {
		return board;
	}
	
	public int getPlayer() {
		return player;
	}
	
	public int getSize() {
		return board.length;
	}
	
	public ArrayList<ArrayList<Integer>> getOpenSpots() {
		
		ArrayList<ArrayList<Integer>> openSpots = new ArrayList<ArrayList<Integer>>();
		
		for(int row = 0; row < getSize(); row++) {
			
			for(int col = 0; col < getSize(); col++) {
				
				if(board[row][col].getPiece() == ' ') {
					
					ArrayList<Integer> openSpot = new ArrayList<Integer>();
					
					openSpot.add(row);
					openSpot.add(col);
					
					openSpots.add(openSpot);
				}
			}
		}
		
		return openSpots;
	}
	
	public boolean isFull() {
		return getOpenSpots().size() == 0;
	}

	public boolean move(int row, int col, char piece) {
		
		if(row < 0 || row >= getSize() ||
				col < 0 || col >= getSize() ||
				board[row][col].getPiece() != ' ' ||
				player < 1 || player > 2 ||
				(piece != 'O' && piece != 'S')) {

			return false;
		}
		
		board[row][col].setPlayer(player);
		board[row][col].setPiece(piece);
		
		player = player == 1 ? 2 : 1;
		
		return true;
	}
	
	private boolean getSOSCheckSpot(int player, int row, int col) {
		
		if(row < 0 || row >= getSize() || col < 0 || col >= getSize())
			return false;
		
		return board[row][col].getPlayer() == player &&
			board[row][col].getPiece() == 'S';
	}
	
	private int getSOSCheck(int player, int row, int col) {
		
		int count = 0;
		
		for(int i = -1; i <= 1; i++) {
			
			if(getSOSCheckSpot(player, row - 1, col + i) &&
				getSOSCheckSpot(player, row + 1, col - i)) {
				
				count += 1;
			}
		}
		
		if(getSOSCheckSpot(player, row, col - 1) &&
			getSOSCheckSpot(player, row, col + 1)) {
			
			count += 1;
		}
		
		return count;
	}
	
	public int getSOS(int player) {
		
		int count = 0;
		
		for(int row = 0; row < getSize(); row++) {
			
			for(int col = 0; col < getSize(); col++) {
				
				if(board[row][col].getPiece() == 'O' &&
					board[row][col].getPlayer() == player) {
					
					count += getSOSCheck(player, row, col);
				}
			}
		}
		
		return count;
	}
	
	public int getState(boolean isSimple) {
		
		boolean full = isFull();
		
		if(!isSimple && !full)
			return ACTIVE;
		
		int player1 = getSOS(1);
		int player2 = getSOS(2);
		
		if(player1 > player2)
			return PLAYER_1_WINS;
		
		if(player1 < player2)
			return PLAYER_2_WINS;
		
		return isSimple ? (full ? DRAW : ACTIVE) : DRAW;
	}
}

class Move {
	
	private ArrayList<Integer> spot;
	private char piece;
	
	public Move(ArrayList<Integer> spot, char piece) {
		this.spot = spot;
		this.piece = piece;
	}
	
	public ArrayList<Integer> getSpot() {
		return spot;
	}
	
	public char getPiece() {
		return piece;
	}
}

class AI {
	
	public AI() {
		
	}
	
	public static Move getBestMove(State state) {
		
		ArrayList<ArrayList<Integer>> options = state.getOpenSpots();
		
		if(options.size() == 0)
			return null;
		
		ArrayList<Integer> spot = options.get((int) Math.floor(Math.random() * options.size()));
		char piece = Math.random() < .5 ? 'O' : 'S';
		
		// STUB - BEGIN
		
		return new Move(spot, piece);
	}
}

public class GUI {
	// Class Variables
	static JButton button = new JButton("Submit");
	static JRadioButton sbutton = new JRadioButton("S");
	static JRadioButton obutton = new JRadioButton("O");
	static JRadioButton simplebutton = new JRadioButton("Simple game");
	static JRadioButton genbutton = new JRadioButton("General game");
	static JTextField field = new JTextField("", 3);
	static JLabel currentPlayer = new JLabel("Current player");
	static JLabel titleLabel = new JLabel("SOS Game");
	static int redScore = 0;
	static int blueScore = 0;
	static JButton resetbutton = new JButton("Reset");
	static JRadioButton aiOn = new JRadioButton("AI Player One On");
	static JRadioButton aiOff = new JRadioButton("AI Player One Off");
	static JRadioButton ai2On = new JRadioButton("AI Player Two On");
	static JRadioButton ai2Off = new JRadioButton("AI Player Two Off");
 
 
	static State board;
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
		c.add(jpaGrid, BorderLayout.CENTER);
		// c.add( jpaGridinGrid, BorderLayout.CENTER);
		c.add(jpaLetter, BorderLayout.EAST);
		c.add(createAIPanel(), BorderLayout.WEST);
		c.add(createAI2Panel(), BorderLayout.SOUTH);
		
 
		// c.add( jpaTurn, BorderLayout.SOUTH );
 
		buttonAction(jpaGrid);
		
		simplebutton.setSelected(true);
		sbutton.setSelected(true);
		aiOff.setSelected(true);
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
 
		return jpaLetter;
	}
	 
	private static JPanel createAIPanel() {
 
		JPanel jpAI = new JPanel();
 
		ButtonGroup bgAI = new ButtonGroup();
 
		// Add radio buttons to button group
 
		bgAI.add(aiOn);
		bgAI.add(aiOff);
 
		// Add GUI components
 
		jpAI.add(aiOn);
		jpAI.add(aiOff);
 
		// Instantiate GUI Components
 
		return jpAI;
	}
	
	private static JPanel createAI2Panel() {
		 
		JPanel jpAI2 = new JPanel();
 
		ButtonGroup bgAI2 = new ButtonGroup();
 
		// Add radio buttons to button group
 
		bgAI2.add(ai2On);
		bgAI2.add(ai2Off);
 
		// Add GUI components
 
		jpAI2.add(ai2On);
		jpAI2.add(ai2Off);
 
		// Instantiate GUI Components
 
		return jpAI2;
	}
	
	static void makeMove(int row, int col, char piece, JPanel grid, JButton tic) {
		
		boolean valid = board.move(row, col, piece);
		
		if(!valid)
			return;
		
		System.out.println(row + " - " + col);
		
		tic.setText((board.getPlayer() == 1 ? "Blue - " : "Red - ") + piece);
		tic.setBackground(board.getPlayer() == 1 ? Color.CYAN : Color.RED);
		
		currentPlayer.setText(
			"Current player: " +
			(board.getPlayer() == 1 ? "Red" : "Blue")
		);
		
		int state = board.getState(isGameSimple());
		
		if(state == State.PLAYER_1_WINS)
			JOptionPane.showMessageDialog(grid, "Red won!");
		
		if(state == State.PLAYER_2_WINS)
			JOptionPane.showMessageDialog(grid, "Blue won!");
		
		if(state == State.DRAW)
			JOptionPane.showMessageDialog(grid, "It's a draw!");
	}
 
	static void buttonAction(JPanel grid) {
 
		button.addActionListener(new ActionListener() {
 
			public void actionPerformed(ActionEvent e) {
 
				try {
 
					int n = Integer.parseInt(field.getText());
 
					setGrid(grid, n);
					currentPlayer.setText("Current player: Red");
					sbutton.setSelected(true);
					
					board = new State(n);
					
					JButton[][] buttons = new JButton[n][n];
					
					for (int i = 0; i < n * n; i++) {
 
						JButton tic = new JButton();
 
						final int index = i;
						
						int col = index % n;
						int row = index / n;
						
						buttons[row][col] = tic;
 
						tic.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								
								makeMove(row, col, getLetter(), grid, tic);
								
								if(isAIOn()) {
									
									Move move = AI.getBestMove(board);
									
									if(move != null) {
										
										makeMove(
												move.getSpot().get(0),
												move.getSpot().get(1),
												move.getPiece(),
												grid,
												buttons[move.getSpot().get(0)][move.getSpot().get(1)]);
									}
								}						
								if(isAI2On()) {
									Move move = AI.getBestMove(board);
									
									if(move != null) {
										
										makeMove(
												move.getSpot().get(0),
												move.getSpot().get(1),
												move.getPiece(),
												grid,
												buttons[move.getSpot().get(0)][move.getSpot().get(1)]);
									}
									
								}
							}							
						});
 
						grid.add(tic);
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
 
	private static char getLetter() {
		return sbutton.isSelected() ? 'S' : 'O';
	}
 
	private static Boolean isGameSimple() {
		return simplebutton.isSelected();
	}
	 
	private static Boolean isAIOn() {
		return aiOn.isSelected();
	}
	
	private static Boolean isAI2On() { 
		return ai2On.isSelected(); 
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
