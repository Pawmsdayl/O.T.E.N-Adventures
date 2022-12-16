package ui;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Rectangle;

import gamestates.Gamestate;
import utilities.LoadSave;
import static utilities.Constants.UI.Buttons.*;

public class MenuButton {
    private int xPos, yPos, rowIndex, index;
    private int xOffsetCenter = BT_WIDTH /2;
    private Boolean mouseOver, mousePressed;
    private Rectangle bounds;
    private BufferedImage[] imgs;
    private Gamestate state;

    public MenuButton(int xPos, int yPos, int rowIndex, Gamestate state) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.state = state;
        loadImgs();
        initBounds();
    }

    private void initBounds() {
        bounds = new Rectangle(xPos - xOffsetCenter, yPos, BT_WIDTH, BT_HEIGHT);
    }

    private void loadImgs() {
        imgs = new BufferedImage[3];
        // TODO: phota graphics HALP
        BufferedImage temp = LoadSave.getPlayerAtlas(LoadSave.BUTTON_FOLDER, LoadSave.MENU_BUTTONS); 
        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = temp.getSubimage(i * BT_WIDTH_DEFAULT, rowIndex * BT_HEIGHT_DEFAULT, BT_WIDTH_DEFAULT, BT_HEIGHT_DEFAULT);
        }
    }

    public void draw(Graphics g) {
        g.drawImage(imgs[index], xPos - xOffsetCenter, yPos, BT_WIDTH, BT_HEIGHT, null);
    }

    public void update() {
        index = 0;
        if (mouseOver) {
            index = 1;
        }

        if (mousePressed) {
            index = 2;
        }
    }

    public boolean isMouseOver() {
		return mouseOver;
	}

	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public boolean isMousePressed() {
		return mousePressed;
	}

	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void applyGamestate() {
		Gamestate.state = state;
	}

	public void resetBools() {
		mouseOver = false;
		mousePressed = false;
	}

}