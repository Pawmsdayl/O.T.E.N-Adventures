/**
 * 
 * @author Jeremy Jobert Peñafiel
 *
 */
package main;

import java.awt.Graphics;
import gamestates.Gamestate;
import gamestates.Menu;
import gamestates.Playing;

public class Game implements Runnable {
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	private final int FPS_SET = 120; // Set frames per second
	private final int UPS_SET = 200; // Set updates per second
	private boolean running = false;
	
    private Playing playing;
    private Menu menu;

	public final static int TILE_DEFAULT_SIZE = 16;
	public final static float SCALE = 3f;
	public final static int TILES_IN_WIDTH = 26;
	public final static int TILES_IN_HEIGHT = 14;
	public final static int TILE_SIZE = (int) (TILE_DEFAULT_SIZE * SCALE);
	public final static int GAME_WIDTH = TILES_IN_WIDTH * TILE_SIZE;
	public final static int GAME_HEIGHT = TILES_IN_HEIGHT * TILE_SIZE;

	public Game() {
		initClasses();

		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus();

		startGameLoop();
	}

	private void initClasses() {
        menu = new Menu(this);
        playing = new Playing(this);
	}

	/**
	 * Starts the game
	 */
	private void startGameLoop() {
		running = true;
		gameThread = new Thread(this);
		gameThread.start();
	}

	/**
	 * updates the player and the levelManager
	 */
	public void update() {
        switch (Gamestate.state) {
            case MENU:
                menu.update();
                break;
            case PLAYING:
                playing.update();
                break;
            default:
                break;
        }
	}
	
	/**
	 * Renders the player and the levels
	 * @param g -  a Graphics object
	 */
	public void render(Graphics g) {
        switch (Gamestate.state) {
            case MENU:
                menu.draw(g);
                break;
            case PLAYING:
                playing.draw(g);
                break;
            default:
                break;
        }
    
	}

	/**
	 * Contains the game loop and FPS counter
	 */
	@Override
	public void run() {

		final double timePerFrameNanos = 1000000000.0 / FPS_SET;
		final double timePerUpdateNanos = 1000000000.0 / UPS_SET;

		long startTimeNanos = System.nanoTime();
		long frameTimerMillis = System.currentTimeMillis();
		double updateTimeDelta = 0;
		double framesTimeDelta = 0;
		int frames = 0;

		while (running) {

			long currentTimeNanos = System.nanoTime();
			updateTimeDelta += (currentTimeNanos - startTimeNanos);
			framesTimeDelta += (currentTimeNanos - startTimeNanos);
			startTimeNanos = currentTimeNanos;

			if (updateTimeDelta >= timePerUpdateNanos) {
				update();
				updateTimeDelta -= timePerUpdateNanos;
			}

			if (framesTimeDelta >= timePerFrameNanos) {
				gamePanel.repaint();
				frames++;
				framesTimeDelta -= timePerFrameNanos;
			}

			if (System.currentTimeMillis() - frameTimerMillis >= 1000) {
				//System.out.println(frames);
				frames = 0;
				frameTimerMillis += 1000;
			}
		}
	}
	
	public void windowFocusLost() {
		if (Gamestate.state == Gamestate.PLAYING) {
            playing.getPlayer().resetDirectionBooleans();
        }
	}

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }
}