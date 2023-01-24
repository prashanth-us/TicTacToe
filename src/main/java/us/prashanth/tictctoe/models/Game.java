package us.prashanth.tictctoe.models;

import us.prashanth.tictctoe.exceptions.InvalidGameConstructionParameterException;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private GameStatus gameStatus;
    private int nextPlayerIndex;

    public Game() {
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public void undo() {
    }

    public void makeNextMove() {
    }

    public void displayBoard() {
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }


    public static class Builder {
        private int dimension;
        private List<Player> players;


        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        private boolean isValid() throws InvalidGameConstructionParameterException {
            if (this.dimension < 3) {
                throw new InvalidGameConstructionParameterException("Dimension of Game cannot be less than 3");
            }
            if (this.players.size() < (this.dimension - 1)) {
                throw new InvalidGameConstructionParameterException("number of players must be 1less than the dimension");
            }
            return true;
        }

        public Game build() throws InvalidGameConstructionParameterException {
            try {
                isValid();
            } catch (Exception e) {
                throw new InvalidGameConstructionParameterException(e.getMessage());
            }
            Game game = new Game();
            game.setGameStatus(GameStatus.IN_PROGRESS);
            game.setPlayers(players);
            game.setMoves(new ArrayList<>());
            game.setBoard(new Board(dimension));
            game.setNextPlayerIndex(-1);
            return game;
        }
    }
}
