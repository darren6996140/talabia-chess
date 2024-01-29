package pieces;
//Done by Liau Kai Ze//

import java.net.URL;
import javax.swing.ImageIcon;
import chess.Board;

public class Point extends Piece {
	protected boolean isMovingBackward = false; // Flag to indicate if the piece is moving backward
    public Point(Board board, int col, int row, boolean checkWhite) {
        super(board);

        this.col = col;
        this.row = row;
        this.xPos = col * board.tileSize;
        this.yPos = row * board.tileSize;

        this.checkWhite = checkWhite;
        this.name = "Point";

        String filename = checkWhite ? "/5.png" : "/10.png";

        URL url = getClass().getResource(filename);
        if (url != null) {
            ImageIcon imageIcon = new ImageIcon(url);
            this.sprite = imageIcon.getImage();
        } else {
            System.out.println("Failed to load image.");
        }
    }


    public boolean checkValidMovement(int col, int row) {
        // Determine the basic movement direction based on the piece's color
        final int numRows = 6; // Assuming a 6x7 board

        int basicDirection = checkWhite ? -1 : 1;

        boolean atWhiteEnd = checkWhite && this.row == numRows - 1;
        boolean atBlackEnd = !checkWhite && this.row == 0;
        int direction = Board.isBoardFlipped ? -basicDirection : basicDirection;


        // Check if the piece has reached the end of the board
        if (atWhiteEnd || atBlackEnd) {
            basicDirection *= -1;
            isMovingBackward = true;
        }
        boolean moveBackWardsOneTile = isMovingBackward && this.col == col && row == this.row - direction;
        boolean moveBackWardsTwoTiles =isMovingBackward && this.col == col && row == this.row - 2 * direction
                               && !checkCollision(this.col, this.row - direction)
                               && !checkCollision(this.col, this.row - 2 * direction);

        // Adjust the direction if the board is flipped

        // The Point moves straight forward by one or two rows
        boolean moveOneTile =!isMovingBackward && this.col == col && row == this.row + direction;
        boolean moveTwoTiles =!isMovingBackward && this.col == col && row == this.row + 2 * direction
                && !checkCollision(this.col, this.row + direction)
                && !checkCollision(this.col, this.row + 2 * direction);

        return moveOneTile || moveTwoTiles || moveBackWardsOneTile || moveBackWardsTwoTiles;
    }


    // Additional methods like checkCollision would need to be implemented or adjusted accordingly

    public boolean checkCollision(int col, int row) {
        if (this.row > row)
            for (int i = this.row - 1; i > row; i--)
                if (board.getPiece(this.col, i) != null)
                    return true;

        if (this.row < row)
            for (int i = this.row + 1; i < row; i++)
                if (board.getPiece(this.col, i) != null)
                    return true;

        return false;
    }
    
    public void updateImage() {
        String filename;
        if (Board.isBoardFlipped) {
            // Set filename for the flipped image
            filename = checkWhite ? "/13.png" : "/12.png"; // Adjust these filenames as needed
        } else {
            // Set filename for the normal image
            filename = checkWhite ? "/5.png" : "/10.png";
        }

        URL url = getClass().getResource(filename);
        if (url != null) {
            ImageIcon imageIcon = new ImageIcon(url);
            this.sprite = imageIcon.getImage();
        } else {
            System.out.println("Failed to load image: " + filename);
        }
    }
}