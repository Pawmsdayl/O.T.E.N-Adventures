package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import entities.Player;
import levels.LevelManager;
import main.Game;

public class Playing extends State implements Statemethods {
    private Player player;
	private LevelManager levelManager;
 
    public Playing(Game game) {
        super(game); 
        initClasses();
    }

    private void initClasses() {
		levelManager = new LevelManager(game);
		player = new Player(200, 200, (int) (64 *Game.SCALE), (int) (64 *Game.SCALE));
		player.loadLevelData(levelManager.getCurrentLevel().getLevelData());
	}

    public Player getPlayer() {
		return player;
	}

	public void windowFocusLost() {
		player.resetDirectionBooleans();
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        levelManager.update();
        player.update();
        
    }

    @Override
    public void draw(Graphics g) {
        // TODO Auto-generated method stub
        levelManager.draw(g);
        player.render(g);
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        switch (e.getKeyCode()) {
			case KeyEvent.VK_SPACE:
			case KeyEvent.VK_W:
				player.setMovingUp(true);
				break;

			case KeyEvent.VK_A:
				player.setMovingLeft(true);
				break;

			case KeyEvent.VK_D:
				player.setMovingRight(true);
				break;

            case KeyEvent.VK_BACK_SPACE:
                Gamestate.state = Gamestate.MENU;
                break;
		}
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        switch (e.getKeyCode()) {
			case KeyEvent.VK_SPACE:
			case KeyEvent.VK_W:
				player.setMovingUp(false);
				break;
			
            case KeyEvent.VK_A:
				player.setMovingLeft(false);
				break;
			
            case KeyEvent.VK_D:
				player.setMovingRight(false);
				break;
		}
    }
}