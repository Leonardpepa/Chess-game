import java.awt.Color;
import java.awt.Graphics;

public class Knight extends Piece{
	
	public Knight(int x, int y, boolean iswhite, Board board) {
		super(x, y, iswhite, board);
	}

	public void intializeSide() {
		if(isWhite) {
			valueInTheBoard = 1;
			pieceImage = PieceImages.KNIGHT;
			pieceColor = PieceImages.WHITECOLOR;
		}
		else {
			valueInTheBoard = 2;
			pieceImage = PieceImages.KNIGHT;
			pieceColor = PieceImages.BLACKCOLOR;
		}
	}
	
	
	
	public boolean makeMove(int toX,int toY) {
		if(canMove(toX, toY) && alive() && board.getXY(toX, toY) != valueInTheBoard) {
			board.updatePieces(xCord, yCord, toX, toY,this);
			xCord = toX;
			yCord = toY;
			return true;
		}
		return false;
		
	}

	
	public boolean canMove(int x ,int y) {
			if(x == xCord+1 && y == yCord-2 ) {
				return true;
			}
			if(x == xCord-1 && y == yCord-2 ) {
				return true;	
			}
			
			if(x == xCord-1 && y == yCord+2 ) {
				return true;	
			}
			if(x == xCord+1 && y == yCord+2 ) {
				return true;	
			}
			
			if(x == xCord+2 && y == yCord-1 ) {
				return true;	
			}
			if(x == xCord+2 && y == yCord+1 ) {
				return true;	
			}
			if(x == xCord-2 && y == yCord-1 ) {
				return true;	
			}
			if(x == xCord-2 && y == yCord+1 ) {
				return true;	
			}
	
		return false;
	}
	
	
	

	@Override
	public boolean alive() {
		if(board.getXY(xCord, yCord) != valueInTheBoard || board.getXY(xCord, yCord) == 0 ) {
			isAlive = false;
		}
		return isAlive;
	}
	
	

}
