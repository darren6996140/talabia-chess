package chess;

//import all pieces
import pieces.Hourglass;
import pieces.Piece;
import pieces.Plus;
import pieces.Point;
import pieces.Sun;
import pieces.Time;

//import necessary tools
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class Board extends JPanel {

	//declaration of public variables
	public int tileSize = 85;
	public static int round = 1;
	public static int turn = 0;
	public static boolean isBoardFlipped = false;

	//set default starting player
	public static Player activeplayer = Player.whiteplayer;

	//set board dimensions
	int cols = 7;
	public static int rows = 6;

	//initialise ArrayList
	static ArrayList<Piece> piecelist = new ArrayList<Piece>();

	public Piece selectedPiece;

	Input input = new Input(this);

	//function to initialise starting game //Done by: Leong Jia Yi
	public static void createGame() throws IOException {

		//frame that the program will display in
		JFrame startGameFrame = new JFrame("Talabia Chess");
		startGameFrame.setSize(550, 500);

		//text that will display when program started
		JLabel startGameLabel = new JLabel("Welcome to Talabia Chess!");
		startGameLabel.setBounds(190, 150, 200, 50);
		startGameFrame.add(startGameLabel);

		//button that will start the game
		JButton startButton = new JButton("Start Game");
		startButton.setBounds(195, 200, 150, 40);

		//button that loads previously saved games
		JButton loadButton = new JButton("Load Game");
		loadButton.setBounds(195, 250, 150, 40);

		//to detect if user clicked on the "Start Game" button
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gamestart();

				// Close the startGameFrameds
				startGameFrame.dispose();
			}
		});

		//to detect if user clicked on the "Load Game" button
		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String fileName = JOptionPane.showInputDialog(startGameFrame, "Enter file name:");
				if (fileName != null && !fileName.trim().isEmpty()) {
					// Assuming 'board' is the current instance of the Board class
					Board board = new Board();
					gamestart();
					board.loadGame(fileName);
				} else {
					// Handle invalid or empty file name
					JOptionPane.showMessageDialog(startGameFrame, "Invalid file name. Please try again.",
							"Load Game Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		//display all buttons and frames
		startGameFrame.setLayout(null);
		startGameFrame.add(startButton);
		startGameFrame.add(loadButton);
		startGameFrame.setVisible(true);
	}

	//function to start game mechanics //Done by: Leong Jia Yi
	public static void gamestart() {
		JFrame frame = new JFrame();
		frame.setTitle("Talabia Chess");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setLayout(new BorderLayout());

		// Creating and adding JMenuBar to the frame
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		//adds topnav when in game
		JMenu fileMenu = new JMenu("Main Menu");
		menuBar.add(fileMenu);

		JMenuItem saveMenuItem = new JMenuItem("Save Game");
		JMenuItem exitMenuItem = new JMenuItem("Exit");

		//to detect when user clicks on "Save Game" button //Done By Lai Cheng Yung
		saveMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Get the file name from the user
				String fileName = JOptionPane.showInputDialog(frame, "Enter file name:");

				if (fileName != null && !fileName.trim().isEmpty()) {
					try {
						// Create a FileWriter to write the game state to a file
						FileWriter writer = new FileWriter(fileName + ".txt");

						// Iterate through the piecelist and write the information of each piece to the file
						for (Piece piece : piecelist) {
							// Use a specific format to represent each piece's information
							String pieceInfo = String.format("%s %d %d %b%n",
									piece.getClass().getSimpleName(), piece.col, piece.row, piece.checkWhite);
							// Write the piece information to the file
							writer.write(pieceInfo);
						}

						// Close the FileWriter to save changes
						writer.close();

						// Display a success message to the user
						JOptionPane.showMessageDialog(frame, "Game saved successfully!", "Save Game",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (IOException ex) {
						// Handle any IO exceptions, such as file not found or permission issues
						ex.printStackTrace();
						JOptionPane.showMessageDialog(frame, "Error saving the game.", "Save Game Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					// Display an error message if the file name is empty or canceled
					JOptionPane.showMessageDialog(frame, "Invalid file name. Please try again.", "Save Game Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		//to detect when user clicks on "Exit Game" button
		exitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		fileMenu.add(saveMenuItem);
		fileMenu.add(exitMenuItem);

		// Creating and adding Board to the frame
		Board board = new Board();
		frame.add(board, BorderLayout.CENTER);

		frame.setSize(new Dimension(650, 600));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public Board() {
		this.setPreferredSize(new Dimension(cols * tileSize, rows * tileSize));
		this.setBackground(Color.green);

		this.addMouseListener(input);
		this.addMouseMotionListener(input);

		addPieces();
	}

	// YELLOW == WHITE
	// BLUE == BLACK

	//add pieces to the board to their default starting positions //Done by: Lai Cheng Yung
	public void addPieces() {
		// black
		piecelist.add(new Plus(this, 0, 0, false));
		piecelist.add(new Hourglass(this, 1, 0, false));
		piecelist.add(new Time(this, 2, 0, false));

		piecelist.add(new Sun(this, 3, 0, false));

		piecelist.add(new Time(this, 4, 0, false));
		piecelist.add(new Hourglass(this, 5, 0, false));
		piecelist.add(new Plus(this, 6, 0, false));

		piecelist.add(new Point(this, 0, 1, false));
		piecelist.add(new Point(this, 1, 1, false));
		piecelist.add(new Point(this, 2, 1, false));
		piecelist.add(new Point(this, 3, 1, false));
		piecelist.add(new Point(this, 4, 1, false));
		piecelist.add(new Point(this, 5, 1, false));
		piecelist.add(new Point(this, 6, 1, false));

		// white
		piecelist.add(new Point(this, 0, 4, true));
		piecelist.add(new Point(this, 1, 4, true));
		piecelist.add(new Point(this, 2, 4, true));
		piecelist.add(new Point(this, 3, 4, true));
		piecelist.add(new Point(this, 4, 4, true));
		piecelist.add(new Point(this, 5, 4, true));
		piecelist.add(new Point(this, 6, 4, true));

		piecelist.add(new Plus(this, 0, 5, true));
		piecelist.add(new Hourglass(this, 1, 5, true));
		piecelist.add(new Time(this, 2, 5, true));

		piecelist.add(new Sun(this, 3, 5, true));

		piecelist.add(new Time(this, 4, 5, true));
		piecelist.add(new Hourglass(this, 5, 5, true));
		piecelist.add(new Plus(this, 6, 5, true));
	}

	//returns selected piece //Done by: Lai Cheng Yung
	public static Piece getPiece(int col, int row) {

		for (Piece piece : piecelist) {
			if (piece.col == col && piece.row == row) {
				return piece;
			}
		}

		return null;
	}

	//check whether 2 pieces is same team //Done by: Lai Cheng Yung
	public boolean sameTeam(Piece piece1, Piece piece2) {

		if (piece1 == null || piece2 == null) {
			return false;
		}

		return piece1.checkWhite == piece2.checkWhite;
	}

	//check whether intended move is a valid move //Done by: Leong Jia Yi
	public boolean checkValidMove(Move move) {

		//cannot capture if same team
		if (sameTeam(move.piece, move.capture)) {
			return false;
		}

		//cannot capture if movement is invalid according to a certain piece
		if (!move.piece.checkValidMovement(move.newCol, move.newRow)) {
			return false;
		}

		//cannot capture if blocked by a piece of the same team
		if (move.piece.checkCollision(move.newCol, move.newRow)) {
			return false;
		}

		//else then able to capture
		return true;
	}

	//make moves with selected piece //Done by: Leong Jia Yi
	public void makeMove(Move move, Piece piece) {
		move.piece.col = move.newCol;
		move.piece.row = move.newRow;
		move.piece.xPos = move.newCol * tileSize;
		move.piece.yPos = move.newRow * tileSize;

		//capture a piece if needed to
		capture(move);

		updateActivePlayer(piece);
		input.mousePressed(null);
	}

	//change active player //Done by: Leong Jia Yi
	public void updateActivePlayer(Piece piece) {
		// Toggle the active player
		if (activeplayer == Player.whiteplayer) {
			activeplayer = Player.blackplayer;
		} else {
			activeplayer = Player.whiteplayer;
		}

		turn++;

		// Swap pieces every 2 turns
		if (turn % 2 == 0) {
			swapPieces();
		}
		rotateBoard();

	}

	//for board rotation //Done by: Lam Yuet Xin
	public void rotateBoard() {
		for (Piece piece : piecelist) {
			// Rotating the board by flipping the piece positions
			piece.col = cols - 1 - piece.col;
			piece.row = rows - 1 - piece.row;

			// Update the graphical position of the piece
			piece.xPos = piece.col * tileSize;
			piece.yPos = piece.row * tileSize;

		}

		// Trigger a repaint to reflect changes on the board
		repaint();
		isBoardFlipped = !isBoardFlipped;

		for (Piece piece : piecelist) {
			if (piece instanceof Point) {
				((Point) piece).updateImage();
			}
		}
	}

	//to capture a piece //Done by: Leong Jia Yi
	public void capture(Move move) {
		piecelist.remove(move.capture);
	}

	//to swap the Time piece and the Plus piece and vice versa //Done by: Leong Jia Yi
	public void swapPieces() {
		// Temporary list to hold the new swapped pieces
		ArrayList<Piece> newPieces = new ArrayList<>();

		for (Piece piece : piecelist) {
			if (piece instanceof Time) {
				newPieces.add(new Plus(this, piece.col, piece.row, piece.checkWhite));
			} else if (piece instanceof Plus) {
				newPieces.add(new Time(this, piece.col, piece.row, piece.checkWhite));
			} else {
				newPieces.add(piece); // Add the piece as is if it's not Time or Plus
			}
		}

		// Replace the old piece list with the new one
		piecelist = newPieces;
	}

	//Done by: Lai Cheng Yung
	public void loadGame(String fileName) {
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName + ".txt"))) {
			piecelist.clear(); // Clear existing pieces
			String line;

			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(" ");
				if (parts.length != 4) {
					continue;
				}

				String pieceType = parts[0];
				int col = Integer.parseInt(parts[1]);
				int row = Integer.parseInt(parts[2]);
				boolean isWhite = Boolean.parseBoolean(parts[3]);

				Piece piece = createPiece(this, pieceType, col, row, isWhite); // Pass 'this' as the board reference
				piecelist.add(piece);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Done by: Leong Jia Yi
	private static Piece createPiece(Board board, String pieceType, int col, int row, boolean isWhite) {
		// Create a piece based on the type
		switch (pieceType) {
			case "Time":
				return new Time(board, col, row, isWhite);
			case "Hourglass":
				return new Hourglass(board, col, row, isWhite);
			case "Plus":
				return new Plus(board, col, row, isWhite);
			case "Sun":
				return new Sun(board, col, row, isWhite);
			case "Point":
				return new Point(board, col, row, isWhite);
			// Add cases for any other piece types you have
			default:
				throw new IllegalArgumentException("Unknown piece type: " + pieceType);
		}
	}

	//Done by: Leong Jia Yi
	private static void addPieceToBoard(Piece piece) {
		// Implement logic to add the piece to the board
		// For example, add the piece to an ArrayList of pieces
		piecelist.add(piece);
	}

	//to check winner of the game when the Sun piece is captured //Done by: Lam Yuet Xin
	public void checkForWinner() {
		boolean wKingExists = false;
		boolean bKingExists = false;

		//to detect presence of Sun piece from both teams
		for (Piece piece : piecelist) {
			if (piece instanceof Sun) {
				if (piece.checkWhite == true) {
					wKingExists = true;
				} else if (piece.checkWhite == false) {
					bKingExists = true;
				}
			}
		}

		if (!bKingExists) {
			displayWinner("Yellow");
		} else if (!wKingExists) {
			displayWinner("Blue");
		}
	}

	//to display winner //Done by: Lam Yuet Xin
	public void displayWinner(String winner) {

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//frame to display colour of winner
		JDialog dialog = new JDialog(frame, "Game Over", true);
		dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));

		JLabel label = new JLabel("Game Over! \n" + winner + " wins! \n\n");
		label.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		//button to exit game
		JButton exitButton = new JButton("Exit Game");
		exitButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		//button to restart game
		JButton restartButton = new JButton("Restart Game");
		restartButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		//to detect when user clicks on "Exit Game" button
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(
						null,
						"Are you sure you want to exit the game?",
						"Confirm Exit",
						JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION) {
					System.exit(0);
				}

				dialog.dispose();
			}
		});

		//to detect when user clicks on "Restart Game" button
		restartButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				restartGame();
				dialog.dispose();
			}
		});

		dialog.add(Box.createRigidArea(new Dimension(0, 10)));
		dialog.add(label);
		dialog.add(Box.createRigidArea(new Dimension(0, 10)));
		dialog.add(restartButton);
		dialog.add(Box.createRigidArea(new Dimension(0, 5)));
		dialog.add(exitButton);

		dialog.setSize(300, 150);
		dialog.setLocationRelativeTo(frame);
		// Center the dialog on the screen
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}

	//to restart game //Done by: Lam Yuet Xin
	public void restartGame() {
		// Clear the list of pieces
		piecelist.clear();

		// Add pieces again to restart the game
		addPieces();

		// Reset active player to white
		activeplayer = Player.whiteplayer;

		// Reset round to 1
		round = 1;

		// Reset turn to 1
		turn = 1;

		// Reset rotation status
		isBoardFlipped = false;

		// Repaint the board
		repaint();
	}

	//Done by: Leong Jia Yi
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		// painting board
		for (int r = 0; r < rows; r++)
			for (int c = 0; c < cols; c++) {
				g2d.setColor((c + r) % 2 == 0 ? Color.GRAY : new Color(157, 105, 53));
				g2d.fillRect(c * tileSize, r * tileSize, tileSize, tileSize);
			}

		// painting selected piece highlights
		if (selectedPiece != null)
			for (int r = 0; r < rows; r++)
				for (int c = 0; c < cols; c++) {

					if (checkValidMove(new Move(this, selectedPiece, c, r))) {
						g2d.setColor(new Color(128, 255, 128, 170));
						g2d.fillRect(c * tileSize, r * tileSize, tileSize, tileSize);

					}

				}

		// painting pieces
		for (Piece piece : piecelist) {
			piece.paint(g2d);
		}
	}

	public static void main(String[] args) throws IOException {
		createGame();
	}
}
