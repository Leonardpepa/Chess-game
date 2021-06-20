
public class Pawn extends Piece {
	private boolean firstMove;
	private static boolean movedTwoSqFisrtTime = false;
	public Pawn(int x, int y, boolean iswhite, Board board, int value) {
		super(x, y, iswhite, board, value);
		firstMove = true;
		this.pieceImage = PieceImages.PAWN;
	}

	@Override
	boolean makeMove(int toX, int toY, Board sboard) {
		Move move = new Move(xCord, yCord, toX, toY);
		if(!alive()) {
			return false;
		}
		for(Move m: moves) {
			if(m.compareTo(move) == 0) {
				if(firstMove && Math.abs(yCord - m.getToY()) == 2) {
					movedTwoSqFisrtTime = true;
				}
				else {
					movedTwoSqFisrtTime = false;	
				}
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

	public boolean canMove(int x, int y) {
		Pawn pawnRight = null;
		Pawn pawnLeft = null;
		
		if(isWhite()) {
			if(xCord != 7 && board.getPiece(xCord + 1, yCord) != null && board.getPiece(xCord + 1, yCord) instanceof Pawn) {
				pawnRight = board.getPiece(xCord + 1, yCord).isWhite() != this.isWhite()? (Pawn) board.getPiece(xCord + 1, yCord) : null;			
				if(pawnRight != null && y == pawnRight.getYcord() - 1 && x == pawnRight.getXcord() &&pawnRight.isMovedTwoSqFisrtTime()) {
					return true;
				}
			}
			if(xCord != 0 &&board.getPiece(xCord - 1, yCord) != null && board.getPiece(xCord - 1, yCord) instanceof Pawn) {
				pawnLeft = board.getPiece(xCord - 1, yCord).isWhite() != this.isWhite()?  (Pawn) board.getPiece(xCord - 1, yCord): null;	
				if(pawnLeft != null && y == pawnLeft.getYcord() - 1 && x == pawnLeft.getXcord() && pawnLeft.isMovedTwoSqFisrtTime()) {
					return true;
				}
			}			
		}

		
		
		if ((board.getPiece(x, y) != null && board.getPiece(x, y).isWhite() == isWhite())) {
			return false;
		}

		if (xCord != x && board.getPiece(x, y) == null) {
			return false;
		}

		if (isWhite) {
			if (firstMove) {
				if (x == xCord && (y == yCord - 1 || y == yCord - 2) && board.getPiece(x, y) == null && board.getPiece(x, y + 1) == null) {
					return true;
				}
			}
			if (x == xCord && y == yCord - 1 && board.getPiece(x, y) == null) {
				return true;
			}
			if (y == yCord - 1 && x == xCord + 1) {
				return true;
			}

			if (y == yCord - 1 && x == xCord - 1) {
				return true;
			}

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

	public boolean isMovedTwoSqFisrtTime() {
		return movedTwoSqFisrtTime;
	}

	public void setMovedTwoSqFisrtTime(boolean movedTwoSqFisrtTime) {
		this.movedTwoSqFisrtTime = movedTwoSqFisrtTime;
	}

	
	
}
