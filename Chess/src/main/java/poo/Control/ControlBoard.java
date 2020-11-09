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
        this.board = (numberP <= 12) ? new Board(8, 8) : new Board(15, 15);
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

    private int absoluteValue(int value) {
        return (value < 0) ? value * -1 : value;
    }

    public boolean validateSpecialPiecesMove(Piece piece, int desiredX, int desiredY) {
        boolean valid = true;
        if (piece instanceof Pawn) {
            if (!(((((Pawn) (piece)).isFirstMove()) && (this.absoluteValue(desiredY) <= 2)))
                    || !(this.absoluteValue(desiredY) <= 1)) {
                valid = false;
            }
            if (((Pawn) (piece)).isFirstMove())
                ((Pawn) (piece)).setFirstMove(false);
        } else if (piece instanceof Rook) {
            if (!(desiredX == 0 || desiredY == 0))
                valid = false;
        } else if (piece instanceof Knight) {
            if (!(((this.absoluteValue(desiredX) == 1 && this.absoluteValue(desiredY) == 2))||(this.absoluteValue(desiredX) == 2 && this.absoluteValue(desiredY) == 1)))
                valid = false;
        } else if (piece instanceof Bishop) {
            if (this.absoluteValue(desiredX) != this.absoluteValue(desiredY))
                valid = false;
        }
        return valid;
    }

    public Piece lookPiece(int x, int y) {
        try {
            Piece p = board.getPieces()[y - 1][x - 1];
            return p;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean movePiece(int numPlayer, int pieceX, int pieceY, int desiredX, int desiredY) {
        Piece pM = this.lookPiece(pieceX, pieceY);
        Piece p2 = this.lookPiece(pieceX + desiredX, pieceY + desiredY);
        if (pM == null || this.validatePossibleMove(pieceX, pieceY, pieceX + desiredX, pieceY + desiredY) == false)
            return false;
        if (this.validateSpecialPiecesMove(pM, desiredX, desiredY)==false)
            return false;
        if ((p2 == null) || (!(pM.getColor().equals(p2.getColor())))) {
            boolean p = this.players[numPlayer - 1].movePiece(pM, desiredX, desiredY, this.board.getLengthX(),
                    this.board.getLengthY());
            if (p) {
                // Have not updated board, therefore, I can look for the piece in oldX and oldY
                this.updateBoard(this.lookPiece(pieceX, pieceY), pieceX, pieceY);
            }
            return p;
        } else
            return false;
    }

    private boolean validatePossibleMove(int pieceX, int pieceY, int desiredX, int desiredY) {
        boolean valid = true;
        if (desiredX == 0 && desiredY == 0)
            return valid;
        if (desiredX == 0 && desiredY != 0) {
            int increaseY = (pieceY < desiredY) ? 1 : -1;
            // Same x
            for (int j = pieceY + increaseY; j != desiredY && valid; j += increaseY) {
                if (this.lookPiece(pieceX, j) != null)
                    valid = false;
            }
        } else if (desiredY == 0 && desiredX != 0) {
            int increaseX = (pieceX < desiredX) ? 1 : -1;
            // Same y
            for (int i = pieceX + increaseX; i != desiredX && valid; i += increaseX) {
                if (this.lookPiece(i, pieceY) != null)
                    valid = false;
            }
        } else {
            int increaseY = (pieceY < desiredY) ? 1 : -1;
            int increaseX = (pieceX < desiredX) ? 1 : -1;
            if (increaseX == 1 && increaseY == 1) {
                // All positive
                for(int i=1; i<=desiredX&&valid; i++){
                    if (this.lookPiece(pieceX+i, pieceY+i) != null)
                        valid = false;
                }
            }
            else if (increaseX == 1 && increaseY == -1) {
                // X+ Y-
                for(int i=1; i<=desiredX&&valid; i++){
                    if (this.lookPiece(pieceX+i, pieceY-i) != null)
                        valid = false;
                }
            }
            else if (increaseX == -1 && increaseY == 1) {
                // X+ Y-
                for(int i=-1; i>=desiredX&&valid; i--){
                    if (this.lookPiece(pieceX+i, pieceY-i) != null)
                        valid = false;
                }
            }
            else{
                // X- Y-
                for(int i=-1; i>=desiredX&&valid; i--){
                    if (this.lookPiece(pieceX+i, pieceY+i) != null)
                        valid = false;
                }
            }
        }
        return valid;
    }

    public String printBoard() {
        String board = "";
        for (int i = 0; i < this.board.getLengthY(); i++) {
            for (int j = this.board.getLengthX()-1; j >=0 ; --j) {
                try{
                    board += " |["+String.valueOf(i+1)+"]["+String.valueOf(j+1)+"]";
                    board += String.valueOf(this.lookPiece(j+1, i+1).getClass().getCanonicalName())+' '+String.valueOf(this.lookPiece(j + 1, i + 1).getColor());
                }catch(Exception e){
                    board+="NULL";
                }
            }
            board += "|\n";
        }
        return board;
    }

    public boolean updateBoard(Piece piece, int oldX, int oldY) {
        Piece np = this.lookPiece(piece.getPosition_x(), piece.getPosition_y());
        if (np != null && !(np.getColor().equals(piece.getColor())))
            piece.eatPiece(piece);
        this.board.getPieces()[oldY - 1][oldX - 1] = null;
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

    public EnumPieces getEnumPiece(int n) throws Error {
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
                    ep = EnumPieces.King;
                    break;
                }
                case 4: {
                    ep = EnumPieces.Bishop;
                    break;
                }
                case 5: {
                    ep = EnumPieces.Knight;
                    break;
                }
                case 6: {
                    ep = EnumPieces.Rook;
                    break;
                }
                default: {
                    throw new Error();
                }
            }
            return ep;
        } catch (Exception e) {
            return ep;
        }
    }

    // For 2 players
    public void startFor2() {
        for (int i = 0; i < this.board.getLengthX(); ++i) {
            for (int j = 0; j < this.board.getLengthY(); ++j) {
                if ((i == 0 && j == 3) || (i == 7 && j == 3))
                    this.addPieceToBoard(EnumPieces.Queen, (i == 0) ? 1 : 2, j + 1, i + 1);
                else if ((i == 1) || (i == 6))
                    this.addPieceToBoard(EnumPieces.Pawn, (i == 1) ? 1 : 2, j + 1, i + 1);
                else if ((i == 0 && j == 4) || (i == 7 && j == 4))
                    this.addPieceToBoard(EnumPieces.King, (i == 0) ? 1 : 2, j + 1, i + 1);
            }
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
                creada = new King(1, 1, true, x, y, (playerNumber == 1) ? "FFFFFF"
                        : (playerNumber == 2) ? "000000" : (playerNumber == 3) ? "FFF000" : "AAAFFF");
                break;
            }
            case Queen: {
                creada = new Queen(8, 8, true, x, y, (playerNumber == 1) ? "FFFFFF"
                        : (playerNumber == 2) ? "000000" : (playerNumber == 3) ? "FFF000" : "AAAFFF");
                break;
            }
            case Bishop: {
                creada = new Bishop(8, 8, true, x, y, (playerNumber == 1) ? "FFFFFF"
                        : (playerNumber == 2) ? "000000" : (playerNumber == 3) ? "FFF000" : "AAAFFF");
            }
            case Knight: {
                creada = new Knight(2, 2, true, x, y, (playerNumber == 1) ? "FFFFFF"
                        : (playerNumber == 2) ? "000000" : (playerNumber == 3) ? "FFF000" : "AAAFFF");
                break;
            }
            case Rook: {
                creada = new Rook(8, 8, true, x, y, (playerNumber == 1) ? "FFFFFF"
                        : (playerNumber == 2) ? "000000" : (playerNumber == 3) ? "FFF000" : "AAAFFF");
                break;
            }
            default: {
                break;
            }
        }
        return creada;
    }
}