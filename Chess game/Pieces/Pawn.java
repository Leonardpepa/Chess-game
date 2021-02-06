import java.awt.Color;
import java.awt.Graphics;

public class Pawn extends Piece {
	private boolean firstMove;
	private boolean hasReachedTheEnd;
	public Pawn(int x, int y, boolean iswhite, Board board) {
		super(x, y, iswhite, board);
		firstMove = true;
		hasReachedTheEnd = false;
	}

	public void intializeSide() {
		if(isWhite) {
			valueInTheBoard = 1;
			pieceImage = PieceImages.PAWN;
			pieceColor = PieceImages.WHITECOLOR;
		}
		else {
			valueInTheBoard = 2;
			pieceImage = PieceImages.PAWN;
			pieceColor = PieceImages.BLACKCOLOR;
		}
	}
	
	
	public boolean makeMove(int toX,int toY) {
		if(canMove(toX, toY) && alive())  {
			if(board.getXY(toX, toY) != valueInTheBoard) {
				board.updatePieces(xCord, yCord, toX, toY, this);
				xCord = toX;
				yCord = toY;
				firstMove = false;
				return true;
				}
		}
		return false;
		
	}

	
	public boolean canMove(int x ,int y) {
		if(isWhite ) {
			if(firstMove){
				if(x == xCord && (y == yCord-1 || y == yCord-2) && board.getXY(xCord, yCord-1)==0 ) {
					return true;
				}
			}
			if(x == xCord && y == yCord-1 && board.getXY(x, y)==0) {
				return true;
			}
			if( board.getXY(x, y) != 0 && board.getXY(x, y) != valueInTheBoard){
				if(y == yCord-1 && x == xCord+1 ) {
					return true;
				}
			
				if(y == yCord-1 && x == xCord-1 ) {
						return true;
				}
		}
		}
		 if(!isWhite) {
			if(firstMove){		
				if(x == xCord && (y == yCord+1 || y == yCord+2) && board.getXY(xCord, yCord+1)==0 ) {
						return true;
				}
			}
			if(x == xCord && y == yCord+1 && board.getXY(x, y)==0) {
				return true;
			}

			if(board.getXY(x, y) != 0 && board.getXY(x, y) != valueInTheBoard){
				if(y == yCord+1 && x == xCord+1 ) {
					return true;
				}
				if(y == yCord+1 && x == xCord-1 ) {
					return true;
				}
			}
		}		
		return false;
	}
	
	@Override
	public void showMoves(Graphics g) {
		g.setColor(Color.CYAN);
		for(int i=0; i<8; i++) {
    		for(int j=0; j<8; j++) {
    				if(canMove(i, j) && board.getXY(i, j) == 0) {
    					g.drawRect(i*size, j*size, size, size);
    				}
    		}
    	}
	}

	@Override
	public boolean alive() {
		if(board.getXY(xCord, yCord) != valueInTheBoard || board.getXY(xCord, yCord) == 0 ) {
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

	public boolean isHasReachedTheEnd() {
		return hasReachedTheEnd;
	}

	public void setHasReachedTheEnd(boolean hasReachedTheEnd) {
		this.hasReachedTheEnd = hasReachedTheEnd;
	}
	
	
	

	
	
	
	
	
	
	
}
