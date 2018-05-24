package es.codeurjc.ais.tictactoe;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import es.codeurjc.ais.tictactoe.*;
import es.codeurjc.ais.tictactoe.TicTacToeGame.Cell;
import es.codeurjc.ais.tictactoe.TicTacToeGame.WinnerResult;

public class BoardTest {

	public Board tablero() {
		Board b = new Board();
		b.enableAll();
		Cell c0 = b.getCell(0);
		c0.value="X";
		Cell c1 = b.getCell(1);
		c1.value="O";
		Cell c2 = b.getCell(2);
		c2.value="X";
		Cell c4 = b.getCell(4);
		c4.value="O";
		Cell c3 = b.getCell(3);
		c3.value="X";
		Cell c5 = b.getCell(5);
		c5.value="O";
		
		return b;
	}
	public boolean isIn (int i, int[] terna) {
		for (int j : terna) {
			if (i==j) {
				return true;
			}
		}
		return false;
	}
	
	
	
	@Test
	public void testBoardWin(){
		
		Board b = tablero();
		Cell c = b.getCell(6);
		c.value="X";
		
		int [] res = {0, 3, 6};
		int [] res2 = b.getCellsIfWinner("X");
	
		assertFalse(b.checkDraw());
		for(int i : res2) {
			assertTrue(isIn(i, res));
		}
		
		
	}
	
	@Test
	public void testBoardLose(){
		Board b = tablero();
		Cell c = b.getCell(8);
		c.value="X";
		Cell c1 = b.getCell(7);
		c1.value="O";
		
		assertFalse(b.checkDraw());
		
		assertNull(b.getCellsIfWinner("X"));
		
		int [] res = {1, 4, 7};
		int [] res2 = b.getCellsIfWinner("O");
	
		assertFalse(b.checkDraw());
		for(int i : res2) {
			assertTrue(isIn(i, res));
		}
	}
	
	@Test 
	public void testBoardDraw(){
		Board b = tablero();
		Cell c = b.getCell(7);
		c.value="X";
		Cell c1 = b.getCell(6);
		c1.value="O";
		Cell c2 = b.getCell(8);
		c2.value="X";
		
		assertNull(b.getCellsIfWinner("X"));
		
		assertNull(b.getCellsIfWinner("O"));
		
		assertTrue(b.checkDraw());
	}
	
}
