package pieces;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import chess.Board;

public class Time extends Piece {

    public Time(Board board, int col, int row, boolean isWhite) {
        super(board);

        this.col = col;
        this.row = row;
        this.xPos = col * board.tileSize;
        this.yPos = row * board.tileSize;

        this.isWhite = isWhite;
        this.name = "Time";

        String filename = isWhite ? "/2.png" : "/7.png";

        URL url = getClass().getResource(filename);
        if (url != null) {
            ImageIcon imageIcon = new ImageIcon(url);
            this.sprite = imageIcon.getImage();
            
        } else {
            System.out.println("Failed to load image.");
        }
    }
}