package fiveMens.utils;

import javafx.scene.image.ImageView;

public class BoardField {
    private BoardField up;
    private BoardField down;
    private BoardField left;
    private BoardField right;

    private Pawn pawn;
    
    private ImageView image;

    public ImageView getNode() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

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

    public boolean pawnsInRow() {
        if (pawn == null) {
            return false;
        }

        int player = pawn.getPlayer();

        BoardField boardField = this;

        while (boardField.up != null) {
            boardField = boardField.up;
        }

        int count = 0;

        while (boardField != null) {
            if (boardField.pawn == null) {
                break;
            }

            if (boardField.pawn.getPlayer() == player) {
                count++;
            }

            boardField = boardField.down;
        }

        if (count == 3) {
            return true;
        }

        boardField = this;

        while (boardField.left != null) {
            boardField = boardField.left;
        }

        count = 0;

        while (boardField != null) {
            if (boardField.pawn == null) {
                break;
            }

            if (boardField.pawn.getPlayer() == player) {
                count++;
            }

            boardField = boardField.right;
        }

        if (count == 3) {
            return true;
        }

        return false;
    }
}