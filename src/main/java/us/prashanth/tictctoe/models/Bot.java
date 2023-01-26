package us.prashanth.tictctoe.models;

import us.prashanth.tictctoe.factories.BotPlayingStrategyFactory;
import us.prashanth.tictctoe.strategies.botplayingstrategy.BotPlayingStrategy;

import java.util.Scanner;

public class Bot extends Player {
    private final BotPlayingStrategy botPlayingStrategy;
    private BotDifficultyLevel botDifficultyLevel;

    public Bot(String name, char symbol, BotDifficultyLevel botDifficultyLevel) {
        super(name, symbol, PlayerType.BOT);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getStrategyForDifficultyLevel(this.botDifficultyLevel);
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }

    @Override
    public Move decideMove(Board board) {
        return botPlayingStrategy.decideMove(this, board);
    }
}
