package connect4;

import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.Color;
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
 * Playing class that creates the playing interface. 
 *
 * @author Shasta Narayanan, Allen Ye, Evan Hwang
 * @version 5/30/19
 *
 * @author Period - 1
 * @author Assignment - Final Project- Connect4
 * 
 */
public class Playing extends BasicGameState {

	private Image board = null;
	Engine engine;

	int AI = 2;
	int row = 6;
	int col = 7;

	/**
	 * Empty constructor
	 **/

	public Playing() {

	}

	/**
	 * Creates a new engine object and creates the board
	 * 
	 * @param arg0
	 *            is the game container
	 * @param arg1
	 *            is the state based game
	 **/

	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		board = new Image("C:\\Users\\NARAVENK\\eclipse-workspace\\APCSFinalProject\\src\\connect4\\connect4grid.png");
		engine = new Engine(row, col);
		// engine.makeMove(0);
		// engine.makeMove(1);

		engine.print();
	}

	/*
	 * function minimax(board, depth, isMaximizingPlayer):
	 * 
	 * if current board state is a terminal state : return value of the board
	 * 
	 * if isMaximizingPlayer : bestVal = -INFINITY for each move in board : value =
	 * minimax(board, depth+1, false) bestVal = max( bestVal, value) return bestVal
	 * 
	 * else : bestVal = +INFINITY for each move in board : value = minimax(board,
	 * depth+1, true) bestVal = min( bestVal, value) return bestVal
	 */
	/**
	 * This method runs the AI using a minmax algorithm
	 * 
	 * @param search_depth
	 *            is how deep the AI will search for the best move
	 * @param maximizingPlayer
	 *            is true if that move is the best possible move
	 * @param player_id
	 *            is the player id
	 * @return the column as an array
	 **/

	public int[] minmax(int search_depth, boolean maximizingPlayer, int player_id) {
		int[] column_score = new int[2];
		column_score[0] = -1;

		int winner = engine.connected();
		if (winner != 0) {
			int score = 1000 * (winner == player_id ? 1 : -1);
			System.out.println("score: " + score);
			column_score[1] = score;
			return column_score;
		}

		if (search_depth == 0) {
			column_score[1] = engine.evaluateBoardScore(player_id);
			return column_score;
		}

		ArrayList<Integer> eligibleMoves = engine.eligibleMoves();
		if (eligibleMoves.isEmpty()) {
			return column_score;
		}
		if (maximizingPlayer) {
			int best_val = -100000;
			for (int i = 0; i < eligibleMoves.size(); i++) {
				engine.makeMove(eligibleMoves.get(i));
				System.out.println("search_depth: " + search_depth);
				engine.print();
				int[] results = minmax(search_depth - 1, !maximizingPlayer, player_id);

				if (results[1] > best_val) {
					best_val = results[1];
					column_score[1] = best_val;
					column_score[0] = eligibleMoves.get(i);
				}
				System.out.println("Best Value: " + best_val + " at depth " + search_depth);
				engine.undo();
			}
		} else {
			int best_val = 100000;
			for (int i = 0; i < eligibleMoves.size(); i++) {
				engine.makeMove(eligibleMoves.get(i));
				System.out.println("search_depth: " + search_depth);
				engine.print();
				int[] results = minmax(search_depth - 1, !maximizingPlayer, player_id);
				if (results[1] < best_val) {
					best_val = results[1];
					column_score[1] = best_val;
					column_score[0] = eligibleMoves.get(i);
				}
				System.out.println("Best Value: " + best_val + " at depth " + search_depth);
				engine.undo();
			}
		}
		return column_score;
	}

	/**
	 * Updates the board and the visuals
	 * 
	 * @param container
	 *            is the Gamecontainer
	 * @param sbg
	 *            is the state based game
	 * @param arg2
	 *            is an integer argument
	 **/

	public void update(GameContainer container, StateBasedGame sbg, int arg2) throws SlickException {

		if (container.getInput().isKeyPressed(Input.KEY_BACK)) {
			engine.reset();
			sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
		}
		if (container.getInput().isKeyPressed(Input.KEY_0)) {

			engine.undo();

			// engine.print();

		}

		if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {

			System.out.println(container.getInput().getMouseX() + " ," + container.getInput().getMouseY());

			int pcol = container.getInput().getMouseX();
			pcol = (pcol - engine.shiftLeft + 20) / 75;
			boolean pfull = true;

			for (int i = 0; i < row; i++)
				if (engine.grid[i][pcol] == 0) {
					pfull = false;
					break;
				}

			if (pfull == true)
				return;

			int result = engine.connected();

			if (result != 0) {

				return;

			}

			engine.update(container.getInput().getMouseX(), container.getInput().getMouseY());

			System.out.println("----------------");

			// engine.print();
			if(Menu.depth <5) {
				int aiMove = findBestMove(engine.GetPlayerID());

				if (aiMove != -1)

					engine.makeMove(aiMove);

				System.out.println("AI moves");
				}

			System.out.println("player 1 pressed");

			

			// engine.testing();

			result = engine.connected();

			if (result == 1) {

				System.out.println("Player 1 wins");

			} else if (result == AI) {

				System.out.println("Player 2 wins");

			}

		}

	}
	/*
	 * public void update(GameContainer container, StateBasedGame sbg, int arg2)
	 * throws SlickException {
	 * 
	 * if (container.getInput().isKeyPressed(Input.KEY_0)) { engine.undo();
	 * engine.print(); } if
	 * (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
	 * 
	 * System.out.println(container.getInput().getMouseX() + " ," +
	 * container.getInput().getMouseY());
	 * 
	 * int result = engine.connected();
	 * 
	 * if (result != 0) { return; }
	 * 
	 * engine.update(container.getInput().getMouseX(),
	 * container.getInput().getMouseY()); System.out.println("----------------");
	 * engine.print();
	 * 
	 * 
	 * 
	 * int aiMove = findBestMove(engine.GetPlayerID()); if(aiMove !=-1)
	 * engine.makeMove(aiMove);
	 * 
	 * // engine.testing(); result = engine.connected();
	 * 
	 * if (result == 1) { System.out.println("Player 1 wins");
	 * 
	 * } else if (result == AI) { System.out.println("Player 2 wins");
	 * 
	 * } } }
	 */

	/**
	 * Finds the best possible move for a player
	 * 
	 * @param player_id
	 *            is the player id
	 * @return the best column to put the checker in
	 **/

	public int findBestMove(int player_id) {

		int[] col_score = minmax(Menu.depth, true, player_id);
		System.out.println("minmax column-score result: " + Arrays.toString(col_score));
		return col_score[0];

	}

	/**
	 * Renders the board and draws everything
	 * 
	 * @param container
	 *            is the container
	 * @param sbg
	 *            is a state based game
	 * @param g
	 *            is the Graphics
	 **/

	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		container.setShowFPS(false);

		Image background = new Image("C:\\Users\\NARAVENK\\eclipse-workspace\\APCSFinalProject\\src\\connect4\\b2.png");
		
		g.drawImage(background, 0, 0);
		// board.draw(89, 110);
		board.draw(100, 110);

		g.setBackground(Color.black);

		g.drawString("Press '0' to undo a move", 240, 540);
		g.drawString("Press 'BACKSPACE' to go back to Menu", 180, 560);


		engine.draw(g);
		int winner = engine.connected();
		if (winner == 1) {
			g.drawString("PLAYER 1 WINS!", 300, 50);
		} else if (winner == 2) {
			g.drawString("PLAYER 2 WINS!", 300, 50);
		}
	}

	/**
	 * returns the value of the state id
	 */
	public int getID() {
		return 3;
	}

}
