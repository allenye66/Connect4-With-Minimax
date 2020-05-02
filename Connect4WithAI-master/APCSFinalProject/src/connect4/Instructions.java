package connect4;

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
 * Instructions state that shows the instructions for how to play the game. 
 *
 * @author Shasta Narayanan, Allen Ye, Evan Hwang
 * @version 5/30/19
 *
 * @author Period - 1
 * @author Assignment - Final Project- Connect4
 * 
 */
public class Instructions extends BasicGameState {

	// instructions and play button

	/**
	 * initializes the state
	 */
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * renders the instruction screen
	 */
	public void render(GameContainer container, StateBasedGame sbd, Graphics g) throws SlickException {
		container.setShowFPS(false);

		Image background = new Image("C:\\Users\\NARAVENK\\eclipse-workspace\\APCSFinalProject\\src\\connect4\\b1.png");
		g.drawImage(background, 0, 0);
		g.drawString("This is how to play", 250, 50);
		g.drawString("There are two game modes. You can chose to play against the AI (computer)", 30, 155);
		g.drawString("or against another player. There are 3 AI dificulties to chose from.", 40, 175);
		g.drawString("Press 1,2, or 3 to play against the AI, or SPACE to play", 70, 195);
		g.drawString("against another person. Press BACKSPACE at any time to", 75, 215);
		g.drawString("go to the main menu. The first person to connect 4 in a row", 45, 235);
		g.drawString("wins the game. Use your mouse and select the row in which you want", 35, 255);
		g.drawString("to drop your chip. This is a turn based game, so players will alternate", 30, 275);
		g.drawString("placing their piece. The winner will be printed at the top!", 60, 295);


		g.drawString("Press BACKSPACE to go back", 250, 380);
	}

	@Override
	/**
	 * updates the state, gets key board input
	 */
	public void update(GameContainer container, StateBasedGame sbg, int arg2) throws SlickException {
		// TODO Auto-generated method stub
		if (container.getInput().isKeyPressed(Input.KEY_BACK)) {
			sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
			// sbg.enterState(1);
		}

	}

	@Override
	/**
	 * returns state id
	 */
	public int getID() {
		// TODO Auto-generated method stub
		return 2;
	}

}
