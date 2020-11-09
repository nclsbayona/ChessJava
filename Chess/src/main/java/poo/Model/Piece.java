package poo.Model;

import java.util.HashSet;

public abstract class Piece {
    protected HashSet<Piece> eatenPieces;
    protected boolean canBeEaten;
    protected String color;
    protected int eaten;
    protected int position_x;
    protected int position_y;
    protected int maxMovement_length_x;
    protected int maxMovement_length_y;
    protected boolean canMoveBack;

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getEaten() {
        return this.eaten;
    }

    public void setEaten(int eaten) {
        this.eaten = eaten;
    }

    public int getPosition_x() {
        return this.position_x;
    }

    public void setPosition_x(int position_x) {
        this.position_x = position_x;
    }

    public int getPosition_y() {
        return this.position_y;
    }

    public void setPosition_y(int position_y) {
        this.position_y = position_y;
    }

    public int getMaxMovement_length_x() {
        return this.maxMovement_length_x;
    }

    public void setMaxMovement_length_x(int maxMovement_length_x) {
        this.maxMovement_length_x = maxMovement_length_x;
    }

    public int getMaxMovement_length_y() {
        return this.maxMovement_length_y;
    }

    public void setMaxMovement_length_y(int maxMovement_length_y) {
        this.maxMovement_length_y = maxMovement_length_y;
    }

    public boolean isCanMoveBack() {
        return this.canMoveBack;
    }

    public void setCanMoveBack(boolean canMoveBack) {
        this.canMoveBack = canMoveBack;
    }

    public Piece(int movX, int movY, boolean canMoveBack, int x, int y, String color) {
        this.eatenPieces=new HashSet<>();
        this.color = color;
        this.eaten = 0;
        this.maxMovement_length_x = movX;
        this.maxMovement_length_y = movY;
        this.canMoveBack = canMoveBack;
        this.position_x = x;
        this.position_y = y;
    }

    public void eatPiece(Piece piece) {
        this.eatenPieces.add(piece);
        this.eaten += 1;
    }

    public void movePiece(int movement_in_x, int movement_in_y) {
        this.position_x += movement_in_x;
        this.position_y += movement_in_y;
    }

    @Override
    public String toString() {
        return "Color: " + this.color + " eaten: " + this.eaten + '\t' + this.getClass()+' ';
    }
}
