package us.prashanth.tictctoe.models;

import java.util.List;

public class Game {

    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private GameStatus gaameStatus;
    private int nextPlayerIndex;

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

    public GameStatus getGaameStatus() {
        return gaameStatus;
    }

    public void setGaameStatus(GameStatus gaameStatus) {
        this.gaameStatus = gaameStatus;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }
}
