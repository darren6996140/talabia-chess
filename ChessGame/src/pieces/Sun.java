package pieces;
//Done by Liau Kai Ze//

import java.net.URL;
import javax.swing.ImageIcon;
import chess.Board;

public class Sun extends Piece {

    public Sun(Board board, int col, int row, boolean checkWhite) {
        super(board);

        this.col = col;
        this.row = row;
        this.xPos = col * board.tileSize;
        this.yPos = row * board.tileSize;

        this.checkWhite = checkWhite;
        this.name = "Sun";

        String filename = checkWhite ? "/1.png" : "/6.png";

        URL url = getClass().getResource(filename);
        if (url != null) {
            ImageIcon imageIcon = new ImageIcon(url);
            this.sprite = imageIcon.getImage();
        } else {
            System.out.println("Failed to load image.");
        }
    }
    
    public boolean checkValidMovement(int col, int row) {
    	return ((Math.abs(this.col - col) == 1 || Math.abs(this.col - col) == 0) && (Math.abs(this.row - row) == 1 || Math.abs(this.row - row) == 0)); 
    }
}