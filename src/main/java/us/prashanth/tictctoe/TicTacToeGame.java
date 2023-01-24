package us.prashanth.tictctoe;

import us.prashanth.tictctoe.controllers.GameController;
import us.prashanth.tictctoe.exceptions.InvalidGameConstructionParameterException;
import us.prashanth.tictctoe.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {


    public static void main(String[] args) throws InvalidGameConstructionParameterException {

        // It takes input, to create Game class
        // Input: Dimension, Players

        Scanner scanner = new Scanner(System.in);
        GameController gameController = new GameController();
        System.out.println("What should be the dimension of the game");
        int dimension = scanner.nextInt();
        System.out.println("Will there be any bot? ");
        String isBotSTring = scanner.nextLine();
        List<Player> players = new ArrayList<>();


        int toIterate = dimension - 1;

        if (isBotSTring.equals("y")) {
            toIterate = dimension - 2;
        }
        for (int i = 0; i < toIterate; i++) {
            System.out.println("What is the name of the player? ");
            String playerName = scanner.nextLine();

            System.out.println("What is the symbol of the player? ");
            String playerSymbol = scanner.nextLine();

            players.add(new Player(playerName, playerSymbol.charAt(0), PlayerType.HUMAN));
        }
        if (isBotSTring.equals("y")) {
            System.out.println("What is the name of the Bot? ");
            String playerName = scanner.nextLine();

            System.out.println("What is the symbol of the Bot? ");
            String playerSymbol = scanner.nextLine();

            players.add(new Bot(playerName, playerSymbol.charAt(0), BotDifficultyLevel.EASY));
        }

        Game game = gameController.createGame(dimension, players);

        while (gameController.GetGameStatus(game).equals(GameStatus.IN_PROGRESS)) {

            System.out.println("this is the current Board: ");
            gameController.displayBoard(game);

            System.out.println("Does anyone want to undo? y/n");
            String input = scanner.nextLine();

            if (input.equals("y")) {
                gameController.undo(game);
            } else {
                gameController.executeNextMove(game);
                System.out.println("This is the [current player]'s move. " +
                        "Please enter th cell co-ordinate (row,col) starting from 1");
            }
        }
        System.out.println("Game has Ended. Result was: ");
        if (!game.getGameStatus().equals(GameStatus.DRAW)) {
            System.out.println("Winner is: ");
        }
    }
}