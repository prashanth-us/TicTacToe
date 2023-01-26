package us.prashanth.tictctoe.models;

import us.prashanth.tictctoe.exceptions.InvalidGameConstructionParameterException;
import us.prashanth.tictctoe.strategies.gamewinningstrategy.GameWinningStrategy;
import us.prashanth.tictctoe.strategies.gamewinningstrategy.OrderOneGameWinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private GameWinningStrategy gameWinningStrategy;
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private GameStatus gameStatus;
    private int nextPlayerIndex;
    private Player winner;

    public Game() {
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public Player getWinner() {
        return winner;
    }

    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
        this.gameWinningStrategy = gameWinningStrategy;
    }

    public void undo() {
    }

    public void makeNextMove() {
        Player playerToMove = players.get(nextPlayerIndex);
        System.out.println("It is " + players.get(nextPlayerIndex).getName() + "'s turn");

        Move move = playerToMove.decideMove(this.board);
        // validate Move
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        System.out.println("Move happened at: " + row + ", " + col + ".");

        board.getBoard().get(row).get(col).setCellState(CellState.FILLED);
        board.getBoard().get(row).get(col).setPlayer(players.get(nextPlayerIndex));

        Move finalMove = new Move(players.get(nextPlayerIndex), board.getBoard().get(row).get(col));
        move.getCell().setCellState(CellState.FILLED);

        this.moves.add(finalMove);

        if (gameWinningStrategy.checkWinner(board, finalMove.getCell())) {
            gameStatus = GameStatus.ENDED;
            winner = players.get(nextPlayerIndex);
        }
        nextPlayerIndex++;
        nextPlayerIndex = nextPlayerIndex % players.size();
    }

    public void displayBoard() {
        board.display();
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

        private void isValid() throws InvalidGameConstructionParameterException {
            if (this.dimension < 3) {
                throw new InvalidGameConstructionParameterException("Dimension of Game cannot be less than 3");
            }
            if (this.players.size() < (this.dimension - 1)) {
                throw new InvalidGameConstructionParameterException("number of players must be 1less than the dimension");
            }
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
            game.setNextPlayerIndex(0);
            game.setGameWinningStrategy(new OrderOneGameWinningStrategy(dimension));
            return game;
        }
    }
}
