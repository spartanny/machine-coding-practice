package com.spartanny.entities;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.Arrays;

public class Board {

    private Player[] players;
    private Player winner;
    String[][] board;

    public Board(Player p1, Player p2) {
        board = new String[3][3];
        players = new Player[] {p1, p2};
    }

    public void playMove(int i, int j, Player p) {
        if(winner != null) {
            System.out.println(String.format("GAME OVER ! %s already won", winner.getName()));
            return;
        }
        if(i < 0 || j< 0 || i>=3 || j >= 3 || board[i][j] != null) {
            System.out.println("Invalid move , please try again at a valid location");
            return;
        }

        board[i][j] = p.getSymbol();
        printBoard();

        if(checkWinner(i, j, p)) {
            winner = p;
            System.out.println(String.format("%s won the game", p.getName()));
        }
    }

    boolean checkWinner(int i, int j, Player p) {
        // row , col, diagonal, reverse diagonal
        boolean flag = false;
        int count = 0;

        for(int index = 0; index < 3; index++) {
            if(p.getSymbol().equals(board[i][index]))
                count ++;
        }
        flag = flag || count == 3;
        count = 0;

        for(int index = 0; index < 3; index++) {
            if(p.getSymbol().equals(board[index][j]))
                count ++;
        }
        flag = flag || count == 3;
        count = 0;

        for(int index = 0; index < 3; index++) {
            if(p.getSymbol().equals(board[index][index]))
                count ++;
        }
        flag = flag || count == 3;
        count = 0;

        for(int index = 0; index < 3; index++) {
            if(p.getSymbol().equals(board[index][2-index]))
                count ++;
        }
        flag = flag || count == 3;

        return flag;
    }

    public boolean getWinner() {
        return winner != null;
    }

    public void printBoard() {
        StringBuilder sb = new StringBuilder();
        String s = "%s | %s | %s \n";
        for(int i=0; i<3; i++) {
            sb.append(String.format(s, getCell(i,0), getCell(i,1), getCell(i, 2)));
            if(i != 2)
                sb.append("---------- \n");
        }
        System.out.println(sb);
    }

    private String getCell(int i, int j) {
        if(board[i][j] == null)
            return " ";
        return board[i][j];
    }

}
