import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.SwingUtilities;


public class Game {
	static Board board = new Board();
	static Piece[] pawnW = new Pawn[8];
	static Piece[] pawnB = new Pawn[8];
	static Piece knightW1;
	static Piece knightW2;
	static Piece bishopW1;
	static Piece bishopW2;
	static Piece rookW2;
	static Piece rookW1;
	static Piece queenW;
	static Piece knightB1;
	static Piece knightB2;
	static Piece bishopB1;
	static Piece bishopB2;
	static Piece rookB1;
	static Piece rookB2;
	static Piece queenB;
	static Piece kingW;
	static Piece kingB;

	
	String player = "white";
	Piece active = null;
	boolean isSelected = false;
	static ArrayList<Piece>  AllPieces = new ArrayList<Piece>();
	
	ArrayList<Move> allPossiblesMoves = new ArrayList<Move>();
	
	
	public Game() {
		for(int i=0; i<8; i++) {
			pawnW[i] = new Pawn(i,6,true,board, 1);
			pawnB[i] = new Pawn(i,1,false,board, -1);
		}
		 knightB1 = new Knight(6, 0, false, board, -3);
		 knightB2 = new Knight(1, 0, false, board, -3);
		 bishopB1 = new Bishop(5, 0, false, board, -3);
		 bishopB2 = new Bishop(2, 0, false, board, -3);
		 rookB1 = new Rook(7, 0, false, board, -5);
		 rookB2 = new Rook(0, 0, false, board, -5);
		 queenB = new Queen(3, 0, false, board, -8);
		 kingB = new King(4, 0, false, board, -100);
		 
		 
	
		rookW1 = new Rook(7, 7, true, board, 5);
		rookW2 = new Rook(0, 7, true, board, 5);
		knightW1 = new Knight(1, 7, true, board, 3);
		knightW2 = new Knight(6, 7, true, board, 3);
		bishopW1 = new Bishop(2, 7, true, board, 3);
		bishopW2 = new Bishop(5, 7, true, board, 3);
		queenW = new Queen(3, 7, true, board, 8);
		kingW = new King(4, 7, true, board, 100);
		fillAllPieces();
		
	}

    public void Play(MouseEvent e) {
    	
    	if(SwingUtilities.isLeftMouseButton(e)) {
			int x = e.getX()/Piece.size;
			int y = e.getY()/Piece.size;
			

			if(!isSelected){
				active = board.getPiece(x, y);
				if(active!= null){
					if(active.isWhite() && player.equals("black") || !active.isWhite() && player.equals("white")){
						isSelected = false;
						active = null;
					}
					else{
						isSelected = true;
						active.fillAllPossibleMoves();
						
					}
				}	
			}

			if(isSelected && (x!=active.getXcord() || y!=active.getYcord())){
				if(active.makeMove(x, y)){
					if(active instanceof Pawn) {
						if(active.isWhite() && active.yCord == 0) {
							AllPieces.remove(active);
							active = new Queen(active.getXcord(), active.getYcord(), active.isWhite(), board, 8);
							AllPieces.add(active);
						}
					}
					player = player.equals("white") ? "black" : "white";
					active = null;
					isSelected = false;
				}
				else{
					active = null;
					isSelected = false;
//					if(board.getPiece(x, y)!=null && board.getPiece(x, y).isWhite() && player.equals("white")){
//						active = board.getPiece(x, y);
//					}
//					if(board.getPiece(x, y)!=null && !board.getPiece(x, y).isWhite() && player.equals("black")){
//						active = board.getPiece(x, y);
//					}
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

		if(active != null){
			active.showMoves(g);
		}
		
	}

	public void drawPiece(Graphics g) {
		g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,75));
		for(Piece p: AllPieces) {
			p.draw(g);
		}
		
	}
	
	public void fillAllPieces(){
		for(int i=0; i<8; i++) {
			AllPieces.add(pawnB[i]);
			AllPieces.add(pawnW[i]);
		}
		AllPieces.add(queenW);
		AllPieces.add(queenB);
		AllPieces.add(kingW);
		AllPieces.add(kingB);
		AllPieces.add(rookW1);
		AllPieces.add(rookW2);
		AllPieces.add(rookB1);
		AllPieces.add(rookB2);
		AllPieces.add(bishopW2);
		AllPieces.add(bishopW1);
		AllPieces.add(bishopB1);
		AllPieces.add(bishopB2);
		AllPieces.add(knightB1);
		AllPieces.add(knightB2);
		AllPieces.add(knightW1);
		AllPieces.add(knightW2);
	}
	
	
}
