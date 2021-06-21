

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Panel extends JPanel {

	private static final long serialVersionUID = 1L;
	Game game;
	int ti,tj;
	public static int xx, yy;
	
	Panel(){
		this.setFocusable(true);
		this.addMouseListener(new Listener());
		this.addMouseMotionListener(new Listener());
		game = new Game();

	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.draw(g, xx, yy, this);
	}
	

	

	class Listener extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {
			int x = e.getX()/Piece.size;
			int y = e.getY()/Piece.size;
			Game.drag = false;
			game.active = null;
			game.selectPiece(x, y);
			revalidate();
			repaint();
		}
		
		@Override
		public void mouseMoved(MouseEvent e) {
			ti = e.getX()/Piece.size;
			tj = e.getY()/Piece.size;
			if(Game.board.getPiece(ti, tj) != null) {
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
			if(SwingUtilities.isLeftMouseButton(e)) {
				Game.drag = true;
				game.selectPiece(e.getX()/Piece.size, e.getY()/Piece.size);
				xx = e.getX();
				yy = e.getY();
				revalidate();
				repaint();				
			}
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			int x = e.getX() / Piece.size;
			int y = e.getY() / Piece.size;
			game.move(x, y);
			revalidate();
			repaint();
		}

	}
	

	
	
	
	
	
	
}
