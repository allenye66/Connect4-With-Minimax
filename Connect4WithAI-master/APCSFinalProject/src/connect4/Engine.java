package connect4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
/**
 * Engine class with game logic for Connect 4
 *
 * @author Shasta Narayanan, Allen Ye, Evan Hwang
 * @version 5/30/19
 *
 * @author Period - 1
 * @author Assignment - Final Project- Connect4
 * 
 */
public class Engine {

	int row = 0;
	int col = 0;
	int[][] grid;

	ArrayList<Integer> moves = new ArrayList<Integer>();

	boolean eligible;
	int shiftLeft = 100;

	/**
	 * Creates an engine object with said parameters: int r, int c
	 * 
	 * @param c
	 *            is the column
	 * @param r
	 *            is the row
	 **/

	public Engine(int r, int c) {
		row = r;
		col = c;
		grid = new int[row][col];
	}

	/**
	 * Updates the position and places a checker at that specified position
	 * 
	 * @param xPos
	 *            is the position on the x axis of the new checker
	 * @param yPos
	 *            is the position on the y axis of the new checker
	 **/

	public void update(int xPos, int yPos) {
		xPos = (xPos - shiftLeft + 20) / 75;
		// System.out.println(xPos + ", " + yPos);
		makeMove(xPos);
		// System.out.println(Arrays.toString(eligibleMoves()));

	}

	/**
	 * Draws the entire board using Graphics
	 * 
	 * @param g
	 *            is the variable for Graphics
	 **/

	public void draw(Graphics g) {
		// System.out.println("asdf");

		g.setColor(Color.white);

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				drawCell(g, i, j, grid[i][j]);
			}
		}

	}

	/**
	 * Sets a cell to a specific color if taken up by either player, otherwise it is
	 * just white for blank
	 * 
	 * @param g
	 *            is Graphics
	 * @param x
	 *            is the x axis for the checker
	 * @param y
	 *            is the y axis for the checker
	 * @param player
	 *            is which player is taking up that cell with their checker
	 **/

	public void drawCell(Graphics g, int x, int y, int player) {
		if (player == 1) {
			g.setColor(Color.yellow);
		} else if (player == 2) {
			g.setColor(Color.red);
		} else {
			g.setColor(Color.white);
		}
		// g.fillOval(y * 70 + shiftLeft, (col - x) * 70 - 15, 50, 50);
		g.fillOval(y * 70 + shiftLeft + 9, (col - x) * 70 - 20, 56, 56);

	}

	/**
	 * Sets the player 1’s number to the grid
	 * 
	 * @param c
	 *            is the column we are checking
	 **/
	public void setPlayer1(int c) {

		for (int i = 0; i < row; i++) {
			if (grid[i][c] == 0) {
				grid[i][c] = 1;
				break;
			}
		}

	}

	/**
	 * Sets the player 2’s number to the grid
	 * 
	 * @param c
	 *            is the column we are checking
	 **/

	public void setPlayer2(int c) {
		for (int i = 0; i < row; i++) {
			if (grid[i][c] == 0) {
				grid[i][c] = 2;
				break;
			}
		}

	}
	/**
	 * Resets the board when going back to home screen
	 */
	public void reset() {
		for(int i =0 ; i < row; i ++) {
			for(int j =0 ; j < col; j ++) {
				grid[i][j] = 0;
				
			}
		}
	}

	/**
	 * Undos the last move made
	 **/

	public void undo() {
		if (moves.size() == 0)
			return;
		int last_col = moves.get(moves.size() - 1);
		moves.remove(moves.size() - 1);
		for (int i = row - 1; i >= 0; i--) {
			if (grid[i][last_col] != 0) {
				grid[i][last_col] = 0;
				break;
			}
		}
	}

	/**
	 * It makes the move (placing down a checker) at a specific location
	 * 
	 * @param c
	 *            is the column where we want to put the checker
	 **/

	public void makeMove(int c) {

		if (moves.size() % 2 == 0)
			setPlayer1(c);

		else
			setPlayer2(c);
		moves.add(c);
	}

	/**
	 * Prints out the grid with all the checkers on it and prints out all eligible
	 * moves it can make
	 **/

	public void print() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(grid[i][j]);
			}
			System.out.println();

		}
		System.out.print("eligible move:");
		for (int i = 0; i < eligibleMoves().size(); i++) {
			System.out.print(eligibleMoves().get(i));
			System.out.print(',');
		}

		System.out.println();

	}

	/**
	 * Determines if the move is eligible through if that spot does not currently
	 * hold a checker
	 * 
	 * @return if the move is eligible
	 **/

	public boolean eligible(int c) {
		for (int i = 0; i < row; i++) {
			if (grid[i][c] == 0) {
				return true;
			}
		}
		return false;

	}

	/**
	 * Makes an arraylist that holds all eligible moves that could be made
	 * 
	 * @return the arraylist with all eligible moves
	 **/

	public ArrayList<Integer> eligibleMoves() {

		ArrayList<Integer> arr = new ArrayList<Integer>();

		for (int i = 0; i < col; i++) {
			if (eligible(i)) {
				arr.add(i);
			}
		}
		return arr;

	}

	/**
	 * Gets which player’s turn it is
	 * 
	 * @return that player’s ID
	 **/

	public int GetPlayerID() {
		return moves.size() % 2 + 1;

	}

	/*
	 * public int connected() { // System.out.println("enter testing");
	 * 
	 * for (int i = 0; i < row; i++) { for (int j = 0; j < col; j++) {
	 * 
	 * int v = grid[i][j]; if (v == 0) continue; // System.out.print("[" + i + "]["
	 * + j + "]= " + v); // horizontal & vertical check boolean result = false; //
	 * horizontal check if (i + 2 < row && grid[i + 1][j] == v && grid[i + 2][j] ==
	 * v) { result = true; } // vertical check if (j + 2 < col && grid[i][j + 1] ==
	 * v && grid[i][j + 2] == v) { result = true; }
	 * 
	 * if (i + 2 < row && j + 3 < col && grid[i + 1][j + 1] == v && grid[i + 2][j +
	 * 2] == v) { result = true; } if (i - 2 >= 0 && j + 3 < col) { if (grid[i -
	 * 1][j + 1] == v && grid[i - 2][j + 2] == v) { result = true; }
	 * 
	 * } // System.out.println(result); if (result) return v;
	 * 
	 * // diagonal check
	 * 
	 * } } return 0; }
	 */

	/**
	 * Checks if there is any 4 in a row through vertical, horizontal, and diagonal
	 * 
	 * @return true if there is a 4 in a row, false if not
	 **/

	public int connected() {
		// System.out.println("enter testing");

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {

				int v = grid[i][j];
				if (v == 0)
					continue;
				// System.out.print("[" + i + "][" + j + "]= " + v);
				// horizontal & vertical check
				boolean result = false;
				// horizontal check
				if (i + 3 < row && grid[i + 1][j] == v && grid[i + 2][j] == v && grid[i + 3][j] == v) {
					result = true;
				}
				// vertical check
				if (j + 3 < col && grid[i][j + 1] == v && grid[i][j + 2] == v && grid[i][j + 3] == v) {
					result = true;
				}

				if (i + 3 < row && j + 3 < col && grid[i + 1][j + 1] == v && grid[i + 2][j + 2] == v
						&& grid[i + 3][j + 3] == v) {
					result = true;
				}
				if (i - 3 >= 0 && j + 3 < col) {
					if (grid[i - 1][j + 1] == v && grid[i - 2][j + 2] == v && grid[i - 3][j + 3] == v) {
						result = true;
					}

				}
				// System.out.println(result);
				if (result)
					return v;

				// diagonal check

			}
		}
		return 0;
	}

	/**
	 * 
	 * @param i
	 *            the row
	 * @param j
	 *            the col
	 * @return the value at the row and the col
	 */
	public int getGridValue(int i, int j) {
		return grid[i][j];
	}

	/**
	 * Returns the score for the minimax algorithm
	 * 
	 * @param player
	 *            is the player’s number
	 * @return said score
	 **/

	public int evaluateBoardScore(int player) {

		int score = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (grid[i][j] == player) {
					score += col / 2 - Math.abs(col / 2 - j);
				}

			}
		}
		return score;
	}
}
