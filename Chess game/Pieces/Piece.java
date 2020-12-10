import java.awt.Color;
import java.awt.Graphics;

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

	abstract boolean makeMove(int toX,int toY);
	public abstract boolean canMove(int x ,int y);
	public abstract void intializeSide();
	public abstract boolean alive();
	
	public Piece(int x,int y,boolean iswhite,Board board) {
		this.xCord = x;
		this.yCord = y;
		this.isWhite = iswhite;
		isAlive = true;
		this.board = board;
		intializeSide();
		board.setPieceIntoBoard(x, y, this);
	}
	public void showMoves(Graphics g) {
		g.setColor(Color.CYAN);
		for(int i=0; i<8; i++) {
    		for(int j=0; j<8; j++) {
    			if(this.canMove(i, j) && board.getXY(i, j) != valueInTheBoard) {
    				g.drawRect(i*size, j*size, size, size);
    			}
    		}
    	}
	}
	
	
	public void draw(Graphics g) {
		if(this.alive()) {
			g.setColor(pieceColor);
			g.drawString(this.pieceImage, this.xCord*size, (this.yCord+1)*size-10);
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
	
}
