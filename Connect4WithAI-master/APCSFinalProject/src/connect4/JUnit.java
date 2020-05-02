package connect4;

import static org.junit.Assert.assertTrue;

//import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * JUnit testing 
 *
 * @author Shasta Narayanan, Allen Ye, Evan Hwang
 * @version 5/30/19
 *
 * @author Period - 1
 * @author Assignment - Final Project- Connect4
 * 
 */
public class JUnit {
	// --Test Piece

	private static int row = 6;
	private static int col = 7;
	private static int player1 = 1;
	private static int player2 = 2;
	private static int empty = 0;

	@Test
	public void testEngineRowNumber() {
		Engine engine = new Engine(row, col);
		assertTrue("Invalid row number", engine.row == row);

	}

	@Test
	public void testEngineColumnNumber() {
		Engine engine = new Engine(row, col);
		assertTrue("Invalid column number", engine.col == col);
	}

	@Test
	public void testEngineGrid() {
		Engine engine = new Engine(row, col);
		assertNotNull("grid holding pieces is null", engine.grid);
	}

	@Test
	public void testSetPlayer1() {
		Engine engine = new Engine(row, col);
		engine.setPlayer1(3);
		assertTrue("Wrong player1 piece", engine.getGridValue(0, 3) == player1);
	}

	@Test
	public void testSetPlayer1EMpty() {
		Engine engine = new Engine(row, col);
		engine.setPlayer1(3);
		assertTrue("Wrong player1 piece", engine.getGridValue(0, 4) == empty);
	}

	@Test
	public void testSetPlayer1TwoRun() {
		Engine engine = new Engine(row, col);
		engine.setPlayer1(3);
		engine.setPlayer1(3);
		assertTrue("Wrong player1 piece after two run",
				(engine.getGridValue(1, 3) == player1) && (engine.getGridValue(0, 3) == player1));
	}

	@Test
	public void testSetPlayer2() {
		Engine engine = new Engine(row, col);
		engine.setPlayer2(3);
		assertTrue("Wrong player2 piece", engine.getGridValue(0, 3) == player2);
	}

	@Test
	public void testSetPlayer2FolowedByPlayer1() {
		Engine engine = new Engine(row, col);
		engine.setPlayer1(3);
		engine.setPlayer2(3);
		assertTrue("Wrong player2 piece after player1 places piece",
				(engine.getGridValue(1, 3) == player2) && (engine.getGridValue(0, 3) == player1));
	}

	@Test
	public void testMakeMove() {
		Engine engine = new Engine(row, col);
		engine.makeMove(3); // player 1 move
		engine.makeMove(3); // player 2 move
		assertTrue("Wrong piece after makeMove",
				(engine.getGridValue(1, 3) == player2) && (engine.getGridValue(0, 3) == player1));
	}

	@Test
	public void testUndo() {
		Engine engine = new Engine(row, col);
		engine.makeMove(3); // player 1 move
		engine.makeMove(3); // player 2 move
		engine.undo(); // undo player 2 move
		engine.undo(); // undo player 1 move
		assertTrue("Wrong piece after undo",
				(engine.getGridValue(0, 3) == empty) && (engine.getGridValue(1, 3) == empty));
	}

	@Test
	public void testEligible1() {
		boolean check;
		Engine engine = new Engine(row, col);
		engine.makeMove(3); // player 1 move
		engine.makeMove(3); // player 2 move
		check = engine.eligible(3);
		assertTrue("Wrong result for eligible", check);
	}

	@Test
	public void testEligible2() {
		boolean check;
		Engine engine = new Engine(row, col);
		engine.makeMove(3); // player 1 move
		engine.makeMove(3); // player 2 move
		engine.makeMove(3); // player 1 move
		engine.makeMove(3); // player 2 move
		engine.makeMove(3); // player 1 move
		engine.makeMove(3); // player 2 move
		check = engine.eligible(3); // column 3 should be full
		assertTrue("Wrong result for eligible", check == false);
	}

	@Test
	public void testEligibleMoves() {
		ArrayList<Integer> arr;
		Engine engine = new Engine(row, col);
		engine.makeMove(3); // player 1 move
		engine.makeMove(3); // player 2 move
		engine.makeMove(3); // player 1 move
		engine.makeMove(3); // player 2 move
		engine.makeMove(3); // player 1 move
		engine.makeMove(3); // player 2 move
		engine.makeMove(2); // player 1 move
		engine.makeMove(1); // player 2 move
		arr = engine.eligibleMoves(); // column 3 should be full
		assertTrue("Wrong result for eligibleMoves", engine.eligibleMoves().size() == row);
	}

	@Test
	public void testEngineGetPlayerID() {
		int id = 0;
		Engine engine = new Engine(row, col);
		engine.makeMove(3); // player 1 move
		engine.makeMove(3); // player 2 move
		engine.makeMove(3); // player 1 move
		engine.makeMove(3); // player 2 move
		engine.makeMove(3); // player 1 move
		engine.makeMove(3); // player 2 move
		engine.makeMove(2); // player 1 move
		engine.makeMove(1); // player 2 move
		id = engine.GetPlayerID();
		assertTrue("Wrong player ID", id == player1);
	}

	@Test
	public void testConnectedV() {
		int result = 0;
		Engine engine = new Engine(row, col);
		engine.makeMove(3); // player 1 move
		engine.makeMove(1); // player 2 move
		engine.makeMove(3); // player 1 move
		engine.makeMove(2); // player 2 move
		engine.makeMove(3); // player 1 move
		engine.makeMove(4); // player 2 move
		engine.makeMove(3); // player 1 move
		result = engine.connected();
		assertTrue("Wrong result for connected() vertical", result == player1);
	}

	@Test
	public void testConnectedH() {
		int result = 0;
		Engine engine = new Engine(row, col);
		engine.makeMove(6); // player 1 move
		engine.makeMove(1); // player 2 move
		engine.makeMove(6); // player 1 move
		engine.makeMove(2); // player 2 move
		engine.makeMove(6); // player 1 move
		engine.makeMove(3); // player 2 move
		engine.makeMove(0); // player 1 move
		engine.makeMove(4); // player 2 move
		result = engine.connected();
		assertTrue("Wrong result for connected() horizonal", result == player2);
	}

	@Test
	public void testConnectedS() {
		int result = 0;
		Engine engine = new Engine(row, col);
		engine.makeMove(0); // player 1 move
		engine.makeMove(1); // player 2 move
		engine.makeMove(1); // player 1 move
		engine.makeMove(2); // player 2 move
		engine.makeMove(2); // player 1 move
		engine.makeMove(6); // player 2 move
		engine.makeMove(2); // player 1 move
		engine.makeMove(3); // player 2 move
		engine.makeMove(3); // player 1 move
		engine.makeMove(3); // player 2 move
		engine.makeMove(3); // player 1 move
		result = engine.connected();
		assertTrue("Wrong result for connected() slash", result == player1);
	}

	@Test
	public void testConnectedBS() {
		int result = 0;
		Engine engine = new Engine(row, col);
		engine.makeMove(6); // player 1 move
		engine.makeMove(5); // player 2 move
		engine.makeMove(5); // player 1 move
		engine.makeMove(4); // player 2 move
		engine.makeMove(4); // player 1 move
		engine.makeMove(0); // player 2 move
		engine.makeMove(4); // player 1 move
		engine.makeMove(3); // player 2 move
		engine.makeMove(3); // player 1 move
		engine.makeMove(3); // player 2 move
		engine.makeMove(3); // player 1 move
		result = engine.connected();
		assertTrue("Wrong result for connected() back slash", result == player1);
	}

	@Test
	public void testEvaluateBoardScore() {
		int result = 0;
		Engine engine = new Engine(row, col);
		engine.makeMove(3); // player 1 move

		result = engine.evaluateBoardScore(player1);
		assertTrue("Wrong score", result == 3);
	}

	@Test
	public void testPlayingID() {
		int id = 0;
		Playing p = new Playing();
		try {
			id = p.getID();
		} catch (Exception e) {
			System.out.println("Error: Cannot call Playing init()");
		}
		assertTrue("Wrong Playing ID", id == 3);
	}

	
	
	
	@Test
	public void testMinmax() {
		int check = 0;
		int[] col_score = { 0, 0 };
		Playing p = new Playing();
		p.engine = new Engine(p.row, p.col);
		p.engine.makeMove(3); // player 1 move

		try {
			col_score = p.minmax(4, true, player2);
		} catch (Exception e) {
			System.out.println("Error: Cannot call Playing.findBestMove()");
		}
		assertTrue("Wrong minmax results", (col_score[0] == 3) && (col_score[1] == 6));
	}

	@Test
	public void testFindBestMove() {
		int check = 0;
		Playing p = new Playing();
		p.engine = new Engine(p.row, p.col);
		p.engine.makeMove(3); // player 1 move

		try {
			check = p.findBestMove(player2);
		} catch (Exception e) {
			System.out.println("Error: Cannot call Playing.findBestMove()");
		}
		assertTrue("Wrong best move", check == 3);
	}
}
