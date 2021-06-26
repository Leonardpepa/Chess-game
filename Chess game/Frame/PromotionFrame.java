import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PromotionFrame {
	
	public static void  popUpPieces(Color color, Piece p) {
		JFrame frame = new JFrame("Choose piece");
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,2, 1, 1));
		frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		
		JButton rook = new JButton(PieceImages.ROOK);
		rook.setFont(new Font(Font.DIALOG, Font.BOLD, 75));
		rook.setForeground(color);
		rook.setBackground(new Color(182, 142, 96));
		rook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game.choosePiece(p, 1);
				Game.generateAllEnemysMoves(Game.board);
				Game.generateOnePlayerMoves(Game.board);
				Game.checkPlayersLegalMoves();
				frame.dispose();
			}
		});
		
		
		JButton queen = new JButton(PieceImages.QUEEN);
		queen.setFont(new Font(Font.DIALOG, Font.BOLD, 75));
		queen.setForeground(color);
		queen.setBackground(new Color(182, 142, 96));
		queen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game.choosePiece(p, 0);
				Game.generateAllEnemysMoves(Game.board);
				Game.generateOnePlayerMoves(Game.board);
				Game.checkPlayersLegalMoves();
				frame.dispose();
			}
		});
		
		JButton knight = new JButton(PieceImages.KNIGHT);
		knight.setFont(new Font(Font.DIALOG, Font.BOLD, 75));
		knight.setForeground(color);
		knight.setBackground(new Color(113, 144, 58));
		knight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game.choosePiece(p, 2);
				Game.generateAllEnemysMoves(Game.board);
				Game.generateOnePlayerMoves(Game.board);
				Game.checkPlayersLegalMoves();
				frame.dispose();
			}
		});
		
		JButton bishop = new JButton(PieceImages.BISHOP);
		bishop.setFont(new Font(Font.DIALOG, Font.BOLD, 75));
		bishop.setForeground(color);
		bishop.setBackground(new Color(113, 144, 58));
		bishop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game.choosePiece(p, 3);
				Game.generateAllEnemysMoves(Game.board);
				Game.generateOnePlayerMoves(Game.board);
				Game.checkPlayersLegalMoves();
				frame.dispose();
			}
		});
		
		panel.add(bishop);
		panel.add(queen);
		panel.add(rook);
		panel.add(knight);
		
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setContentPane(panel);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}
