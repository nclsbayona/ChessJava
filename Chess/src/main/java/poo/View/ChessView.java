package poo.View;

import java.util.Scanner;

import poo.Control.ControlBoard;

public class ChessView {
    private ControlBoard controlBoard;

    public ControlBoard getControlBoard() {
        return this.controlBoard;
    }

    public void setControlBoard(ControlBoard controlBoard) {
        this.controlBoard = controlBoard;
    }

    public static void main(String[] args) {
        String numberP;
        Scanner sc = new Scanner(System.in);
        ChessView nChessView = null;
        while (nChessView == null) {
            System.out.println("Number of players: ");
            numberP = sc.nextLine();
            if (Integer.valueOf(numberP) < 1 || Integer.valueOf(numberP) > 4)
                System.out.println("Try again");
            else
                nChessView = new ChessView(Integer.parseInt(numberP));
        }
        nChessView.controlBoard.addPieceToBoard(nChessView.controlBoard.getEnumPiece(1), 1, 2, 2);
        nChessView.controlBoard.addPieceToBoard(nChessView.controlBoard.getEnumPiece(2), 2, 4, 8);
        System.out.println(nChessView);
        nChessView.controlBoard.movePiece(2, 4, 8, 0, -6);
        nChessView.controlBoard.movePiece(2, 4, 2, -2, 0);
        System.out.println(nChessView);
        System.out.println(nChessView.controlBoard.lookPiece(2, 2));
        sc.close();
    }

    public ChessView(int numberP) {
        if (numberP < 1 || numberP > 4)
            return;
        this.controlBoard = new ControlBoard(numberP);
    }

    @Override
    public String toString() {
        return "{" + " controlBoard='" + this.getControlBoard() + "'" + "}";
    }

}