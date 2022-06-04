package fiveMens.utils; 

import java.util.ArrayList;

public class Board {
    private ArrayList<BoardField> fields = new ArrayList<BoardField>(); 

    public Board() {
        for(int i = 0; i < 16; i++) { 
            fields.add(new BoardField());
        }

        connectIntoSquare(0);
        connectIntoSquare(8);
        connectSquares(8, 0);
    }

    public ArrayList<BoardField> getFieldsWithPawnsOf(int player) {
        ArrayList<BoardField> playerFields = new ArrayList<BoardField>();

        for(BoardField field : fields) {
            if(field.getPawn().getPlayer() == player) {
                playerFields.add(field);
            }
        }

        return playerFields;
    }

    public BoardField getField(int index) { 
        if(index < 0 || index > 15) {
            return null;
        }

        return fields.get(index);
    }

    public BoardField getField(Pawn pawn) { 
        for(BoardField field : fields) {
            if(field.getPawn() == pawn) {
                return field;
            }
        }

        return null;
    }

    public void putPawnOn(Pawn pawn, int index) { 
        getField(index).setPawn(pawn);
    }

    public void removePawnFrom(int index) {
        getField(index).setPawn(null);
    }

    public void movePawnToAdjacentField(Pawn pawn, BoardField field) { 
        if(field.getPawn() != null) { 
            return;
        }

        BoardField previousField = getField(pawn);
        if(previousField == null) {
            return;
        }
        if(previousField.getUp() != field && previousField.getLeft() != field 
        && previousField.getDown() != field && previousField.getRight() != field) {
            return;
        }

        previousField.setPawn(null);
        field.setPawn(pawn);
    }

    public void movePawnToAnyField(Pawn pawn, BoardField field) { 
        if(field.getPawn() != null) { 
            return;
        }

        BoardField previousField = getField(pawn);
        if(previousField == null) {
            return;
        }
        previousField.setPawn(null);
        field.setPawn(pawn);
    }

    private void connectIntoSquare(int startingIndex) { 
        for(int i = 0; i < 2; i++) { 
            BoardField leftField = fields.get(startingIndex+i);
            BoardField rightField = fields.get(startingIndex+i+1);
            leftField.setRight(rightField);
            rightField.setLeft(leftField);
        }

        for(int i = 0; i < 2; i++) { 
            BoardField upperField = fields.get(startingIndex+2+i);
            BoardField bottomField = fields.get(startingIndex+2+i+1);
            upperField.setDown(bottomField);
            bottomField.setUp(upperField);
        }

        for(int i = 0; i < 2; i++) { 
            BoardField rightField = fields.get(startingIndex+4+i);
            BoardField leftField = fields.get(startingIndex+4+i+1);
            rightField.setLeft(leftField);
            leftField.setRight(rightField);
        }

        for(int i = 0; i < 2; i++) { 
            BoardField bottomField = fields.get(startingIndex+6+i);
            int index = startingIndex+6+i+1;
            if(index == startingIndex + 8)
                index = startingIndex;
            BoardField upperField = fields.get(index);
            bottomField.setUp(upperField);
            upperField.setDown(bottomField);
        }
    }

    private void connectSquares(int innerStartingIndex, int outerStaringIndex) { 
        BoardField innerField = fields.get(innerStartingIndex + 1);
        BoardField outerField = fields.get(outerStaringIndex + 1);

        outerField.setDown(innerField);
        innerField.setUp(outerField);

        innerField = fields.get(innerStartingIndex + 3);
        outerField = fields.get(outerStaringIndex + 3);

        outerField.setLeft(innerField);
        innerField.setRight(outerField);

        innerField = fields.get(innerStartingIndex + 5);
        outerField = fields.get(outerStaringIndex + 5);

        outerField.setUp(innerField);
        innerField.setDown(outerField);

        innerField = fields.get(innerStartingIndex + 7);
        outerField = fields.get(outerStaringIndex + 7);

        outerField.setRight(innerField);
        innerField.setLeft(outerField);
    }

    
}
