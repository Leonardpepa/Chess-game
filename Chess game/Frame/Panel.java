
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Panel extends JPanel implements MouseListener ,MouseMotionListener{

	private static final long serialVersionUID = 1L;
	Game game;
	int ti,tj;
	
	Panel(){
		this.setFocusable(true);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		game = new Game();

	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.drawBoard(g);
		game.drawPiece(g);
		game.drawPossibleMoves(g);
		g.drawRect(ti*Piece.size, tj*Piece.size, Piece.size, Piece.size);
	
	}
	

	
	
	

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		game.Play(e);
		repaint();
		System.out.println();
		for(int j=0;j<8;j++) {
			for(int i=0;i<8;i++) {
				System.out.print(game.board.getXY(i, j) + " ");
			}
			System.out.println();
		}
		
	
		
	
}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		ti = e.getX()/Piece.size;
		tj = e.getY()/Piece.size;
		repaint();
		
	}

	
	
	
	
	
	
}
