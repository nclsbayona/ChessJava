package poo.Model.Pieces;

import poo.Model.Piece;

public class Pawn extends Piece {
    private boolean firstMove;

    public boolean isFirstMove() {
        return this.firstMove;
    }

    public boolean getFirstMove() {
        return this.firstMove;
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }
    public Pawn(int movX, int movY, boolean canMoveBack, int x, int y, String color) {
        super(movX, movY, canMoveBack, x, y, color);
        this.firstMove=true;
    }
}
