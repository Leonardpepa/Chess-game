import java.awt.Color;
import javax.swing.ImageIcon;

public class PieceImages {
	 	static Color WHITECOLOR = Color.WHITE;
	 	static Color BLACKCOLOR = Color.BLACK;
	    static String  PAWN = "♟";
		static String  ROOK = "♜";
		static String  KNIGHT = "♞";
		static String  BISHOP = "♝";
		static String 	QUEEN = "♛";
		static String  KING = "♚";
		
		static ImageIcon wk;
		static ImageIcon bk;
		static ImageIcon wr;
		static ImageIcon br;
		static ImageIcon wq;
		static ImageIcon bq;
		static ImageIcon wb;
		static ImageIcon bb;
		static ImageIcon wn;
		static ImageIcon bn;
		static ImageIcon wp;
		static ImageIcon bp;
		
		public PieceImages() {
			wk = new ImageIcon(getClass().getResource("resources/images/wk.png"));
			bk = new ImageIcon(getClass().getResource("resources/images/bk.png"));
			wr = new ImageIcon(getClass().getResource("resources/images/wr.png"));
			br = new ImageIcon(getClass().getResource("resources/images/br.png"));
			wq = new ImageIcon(getClass().getResource("resources/images/wq.png"));
			bq = new ImageIcon(getClass().getResource("resources/images/bq.png"));
			wb = new ImageIcon(getClass().getResource("resources/images/wb.png"));
			bb = new ImageIcon(getClass().getResource("resources/images/bb.png"));
			wn = new ImageIcon(getClass().getResource("resources/images/wn.png"));
			bn = new ImageIcon(getClass().getResource("resources/images/bn.png"));
			wp = new ImageIcon(getClass().getResource("resources/images/wp.png"));
			bp = new ImageIcon(getClass().getResource("resources/images/bp.png"));
		}
}
