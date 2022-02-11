package com.chessgame.Pieces;
import com.chessgame.Board.Board;

public class Queen extends Piece {

	public Queen(int x, int y, boolean iswhite, Board board, int value) {
		super(x, y, iswhite, board, value);
		this.pieceImage = PieceImages.QUEEN;
	}
	

	public void intializeSide(int value){
		super.intializeSide(value);
		if(isWhite()) {
			image = PieceImages.wq;
		}
		else {
			image = PieceImages.bq;
		}
	}


	@Override
	public boolean canMove(int x, int y, Board board) {
			
			if(board.getPiece(x, y) != null && board.getPiece(x, y).isWhite() == isWhite()) {
				return false;
			}
		
			if(Math.abs(x-xCord) == Math.abs( y-yCord)) {
				return (queenMovesDiagonial(x, y, board));
			}
			if( x == xCord  || y == yCord ) {
				return queenMovesStraight(x, y, board);
			}
			
		return false;
	}
	
	public boolean queenMovesStraight(int x,int y, Board board) {
			if(x == xCord && (y<yCord )) {
					for(int i=yCord-1; i>y;i--) {
						if(board.getXY(x, i) != 0) {
							return false;
						}
					}
					return true;
			}
			
			if(x == xCord && (y>yCord )) {
				for(int i=yCord+1; i<y;i++) {
					if(board.getXY(x, i) != 0) {
						return false;
					}
				}
				return true;
			}
			
			if(y == yCord && (x>xCord )) {
				for(int i=xCord+1; i<x;i++) {
					if(board.getXY(i, y) != 0) {
						return false;
					}
				}
				return true;
			}
			if(y == yCord && (x<xCord )) {
				for(int i=xCord-1; i>x;i--) {
					if(board.getXY(i, y) != 0) {
						return false;
					}
				}
				return true;
			}
			
		
		
		return false;
		
	}
	
	public boolean queenMovesDiagonial(int x,int y, Board board) {
		if(x > xCord && y > yCord) {
			int j = yCord+1;
			for(int i=xCord+1; i<x; i++) {
				if(board.getXY(i, j) != 0) {
					return false;
				}
				j++;
			}
			
		}
		else if(x < xCord && y < yCord) {
			int j = yCord-1;
			for(int i=xCord-1; i>x; i--) {
				if(board.getXY(i, j) != 0) {
					return false;
				}
				j--;
			}
			
		}
		else if(x > xCord && y < yCord) {
			int j = yCord - 1;
			for(int i=xCord+1; i<x; i++) {
				if(board.getXY(i, j) != 0) {
					return false;
				}
				j--;
			}
		}
		else if(x < xCord && y > yCord) {
			int j = yCord+1;
			for(int i=xCord-1; i>x; i--) {
				if(board.getXY(i, j) != 0) {
					return false;
				}
				j++;
			}
		}
		return true;
	}
		

}
