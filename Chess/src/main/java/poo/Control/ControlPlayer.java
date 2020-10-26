package poo.Control;

import poo.Model.*;

public class ControlPlayer {
    private String playerColor;

    public String getPlayerColor() {
        return this.playerColor;
    }

    public void setPlayerColor(String playerColor) {
        this.playerColor = playerColor;
    }

    public boolean movePiece(Piece piece, int x, int y, int boardMaxX, int boardMaxY) {
        boolean value = false;
        if ((piece.getColor().equals(this.playerColor))
                && ((piece.getPosition_x() + x <= boardMaxX) && (piece.getPosition_x() + x > 0))
                && ((piece.getPosition_y() + y <= boardMaxY) && (piece.getPosition_y() + y > 0))) {
            value = true;
            piece.movePiece(x, y);
        }
        // Success or not
        return value;
    }

    @Override
    public String toString() {
        return "{" + " playerColor='" + this.getPlayerColor() + "'" + "}";
    }

    public ControlPlayer(String playerC) {
        this.playerColor = playerC;
    }

}
