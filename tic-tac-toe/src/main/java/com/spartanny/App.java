package com.spartanny;

import com.spartanny.entities.Board;
import com.spartanny.entities.Player;

import java.util.Scanner;

/**
 * TIC_TAC_TOE Application
 */
public class App {
    public static void main(String[] args) {

        Player p1 = new Player("Spartanny", "X");
        Player p2 = new Player("Ghost", "O");
        Board board = new Board(p1, p2);
        int movesCount = 0;

        while(board.getWinner() != true) {
            Scanner scanner = new Scanner(System.in);
            int i = scanner.nextInt(), j = scanner.nextInt();
            Player currentPlayer = movesCount % 2 == 0 ? p1 : p2;
            ++movesCount;
            board.playMove(i, j, currentPlayer);
        }
    }

}
