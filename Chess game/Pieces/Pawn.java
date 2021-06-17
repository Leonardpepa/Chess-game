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
	boolean makeMove(int toX, int toY) {
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

	public boolean canMove(int x, int y) {

		if ((board.getPiece(x, y) != null && board.getPiece(x, y).isWhite() == isWhite())) {
			return false;
		}

		if (xCord != x && board.getPiece(x, y) == null) {
			return false;
		}

		if (isWhite) {
			if (firstMove) {
				if (x == xCord && (y == yCord - 1 || y == yCord - 2) && board.getXY(xCord, yCord - 1) == 0) {
					return true;
				}
			}
			if (x == xCord && y == yCord - 1 && board.getXY(x, y) == 0) {
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
				if (x == xCord && (y == yCord + 1 || y == yCord + 2) && board.getXY(xCord, yCord + 1) == 0) {
					return true;
				}
			}
			if (x == xCord && y == yCord + 1 && board.getXY(x, y) == 0) {
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

	@Override
	public void showMoves(Graphics g) {
		g.setColor(Color.CYAN);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (canMove(i, j)) {
					g.drawRect(i * size, j * size, size, size);
				}
			}
		}
	}

	@Override
	public boolean alive() {
		if (board.getXY(xCord, yCord) != valueInTheBoard || board.getXY(xCord, yCord) == 0) {
			isAlive = false;
		}
		return isAlive;
	}

	public boolean isFirstMove() {
		return firstMove;
	}

	public void setFirstMove(boolean firstMove) {
		this.firstMove = firstMove;
	}

}
