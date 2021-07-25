import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;

public class Main {
	
	public static void main(String[] args) throws InvocationTargetException, InterruptedException {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Frame();				
			}
		});
	}


}
