import java.awt.Color;
import java.awt.Graphics;

public class Pawn extends Piece {
	private boolean firstMove;

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

}
