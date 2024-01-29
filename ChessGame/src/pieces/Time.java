package pieces;
//Done by Liau Kai Ze//

import java.net.URL;
import javax.swing.ImageIcon;
import chess.Board;

public class Time extends Piece {

    public Time(Board board, int col, int row, boolean checkWhite) {
        super(board);

        this.col = col;
        this.row = row;
        this.xPos = col * board.tileSize;
        this.yPos = row * board.tileSize;

        this.checkWhite = checkWhite;
        this.name = "Time";

        String filename = checkWhite ? "/2.png" : "/7.png";

        URL url = getClass().getResource(filename);
        if (url != null) {
            ImageIcon imageIcon = new ImageIcon(url);
            this.sprite = imageIcon.getImage();
            
        } else {
            System.out.println("Failed to load image.");
        }
    }
    
    public boolean checkValidMovement(int col, int row) {
    	return Math.abs(this.col - col) == Math.abs(this.row - row);
    }
    
    public boolean checkCollision(int col, int row) {
    	
    	//northwest
    	if(this.col > col && this.row > row)
    		for (int i = 1; i < Math.abs(this.col - col); i++)
    			if(board.getPiece(this.col - i, this.row - i) != null)
    				return true;
    	
    	//northeast
    	if(this.col < col && this.row > row)
    		for (int i = 1; i < Math.abs(this.col - col); i++)
    			if(board.getPiece(this.col + i, this.row - i) != null)
    				return true;
    	
    	//southwest
    	if(this.col > col && this.row < row)
    		for (int i = 1; i < Math.abs(this.col - col); i++)
    			if(board.getPiece(this.col - i, this.row + i) != null)
    				return true;
    	
    	//southeast
    	if(this.col < col && this.row < row)
    		for (int i = 1; i < Math.abs(this.col - col); i++)
    			if(board.getPiece(this.col + i, this.row + i) != null)
    				return true;
    	
    	return false;
    }
    
}