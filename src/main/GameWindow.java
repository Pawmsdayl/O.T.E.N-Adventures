/**
 * CMSC 22 - 1
 * Class that is the window of the game
 * 
 * @author KV. Celis
 * @author PK. Cordero
 * @author JJ. Peñafiel
 * 
 */

package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;



public class GameWindow {
	
	private JFrame jframe;
	
	public GameWindow(GamePanel gamePanel) {
		
		jframe = new JFrame();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.add(gamePanel);
		jframe.setResizable(false);
		jframe.pack();
		jframe.setVisible(true);
        jframe.setFocusable(true);
		jframe.addWindowFocusListener(new WindowFocusListener() {
            
            @Override
			public void windowLostFocus(WindowEvent e) {
                gamePanel.getGame().windowFocusLost();
			}
			
			@Override
			public void windowGainedFocus(WindowEvent e) {
				
            }
		});
        jframe.setLocationRelativeTo(null); 
        
		
	}
}
