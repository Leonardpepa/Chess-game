import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
	protected int xCord;
	protected int yCord;
	protected boolean isWhite;
	protected boolean isAlive;
	protected int valueInTheBoard;
	protected Board board;
	protected String pieceImage;
	protected Color pieceColor;
	static int size = 75;
	protected List<Move> moves = new ArrayList<>();

	
	boolean makeMove(int toX, int toY) {
		Move move = new Move(xCord, yCord, toX, toY);
		if(!alive()) {
			return false;
		}
		for(Move m: moves) {
			if(m.compareTo(move) == 0) {
				board.updatePieces(xCord, yCord, toX, toY,this);
				xCord = toX;
				yCord = toY;
				return true;
			}
		}
		return false;
		
	}
	public abstract boolean canMove(int x ,int y);

	@SuppressWarnings("unlikely-arg-type")
	public boolean alive() {
		if (board.getXY(xCord, yCord) != valueInTheBoard || board.getXY(xCord, yCord) == 0 || board.getPiece(xCord, yCord) == null) {
			isAlive = false;
			Game.AllPieces.remove(getClass());
		}
		return isAlive;
	}
	
	public void intializeSide(int value){
		if(isWhite) {
			pieceColor = PieceImages.WHITECOLOR;
		}
		else {
			pieceColor = PieceImages.BLACKCOLOR;
		}
		valueInTheBoard = value;
	};
	
	public Piece(int x,int y,boolean iswhite,Board board, int value) {
		this.xCord = x;
		this.yCord = y;
		this.isWhite = iswhite;
		isAlive = true;
		this.board = board;
		intializeSide(value);
		board.setPieceIntoBoard(x, y, this);
	}
	
	public void showMoves(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		
		for(Move m: moves) {
			g.setColor(Color.CYAN);
			g.drawRect(m.getToX()*size, m.getToY()*size, size, size);
			g2.setColor(Color.red);
			g2.drawRect(m.getFromX()*size, m.getFromY()*size, size, size);
		}
	}
	
	
	public void draw(Graphics g, boolean drag) {
		if(this.alive()) {
			g.setColor(pieceColor);
			if(drag) {
				g.drawString(this.pieceImage, this.xCord, this.yCord);
			}else {
				g.drawString(this.pieceImage, this.xCord*size, (this.yCord+1)*size-10);				
			}
		}
	}
	
	public void draw2(Graphics g, boolean player, int x, int y) {
		if(this.alive() && player == isWhite()) {
			g.setColor(pieceColor);
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 85));
			g.drawString(this.pieceImage, x - Piece.size/2, y + Piece.size/2);
			
		}
	}
	
	public void fillAllPossibleMoves() {
		moves = new ArrayList<Move>();
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				if(canMove(i, j)) {
					moves.add(new Move(xCord, yCord, i, j));
				}
			}
		}
	}

	
	public int getXcord() {
		return xCord;
	}

	public void setXcord(int xcord) {
		this.xCord = xcord;
	}

	public int getYcord() {
		return yCord;
	}
	public void setYcord(int ycord) {
		this.yCord = ycord;
	}

	public boolean isWhite() {
		return isWhite;
	}

	public void setWhite(boolean isWhite) {
		this.isWhite = isWhite;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public void setValueInTheboard(int value) {
		this.valueInTheBoard = value;
	}
	public int getValueInTheboard() {
		return valueInTheBoard;
	}
	public List<Move> getMoves() {
		return moves;
	}
	public void setMoves(List<Move> moves) {
		this.moves = moves;
	}
	
}
