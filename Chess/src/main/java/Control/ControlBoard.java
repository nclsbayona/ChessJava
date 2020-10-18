package Control;

import Model.Board;
import Model.Piece;

public class ControlBoard 
{
    @Override
    public String toString() 
    {
        return "{" +
            " board='" + getBoard() + "'" +
            ", numberPlayers='" + getNumberPlayers() + "'" +
            ", players[]='" + getPlayers() + "'" +
            "}";
    }
    private Board board;
    private int numberPlayers;
    private ControlPlayer[] players;

    public ControlBoard(int numberP) 
    {
        this.numberPlayers=numberP;
        this.players=new ControlPlayer[numberP];
    }

    public int getNumberPlayers() 
    {
        return this.numberPlayers;
    }

    public void setNumberPlayers(int numberPlayers) 
    {
        this.numberPlayers = numberPlayers;
    }

    public ControlPlayer[] getPlayers() 
    {
        return this.players;
    }

    public void setPlayers(ControlPlayer players[]) 
    {
        this.players = players;
    }

    public Board getBoard() 
    {
        return this.board;
    }

    public void setBoard(Board board) 
    {
        this.board = board;
    }

    public Piece lookPiece(int x, int y) 
    {
        return board.getBoard()[y - 1][x - 1];
    }

    public boolean movePiece(int numPlayer, int pieceX, int pieceY, int desiredX, int desiredY) 
    {
        return players[numPlayer - 1].movePiece(this.lookPiece(pieceX, pieceY), desiredX, desiredY,
                this.board.getLengthX(), this.board.getLengthY());
    }
}