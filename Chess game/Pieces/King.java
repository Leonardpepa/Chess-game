import java.awt.Color;
import java.awt.Graphics;

public class King extends Piece {
	private boolean hasMoved;

	public King(int x, int y, boolean iswhite, Board board) {
		super(x, y, iswhite, board);
		hasMoved = false;
	}

	@Override
	boolean makeMove(int toX, int toY) {
		if((canMove(toX, toY) || castle(toX,toY)) && alive() && board.getXY(toX, toY) != valueInTheBoard) {
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
		
			if(x == xCord - 1 && y == yCord - 1 ) {
				if(!Game.attackedSquare(x, y,this.isWhite) ) {
					return true;
				}
				
			}
			if(x == xCord && y == yCord - 1 ) {
				if(!Game.attackedSquare(x, y,this.isWhite)) {
					return true;
				}
			}
			if(x == xCord+1 && y == yCord-1  ) {
				if(!Game.attackedSquare(x, y,this.isWhite)) {
					return true;
				}
			}
			if(x == xCord-1 && y == yCord  ) {
				if(!Game.attackedSquare(x, y,this.isWhite)) {
					return true;
				}
			}
			if(x == xCord+1 && y == yCord ) {
				if(!Game.attackedSquare(x, y,this.isWhite)) {
					return true;
				}
			}
			if(x == xCord-1 && y == yCord + 1 ) {
				if(!Game.attackedSquare(x, y,this.isWhite)) {
					return true;
				}
			}
			if(x == xCord && y == yCord + 1 ) {
				if(!Game.attackedSquare(x, y,this.isWhite)) {
					return true;
				}
			}
			if(x == xCord + 1 && y == yCord + 1) {
				if(!Game.attackedSquare(x, y,this.isWhite)) {
					return true;
				}
			}
	
		
		return false;
	}
	
	public boolean castle(int x,int y) {
		Rook tempRook;
		if(x < 4 && isWhite) {
			tempRook = Game.rookW2;
		}
		else if(x > 4 && isWhite) {
			tempRook = Game.rookW1;
		}
		else if(x < 4 && !isWhite) {
			tempRook = Game.rookB2;
		}
		else {
			tempRook = Game.rookB1;
		}
		
		if(!this.hasMoved && !tempRook.isHasMoved()) {
			if(board.getXY(xCord+1, yCord) == 0 && board.getXY(xCord+2, yCord) == 0) {
				if( !Game.attackedSquare(xCord+1, yCord, tempRook.isWhite) &&
					!Game.attackedSquare(xCord+2, yCord, tempRook.isWhite)
						)
				{
					if(y == yCord && x == xCord + 2) {
						board.updatePieces(tempRook.getXcord(), tempRook.getYcord(), tempRook.getXcord() - 2, tempRook.getYcord(), tempRook);
						tempRook.setXcord(tempRook.getXcord() - 2);
						tempRook.setHasMoved(true);
						board.updatePieces(xCord, yCord, x, y,this);
						xCord = x;
						yCord = y;
						return true;
					}
					
				}
			}
			else{
				if(board.getXY(xCord-1, yCord) == 0 && board.getXY(xCord-2, yCord) == 0 && board.getXY(xCord-3, yCord) == 0) 
					
					if( !Game.attackedSquare(xCord-1, yCord, tempRook.isWhite) &&
						!Game.attackedSquare(xCord-2, yCord, tempRook.isWhite) &&
						!Game.attackedSquare(xCord-3, yCord, tempRook.isWhite)
						) 
					{
						if(y == yCord && x == xCord - 2) {
							board.updatePieces(tempRook.getXcord(), tempRook.getYcord(), tempRook.getXcord() + 3, tempRook.getYcord(), tempRook);
							board.updatePieces(xCord, yCord, x-1, y,this);
							tempRook.setXcord(tempRook.getXcord() + 3);
							xCord = x-1;
							yCord = y;
							return true;
							}
						}
				
			}
		}
		return false;
	}
	
	 public static boolean kingsIncheck(int x,int y,boolean isWhite) {
	    	int i = x;
	    	int j = y;
	    	if(!isWhite) {
	    		for(int k=0;k<8;k++) {
	    			if(Game.pawnW[k].capture(i, j)) {
	    				return true;
	    			}
	    		}
	    		
	    		
		    	if(		Game.knightW1.canMove(i, j) ||
		    			Game.knightW2.canMove(i, j) ||
						Game.bishopW1.canMove(i, j) ||
						Game.bishopW2.canMove(i, j) ||
						Game.queenW.canMove(i, j)   ||
						Game.rookW1.canMove(i, i)   ||
						Game.rookW2.canMove(i, j))  
						 {
				
						return true;
				}
	    	}
	    	
	    	if(isWhite) {
	    		for(int k=0;k<8;k++) {
	    			if(Game.pawnB[k].capture(i, j)) {
	    				return true;
	    			}
	    		}
	    		
	    		
	    		if(		Game.knightB1.canMove(i, j) ||
						Game.knightB2.canMove(i, j) ||
						Game.bishopB1.canMove(i, j) ||
						Game.bishopB2.canMove(i, j) ||
						Game.queenB.canMove(i, j)   ||
						Game.rookB1.canMove(i, i)   ||
						Game.rookB2.canMove(i, j))
	    				
						 {
				
						return true;
				}
	    		
	    	}
	    	return false;
	    	
	    	
	    }
	

	@Override
	public void intializeSide() {
		if(isWhite) {
			valueInTheBoard = 1;
			pieceImage = PieceImages.KING;
			pieceColor = PieceImages.WHITECOLOR;
		}
		else {
			valueInTheBoard = 2;
			pieceImage = PieceImages.KING;
			pieceColor = PieceImages.BLACKCOLOR;
		}
		
	}

	@Override
	public boolean alive() {
		return  true;
	}

}
