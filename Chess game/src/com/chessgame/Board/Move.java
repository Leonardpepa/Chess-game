package com.chessgame.Board;
import com.chessgame.Pieces.Piece;

public class Move implements Comparable<Move>{
	int fromX, fromY, toX, toY;
	Piece piece;
	public Move(int fromX, int fromY, int toX, int toY, Piece piece) {
		this.fromX = fromX;
		this.fromY = fromY;
		this.toX = toX;
		this.toY = toY;
		this.piece = piece;
	}

	public int getFromX() {
		return fromX;
	}

	public void setFromX(int fromX) {
		this.fromX = fromX;
	}

	public int getFromY() {
		return fromY;
	}

	public void setFromY(int fromY) {
		this.fromY = fromY;
	}

	public int getToX() {
		return toX;
	}

	public void setToX(int toX) {
		this.toX = toX;
	}

	public int getToY() {
		return toY;
	}

	public void setToY(int toY) {
		this.toY = toY;
	}
	
	

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	@Override
	public int compareTo(Move o) {
		if(toX == o.getToX() && toY == o.getToY()) {
			return 0;
		}
		return -1;
	}
	
	public boolean equals(Object o) {
		Move otherM = (Move) o;
		if(this.getToX() == otherM.getToX() && this.getToY() == otherM.getToY() && this.getFromX() == otherM.getFromX() && this.getFromY() == otherM.getFromY()){
			return true;
		}
		return false;
	}
	
	
}
