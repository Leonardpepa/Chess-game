import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;



public class Game {
	static Board board = new Board();
	static Piece[] pawnW = new Pawn[8];
	static Piece[] pawnB = new Pawn[8];

	static boolean player = true;
	Piece active = null;
	boolean isSelected = false;
	static ArrayList<Piece> AllPieces = new ArrayList<Piece>();

	ArrayList<Move> allPossiblesMoves = new ArrayList<Move>();
	public static boolean drag = false;
	
	static List<Move> allPlayersMove = new ArrayList<Move>();
	static List<Move> allEnemysMove = new ArrayList<Move>();
	
	public Game() {
		//starting position rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR
		new PieceImages();
		loadFenPosition("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
	}
	
	public void draw(Graphics g, int x, int y, JPanel panel) {
		drawBoard(g);
		drawPiece(g, panel);
		drawPossibleMoves(g);
		drag(active,x, y ,g, panel);
	}
	
	public static void generateOnePlayerMoves() {
		allPlayersMove = new ArrayList<Move>();
		for(Piece p: AllPieces) {
			if(p.isWhite() == player) {
				p.fillAllPossibleMoves();
				allPlayersMove.addAll(p.getMoves());
			}
		}
	}
	
	public static void generateAllEnemysMoves() {
		allEnemysMove = new ArrayList<Move>();
		for(Piece p: AllPieces) {
			if(p.isWhite() != player) {
				p.fillAllPossibleMoves();
				allPlayersMove.addAll(p.getMoves());
			}
		}
	}

	public void changeSide() {
		player = !player;
	}
	
	public void selectPiece(int x, int y) {
		if(active == null && board.getPiece(x, y) != null && board.getPiece(x, y).isWhite() == player) {
			active = board.getPiece(x, y);
			active.fillAllPossibleMoves();
		}
	}
	
	public void drag(Piece piece, int x, int y, Graphics g, JPanel panel) {
		if(piece!=null && drag == true) {
			piece.draw2(g, player, x, y, panel);			
		}
	}
	
	public void move(int x, int y) {
		if(active != null) {
			if(active.makeMove(x, y, board)) {
				tryToPromote(active);
				changeSide();
			}
			drag = false;
			active  = null;
		}
	}
	
	public void drawBoard(Graphics g) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((i + j) % 2 == 1) {
					g.setColor(new Color(113, 144, 58));
				} else {
					g.setColor(new Color(182, 142, 96));
				}
				g.fillRect(i * Piece.size, j * Piece.size, Piece.size, Piece.size);
			}
		}
	}
	
	
	public void tryToPromote(Piece p) {
		if(p instanceof Pawn) {
			if(((Pawn)p).madeToTheEnd()) {
				PromotionFrame.popUpPieces(p.pieceColor, p);
			}
		}
	}
	
	public static void choosePiece(Piece p, int choice) {
		switch (choice) {
		case 0:
			AllPieces.remove(p);
			p = new Queen(p.getXcord(), p.getYcord(), p.isWhite(), board, p.isWhite() ? 8: -8);					
			AllPieces.add(p);
			break;
		case 1:
			AllPieces.remove(p);
			p = new Rook(p.getXcord(), p.getYcord(), p.isWhite(), board, p.isWhite() ? 5: -5);
			AllPieces.add(p);
			break;
		case 2:
			AllPieces.remove(p);
			p = new Knight(p.getXcord(), p.getYcord(), p.isWhite(), board, p.isWhite() ? 3: -3);
			AllPieces.add(p);
			break;
		case 3: 
			AllPieces.remove(p);
			p = new Bishop(p.getXcord(), p.getYcord(), p.isWhite(), board, p.isWhite() ? 3: -3);
			AllPieces.add(p);
			break;
		default:
			AllPieces.remove(p);
			p = new Queen(p.getXcord(), p.getYcord(), p.isWhite(), board, p.isWhite() ? 8: -8);
			AllPieces.add(p);
			break;
		}
	}

	public void drawPossibleMoves(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		if (active != null) {
			active.showMoves(g2);
		}

	}

	public void drawPiece(Graphics g, JPanel panel) {
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, Piece.size));
		for (Piece p : AllPieces) {
			p.draw(g, false, panel);
		}

	}
	
	public void loadFenPosition(String fenString) {
		//rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR
		String[] parts = fenString.split(" ");
		String position = parts[0];
		int row = 0, col = 0;
		for(char c: position.toCharArray()) {
			if(c == '/') {
				row++;
				col = 0;
			}
			if(Character.isLetter(c)){
				if(Character.isLowerCase(c)) {
					addToBoard(col, row, c, false);
				}
				else {
					addToBoard(col, row, c, true);
				}
				col++;
			}
			if(Character.isDigit(c)) {
				col += Integer.parseInt(String.valueOf(c));
			}
		}
		
		if(parts[1].equals("w")) {
			player = true;
		}
		else {
			player = false;
		}
		
	}
	
	public void addToBoard(int x, int y, char c, boolean isWhite) {
		switch (String.valueOf(c).toUpperCase()) {
		case "R":
			AllPieces.add(new Rook(x, y, isWhite, board, isWhite ? 5 : -5));
			break;
		case "N":
			AllPieces.add(new Knight(x, y, isWhite, board, isWhite ? 3 : -3));
			break;
		case "B":
			AllPieces.add(new Bishop(x, y, isWhite, board, isWhite ? 3 : -3));
			break;
		case "Q":
			AllPieces.add(new Queen(x, y, isWhite, board, isWhite ? 8 : -8));
			break;
		case "K":
			AllPieces.add(new King(x, y, isWhite, board, isWhite ? 100 : -100));
			break;
		case "P":
			AllPieces.add(new Pawn(x, y, isWhite, board, isWhite ? 1 : -1));
			break;
		}
	}

}
