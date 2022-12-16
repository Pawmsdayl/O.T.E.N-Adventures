package gamestates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import main.Game;
import ui.MenuButton;

public class Menu extends State implements Statemethods {
    
    private MenuButton[] buttons = new MenuButton[3];
    
    public Menu(Game game) { 
        super(game);
        loadButtons();
    }
    
    private void loadButtons() {
        buttons[0] = new MenuButton(Game.GAME_WIDTH /2, (int) (150 * Game.SCALE), 0, Gamestate.PLAYING);
        buttons[1] = new MenuButton(Game.GAME_WIDTH /2, (int) (220 * Game.SCALE), 1, Gamestate.CREDITS);
        //buttons[2] = new MenuButton(Game.GAME_WIDTH /2, (int) (290 * Game.SCALE), 2, Gamestate.QUIT);
    }
    
    @Override
    public void update() {
        // TODO Auto-generated method stub
        for (MenuButton btMenu : buttons) {
            btMenu.update();
        }       
    }

    @Override
    public void draw(Graphics g) {
        // TODO Auto-generated method stub
        for (MenuButton btMenu : buttons) {
            btMenu.draw(g);
        }       
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            Gamestate.state = Gamestate.PLAYING;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        for (MenuButton btMenu : buttons) {
            btMenu.setMouseOver(false);
        }        

        for (MenuButton btMenu : buttons) {
            if (isIn(e, btMenu)) {
                btMenu.setMouseOver(true);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        for (MenuButton btMenu : buttons) {
            if(isIn(e, btMenu)) {
                btMenu.setMousePressed(true);
                break;
            }
        }       
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        for (MenuButton btMenu : buttons) {
            if(isIn(e, btMenu)) {
                if(btMenu.isMousePressed()) {
                    btMenu.applyGamestate();
                }
            }
        }    
        
        resetButtons();
    }

    private void resetButtons() {
        for (MenuButton btMenu : buttons) {
            btMenu.resetBools();
        }
    }

    
}
