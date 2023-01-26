package us.prashanth.tictctoe.strategies.gamewinningstrategy;

import us.prashanth.tictctoe.models.Board;
import us.prashanth.tictctoe.models.Cell;

public interface GameWinningStrategy {
    boolean checkWinner(Board board, Cell moveCell);
}
