

public class Knight extends Piece{
	
	public Knight(int x, int y, boolean iswhite, Board board, int value) {
		super(x, y, iswhite, board, value);
		this.pieceImage = PieceImages.KNIGHT;
	}
	
	public boolean makeMove(int toX,int toY) {
		
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

	
	public boolean canMove(int x ,int y) {
			
			if((board.getPiece(x, y) != null && board.getPiece(x, y).isWhite() == isWhite())) {
					return false;
			}
		
			if(x == xCord+1 && y == yCord-2 ) {
				return true;
			}
			if(x == xCord-1 && y == yCord-2 ) {
				return true;	
			}
			
			if(x == xCord-1 && y == yCord+2 ) {
				return true;	
			}
			if(x == xCord+1 && y == yCord+2 ) {
				return true;	
			}
			
			if(x == xCord+2 && y == yCord-1 ) {
				return true;	
			}
			if(x == xCord+2 && y == yCord+1 ) {
				return true;	
			}
			if(x == xCord-2 && y == yCord-1 ) {
				return true;	
			}
			if(x == xCord-2 && y == yCord+1 ) {
				return true;	
			}
	
		return false;
	}
	
	
	

	@Override
	public boolean alive() {
		if(board.getXY(xCord, yCord) != valueInTheBoard || board.getXY(xCord, yCord) == 0 ) {
			isAlive = false;
		}
		return isAlive;
	}
	
	

}
