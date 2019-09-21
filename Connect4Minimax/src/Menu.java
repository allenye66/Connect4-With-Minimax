
import java.awt.Color;
import java.awt.Font;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
/**
 * Menu state that shows the various options for the user to choose from, PvAI, PVP, Instructions
 *
 * @author Shasta Narayanan, Allen Ye, Evan Hwang
 * @version 5/30/19
 *
 * @author Period - 1
 * @author Assignment - Final Project- Connect4
 * 
 */
public class Menu extends BasicGameState {

	// instructions and play button

	// private Image pvp;
	// private Image pvai=null;
	static int depth = 1;

	@Override
	/**
	 * initializes the state
	 */
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {

	}

	@Override
	/**
	 * renders the menu screen
	 */
	public void render(GameContainer container, StateBasedGame sbd, Graphics g) throws SlickException {
		container.setShowFPS(false);
		Image background = new Image("C:\\Users\\NARAVENK\\eclipse-workspace\\APCSFinalProject\\src\\connect4\\b1.png");
		g.drawImage(background, 0, 0);

		Image pvai = new Image("C:\\Users\\NARAVENK\\eclipse-workspace\\APCSFinalProject\\src\\connect4\\pvai.png");
		g.drawImage(pvai, 200, 200);
		g.drawRect(140, 300, 200, 130);
		g.drawString("Difficulty:", 195, 305);
		g.drawString("1: Press '1'", 190, 330);
		g.drawString("2: Press '2'", 190, 350);
		g.drawString("3: Press '3'", 190, 370);

		g.drawString("Player VS AI", 185, 400);
		// g.drawImage(pvp, 140, 200);

		Image pvp = new Image("C:\\Users\\NARAVENK\\eclipse-workspace\\APCSFinalProject\\src\\connect4\\pvp.png");
		g.drawImage(pvp, 410, 200);
		g.drawRect(350, 300, 200, 130);
		g.drawString("press SPACE for", 380, 340);
		g.drawString("Player VS Player", 375, 360);
	
		
		g.drawString("Connect 4", 310, 50);
		g.drawString("Select a mode", 290, 150);
		g.drawString("Press I for instructions", 240, 500);

	}

	@Override
	/**
	 * updates the main screen
	 */
	public void update(GameContainer container, StateBasedGame sbg, int arg2) throws SlickException {
		// TODO Auto-generated method stub


		// space - pvp
		// i - instructions
		if (container.getInput().isKeyPressed(Input.KEY_1)) {
			depth = 2;
			sbg.enterState(3, new FadeOutTransition(), new FadeInTransition());
			// sbg.enterState(1);
		}

		if (container.getInput().isKeyPressed(Input.KEY_2)) {
			depth = 3;
			sbg.enterState(3, new FadeOutTransition(), new FadeInTransition());
			// sbg.enterState(1);
		}

		if (container.getInput().isKeyPressed(Input.KEY_3)) {
			depth = 4;
			sbg.enterState(3, new FadeOutTransition(), new FadeInTransition());
			// sbg.enterState(1);
		}
		if (container.getInput().isKeyPressed(Input.KEY_SPACE)) {
			depth=5;
			sbg.enterState(3, new FadeOutTransition(), new FadeInTransition());
			// sbg.enterState(1);
		}
		if (container.getInput().isKeyPressed(Input.KEY_I)) {
			sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
			// sbg.enterState(1);
		}

	}


	@Override
	/**
	 * returns the state id
	 */
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}

}