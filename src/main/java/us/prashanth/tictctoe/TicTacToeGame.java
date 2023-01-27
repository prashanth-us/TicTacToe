package us.prashanth.tictctoe;

import us.prashanth.tictctoe.controllers.GameController;
import us.prashanth.tictctoe.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {


    public static void main(String[] args) {

        // It takes input, to create Game class
        // Input: Dimension, Players

        Scanner scanner = new Scanner(System.in);
        GameController gameController = new GameController();
        System.out.println("What should be the dimension of the game?...");
        int dimension = scanner.nextInt();
        System.out.println("Will there be any bot? y/n ");
        String isBotString = scanner.next();
        List<Player> players = new ArrayList<>();


        int toIterate = dimension - 1;

        if (isBotString.equals("y")) {
            toIterate = dimension - 2;
        }
        for (int i = 0; i < toIterate; i++) {
            System.out.println("What is the name of the player " + (i + 1) + "? ");
            String playerName = scanner.next();

            System.out.println("What is the Symbol of the player " + (i + 1) + "? ");
            String playerSymbol = scanner.next();

            players.add(new Player(playerName, playerSymbol.charAt(0), PlayerType.HUMAN));
        }
        if (isBotString.equals("y")) {
            System.out.println("What is the name of the Bot? ");
            String playerName = scanner.next();

            System.out.println("What is the symbol of the Bot? ");
            String playerSymbol = scanner.next();

            players.add(new Bot(playerName, playerSymbol.charAt(0), BotDifficultyLevel.EASY));
        }

        Game game = gameController.createGame(dimension, players);

        while (gameController.GetGameStatus(game).equals(GameStatus.IN_PROGRESS)) {

            System.out.println("this is the current Board: ");
            gameController.displayBoard(game);

            System.out.println("Does anyone want to undo? y/n");
            String input = scanner.next();

            if (input.equals("y")) {
                gameController.undo(game);
            } else {
                int currentPlayerIndex = game.getNextPlayerIndex();
                System.out.println("This is the " + game.getPlayers().get(currentPlayerIndex).getName() + "'s move. " +
                        "Please enter the cell co-ordinate (row,col) starting from 0");
                gameController.executeNextMove(game);

            }
        }
        System.out.println("Game has Ended. Result was: ");
        if (!game.getGameStatus().equals(GameStatus.DRAW)) {
            System.out.println("Winner is: " + gameController.getWinner(game).getName());
        }
    }
}