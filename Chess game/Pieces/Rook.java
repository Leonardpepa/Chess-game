

public class Rook extends Piece {
	
	private boolean hasMoved;

	public Rook(int x, int y, boolean iswhite, Board board, int value) {
		super(x, y, iswhite, board, value);
		hasMoved = false;
		this.pieceImage = PieceImages.ROOK;
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
			hasMoved = true;
			return true;
		}
		return false;
		
	}

	@Override
	public boolean canMove(int x, int y) {
		
			if(board.getPiece(x, y) != null && board.getPiece(x, y).isWhite() == isWhite()) {
				return false;
			}
		
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
