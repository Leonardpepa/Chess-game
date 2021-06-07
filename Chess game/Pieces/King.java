import java.awt.Color;
import java.awt.Graphics;

public class King extends Piece {
	private boolean hasMoved;
	private boolean hasCastled;
	public King(int x, int y, boolean iswhite, Board board, int value) {
		super(x, y, iswhite, board, value);
		hasMoved = false;
		hasCastled = false;
		this.pieceImage = PieceImages.KING;
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
		
		int i = Math.abs(xCord - x);
		int j = Math.abs(yCord - y);
		
		if( j == 1 && i == 1 || (i+j) == 1) {
			if(board.isAttacked(x, y, isWhite())) {
				return false;
			}
			
			if(board.getPiece(x, y) == null) {
				return true;
			}
			else {
				return board.getPiece(x, y).isWhite() != isWhite();				
			}
		}
		return false;
	}

	@Override
	public boolean alive() {
		return  true;
	}


}
