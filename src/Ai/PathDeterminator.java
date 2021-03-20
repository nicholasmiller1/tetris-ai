package Ai;

import Ai.pathgeneration.Command;
import Game.GameBoard;
import Game.blocks.Block;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class PathDeterminator {

    // Criteria: Minimize full holes, minimize height (maybe minimize pillars?)
    // Sample Weights: 2 * numOfHoles + 1.2 * height + (3 * numOfPillars)
    // Additional Attributes can be added later (such as half-holes, bumpiness, etc)
    public static Queue<Command> chooseBestPath(Map<Block, Queue<Command>> sequences) {
        Queue<Command> bestSequence = new LinkedList<>();
        double bestScore = Integer.MAX_VALUE;

        for (Block position : sequences.keySet()) {
            double score = generateScore(position);

            if (score < bestScore) {
                bestScore = score;
                bestSequence = sequences.get(position);
                System.out.println(position);
            }
        }

        System.out.println(bestScore + "    |    " + bestSequence);
        return bestSequence;
    }

    private static double generateScore(Block position) {
        int[][] board = GameBoard.gameBoard;
        int[] coords = position.getCoordinates();
        int maxHeight = board.length;

        // Update the gameboard as if the piece landed and find the max height
        for (int i = 0; i < coords.length; i += 2) {
            board[coords[i+1]][coords[i]] = 1;

            if (coords[i+1] < maxHeight) {
                maxHeight = coords[i+1];
            }
        }

        // Check for holes
        // Algorithm idea (loop through left of farthest left coordinate to right of farthest right coordinate)
        // Keep going down to check for holes until a non-hole is reached (to account for pre-existing holes)
        int numHoles = 0;
//        for (int i = 0; i < coords.length; i += 2) {
//            int j = coords[i+1] + 1;
//            while (j < board.length && board[j][i] == 0) {
//                numHoles++;
//            }
//        }

        // Remove edits to gameboard
        for (int i = 0; i < coords.length; i += 2) {
            board[coords[i+1]][coords[i]] = 0;
        }

        return 2.0 * numHoles + 1.2 * (board.length - maxHeight);
    }
}
