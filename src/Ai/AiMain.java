package Ai;

import Ai.pathgeneration.Command;
import Ai.pathgeneration.PathGenerator;
import Ai.pathgeneration.RoutePosition;
import Game.GameBoard;
import Game.blocks.Block;
import processing.core.PApplet;

import java.util.*;

public class AiMain {

    private static GameBoard game;
    private static Queue<Command> sequence;

    public static void main(String[] args) {
        setupEnvironment();
        sequence = new LinkedList<>();

        while (true) {
            try {
                Thread.sleep(75);
                if (!sequence.isEmpty()) {
                    game.processInput(sequence.poll().getKeyInput());
                } else {
                    game.spawnNewPiece();
                }
            } catch (InterruptedException e) {
                System.out.println("Sequence execution was interrupted");
                e.printStackTrace();
            }
        }
    }

    private static void setupEnvironment() {
        String[] processingArgs = {"Game.GameBoard"};
        game = new GameBoard();
        PApplet.runSketch(processingArgs, game);
    }

    public static void generateNewSequence(Block from) {
        sequence = PathDeterminator.chooseBestPath(PathGenerator.generatePossibleSequences(from));
    }
}
