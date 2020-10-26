package poo.Model;

public class Board {
    private int lengthX;
    private int lengthY;
    private Piece[][] pieces;

    public int getLengthX() {
        return this.lengthX;
    }

    public void setLengthX(int lengthX) {
        this.lengthX = lengthX;
    }

    public int getLengthY() {
        return this.lengthY;
    }

    public void setLengthY(int lengthY) {
        this.lengthY = lengthY;
    }

    public Piece[][] getPieces() {
        return this.pieces;
    }

    public void setPieces(Piece[][] board) {
        this.pieces = board;
    }

    public Board(int x, int y) {
        this.lengthX = x;
        this.lengthY = y;
        this.pieces = new Piece[this.lengthY][this.lengthX];
        for (int i = 0; i < this.lengthY; ++i) {
            for (int j = 0; j < this.lengthY; ++j)
                this.pieces[i][j] = null;
        }
    }

    public boolean addPiece(Piece piece, int x, int y) {
        try {
            if (this.pieces[y][x] != null)
                throw new IllegalAccessException();
            this.pieces[y][x] = piece;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString() {
        String ret = "{" + " lengthX='" + this.getLengthX() + "'" + ", lengthY='" + this.getLengthY() + "'"
                + ", pieces='";
        for (int i = 0; i < this.lengthY; ++i) {
            for (int j = 0; j < this.lengthY; ++j) {
                ret += "[" + String.valueOf(i + 1) + "][" + String.valueOf(j + 1) + "]" + ": "
                        + ((this.pieces[i][j] == null) ? "Empty" : this.pieces[i][j].toString()) + "\'";
                if (i < this.lengthY - 1 || j < this.lengthX - 1)
                    ret += '\t';
            }
        }
        ret += "\'}";
        return ret;
    }

}
