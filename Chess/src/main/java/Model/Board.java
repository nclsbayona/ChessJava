package Model;
public class Board 
{
    private int lengthX;
    private int lengthY;
    private Piece[][] board;

    public int getLengthX() 
    {
        return this.lengthX;
    }

    public void setLengthX(int lengthX) 
    {
        this.lengthX = lengthX;
    }

    public int getLengthY() 
    {
        return this.lengthY;
    }

    public void setLengthY(int lengthY) 
    {
        this.lengthY = lengthY;
    }

    public Piece[][] getBoard() 
    {
		return this.board;
	}

    public void setBoard(Piece[][] board) 
    {
		this.board = board;
	}

    public Board(int x, int y)
    {
        this.lengthX=x;
        this.lengthY=y;
        this.board=new Piece[lengthY][lengthX];
    }
}
