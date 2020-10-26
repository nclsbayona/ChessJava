package poo.Control;

import poo.Model.Board;
import poo.Model.Piece;
import poo.Model.Pieces.*;

public class ControlBoard {
    @Override
    public String toString() {
        String ret = "{" + " board='" + this.getBoard() + "'\n" + ", numberPlayers='" + this.getNumberPlayers() + "'\n"
                + ", players[]='";
        for (ControlPlayer p : this.players)
            ret += p.toString() + "'\t'";
        ret += "'" + "}";
        return ret;
    }

    private Board board;
    private int numberPlayers;
    private ControlPlayer[] players;

    public ControlBoard(int numberP) {
        if (numberP > 4 || numberP < 1)
            return;
        this.board = new Board(8, 8);
        this.numberPlayers = numberP;
        this.players = new ControlPlayer[numberP];
        for (int i = 0; i < this.numberPlayers; ++i) {
            switch (i + 1) {
                case 4: {
                    this.players[i] = new ControlPlayer("AAAFFF");
                    break;
                }
                case 3: {
                    this.players[i] = new ControlPlayer("FFF000");
                    break;
                }
                case 2: {
                    this.players[i] = new ControlPlayer("000000");
                    break;
                }
                case 1: {
                    this.players[i] = new ControlPlayer("FFFFFF");
                    break;
                }
            }
        }
    }

    public int getNumberPlayers() {
        return this.numberPlayers;
    }

    public void setNumberPlayers(int numberPlayers) {
        this.numberPlayers = numberPlayers;
    }

    public ControlPlayer[] getPlayers() {
        return this.players;
    }

    public void setPlayers(ControlPlayer players[]) {
        this.players = players;
    }

    public Board getBoard() {
        return this.board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Piece lookPiece(int x, int y) {
        try {
            Piece p = board.getPieces()[y-1][x-1];
            return p;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean movePiece(int numPlayer, int pieceX, int pieceY, int desiredX, int desiredY) {
        Piece pM=this.lookPiece(pieceX, pieceY);
        Piece p2=this.lookPiece(pieceX + desiredX, pieceY + desiredY);
        if (pM==null)
            return false;
        if ((p2 == null)||(!(pM.getColor().equals(p2.getColor())))) {
            boolean p = this.players[numPlayer - 1].movePiece(pM, desiredX,
                    desiredY, this.board.getLengthX(), this.board.getLengthY());
            if (p)
            {
                System.out.println();
                this.updateBoard(this.lookPiece(pieceX, pieceY), pieceX, pieceY);
            }
            return p;
        } else
            return false;
    }

    public boolean updateBoard(Piece piece, int oldX, int oldY) {
        Piece np=this.lookPiece(piece.getPosition_x(), piece.getPosition_y());
        if (np!=null && !(np.getColor().equals(piece.getColor())))
            piece.eatPiece(piece);
        this.board.getPieces()[oldY-1][oldX-1] = null;
        this.board.getPieces()[piece.getPosition_y() - 1][piece.getPosition_x() - 1] = piece;
        return true;
    }

    public boolean addPieceToBoard(EnumPieces piece, int playerNumber, int x, int y) {
        try {
            Piece created = this.createPiece(piece, playerNumber, x, y);
            if (created == null)
                throw new IllegalAccessException();
            this.board.addPiece(created, x - 1, y - 1);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public EnumPieces getEnumPiece(int n) {
        EnumPieces ep = null;
        try {
            switch (n) {
                case 1: {
                    ep = EnumPieces.Pawn;
                    break;
                }
                case 2: {
                    ep = EnumPieces.Queen;
                    break;
                }
                case 3: {
                    break;
                }
                case 4: {
                    break;
                }
                case 5: {
                    break;
                }
                case 6: {
                    break;
                }
                default: {
                    throw new IllegalAccessException();
                }
            }
            return ep;
        } catch (Exception e) {
            return ep;
        }
    }

    public Piece createPiece(EnumPieces piece, int playerNumber, int x, int y) {
        Piece creada = null;
        if (playerNumber > this.numberPlayers || playerNumber < 1)
            return creada;
        switch (piece) {
            case Pawn: {
                creada = new Pawn(0, 1, false, x, y, (playerNumber == 1) ? "FFFFFF"
                        : (playerNumber == 2) ? "000000" : (playerNumber == 3) ? "FFF000" : "AAAFFF");
                break;
            }
            case King: {

                break;
            }
            case Queen: {
                creada = new Queen(8, 8, true, x, y, (playerNumber == 1) ? "FFFFFF"
                        : (playerNumber == 2) ? "000000" : (playerNumber == 3) ? "FFF000" : "AAAFFF");
                break;
            }
        }
        return creada;
    }
}