package se.tetris.main;
import se.tetris.component.Board;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GetIntervalTest {

	@Test
	void testGetInterval() {
		Board board = new Board();

        assertEquals(board.getInterval(30, 0), 900);
        assertEquals(board.getInterval(35, 3), 900);
        assertEquals(board.getInterval(45, 5), 810);
        assertEquals(board.getInterval(60, 10), 656);
	}

}
