import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;


public class Game {
	static Board board = new Board();
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
	Piece active = null;
	boolean isSelected = false;
	
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
		 kingB = new King(4, 0, false, board);
		 
		 
	
		rookW1 = new Rook(7, 7, true, board);
		rookW2 = new Rook(0, 7, true, board);
		knightW1 = new Knight(1, 7, true, board);
		knightW2 = new Knight(6, 7, true, board);
		bishopW1 = new Bishop(2, 7, true, board);
		bishopW2 = new Bishop(5, 7, true, board);
		queenW = new Queen(3, 7, true, board);
		kingW = new King(4, 7, true, board);

		
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
					}
				}	
			}

			if(isSelected && (x!=active.getXcord() || y!=active.getYcord())){

				if(active.makeMove(x, y)){
					active = null;
					isSelected = false;
					player = player.equals("white") ? "black" : "white";
					System.out.println(player);
				}
				else{
					if(board.getPiece(x, y)!=null && board.getPiece(x, y).isWhite() && player.equals("white")){
						active = board.getPiece(x, y);
					}
					if(board.getPiece(x, y)!=null && !board.getPiece(x, y).isWhite() && player.equals("black")){
						active = board.getPiece(x, y);
					}
				}
			}
			

			

		
		
		
			// 	if(player.equalsIgnoreCase("white")) {
		// 		System.out.println("is " + player + "'s move");
		// 		if(activeWhitePiece!=null && activeWhitePiece.isWhite()) {
		// 			if(activeWhitePiece.makeMove(x, y)) {
		// 				player = "black";
		// 				activeWhitePiece = null;
		// 				activeBlckPiece = null;
		// 			}
		// 			else {
		// 				activeWhitePiece = board.getPiece(x, y);
		// 			}
					
		// 		}
		// 		else {
		// 			activeWhitePiece = board.getPiece(x, y);
		// 			}	
		// 	}
			
		// 	if(player.equalsIgnoreCase("black")) {
		// 		if(activeBlckPiece!=null && !activeBlckPiece.isWhite())
		// 		{
		// 			if(activeBlckPiece.makeMove(x, y)){
		// 				player = "white";
		// 				activeWhitePiece = null;
		// 				activeBlckPiece = null;
		// 			} else {
		// 				activeBlckPiece = board.getPiece(x, y);
		// 			}
		// 		}
		// 		else {
		// 			activeBlckPiece = board.getPiece(x, y);
		// 		}
		// 	}
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
		// if(activeWhitePiece != null && player.equals("white")) {
		// 	g2.drawRect(activeWhitePiece.getXcord()*Piece.size, activeWhitePiece.getYcord()*Piece.size,Piece.size,Piece.size);
		// 	activeWhitePiece.showMoves(g);
		// }
		
		// if(activeBlckPiece != null && player.equals("black")) {
		// 	g2.drawRect(activeBlckPiece.getXcord()*Piece.size, activeBlckPiece.getYcord()*Piece.size, Piece.size, Piece.size);
		// 	activeBlckPiece.showMoves(g);
		// }

		if(active != null){
			active.showMoves(g);
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

	// public Piece searchPiece(int x, int y){

	// }
	
	
}
