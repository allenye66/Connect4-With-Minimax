package connect4;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
/**
 * Main class, creates all states (objects) and sets frame height and width
 *
 * @author Shasta Narayanan, Allen Ye, Evan Hwang
 * @version 5/30/19
 *
 * @author Period - 1
 * @author Assignment - Final Project- Connect4
 * 
 */
public class Main extends StateBasedGame {

	/**
	 * height of frame
	 */
	public static final int CONTAINER_HEIGHT = 600;

	/**
	 * Width of frame
	 */
	public static final int CONTAINER_WIDTH = 700;

	/**
	 * 
	 * @param name
	 *            the string of the title bar
	 */
	public Main(String name) {
		super(name);
	}

	/**
	 * thread starts here
	 * 
	 * @param args
	 *            string parameter for thread
	 * @throws SlickException
	 *             exception for slick2d
	 */
	public static void main(String[] args) throws SlickException {

		AppGameContainer app = new AppGameContainer(new Main("Connect 4"));

		app.setDisplayMode(CONTAINER_WIDTH, CONTAINER_HEIGHT, false);
		app.setTargetFrameRate(60);
		app.start();

		// boolean end = false;
		// int val = 21;
		// boolean first = true;
		// while(!end) {
		// System.out.println("Current position = "+ val +", Player one: " + first);
		// MinimaxTemplate.State s = new MinimaxTemplate.State(val, true);
		// MinimaxTemplate.State decision = MinimaxTemplate.minimaxDecision(s);
		// val = decision.state;
		// if(decision.isTerminal()){
		// end = true;
		// System.out.println("Current position = "+ val +", Player one won: " + first);
		// System.out.println("Game over");
		// }
		// first =! first;
		// }
	}

	@Override
	/**
	 * Creates the various states
	 */
	public void initStatesList(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub

		this.addState(new Menu());
		this.addState(new Playing());
		//this.addState(new GameOver());
		this.addState(new Instructions());
		//this.addState(new Playing2());

	}

}