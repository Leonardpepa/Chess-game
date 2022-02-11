package com.chessgame.Pieces;
import com.chessgame.Board.Board;
import com.chessgame.Board.Move;
import com.chessgame.Game.Game;

public class Pawn extends Piece {
	private boolean firstMove;
	private boolean moved2Squares = false;
	
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
	
	public boolean makeMove(int toX, int toY, Board board) {
		Move move = new Move(xCord, yCord, toX, toY, this);
		if(!alive()) {
			return false;
		}
		if(moves.contains(move)) {
			
			if(toX == xCord + 1 && yCord -(isWhite ? 1 : -1) == toY) {
				if(board.getXY(toX, toY) == 0) {
					Game.AllPieces.remove(board.getPiece(xCord + 1, yCord));
					Game.fillPieces();
					board.setXY(xCord + 1, yCord, 0);
					board.setPieceIntoBoard(xCord + 1, yCord, null);
				}
			}
			
			if(toX == xCord -1 && yCord -(isWhite ? 1 : -1) == toY) {
				if(board.getXY(toX, toY) == 0) {
					Game.AllPieces.remove(board.getPiece(xCord - 1, yCord));
					Game.fillPieces();
					board.setXY(xCord -1, yCord, 0);
					board.setPieceIntoBoard(xCord - 1, yCord, null);
				}
			}
			
			
			if(firstMove && Math.abs((yCord - toY)) == 2){
				moved2Squares = true;
			}
			removeEnpassant();
			board.updatePieces(xCord, yCord, toX, toY,this);
			xCord = toX;
			yCord = toY;	
			firstMove = false;
			return true;
		}

		return false;
		
	}
	
	private void removeEnpassant() {
		for(Piece p: Game.AllPieces) {
			if(p instanceof Pawn && p != this) {
				((Pawn) p ).setMoved2Squares(false);
			}
		}
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
		int enpassant = 0;
		
		if(isWhite) {
			enpassant = -1;
		}
		else {
			enpassant = 1;
		}
		
		if(xCord > 0 && xCord  < 7) {
			if(board.getXY(xCord + 1, yCord) == enpassant) {
				Pawn leftPawn = (Pawn) board.getPiece(xCord + 1, yCord);
				if(x == leftPawn.getXcord() && y == leftPawn.getYcord() + enpassant && leftPawn.isMoved2Squares()) {
					return true;
				}
			}
			
			if(board.getXY(xCord - 1, yCord) == enpassant) {
				Pawn rightPawn = (Pawn) board.getPiece(xCord - 1, yCord);
				if(x == rightPawn.getXcord() && y == rightPawn.getYcord() + enpassant && rightPawn.isMoved2Squares()) {
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
	
	public void removeEnpassanCapturedpiece(int x, int y) {
		
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
	
	
	
}
