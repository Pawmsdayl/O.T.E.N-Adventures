/**
 * @author Jeremy Jobert Peñafiel
 */

package entities;

import static utilities.Constants.PlayerConstants.DOUBLE_JUMP;
import static utilities.Constants.PlayerConstants.GetNumberOfImages;
import static utilities.Constants.PlayerConstants.IDLE;
import static utilities.Constants.PlayerConstants.JUMP;
import static utilities.Constants.PlayerConstants.RUN;
import static utilities.HelperMethods.canMoveHere;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import levels.LevelOne;
import utilities.LoadSave;

public class Player extends Entity {

	private BufferedImage[] playerAnimations = new BufferedImage[4]; // array of all animations of the player
	private int playerAction = IDLE;// sprite images of player
	private int animationTicker = 0; // keeps count of how many frames have
	private int animationIndex = 0;
	private int animationSpeedFrames = 15; // number of frames that passes until a new animation image is shown
	private float playerSpeed = 2.0f;
	private boolean isPlayerMoving = false;
	private boolean isMovingLeft;
	private boolean isMovingRight;
	private boolean isMovingDown;
	private boolean isMovingUp;
	private Hitbox hitbox;
	private int[][] lvlData = new LevelOne().getLevelData();
	private int availableJumps = 2;

	public Player(float x, float y, int width, int height) {
		super(x, y, width, height);
		hitbox = new Hitbox((int) x, (int) y, width, height);
		setImages();
	}

	public void update() {
		updateAnimationTicker();
		setPlayerAction();
		updatePos();
		hitbox.update(x, y);
	}

	public void render(Graphics g) {

		BufferedImage subImg = playerAnimations[playerAction].getSubimage(animationIndex * 32, 0, 32, 32);
		g.drawImage(subImg, (int) x, (int) y, (int) (width),(int) (height), null);
		hitbox.drawHitbox(g);
	}
	
	public void loadLevelData(int[][] lvlData) {
		this.lvlData = lvlData;
	}

	/**
	 * Updates position of player
	 */
	public void updatePos() {
		isPlayerMoving = false;

		if (!isMovingDown && !isMovingUp && !isMovingLeft && !isMovingRight) {
			return;
		}
		
		float xSpeed = 0;
		float ySpeed = 0;

		if (isMovingLeft && !isMovingRight) {
			xSpeed = -playerSpeed;
		} else if (isMovingRight && !isMovingLeft) {
			xSpeed = playerSpeed;
		}
		if (isMovingUp && !isMovingDown) {
			ySpeed = -playerSpeed;
		} else if (isMovingDown && !isMovingUp) {
			ySpeed = playerSpeed;
		}
		
		if (canMoveHere(
				hitbox.getX() + xSpeed, 
				hitbox.getY() + ySpeed, 
				hitbox.getWidth(), 
				hitbox.getHeight(), 
				lvlData)) {
			this.x += xSpeed;
			this.y += ySpeed;
			isPlayerMoving = true;
		}
	}

	/**
	 * updates the animation index
	 * 
	 * @param numberOfAnimationImages - number of images a certain animation ahs
	 */
	public void updateAnimationTicker() {
		animationTicker++;
		if (animationTicker >= animationSpeedFrames) {
			animationTicker = 0;
			animationIndex++;
			if (animationIndex >= GetNumberOfImages(playerAction)) {
				animationIndex = 0;
			}
		}
	}

	/**
	 * 
	 */
	public void setPlayerAction() {
		int startAni = playerAction;
		if (isPlayerMoving) {
			if (isMovingUp) {
				playerAction = JUMP;
			} else {
				playerAction = RUN;
			}
		} else {
			playerAction = IDLE;
		}

		// When there is a new player action
		if (startAni != playerAction) {
			resetAnimationTicker();
		}
	}

	/**
	 * Resets the animation ticker and index to 0
	 */
	private void resetAnimationTicker() {
		animationTicker = 0;
		animationIndex = 0;
	}

	/**
	 * Sets image of player
	 */
	public void setImages() {
		playerAnimations[IDLE] = LoadSave.getPlayerAtlas(LoadSave.NINJA_FROG_FOLDER_NAME, LoadSave.PLAYER_IDLE);
		playerAnimations[RUN] = LoadSave.getPlayerAtlas(LoadSave.NINJA_FROG_FOLDER_NAME, LoadSave.PLAYER_RUN);
		playerAnimations[JUMP] = LoadSave.getPlayerAtlas(LoadSave.NINJA_FROG_FOLDER_NAME, LoadSave.PLAYER_JUMP);
		playerAnimations[DOUBLE_JUMP] = LoadSave.getPlayerAtlas(LoadSave.NINJA_FROG_FOLDER_NAME,
				LoadSave.PLAYER_DOUBLE_JUMP);
	}

	/**
	 * Stops the movement of player by setting movement booleans of player to false
	 */
	public void resetDirectionBooleans() {
		isMovingUp = false;
		isMovingDown = false;
		isMovingLeft = false;
		isMovingRight = false;
	}

	public boolean isMovingLeft() {
		return isMovingLeft;
	}

	public void setMovingLeft(boolean isMovingLeft) {
		this.isMovingLeft = isMovingLeft;
	}

	public boolean isMovingRight() {
		return isMovingRight;
	}

	public void setMovingRight(boolean isMovingRight) {
		this.isMovingRight = isMovingRight;
	}

	public boolean isMovingDown() {
		return isMovingDown;
	}

	public void setMovingDown(boolean isMovingDown) {
		this.isMovingDown = isMovingDown;
	}

	public boolean isMovingUp() {
		return isMovingUp;
	}

	public void setMovingUp(boolean isMovingUp) {
		this.isMovingUp = isMovingUp;
	}

}
