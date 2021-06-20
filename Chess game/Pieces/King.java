
public class King extends Piece {
	private boolean hasMoved;
	
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
				board.updatePieces(xCord, yCord, x, y,this);
				xCord = x;
				yCord = y;
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
		return false;
	}
	

	
}
