import java.util.ArrayList;
import java.util.List;

public class Board implements Cloneable{
	public static final int ROWS = 8;
	public static final int COLUMNS = 8;
	
	public int[][] grid;
	public Piece[][] pieces;
	public Piece lastPieceMoved;
	public Move lastMove;
	public Piece died;
	public  List<Piece> piecesList = new ArrayList<Piece>();
	public Board() {
		grid = new int[ROWS][COLUMNS];
		pieces = new Piece[ROWS][COLUMNS];
	}
	

	public void setPieceIntoBoard(int x,int y,Piece piece) {
		grid[x][y] = piece.getValueInTheboard();
		pieces[x][y] = piece;
		piecesList.add(piece);
	}	
	public void updatePieces(int fromX,int fromY,int toX,int toY,Piece piece) {
		lastMove = new Move(fromX, fromY, toX, toY);
		lastPieceMoved = piece;
		if(pieces[toX][toY] != null) {
			died = pieces[toX][toY];
			piecesList.remove(died);
		}
		grid[fromX][fromY] = 0;
		grid[toX][toY] =  piece.getValueInTheboard();
		pieces[fromX][fromY] = null;
		pieces[toX][toY] = piece;
	}
	
	public void undoMove() {
		
		if(lastMove == null) return;
		
		pieces[lastMove.getFromX()][lastMove.getFromY()] = lastPieceMoved;
		grid[lastMove.getFromX()][lastMove.getFromY()] = lastPieceMoved.getValueInTheboard();
		lastPieceMoved.setXcord(lastMove.getFromX());
		lastPieceMoved.setYcord(lastMove.getFromY());
		
		if(died != null) {
			pieces[lastMove.getToX()][lastMove.getToY()] = died;
			grid[lastMove.getToX()][lastMove.getToY()] = died.getValueInTheboard();
			died.setXcord(lastMove.getToX());
			died.setYcord(lastMove.getToY());
		}
		
		
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
	
	public Board getNewBoard() {
		Board b = new Board();
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				if(this.getPiece(i, j) != null) {
					b.setPieceIntoBoard(i, j, this.getPiece(i, j).getClone());
				}
			}
		}
		return b;
	}
	
	public void printBoard() {
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				System.out.print(grid[j][i] +  "  ");
			}
			System.out.println();
		}
	}
	
	
	
}
