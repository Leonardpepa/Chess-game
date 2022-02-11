package com.chessgame.Pieces;
import com.chessgame.Board.Board;

public class Rook extends Piece {

	private boolean hasMoved;
	private boolean justMoved = false;

	public Rook(int x, int y, boolean iswhite, Board board, int value) {
		super(x, y, iswhite, board, value);
		hasMoved = false;
		this.pieceImage = PieceImages.ROOK;
	}
	
	public void intializeSide(int value){
		super.intializeSide(value);
		if(isWhite()) {
			image = PieceImages.wr;
		}
		else {
			image = PieceImages.br;
		}
	}
	
	@Override
	public boolean makeMove(int toX, int toY, Board board) {
		if(super.makeMove(toX, toY, board)) {
			if(!hasMoved) {
				justMoved = true;
			}else {
				justMoved = false;
			}
			hasMoved = true;
			return true;
		}
		return false;
	}

	@Override
	public boolean canMove(int x, int y, Board board) {

		if (board.getPiece(x, y) != null && board.getPiece(x, y).isWhite() == isWhite()) {
			return false;
		}

		if (x == xCord && (y < yCord)) {
			for (int i = yCord - 1; i > y; i--) {
				if (board.getXY(x, i) != 0) {
					return false;
				}
			}
			return true;
		}

		if (x == xCord && (y > yCord)) {
			for (int i = yCord + 1; i < y; i++) {
				if (board.getXY(x, i) != 0) {
					return false;
				}
			}
			return true;
		}

		if (y == yCord && (x > xCord)) {
			for (int i = xCord + 1; i < x; i++) {
				if (board.getXY(i, y) != 0) {
					return false;
				}
			}
			return true;
		}
		if (y == yCord && (x < xCord)) {
			for (int i = xCord - 1; i > x; i--) {
				if (board.getXY(i, y) != 0) {
					return false;
				}
			}
			return true;
		}

		return false;

	}
	
	public void castleDone(int x, Board board) {
		if(x == 6) {
			board.updatePieces(xCord, yCord, x-1, yCord,this);
			xCord = x-1;
		}
		else {
			board.updatePieces(xCord, yCord, x+1, yCord,this);
			xCord = x+1;
		}
		hasMoved = true;
	}
	
	public boolean HasMoved() {
		return hasMoved;
	}

	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}

	public boolean isJustMoved() {
		return justMoved;
	}

	public void setJustMoved(boolean justMoved) {
		this.justMoved = justMoved;
	}

}
