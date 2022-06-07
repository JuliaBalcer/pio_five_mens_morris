package fiveMens.utils;

/**
 * Pawn class represents a pawn in a game Five Men's Morris.
 */
public class Pawn {
    /**
     * player who owns this pawn
     */
    private int player;
    
    public void setPlayer(int player) {
        this.player = player;
    }

    /**
     * @param player - describes belonging to the player
     */
    public Pawn(int player){
        this.player = player;
    }

    /**
     *
     * @return player who owns this pawn
     */
    public int getPlayer() {
        return player;
    }
}
