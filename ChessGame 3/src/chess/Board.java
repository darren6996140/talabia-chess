package chess;

import javax.swing.JPanel;

import pieces.Hourglass;
//import pieces.Knight;
import pieces.Piece;
import pieces.Plus;
import pieces.Point;
import pieces.Sun;
import pieces.Time;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.*;

public class Board extends JPanel{

	public int tileSize = 85;
	
	int cols = 7;
	int rows = 6;
	
	ArrayList<Piece> piecelist = new ArrayList();
	
	Board(){
		this.setPreferredSize(new Dimension(cols * tileSize , rows * tileSize));
		this.setBackground(Color.green);
		addPieces();
	}
	
	public void addPieces() {
		piecelist.add(new Plus(this, 0 , 0 , false));
		piecelist.add(new Hourglass(this, 1 , 0 , false));
		piecelist.add(new Time(this, 2 , 0 , false));
		piecelist.add(new Sun(this, 3 , 0 , false));
		
		piecelist.add(new Plus(this, 6 , 0 , false));
		piecelist.add(new Hourglass(this, 5 , 0 , false));
		piecelist.add(new Time(this, 4 , 0 , false));
		
		
		
		
		
		piecelist.add(new Point(this, 0 , 1 , false));
		piecelist.add(new Point(this, 1 , 1 , false));
		piecelist.add(new Point(this, 2 , 1 , false));
		piecelist.add(new Point(this, 3 , 1 , false));
		piecelist.add(new Point(this, 4 , 1 , false));
		piecelist.add(new Point(this, 5 , 1 , false));
		piecelist.add(new Point(this, 6 , 1 , false));
		
		
		piecelist.add(new Point(this, 0 , 4 , true));
		piecelist.add(new Point(this, 1 , 4 , true));
		piecelist.add(new Point(this, 2 , 4 , true));
		piecelist.add(new Point(this, 3 , 4 , true));
		piecelist.add(new Point(this, 4 , 4 , true));
		piecelist.add(new Point(this, 5 , 4 , true));
		piecelist.add(new Point(this, 6 , 4 , true));
		
		
		
		piecelist.add(new Plus(this, 0 , 5 , true));
		piecelist.add(new Hourglass(this, 1 , 5 , true));
		piecelist.add(new Time(this, 2 , 5 , true));
		piecelist.add(new Sun(this, 3 , 5 , true));
		
		
		
		
		piecelist.add(new Hourglass(this, 5 , 5 , true));
		piecelist.add(new Plus(this, 6 , 5 , true));
		piecelist.add(new Hourglass(this, 5 , 5 , true));
		piecelist.add(new Time(this, 4 , 5 , true));

		
		
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		for(int r = 0 ; r < rows ; r++)
			for(int c = 0 ; c < cols ; c++) {
				g2d.setColor((c + r)% 2 == 0 ? Color.GRAY : new Color(157,105,53));
				g2d.fillRect(c * tileSize , r * tileSize , tileSize , tileSize);
			}
		
		for (Piece piece : piecelist) {
			
			piece.paint(g2d);
		}
	}
}
