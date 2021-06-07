import java.util.ArrayList;

public class Bishop extends Piece {
	
	private ArrayList<Move> moves = new ArrayList<>();
	
	public Bishop(int x, int y, boolean iswhite, Board board, int value) {
		super(x, y, iswhite, board, value);
		this.pieceImage = PieceImages.BISHOP;
	}

	@Override
	boolean makeMove(int toX, int toY) {
		if(!alive()) {
			return false;
		}
		if(canMove(toX, toY)) {
			board.updatePieces(xCord, yCord, toX, toY,this);
			xCord = toX;
			yCord = toY;
			return true;
		}
		return false;
	}

	@Override
	public boolean canMove(int x, int y) {
			
			if(board.getPiece(x, y) != null && board.getPiece(x, y).isWhite() == isWhite()) {
				return false;
			}
		
			if(Math.abs(x-xCord) == Math.abs( y-yCord)) {
				return bishopMoves(x, y);
			}
		return false;
	}
	
	public boolean bishopMoves(int x,int y) {
		if(x > xCord && y > yCord) {
			int j = yCord+1;
			for(int i=xCord+1; i<x; i++) {
				if(board.getXY(i, j) != 0) {
					return false;
				}
				j++;
			}
			
		}
		else if(x < xCord && y < yCord) {
			int j = yCord-1;
			for(int i=xCord-1; i>x; i--) {
				if(board.getXY(i, j) != 0) {
					return false;
				}
				j--;
			}
			
		}
		else if(x > xCord && y < yCord) {
			int j = yCord - 1;
			for(int i=xCord+1; i<x; i++) {
				if(board.getXY(i, j) != 0) {
					return false;
				}
				j--;
			}
		}
		else if(x < xCord && y > yCord) {
			int j = yCord+1;
			for(int i=xCord-1; i>x; i--) {
				if(board.getXY(i, j) != 0) {
					return false;
				}
				j++;
			}
		}
		return true;
		
	}


	@Override
	public boolean alive() {
		if(board.getXY(xCord, yCord) != valueInTheBoard || board.getXY(xCord, yCord) == 0 ) {
			isAlive = false;
		}
		return isAlive;
	}

	



}
