package fiveMens.utils;


public class BoardField {
    private BoardField up;
    private BoardField down;
    private BoardField left;
    private BoardField right;

    private Pawn pawn;

    public BoardField() {
        this.up = null;
        this.down = null;
        this.left = null;
        this.right = null;

        this.pawn = null;
    }

    public BoardField getUp() {
        return up;
    }

    public void setUp(BoardField up) {
        this.up = up;
    }

    public BoardField getDown() {
        return down;
    }

    public void setDown(BoardField down) {
        this.down = down;
    }

    public BoardField getLeft() {
        return left;
    }

    public void setLeft(BoardField left) {
        this.left = left;
    }

    public BoardField getRight() {
        return right;
    }

    public void setRight(BoardField right) {
        this.right = right;
    }

    public Pawn getPawn() {
        return pawn;
    }

    public void setPawn(Pawn pawn) {
        this.pawn = pawn;
    }
}