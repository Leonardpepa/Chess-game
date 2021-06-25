
public class Pawn extends Piece {
	private boolean firstMove;
	private static boolean canEnPassant = false;
	private boolean moved2Squares = false;
	private Pawn pawnRight = null;
	private Pawn pawnLeft = null;
	
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
				if(firstMove && Math.abs(yCord - m.getToY()) == 2) {
					canEnPassant = true;
					moved2Squares = true;
				}else {
					canEnPassant = false;
					removePawnCapturedFromEnPassant(toX, toY, board);
					removeEnpassantAtr(board);
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

	public boolean canMove(int x, int y, Board board) {
	
		if(xCord != 7 && board.getPiece(xCord + 1, yCord) != null && board.getPiece(xCord + 1, yCord) instanceof Pawn) {
			pawnRight = board.getPiece(xCord + 1, yCord).isWhite() != this.isWhite()? (Pawn) board.getPiece(xCord + 1, yCord) : null;			
			if(pawnRight != null && Pawn.canEnPassant) {
				if(this.isWhite() && y == pawnRight.getYcord() - 1 && x == pawnRight.getXcord() && pawnRight.isMoved2Squares()) {
					return true;
				}
				else if(!this.isWhite() && y == pawnRight.getYcord() + 1 && x == pawnRight.getXcord() && pawnRight.isMoved2Squares()) {
					return true;
				}
				
			}
			
		}
		if(xCord != 0 &&board.getPiece(xCord - 1, yCord) != null && board.getPiece(xCord - 1, yCord) instanceof Pawn) {
			pawnLeft = board.getPiece(xCord - 1, yCord).isWhite() != this.isWhite()?  (Pawn) board.getPiece(xCord - 1, yCord): null;	
			if(pawnLeft != null && Pawn.canEnPassant) {
				if(this.isWhite() && y == pawnLeft.getYcord() - 1 && x == pawnLeft.getXcord() && pawnLeft.isMoved2Squares()) {
					return true;
				}
				else if(!this.isWhite() && y == pawnLeft.getYcord() + 1 && x == pawnLeft.getXcord() && pawnLeft.isMoved2Squares()) {
					return true;
				}
				
			}
			
		}					
	

		
		
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

	public boolean isMoved2Squares() {
		return moved2Squares;
	}

	public void setMoved2Squares(boolean moved2Squares) {
		this.moved2Squares = moved2Squares;
	}

	public void removeEnpassantAtr(Board board) {
		for(Piece piece: board.piecesList) {
			if(piece instanceof Pawn) {	
				((Pawn) piece).setMoved2Squares(false);
			}
		}
	}
	
	public void removePawnCapturedFromEnPassant(int x, int y, Board board) {
		if(isWhite()) {
			if(pawnLeft != null && x == pawnLeft.getXcord() && y + 1 == pawnLeft.getYcord() && pawnLeft.isMoved2Squares()) {
				board.setXY(pawnLeft.getXcord(), pawnLeft.getYcord(), 0);
			}
			if(pawnRight != null && x == pawnRight.getXcord() && y + 1 == pawnRight.getYcord() && pawnRight.isMoved2Squares()) {
				board.setXY(pawnRight.getXcord(), pawnRight.getYcord(), 0);	
			}
		}
		else {
			if(pawnLeft != null && x == pawnLeft.getXcord() && y- 1 == pawnLeft.getYcord() && pawnLeft.isMoved2Squares()) {
				board.setXY(pawnLeft.getXcord(), pawnLeft.getYcord(), 0);
			}
			if(pawnRight != null && x == pawnRight.getXcord() && y - 1 == pawnRight.getYcord() && pawnRight.isMoved2Squares()) {
				board.setXY(pawnRight.getXcord(), pawnRight.getYcord(), 0);	
			}
		}
	}
	
}
