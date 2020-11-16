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
    private static Thread currentThread;

    public static void main(String[] args) {
        setupEnvironment();
    }

    private static void setupEnvironment() {
        String[] processingArgs = {"Game.GameBoard"};
        game = new GameBoard();
        PApplet.runSketch(processingArgs, game);
    }

    public static void runNextBlock(Block from) {
        if (currentThread != null && currentThread.isAlive()) {
            currentThread.interrupt();
        }

        currentThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Map<Block, Queue<Command>> generatedSequences = PathGenerator.generatePossibleSequences(from);

                System.out.println(generatedSequences);
                System.out.println(generatedSequences.size());

                try {
                    executeSequence(generatedSequences.values().iterator().next());
                } catch (InterruptedException e) {
                    System.out.println("executeSequence was interrupted");
                }
            }
        });
        currentThread.start();
    }

    private static void executeSequence(Queue<Command> sequence) throws InterruptedException {
        for (Command c : sequence) {
            if (currentThread.isInterrupted()) {
                System.out.println("Thread was interrupted");
                return;
            }

            game.processInput(c.getKeyInput());
            Thread.sleep(100);
        }
    }
}
