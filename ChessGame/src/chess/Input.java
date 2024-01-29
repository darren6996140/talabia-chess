package chess;
//DONE BY LIAU KAI ZE//

import pieces.Piece;
import chess.Board;
import chess.Move;

import java.awt.event.*;

public class Input extends MouseAdapter{
	
	Board board;
	Piece piece;
	Move move;
	
	public Input(Board board) {
		this.board = board;
	}
	
	public Input(Piece piece) {
		this.piece = piece;
	}
	
	public Input(Move move) {
		this.move = move;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
		if(board.selectedPiece != null) {
			board.selectedPiece.xPos = e.getX() - board.tileSize / 2;
			board.selectedPiece.yPos = e.getY() - board.tileSize / 2;
			
			board.repaint();
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int col = e.getX() / board.tileSize;
		int row = e.getY() / board.tileSize;
		
		if(board.selectedPiece != null) {
			Move move = new Move(board, board.selectedPiece, col, row);
			
			if (board.checkValidMove(move)) {
				board.makeMove(move, piece);
			}
			
			else {
				board.selectedPiece.xPos = board.selectedPiece.col * board.tileSize;
				board.selectedPiece.yPos = board.selectedPiece.row * board.tileSize;
			}
			
		}
		
		board.selectedPiece = null;
		board.repaint();
		board.checkForWinner();
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
	    int col = e.getX() / board.tileSize;
	    int row = e.getY() / board.tileSize;

	    // Only allow black player to move on even turns
	    if (board.turn % 2 == 0 && board.activeplayer == Player.blackplayer) {
	        Piece pieceXY = board.getPiece(col, row);

	        if (pieceXY != null && pieceXY.checkWhite == true) {
	            board.selectedPiece = pieceXY;
	        }
	    } else if (board.turn % 2 != 0 && board.activeplayer == Player.whiteplayer) {
	        // Only allow white player to move on odd turns
	        Piece pieceXY = board.getPiece(col, row);
	        if (pieceXY != null && pieceXY.checkWhite == false) {
	            board.selectedPiece = pieceXY;
	        }
	        
	    }
	}

}