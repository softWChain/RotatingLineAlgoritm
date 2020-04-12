package window;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {
	
	private JFrame frame;
	
	public Window(int width,int height,Game game){
		
		frame = new JFrame();
		
		game.setPreferredSize(new Dimension(width,height));
		game.setMaximumSize(new Dimension(width,height));
		game.setMinimumSize(new Dimension(width,height));
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(game);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
