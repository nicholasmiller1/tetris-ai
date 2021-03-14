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
            System.out.println("Waiting...");
            while (currentThread.isAlive()) {
                // wait
            }
//            currentThread.interrupt();
        }

        currentThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Map<Block, Queue<Command>> generatedSequences = PathGenerator.generatePossibleSequences(from);

//                System.out.print(generatedSequences + "    |    ");
                Queue<Command> chosenSequence = PathDeterminator.chooseBestPath(generatedSequences);
//                System.out.println(chosenSequence);

                try {
                    executeSequence(chosenSequence);
                } catch (InterruptedException e) {
                    System.out.println("executeSequence was interrupted");
                    e.printStackTrace();
                }
            }
        });
        currentThread.start();
    }

    private static void executeSequence(Queue<Command> sequence) throws InterruptedException {
        System.out.print("00.0    |    [");
        for (Command c : sequence) {
            if (currentThread.isInterrupted()) {
                System.out.println("Thread was interrupted");
                return;
            }

            System.out.print(c.getStringInput() + ", ");
            game.processInput(c.getKeyInput());
            Thread.sleep(100);
        }
        System.out.println();
    }
}
