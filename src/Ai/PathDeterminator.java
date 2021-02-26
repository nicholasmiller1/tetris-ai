package Ai;

import Ai.pathgeneration.Command;
import Game.blocks.Block;

import java.util.Map;
import java.util.Queue;

public class PathDeterminator {
    public static Queue<Command> chooseBestPath(Map<Block, Queue<Command>> sequences) {
        Queue<Command> bestSequence = sequences.values().iterator().next();

        // TODO: Write determination algorithm
        // Criteria: Minimize full holes, minimize height (maybe minimize pillars?)
        // Sample Weights: 2 * numOfHoles + 1.2 * height + (3 * numOfPillars)
        // Additional Attributes can be added later (such as half-holes, bumpiness, etc)

        return bestSequence;
    }
}
