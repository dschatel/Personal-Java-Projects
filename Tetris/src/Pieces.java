import java.awt.*;


public class Pieces {

	public boolean [][] pieceShape;
	public int [] currentCoords;
	public Color pieceColor;
	public int [] futureCoords;
	
	//Constructor accepts random integer input that will return a specified shape in the form of true/false array values
	public Pieces (int randomPiece, int startRow, int startCol ) {
		currentCoords = new int [] {startRow, startCol};
		futureCoords = new int [] {startRow, startCol};
		switch (randomPiece) {
		
		case 1:		pieceShape = new boolean [][] { {true, true, true},
											      {false, false, true} };
					pieceColor = Color.BLUE;
					break;
		case 2:		pieceShape = new boolean [][] { {true, true, true, true} };
		      		pieceColor = Color.CYAN;
		      		break;
		case 3:		pieceShape = new boolean [][] { {true, true, true},
													{true, false, false} };
					pieceColor = Color.ORANGE;
					break;
		case 4:		pieceShape = new boolean [][] { {true, true},
													{true, true} };
					pieceColor = Color.YELLOW;
					break;
		case 5:		pieceShape = new boolean [][] { {false, true, true},
													{true, true, false} };
					pieceColor = Color.GREEN;
					break;
		case 6:		pieceShape = new boolean [][] { {true, true, false},
													{false, true, true} };
					pieceColor = Color.RED;
					break;
		case 7:		pieceShape = new boolean [][] { {true, true, true},
													{false, true, false} };
					pieceColor = Color.MAGENTA;
					break;
		}
	}
}