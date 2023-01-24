package us.prashanth.tictctoe.controllers;

import us.prashanth.tictctoe.exceptions.InvalidGameConstructionParameterException;
import us.prashanth.tictctoe.models.Game;
import us.prashanth.tictctoe.models.GameStatus;
import us.prashanth.tictctoe.models.Player;

import java.util.List;

public class GameController {
    public void undo(Game game) {
        game.undo();
    }


    public Game createGame(int dimension, List<Player> players) {

        try {
            return Game.getBuilder()
                    .setDimension(dimension)
                    .setPlayers(players)
                    .build();
        } catch (Exception e) {
            return null;
        }
    }

    public void displayBoard(Game game) {
        game.displayBoard();
    }

    public GameStatus GetGameStatus(Game game) {

        return game.getGameStatus();
    }

    public void executeNextMove(Game game) {
        game.makeNextMove();
    }
}
