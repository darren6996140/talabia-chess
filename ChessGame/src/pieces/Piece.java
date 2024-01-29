package pieces;
//Done by Leong Jia Yi//

import chess.Board;
import java.awt.*;

public class Piece {

	public int col, row;
	public int xPos, yPos;
	
	public boolean checkWhite;
	public String name;
	public int value;
	
	Image sprite;
	Board board;
	
	
	public Piece(Board board) {
		this.board = board;
	}
	
	public boolean checkValidMovement(int col, int row){
		return true;
	}
	
	public boolean checkCollision(int col, int row){
		return false;
	}
	
	public void paint(Graphics2D g2d) {
		g2d.drawImage(sprite, xPos, yPos, null);
	}
	
	
}
