
public class Board {
	public static final int ROWS = 8;
	public static final int COLUMNS = 8;
	
	public int[][] grid;
	public Piece[][] pieces;
	public Board() {
		grid = new int[ROWS][COLUMNS];
		pieces = new Piece[ROWS][COLUMNS];
	}
	
	public boolean isAttacked(int x, int y, boolean isWhite) {
		for(Piece p: Game.AllPieces) {
			if(p.isWhite() == isWhite) {
				continue;
			}
			if(p.canMove(x, y)) {
				return true;
			}
		}
		return false;
	}
	
	public void setPieceIntoBoard(int x,int y,Piece piece) {
		grid[x][y] = piece.getValueInTheboard();
		pieces[x][y] = piece; 
	}
	public void yes(int fromX,int fromY,int toX,int toY,Piece piece) {
		pieces[fromX][fromY] = null;
		pieces[toX][toY] = piece;
	}
	
	public void updatePieces(int fromX,int fromY,int toX,int toY,Piece piece) {
			grid[fromX][fromY] = 0;
			grid[toX][toY] =  piece.getValueInTheboard();
			pieces[fromX][fromY] = null;
			pieces[toX][toY] = piece;
	}
	
	public Piece getPiece(int x,int y) {
		return pieces[x][y];
	}


	public int[][] getGrid() {
		return grid;
	}


	public void setGrid(int[][] grid) {
		this.grid = grid;
	}
	
	public int getXY(int x,int y) {
		return grid[x][y];
	}
	
	public void setXY(int x,int y,int v) {
		 grid[x][y] = v ;
	}
	
}
