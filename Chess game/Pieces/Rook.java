

public class Rook extends Piece {
	
	private boolean hasMoved;

	public Rook(int x, int y, boolean iswhite, Board board) {
		super(x, y, iswhite, board);
		hasMoved = false;
	}

	@Override
	boolean makeMove(int toX, int toY) {
		if(canMove(toX, toY) && alive() && board.getXY(toX, toY) != valueInTheBoard) {
			board.updatePieces(xCord, yCord, toX, toY,this);
			xCord = toX;
			yCord = toY;
			hasMoved = true;
			return true;
		}
		return false;
		
	}

	@Override
	public boolean canMove(int x, int y) {
		
			if(x == xCord && (y<yCord )) {
					for(int i=yCord-1; i>y;i--) {
						if(board.getXY(x, i) != 0) {
							return false;
						}
					}
					return true;
			}
			
			if(x == xCord && (y>yCord )) {
				for(int i=yCord+1; i<y;i++) {
					if(board.getXY(x, i) != 0) {
						return false;
					}
				}
				return true;
			}
			
			if(y == yCord && (x>xCord )) {
				for(int i=xCord+1; i<x;i++) {
					if(board.getXY(i, y) != 0) {
						return false;
					}
				}
				return true;
			}
			if(y == yCord && (x<xCord )) {
				for(int i=xCord-1; i>x;i--) {
					if(board.getXY(i, y) != 0) {
						return false;
					}
				}
				return true;
			}
			
		return false;
		
	}
	
	


	@Override
	public void intializeSide() {
		if(isWhite) {
			valueInTheBoard = 1;
			pieceImage = PieceImages.ROOK;
			pieceColor = PieceImages.WHITECOLOR;
		}
		else {
			valueInTheBoard = 2;
			pieceImage = PieceImages.ROOK;
			pieceColor = PieceImages.BLACKCOLOR;
		}
		
	}



	public boolean HasMoved() {
		return hasMoved;
	}

	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}

	@Override
	public boolean alive() {
		if(board.getXY(xCord, yCord) != valueInTheBoard || board.getXY(xCord, yCord) == 0 ) {
			isAlive = false;
		}
		return isAlive;
	}
	
	
	
	
	

}
