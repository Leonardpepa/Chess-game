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
		if(canMove(toX, toY) && alive() && board.getXY(toX, toY) != valueInTheBoard) {
			if(!isInCheck(toX, toY)){
				board.updatePieces(xCord, yCord, toX, toY,this);
				xCord = toX;
				yCord = toY;
				hasMoved = true;
				isInCheck(this.xCord, this.yCord);
				return true;
			}
		}
		return castle(toX, toY);
	}

	@Override
	public boolean canMove(int x, int y) {
		
			if(x == xCord - 1 && y == yCord - 1 ) {
					return true;
			}
			if(x == xCord && y == yCord - 1 ) {
				return true;
			}
			if(x == xCord+1 && y == yCord-1  ) {
				return true;
			}
			if(x == xCord-1 && y == yCord  ) {
				return true;
			}
			if(x == xCord+1 && y == yCord ) {
				return true;
			}
			if(x == xCord-1 && y == yCord + 1 ) {
				return true;
			}
			if(x == xCord && y == yCord + 1 ) {
				return true;
			}
			if(x == xCord + 1 && y == yCord + 1) {
				return true;
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
		
		if(!this.hasMoved && !tempRook.HasMoved()) {
			if(board.getXY(xCord+1, yCord) == 0 && board.getXY(xCord+2, yCord) == 0) {
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
			
			else 
			{
				
				if(board.getXY(xCord-1, yCord) == 0 && board.getXY(xCord-2, yCord) == 0 && board.getXY(xCord-3, yCord) == 0) 
					
						if(y == yCord && x == xCord - 2) {
							board.updatePieces(tempRook.getXcord(), tempRook.getYcord(), tempRook.getXcord() + 3, tempRook.getYcord(), tempRook);
							board.updatePieces(xCord, yCord, x, y,this);
							tempRook.setXcord(tempRook.getXcord() + 3);
							xCord = x;
							yCord = y;
							return true;
						}
								
							
				}
		}
		return false;
	}

	public boolean isInCheck(int x, int y){
		Piece p;
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				p = board.getPiece(i, j);
				if(p!=null && (p.isWhite() != this.isWhite()) && p.canMove(x, y)){
					System.out.println("i am in check");
					return true;
				}
			}	
		}
		
		return false;
	}
	
	public void showMoves(Graphics g) {
		g.setColor(Color.CYAN);
		for(int i=0; i<8; i++) {
    		for(int j=0; j<8; j++) {
    			if(!isInCheck(i, j) &&  this.canMove(i, j) && board.getXY(i, j) != valueInTheBoard ) {
    				g.drawRect(i*size, j*size, size, size);
    			}
    		}
    	}
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
