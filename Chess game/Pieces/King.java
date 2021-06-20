
public class King extends Piece {
	private boolean hasMoved;
	private Rook rook = null;
	
	public King(int x, int y, boolean iswhite, Board board, int value) {
		super(x, y, iswhite, board, value);
		hasMoved = false;
		this.pieceImage = PieceImages.KING;
	}
	
	public boolean makeMove(int x, int y, Board board) {
		Move move = new Move(xCord, yCord, x, y);
		if(!alive()) {
			return false;
		}
		for(Move m: moves) {
			if(m.compareTo(move) == 0) {
				getRook(x);
				board.updatePieces(xCord, yCord, x, y,this);
				xCord = x;
				yCord = y;
				if(!this.hasMoved && !rook.HasMoved()) {
					if(x == rook.getXcord() - 1 || x == rook.getXcord() + 2) {
						rook.castleDone(xCord);
					}					
				}
				hasMoved = true;
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean canMove(int x, int y) {
		
		int i = Math.abs(xCord - x);
		int j = Math.abs(yCord - y);
		
		if( j == 1 && i == 1 || (i+j) == 1) {
			
			if(board.getPiece(x, y) == null) {
				return true;
			}
			else {
				return board.getPiece(x, y).isWhite() != isWhite();				
			}
		}
		
		
		getRook(x);
		
		if(rook.HasMoved() || this.hasMoved) {
			return false;
		}else{
			for(int k=xCord + 1; k<rook.getXcord() - 1; k++) {
				if(board.getPiece(k, yCord) != null) {
					return false;
				}
			}	
			if(x == rook.getXcord() - 1 && y == yCord) {
				return true;
			}
			
			for(int k=xCord - 1; k>rook.getXcord() + 1; k--) {
				if(board.getPiece(k, yCord) != null) {
					return false;
				}
			}
			if(x == rook.getXcord() + 2 && y == yCord) {
				return true;
			}
			
		}
		
		
		
		return false;
	
	}
	
	private void getRook(int x) {
		if(isWhite()) {
			if(x >= xCord) {
				rook = (Rook) board.getPiece(7, 7);
			}
			else{
				rook = (Rook) board.getPiece(0, 7);
			}
		}
		else {
			if(x >= xCord) {
				rook = (Rook) board.getPiece(7, 0);
			}
			else{
				rook = (Rook) board.getPiece(0, 0);	
			}
		}
	}
	

	
}
