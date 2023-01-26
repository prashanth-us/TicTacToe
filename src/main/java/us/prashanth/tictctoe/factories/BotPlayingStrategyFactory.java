package us.prashanth.tictctoe.factories;

import us.prashanth.tictctoe.models.BotDifficultyLevel;
import us.prashanth.tictctoe.strategies.botplayingstrategy.BotPlayingStrategy;
import us.prashanth.tictctoe.strategies.botplayingstrategy.RandomBotPlayingStrategy;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getStrategyForDifficultyLevel(BotDifficultyLevel difficultyLevel) {
        return new RandomBotPlayingStrategy();
    }
}
