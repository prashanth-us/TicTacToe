package us.prashanth.tictctoe.strategies.gamewinningstrategy;

import us.prashanth.tictctoe.models.Board;
import us.prashanth.tictctoe.models.Cell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneGameWinningStrategy implements GameWinningStrategy {

    private final List<HashMap<Character, Integer>> rowSymbolCounts = new ArrayList<>();
    private final List<HashMap<Character, Integer>> colSymbolCounts = new ArrayList<>();

    private final HashMap<Character, Integer> topLeftDiagSymbolCount = new HashMap<>();
    private final HashMap<Character, Integer> topRightDiagSymbolCount = new HashMap<>();

    public OrderOneGameWinningStrategy(int dimension) {
        for (int i = 0; i < dimension; i++) {
            rowSymbolCounts.add(new HashMap<>());
            colSymbolCounts.add(new HashMap<>());
        }
    }

    public boolean isCellOnTopLeftDiag(int row, int col) {

        return row == col;
    }

    public boolean isCellOnTopRightDiag(int row, int col, int dimension) {

        return (row + col) == (dimension - 1);
    }

    @Override
    public boolean checkWinner(Board board, Cell moveCell) {
        char symbol = moveCell.getPlayer().getSymbol();
        int row = moveCell.getRow();
        int col = moveCell.getRow();
        int dimension = board.getBoard().size();

        if (!rowSymbolCounts.get(row).containsKey(symbol)) {
            rowSymbolCounts.get(row).put(symbol, 0);
        }
        rowSymbolCounts.get(row).put(
                symbol,
                1 + rowSymbolCounts.get(row).get(symbol)
        );

        if (!colSymbolCounts.get(col).containsKey(symbol)) {
            colSymbolCounts.get(col).put(symbol, 0);
        }
        colSymbolCounts.get(col).put(
                symbol,
                1 + colSymbolCounts.get(col).get(symbol)
        );

        if (isCellOnTopLeftDiag(row, col)) {

            if (!topLeftDiagSymbolCount.containsKey(symbol)) {
                topLeftDiagSymbolCount.put(symbol, 0);
            }
            topLeftDiagSymbolCount.put(
                    symbol,
                    1 + topLeftDiagSymbolCount.get(symbol)
            );
        }

        if (isCellOnTopRightDiag(row, col, dimension)) {

            if (!topRightDiagSymbolCount.containsKey(symbol)) {
                topRightDiagSymbolCount.put(symbol, 0);
            }
            topRightDiagSymbolCount.put(
                    symbol,
                    1 + topRightDiagSymbolCount.get(symbol)
            );
        }


        return rowSymbolCounts.get(row).get(symbol) == dimension ||
                colSymbolCounts.get(row).get(symbol) == dimension ||
                (topLeftDiagSymbolCount.containsKey(symbol) && topLeftDiagSymbolCount.get(symbol) == dimension) ||
                (topRightDiagSymbolCount.containsKey(symbol) && topRightDiagSymbolCount.get(symbol) == dimension);
    }
}
