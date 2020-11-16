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
    }

    private static void setupEnvironment() {
        String[] processingArgs = {"Game.GameBoard"};
        game = new GameBoard();
        PApplet.runSketch(processingArgs, game);
    }

    public static void runNextBlock(Block from) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                Map<Block, Queue<Command>> generatedSequences = PathGenerator.generatePossibleSequences(from);

                System.out.println(generatedSequences);
                System.out.println(generatedSequences.size());

                try {
                    executeSequence(generatedSequences.values().iterator().next());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    private static void executeSequence(Queue<Command> sequence) throws InterruptedException {
        for (Command c : sequence) {
            game.processInput(c.getKeyInput());
            Thread.sleep(100);
        }
    }
}
