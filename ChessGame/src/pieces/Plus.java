package pieces;
//Done by Liau Kai Ze//

import java.net.URL;
import javax.swing.ImageIcon;
import chess.Board;

public class Plus extends Piece {

    public Plus(Board board, int col, int row, boolean checkWhite) {
        super(board);

        this.col = col;
        this.row = row;
        this.xPos = col * board.tileSize;
        this.yPos = row * board.tileSize;

        this.checkWhite = checkWhite;
        this.name = "Plus";

        String filename = checkWhite ? "/4.png" : "/9.png";

        URL url = getClass().getResource(filename);
        if (url != null) {
            ImageIcon imageIcon = new ImageIcon(url);
            this.sprite = imageIcon.getImage();
        } else {
            System.out.println("Failed to load image.");
        }
    }
    
    public boolean checkValidMovement(int col, int row) {
    	return this.col	== col || this.row == row;
    }
    
    
    public boolean checkCollision(int col, int row) {
    	
    	//up
    	if(this.row > row) 
    		for (int i = this.row - 1; i > row; i--)
    			if (board.getPiece(this.col, i) != null)
    				return true; 
    	
    	//down
    	if(this.row < row) 
    		for (int i = this.row + 1; i < row; i++)
    			if (board.getPiece(this.col, i) != null)
    				return true;
    	
    	//left
    	if(this.col > col) 
    		for (int i = this.col - 1; i > col; i--)
    			if (board.getPiece(i, this.row) != null)
    				return true; 
    	
    	//right
    	if(this.col < col) 
    		for (int i = this.col + 1; i < col; i++)
    			if (board.getPiece(i, this.row) != null)
    				return true;
    	
    	return false;
    }
    
}