import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;


public class Tetris extends JPanel{
	
	

	private Color [][] gameBoard;
	private Pieces curPiece;
	private Timer timer;
	

	public Tetris () {
		gameBoard = new Color[16][10];
		
		for (Color[] row: gameBoard)
		    Arrays.fill(row, Color.BLACK);
		curPiece = new Pieces((int)(Math.random() * 7 + 1), 0, (gameBoard[0].length -1) / 2);
		
		repaint();
		
		ActionListener action = new ActionListener () {
			public void actionPerformed (ActionEvent evt) {
					fallingPiece();
			}
		};
		
		Listener listener = new Listener ();
		addKeyListener(listener);
		timer = new Timer (1000, action);
		timer.start();
		
	}
	
	//Event Listener for keypressed
	private class Listener implements KeyListener {
		public void keyTyped (KeyEvent evt){}
		public void keyPressed (KeyEvent evt) {
			int key = evt.getKeyCode();
			
			if (key == KeyEvent.VK_LEFT)
				moveLeft();
			else if (key == KeyEvent.VK_RIGHT)
				moveRight();
			else if (key == KeyEvent.VK_DOWN) 
				fallingPiece();
			else if (key == KeyEvent.VK_UP) {
				rotate();
			}
		}
		public void keyReleased (KeyEvent evt){}
	
}
	//Paint method for drawing the gameboard and the pieces
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		drawBoard(g);
		drawPiece(g);
		
	}
	
	//Method for creating the gameBoard. gameBoard is a 2D array of Color objects that starts out Black
	//Array values change as pieces are added to the board
	public void drawBoard(Graphics g) {
		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard[i].length; j++) {
				g.setColor(gameBoard[i][j]);
				g.fillRect(30*j, 30*i, 30, 30);
				g.setColor(Color.WHITE);
				g.drawRect(30*j, 30*i, 30, 30);
			}
		}
	}
	
	//Draws the current piece over the gameBoard
	public void drawPiece (Graphics g) {
		
		for (int i = 0; i < curPiece.pieceShape.length; i++) {
			for (int j = 0; j < curPiece.pieceShape[i].length; j++) {
				if (curPiece.pieceShape[i][j] == true) {
				g.setColor(curPiece.pieceColor);
				g.fillRect(30*(curPiece.currentCoords[1] + j), 30*(curPiece.currentCoords[0] + i), 30, 30);
				g.setColor(Color.WHITE);
				g.drawRect(30*(curPiece.currentCoords[1] + j), 30*(curPiece.currentCoords[0] + i), 30, 30);
				}
			}	
		}
		
	}
	
	//Moves the piece in the downward direction
	public void fallingPiece() {
		curPiece.futureCoords[0] = curPiece.currentCoords[0]+1;
		if (!willCollide(curPiece))
			curPiece.currentCoords[0]++;
		else {
			
			for (int i = 0; i < curPiece.pieceShape.length; i++) {
				for (int j = 0; j < curPiece.pieceShape[i].length; j++) {
					if (curPiece.pieceShape[i][j] == true)
						gameBoard[curPiece.currentCoords[0] + i][curPiece.currentCoords[1] + j] = curPiece.pieceColor;
				}	
			}
			deleteRow();
			curPiece = new Pieces((int)(Math.random() * 7 + 1), 0, (gameBoard[0].length -1) / 2);
			if (willCollide(curPiece))
				timer.stop();
		}
		repaint();
	}
	
	//Boolean method to check whether a piece will collide with another piece or with one of the bounds of the gameBoard
	public boolean willCollide (Pieces p) {
		if (p.currentCoords[0] == gameBoard.length - p.pieceShape.length)
			return true;
		
		for (int i = 0; i < p.pieceShape.length; i++) {
			for (int j = 0; j < p.pieceShape[i].length; j++) {
				if (p.pieceShape[i][j] == true && (p.futureCoords[1] + j  >= gameBoard[0].length
															|| p.futureCoords[1] + j < 0
													  		|| p.currentCoords[0] + i == gameBoard.length
													  		|| gameBoard[p.futureCoords[0] + i][p.futureCoords[1] + j] != Color.BLACK))
					return true;
			}
		}
		return false;
	}
	
	//Moves a piece to the left
	public void moveLeft () {
		curPiece.futureCoords[1] = curPiece.currentCoords[1]- 1;
		if (!willCollide(curPiece)) {
			curPiece.currentCoords[1]--;
			repaint();
		}
		else
			curPiece.futureCoords[1] = curPiece.currentCoords[1];
	}
	
	
	//Moves a piece to the right
	public void moveRight () {
		curPiece.futureCoords[1] = curPiece.currentCoords[1]+ 1;
		if (!willCollide(curPiece)) {
			curPiece.currentCoords[1]++;
			repaint();
		}
		else
			curPiece.futureCoords[1] = curPiece.currentCoords[1];
	}
	
	//Rotates a piece clockwise
	public void rotate () {
		
		Pieces rotate = new Pieces (1, curPiece.currentCoords[0], curPiece.currentCoords[1]);
		
		boolean [][] rotateSize = new boolean [curPiece.pieceShape[0].length][curPiece.pieceShape.length];
		
		rotate.pieceShape = rotateSize;
		
		for (int i = 0; i < rotate.pieceShape.length; i++) {
			for (int j = 0; j < rotate.pieceShape[i].length; j++) {
					rotate.pieceShape[i][j] = curPiece.pieceShape[j][curPiece.pieceShape[0].length - 1 - i];
			}
		}
						
		if (!willCollide(rotate)) {
			curPiece.pieceShape = rotate.pieceShape;				
			repaint();
		}
		else
			return;

		
	}
	
	//Checks if a row is full and deletes that row
	public void deleteRow () {
		
		Color [][] newBoard = new Color [16][10];
		int newRow = gameBoard.length -1;
		
		for (Color[] row: newBoard)
		    Arrays.fill(row, Color.BLACK);
		
		for (int i = gameBoard.length -1; i >= 0; i--) {
			for (int j = 0; j < gameBoard[i].length; j++) {
				if (gameBoard[i][j] == Color.BLACK) {
					newBoard[newRow] = gameBoard[i];
					newRow--;
					break;
				}					
			}
		}
		gameBoard = newBoard;
	}
	

	
	
	public static void main (String[] args) {
        JFrame window = new JFrame("Tetris");
        Tetris content = new Tetris();
        window.setContentPane( content );
        window.setSize(320,520);
        window.setLocation(100,100);
        window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        window.setVisible(true);
        content.requestFocusInWindow();
	}
	
}