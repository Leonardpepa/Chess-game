
public class Pawn extends Piece {
	private boolean firstMove;
	
	public Pawn(int x, int y, boolean iswhite, Board board, int value) {
		super(x, y, iswhite, board, value);
		firstMove = true;
		this.pieceImage = PieceImages.PAWN;
	}
	
	public void intializeSide(int value){
		super.intializeSide(value);
		if(isWhite()) {
			image = PieceImages.wp;
		}
		else {
			image = PieceImages.bp;
		}
	}

	@Override
	boolean makeMove(int toX, int toY, Board board) {
		Move move = new Move(xCord, yCord, toX, toY);
		if(!alive()) {
			return false;
		}
		for(Move m: moves) {
			if(m.compareTo(move) == 0) {
				board.updatePieces(xCord, yCord, toX, toY,this);
				xCord = toX;
				yCord = toY;				
				firstMove = false;
				return true;
			}
		}
		return false;
		
	}
	
	public boolean madeToTheEnd() {
		if(isWhite && yCord == 0) {
			return true;
		}
		
		if(!isWhite && yCord == 7) {
			return true;
		}
		return false;
	}

	public boolean canMove(int x, int y, Board board) {
	
	
		
		//something blocking the way
		if ((board.getPiece(x, y) != null && board.getPiece(x, y).isWhite() == isWhite())) {
			return false;
		}

		//cant move diagonial if it isnt  for capture
		if (xCord != x && board.getPiece(x, y) == null) {
			return false;
		}

		if (isWhite) {
			//move two or 1 square at beggining
			if (firstMove) {
				if (x == xCord && (y == yCord - 1 || y == yCord - 2) && board.getPiece(x, y) == null && board.getPiece(x, y + 1) == null) {
					return true;
				}
			}
			//move forward
			if (x == xCord && y == yCord - 1 && board.getPiece(x, y) == null) {
				return true;
			}
			
			return capture(x, y, board);

		}
		if (!isWhite) {
			if (firstMove) {
				if (x == xCord && (y == yCord + 1 || y == yCord + 2) && board.getPiece(x, y) == null && board.getPiece(x, y - 1) == null) {
					return true;
				}
			}
			if (x == xCord && y == yCord + 1 && board.getPiece(x, y) == null) {
				return true;
			}

			return capture(x, y, board);
		}

		return false;
	}


	public boolean capture(int x, int y, Board board) {
		if(isWhite()) {
			//capture
			if (y == yCord - 1 && x == xCord + 1) {
				return true;
			}
			//capture
			if (y == yCord - 1 && x == xCord - 1) {
				return true;
			}
		}
		else {
			if (y == yCord + 1 && x == xCord + 1) {
				return true;
			}
			if (y == yCord + 1 && x == xCord - 1) {
				return true;
			}
		}
		return false;
	}
		
	public boolean isFirstMove() {
		return firstMove;
	}

	public void setFirstMove(boolean firstMove) {
		this.firstMove = firstMove;
	}
	
}
