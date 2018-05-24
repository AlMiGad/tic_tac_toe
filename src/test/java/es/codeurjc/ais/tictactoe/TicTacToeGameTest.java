package es.codeurjc.ais.tictactoe;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;


import es.codeurjc.ais.tictactoe.TicTacToeGame.EventType;
import es.codeurjc.ais.tictactoe.TicTacToeGame.WinnerValue;

public class TicTacToeGameTest {
	Connection c1 = mock(Connection.class);
	Connection c2 = mock(Connection.class);
	Player p1 = new Player(1, "X", "AlMiGad");
	Player p2 = new Player(2, "O", "Laya");
	TicTacToeGame game = new TicTacToeGame();
	public void juego() {
		
		
		game.addConnection(c1);
		game.addConnection(c2);
		
		game.addPlayer(p1);
		game.addPlayer(p2);
		
		verify(c1, Mockito.times(2)).sendEvent(eq(EventType.JOIN_GAME), eq(game.getPlayers()));
		verify(c2, Mockito.times(2)).sendEvent(eq(EventType.JOIN_GAME), eq(game.getPlayers()));
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p1));
		reset(c1);
		reset(c2);
		
		game.mark(0);
		
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p2));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p2));
		reset(c1);
		reset(c2);
		
		game.mark(1);
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p1));
		reset(c1);
		reset(c2);
		game.mark(2);
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p2));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p2));
		reset(c1);
		reset(c2);
		game.mark(4);
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p1));
		reset(c1);
		reset(c2);
		game.mark(3);
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p2));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p2));
		reset(c1);
		reset(c2);
		game.mark(5);
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p1));
		reset(c1);
		reset(c2);
		
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
	public void testGamedWin() {
		int [] res = {0, 3, 6};
		juego();
		game.mark(6);

		ArgumentCaptor<WinnerValue> argument = ArgumentCaptor.forClass(WinnerValue.class);		
		verify(c1).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		verify(c2).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		Object event = argument.getValue();
		WinnerValue res2 = (WinnerValue) event;
		
		for(int i : res2.pos) {
			assertTrue(isIn(i, res));
		}		
		assertEquals(p1, res2.player);

	}
	
	@Test
	public void testGameLose() {
		int [] res = {1, 4, 7};
		juego();
		game.mark(8);
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p2));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p2));
		reset(c1);
		reset(c2);
		
		game.mark(7);

		ArgumentCaptor<WinnerValue> argument = ArgumentCaptor.forClass(WinnerValue.class);		
		verify(c1).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		verify(c2).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		Object event = argument.getValue();
		WinnerValue res2 = (WinnerValue) event;
		
		for(int i : res2.pos) {
			assertTrue(isIn(i, res));
		}		
		assertEquals(p2, res2.player);

	}
	
	@Test
	public void testGamedDraw() {
		juego();
		game.mark(7);
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p2));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p2));
		reset(c1);
		reset(c2);
		
		game.mark(6);
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p1));
		reset(c1);
		reset(c2);
		
		game.mark(8);

		ArgumentCaptor<WinnerValue> argument = ArgumentCaptor.forClass(WinnerValue.class);		
		verify(c1).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		verify(c2).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		Object event = argument.getValue();
		WinnerValue res2 = (WinnerValue) event;
	
		assertNull(res2);

	}
}
