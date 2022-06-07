package fiveMens;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fiveMens.utils.Board;
import fiveMens.utils.BoardField;
import fiveMens.utils.Pawn;

public class BoardFieldJUnitTest {
    @Test
    public void shouldNotFindThreeInRowForEmptyFields() {
        Board board = new Board();
        for (int i = 0; i < 16; i++) {
            BoardField field = board.getField(i);
            assertEquals(false, field.pawnsInRow());
        }
    }

    @Test
    public void shouldFindThreeInRow() {
        Board board = new Board();
        board.putPawnOn(new Pawn(0), 0);
        board.putPawnOn(new Pawn(0), 1);
        board.putPawnOn(new Pawn(0), 2);
        for (int i = 0; i < 3; i++) {
            BoardField field = board.getField(i);
            assertEquals(true, field.pawnsInRow());
        }

        board = new Board();
        board.putPawnOn(new Pawn(1), 12);
        board.putPawnOn(new Pawn(1), 13);
        board.putPawnOn(new Pawn(1), 14);
        for (int i = 12; i < 15; i++) {
            BoardField field = board.getField(i);
            assertEquals(true, field.pawnsInRow());
        }
    }

    @Test
    public void shouldNotFindThreeInRowForPawnsOfDifferentPlayers() {
        Board board = new Board();
        board.putPawnOn(new Pawn(0), 0);
        board.putPawnOn(new Pawn(1), 1);
        board.putPawnOn(new Pawn(0), 2);
        for (int i = 0; i < 3; i++) {
            BoardField field = board.getField(i);
            assertEquals(false, field.pawnsInRow());
        }
    }

    @Test
    public void shouldNotFindFindThreeInRowForNotAdjacentFields() {
        Board board = new Board();
        board.putPawnOn(new Pawn(1), 7);
        board.putPawnOn(new Pawn(1), 10);
        board.putPawnOn(new Pawn(1), 14);

        assertEquals(false, board.getField(7).pawnsInRow());
        assertEquals(false, board.getField(10).pawnsInRow());
        assertEquals(false, board.getField(14).pawnsInRow());

    }

    @Test
    public void shouldNotFindThreeInRowIfNotStraightLine() {
        Board board = new Board();
        board.putPawnOn(new Pawn(1), 7);
        board.putPawnOn(new Pawn(1), 8);
        board.putPawnOn(new Pawn(1), 9);

        for (int i = 7; i < 10; i++) {
            BoardField field = board.getField(i);
            assertEquals(false, field.pawnsInRow());
        }
    }
}
