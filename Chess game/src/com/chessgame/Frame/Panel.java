package com.chessgame.Frame;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.chessgame.Game.*;
import com.chessgame.Pieces.Piece;

public class Panel extends JPanel {

	private static final long serialVersionUID = 1L;
	Game game;
	int ti,tj;
	public static int xx, yy;
	JPanel panel = this;
	
	Panel(){
		this.setFocusable(true);
		this.addMouseListener(new Listener());
		this.addMouseMotionListener(new Listener());
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 37) {
					Game.board.undoMove();
				}
			}
		});
		game = new Game();

	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.draw(g, xx, yy, this);
	}
	

	

	class Listener extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {
			if(SwingUtilities.isLeftMouseButton(e)) {
				int x = e.getX()/Piece.size;
				int y = e.getY()/Piece.size;
				Game.drag = false;
				game.active = null;
				game.selectPiece(x, y);
				revalidate();
				repaint();
			}
		}
		
		@Override
		public void mouseMoved(MouseEvent e) {
			// temp index i and j for the gui
			ti = e.getX()/Piece.size;
			tj = e.getY()/Piece.size;
			if(Game.board.getPiece(ti, tj) != null)  {
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
			if(!Game.drag && game.active != null) {
				game.active = null;
			}
			if(SwingUtilities.isLeftMouseButton(e)) {
				game.selectPiece(e.getX()/Piece.size, e.getY()/Piece.size);
				Game.drag = true;
				xx = e.getX();
				yy = e.getY();				
			}
			revalidate();
			repaint();
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
