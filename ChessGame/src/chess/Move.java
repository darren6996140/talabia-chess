package chess;
//DONE BY LIAU KAI ZE//

import java.io.IOException;

import pieces.Piece;

public class Move {
	
	int lastCol;
	int lastRow;
	int newCol;
	int newRow;
	
	Piece piece;
	Piece capture;
	
	public Move(Board board, Piece piece, int newCol, int newRow){
		this.lastCol = piece.col;
		this.lastRow = piece.row;
		this.newCol = newCol;
		this.newRow = newRow;
		
		this.piece = piece;
		this.capture = board.getPiece(newCol, newRow);
		
		
	}
}