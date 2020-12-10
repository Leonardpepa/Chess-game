import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.SwingUtilities;


public class Game {
	Board board = new Board();
	static Pawn[] pawnW = new Pawn[8];
	static Pawn[] pawnB = new Pawn[8];
	static Knight knightW1;
	static Knight knightW2;
	static Bishop bishopW1;
	static Bishop bishopW2;
	static Rook rookW2;
	static Rook rookW1;
	static Queen queenW;
	static Knight knightB1;
	static Knight knightB2;
	static Bishop bishopB1;
	static Bishop bishopB2;
	static Rook rookB1;
	static Rook rookB2;
	static Queen queenB;
	static King kingW;
	static King kingB;
	
	String player = "white";
	Piece activeWhitePiece = null;
	Piece activeBlckPiece = null;
	
	
	public Game() {
		for(int i=0; i<8; i++) {
			pawnW[i] = new Pawn(i,6,true,board);
			pawnB[i] = new Pawn(i,1,false,board);
		}
		 knightB1 = new Knight(6, 0, false, board);
		 knightB2 = new Knight(1, 0, false, board);
		 bishopB1 = new Bishop(5, 0, false, board);
		 bishopB2 = new Bishop(2, 0, false, board);
		 rookB1 = new Rook(7, 0, false, board);
		 rookB2 = new Rook(0, 0, false, board);
		 queenB = new Queen(3, 0, false, board);
		 kingW = new King(4, 7, true, board);
		 
	
		rookW1 = new Rook(7, 7, true, board);
		rookW2 = new Rook(0, 7, true, board);
		knightW1 = new Knight(1, 7, true, board);
		knightW2 = new Knight(6, 7, true, board);
		bishopW1 = new Bishop(2, 7, true, board);
		bishopW2 = new Bishop(5, 7, true, board);
		queenW = new Queen(3, 7, true, board);
		kingB = new King(4, 0, false, board);
		
		
		

	}
	
    public static boolean attackedSquare(int x,int y,boolean isWhite) {
    	int i = x;
    	int j = y;
    	if(!isWhite) {
    		for(int k=0;k<8;k++) {
    			if(pawnW[k].capture(i, j)) {
    				return true;
    			}
    		}
    		if(		knightW1.canMove(i, j) ||
    				knightW2.canMove(i, j) ||
    				bishopW1.canMove(i, j) ||
    				bishopW2.canMove(i, j) ||
    				queenW.canMove(i, j)   ||
    				rookW1.canMove(i, i)   ||
    				rookW2.canMove(i, j)   ||
    				kingW.canMove(i, j)) {
    			
    			return true;
    		}
    	}
    	
    	if(isWhite){
    		for(int k=0;k<8;k++) {
    			if(pawnB[k].capture(i, j)) {
    				return true;
    			}
    		}
    		
    		if(		knightB1.canMove(i, j) ||
    				knightB2.canMove(i, j) ||
    				bishopB1.canMove(i, j) ||
    				bishopB2.canMove(i, j) ||
    				queenB.canMove(i, j)   ||
    				rookB1.canMove(i, i)   ||
    				rookB2.canMove(i, j)   ||
    				kingB.canMove(i, j)     )
    		{
    			
    			return true;
    		}
	    		
    			}
    		
    	
    	return false;
    	
    }
    
    

    public void Play(MouseEvent e) {
    	if(SwingUtilities.isLeftMouseButton(e)) {
			int x = e.getX()/Piece.size;
			int y = e.getY()/Piece.size;
			
		
			
			if(player.equalsIgnoreCase("white")) {
				System.out.println("is " + player + "'s move");
				if(activeWhitePiece!=null && activeWhitePiece.isWhite()) {
					if(activeWhitePiece.makeMove(x, y)) {
						player = "black";
						activeWhitePiece = null;
						activeBlckPiece = null;
					}
					else {
						activeWhitePiece = board.getPiece(x, y);
					}
					
				}
				else {
					activeWhitePiece = board.getPiece(x, y);
					}	
			}
			
			if(player.equalsIgnoreCase("black")) {
				System.out.println("is " + player + "'s move");
				if(activeBlckPiece!=null && !activeBlckPiece.isWhite())
				{
					if(activeBlckPiece.makeMove(x, y)){
						player = "white";
						activeWhitePiece = null;
						activeBlckPiece = null;
					} else {
						activeBlckPiece = board.getPiece(x, y);
					}
				}
				else {
					activeBlckPiece = board.getPiece(x, y);
				}
			}
		}
    	
    }
	
	public void drawBoard(Graphics g) {
			
			for(int i=0; i<8; i++) {
				for(int j=0; j<8; j++) {
					if((i+j)%2 == 1) {
						g.setColor(new Color(113, 144, 58));
					}
					else {
						g.setColor(new Color(182, 142, 96));
					}
					g.fillRect(i*Piece.size, j*Piece.size, Piece.size, Piece.size);
				}
			}
		}
	public void drawPossibleMoves(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(2));
		if(activeWhitePiece != null && player.equals("white")) {
			g2.drawRect(activeWhitePiece.getXcord()*Piece.size, activeWhitePiece.getYcord()*Piece.size,Piece.size,Piece.size);
			activeWhitePiece.showMoves(g);
		}
		
		if(activeBlckPiece != null && player.equals("black")) {
			g2.drawRect(activeBlckPiece.getXcord()*Piece.size, activeBlckPiece.getYcord()*Piece.size, Piece.size, Piece.size);
			activeBlckPiece.showMoves(g);
		}
		
	}

	public void drawPiece(Graphics g) {
		g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,75));
		for(int i=0; i<8; i++) {
			pawnW[i].draw(g);
			pawnB[i].draw(g);
		}
		rookB1.draw(g);
		rookB2.draw(g);
		rookW1.draw(g);
		rookW2.draw(g);
		
		bishopB1.draw(g);
		bishopB2.draw(g);
		bishopW1.draw(g);
		bishopW2.draw(g);
		
		knightB1.draw(g);
		knightB2.draw(g);
		knightW1.draw(g);
		knightW2.draw(g);

		
		queenB.draw(g);
		queenW.draw(g);
		
		kingB.draw(g);
		kingW.draw(g);
		
	}
	
	
}
