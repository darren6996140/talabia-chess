package pieces;

import java.awt.Image;

import chess.Board;
import java.awt.*;



public class Piece {

	public int col , row ;
	public int xPos , yPos ;
	
	public boolean isWhite;
	public String name;
	public int value;
	
	
	
	
	Image sprite;
	
	Board board;
	
	
	public Piece(Board board) {
		
		this.board = board ;
		
	}
	
	public void paint(Graphics2D g2d) {
		g2d.drawImage(sprite, xPos, yPos, null);
		
	}
	
	
}
