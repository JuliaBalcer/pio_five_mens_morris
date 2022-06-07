package fiveMens;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fiveMens.utils.Board;
import fiveMens.utils.Pawn;

public class BoardMethodsJUnitTest {
    @Test
    public void movePawnToAdjacentFieldShouldWork() { 
        Board board = new Board();
        Pawn pawn = new Pawn(0);
        board.putPawnOn(pawn, 0);

        assertEquals(true, board.movePawnToAdjacentField(pawn, board.getField(1)));
    }

    @Test
    public void movePawnToAdjacentFieldShouldFailForNotAdjacentField() { 
        Board board = new Board();
        Pawn pawn = new Pawn(0);
        board.putPawnOn(pawn, 0);

        assertEquals(false, board.movePawnToAdjacentField(pawn, board.getField(10)));
    }

    @Test
    public void movePawnToAdjacentFieldShouldFailForTakenField() { 
        Board board = new Board();
        Pawn pawn = new Pawn(0);
        board.putPawnOn(pawn, 0);
        board.putPawnOn(new Pawn(1), 1);

        assertEquals(false, board.movePawnToAdjacentField(pawn, board.getField(1)));
    }

    @Test
    public void movePawnToAnyFieldShouldWork() { 
        Board board = new Board();
        Pawn pawn = new Pawn(0);
        board.putPawnOn(pawn, 0);

        assertEquals(true, board.movePawnToAnyField(pawn, board.getField(10)));
    }

    @Test
    public void movePawnToAnyFieldShouldFailForTakenField() { 
        Board board = new Board();
        Pawn pawn = new Pawn(0);
        board.putPawnOn(pawn, 0);
        board.putPawnOn(new Pawn(0), 10);

        assertEquals(false, board.movePawnToAnyField(pawn, board.getField(10)));
    }
}
