
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class Panel extends JPanel {

	private static final long serialVersionUID = 1L;
	Game game;
	int ti,tj;
	int xx, yy;
	
	Panel(){
		this.setFocusable(true);
		this.addMouseListener(new Listener());
		this.addMouseMotionListener(new Listener());
		game = new Game();

	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.drawBoard(g);
		game.drawPiece(g);
		game.drawPossibleMoves(g);
		g.setColor(Color.BLACK);
		g.drawRect(ti*Piece.size, tj*Piece.size, Piece.size, Piece.size);
		game.dragAndDrop(game.active,xx, yy ,g);
	}
	

	class Listener extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {
//			game.Play(e);
			revalidate();
			repaint();
			
		}
		
		@Override
		public void mouseMoved(MouseEvent e) {
			ti = e.getX()/Piece.size;
			tj = e.getY()/Piece.size;
			if(Game.board.getPiece(e.getX()/Piece.size, e.getY()/Piece.size) != null) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			else {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			revalidate();
			repaint();
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			Game.drag = true;
			game.selectPiece(e.getX()/Piece.size, e.getY()/Piece.size);
			xx = e.getX();
			yy = e.getY();
			repaint();
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			int x = e.getX() / Piece.size;
			int y = e.getY() / Piece.size;
			
			if(game.active!=null && game.active.makeMove(x, y)) {
				System.out.println("moved");
			}
			Game.drag = false;
			game.active  = null;
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		
		
		
	}
	

	
	
	
	
	
	
}
