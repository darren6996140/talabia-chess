package pieces;
//Done by Liau Kai Ze//

import java.net.URL;
import javax.swing.ImageIcon;
import chess.Board;

public class Hourglass extends Piece {

    public Hourglass(Board board, int col, int row, boolean checkWhite) {
        super(board);

        this.col = col;
        this.row = row;
        this.xPos = col * board.tileSize;
        this.yPos = row * board.tileSize;

        this.checkWhite = checkWhite;
        this.name = "Hourglass";

        String filename = checkWhite ? "/3.png" : "/8.png";
        URL url = getClass().getResource(filename);
        if (url != null) {
            ImageIcon imageIcon = new ImageIcon(url);
            this.sprite = imageIcon.getImage();
        } else {
            System.out.println("Failed to load image.");
        }
    }
    
    public boolean checkValidMovement(int col, int row) {
    	return Math.abs(col - this.col) * Math.abs(row - this.row) == 2;
    }
    
}