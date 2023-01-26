package us.prashanth.tictctoe.strategies.botplayingstrategy;

import us.prashanth.tictctoe.models.Board;
import us.prashanth.tictctoe.models.Move;
import us.prashanth.tictctoe.models.Player;

public interface BotPlayingStrategy {

    Move decideMove(Player player, Board board);

}
