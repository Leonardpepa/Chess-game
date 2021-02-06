

public class Bishop extends Piece {
	
	

	public Bishop(int x, int y, boolean iswhite, Board board) {
		super(x, y, iswhite, board);
	}

	@Override
	boolean makeMove(int toX, int toY) {
		if(canMove(toX, toY) && alive() && board.getXY(toX, toY) != valueInTheBoard) {
			board.updatePieces(xCord, yCord, toX, toY,this);
			xCord = toX;
			yCord = toY;
			return true;
		}
		return false;
	}

	@Override
	public boolean canMove(int x, int y) {
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
	public void intializeSide() {
		if(isWhite) {
			valueInTheBoard = 1;
			pieceImage = PieceImages.BISHOP;
			pieceColor = PieceImages.WHITECOLOR;
		}
		else {
			valueInTheBoard = 2;
			pieceImage = PieceImages.BISHOP;
			pieceColor = PieceImages.BLACKCOLOR;
		}
	}

	@Override
	public boolean alive() {
		if(board.getXY(xCord, yCord) != valueInTheBoard || board.getXY(xCord, yCord) == 0 ) {
			isAlive = false;
		}
		return isAlive;
	}

	



}
