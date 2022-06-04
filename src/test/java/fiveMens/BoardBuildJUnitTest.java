package fiveMens;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fiveMens.utils.Board;
import fiveMens.utils.BoardField;

public class BoardBuildJUnitTest {
    @Test
    public void boardNodesShouldCreateTwoSquares() {
        Board board = new Board();
        travelThroughSquareClockwise(0, board);
        travelThroughSquareReverse(0, board);
        travelThroughSquareClockwise(8, board);
        travelThroughSquareReverse(8, board);
    }

    @Test
    public void boardSquaresShouldBeConnected() {
        Board board = new Board();
        for (int i = 0; i < 4; i++) {
            int index = i * 2 + 1;
            BoardField field = board.getField(index);
            if (i == 0) {
                assertEquals(field.getDown(), board.getField(index + 8));
            } else if (i == 1) {
                assertEquals(field.getLeft(), board.getField(index + 8));
            } else if (i == 2) {
                assertEquals(field.getUp(), board.getField(index + 8));
            } else {
                assertEquals(field.getRight(), board.getField(index + 8));
            }
        }
    }

    private void travelThroughSquareClockwise(int index, Board board) {
        if (index != 0 && index != 8)
            return;

        int dir = 0;
        for (int i = index; i < index + 8; i++) {
            BoardField field = board.getField(i);
            int nextIndex = i + 1;
            if (nextIndex == index + 8)
                nextIndex = index;
            if (dir == 0) {
                if (field.getRight() == null) {
                    dir = 1;
                } else {
                    assertEquals(field.getRight(), board.getField(nextIndex));
                }

            }
            if (dir == 1) {
                if (field.getDown() == null) {
                    dir = 2;
                } else {
                    assertEquals(field.getDown(), board.getField(nextIndex));
                }
            }
            if (dir == 2) {
                if (field.getLeft() == null) {
                    dir = 3;
                } else {
                    assertEquals(field.getLeft(), board.getField(nextIndex));
                }

            }
            if (dir == 3) {
                if (field.getUp() == null) {
                    dir = 0;
                } else {
                    assertEquals(field.getUp(), board.getField(nextIndex));
                }

            }
        }
    }

    private void travelThroughSquareReverse(int index, Board board) {
        if (index != 0 && index != 8)
            return;

        int dir = 0;
        for (int i = index + 8 - 1; i >= index; i--) {
            int nextIndex = i - 1;
            if (nextIndex < 0)
                nextIndex = index + 8 - 1;
            BoardField field = board.getField(i);
            if (dir == 0) {
                if (field.getDown() == null)
                    dir = 1;
                else {
                    assertEquals(field.getDown(), board.getField(nextIndex));
                }
            }
            if (dir == 1) {
                if (field.getRight() == null) {
                    dir = 2;
                } else {
                    assertEquals(field.getRight(), board.getField(nextIndex));
                }

            }
            if (dir == 2) {
                if (field.getUp() == null) {
                    dir = 3;
                } else {
                    assertEquals(field.getUp(), board.getField(nextIndex));
                }

            }
            if (dir == 3) {
                if (field.getLeft() == null) {
                    dir = 0;
                } else {
                    assertEquals(field.getLeft(), board.getField(nextIndex));
                }

            }
        }
    }
}
