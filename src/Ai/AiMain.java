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

    public static void main(String[] args) {
        setupEnvironment();

        Block from = new Block(game.getCurrentBlock());
        Map<Block, Queue<Command>> generatedSequences = PathGenerator.generatePossibleSequences(from);

        System.out.println(generatedSequences);
        System.out.println(generatedSequences.size());

        try {
            executeSequence(generatedSequences.values().iterator().next());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void setupEnvironment() {
        String[] processingArgs = {"Game.GameBoard"};
        game = new GameBoard();
        PApplet.runSketch(processingArgs, game);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void executeSequence(Queue<Command> sequence) throws InterruptedException {
        for (Command c : sequence) {
            System.out.print(c.getKeyInput() + " | ");
            game.processInput(c.getKeyInput());
            Thread.sleep(100);
        }
    }
}
