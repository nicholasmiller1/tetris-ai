package Ai;

import Ai.pathgeneration.Command;
import Ai.pathgeneration.PathGenerator;
import Ai.pathgeneration.Position;
import Ai.pathgeneration.RoutePosition;
import Game.GameBoard;
import Game.blocks.Block;
import processing.core.PApplet;

import java.awt.event.KeyEvent;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AiMain {

    private static GameBoard game;

    public static void main(String[] args) {
        setupEnvironment();

        Position from = new Position(0,0,0);
        Position end = new Position(3,19,0);
        Map<Position, Queue<Command>> generatedSequences = PathGenerator.generatePossibleSequences(from);

        System.out.println(generatedSequences);
        System.out.println(generatedSequences.size());

        executeSequence(generatedSequences.get(end));
    }

    private static void setupEnvironment() {
        String[] processingArgs = {"Game.GameBoard"};
        game = new GameBoard();
        PApplet.runSketch(processingArgs, game);
    }

    private static void executeSequence(Queue<Command> sequence) {
        for (Command c : sequence) {
            System.out.print(c.getKeyInput() + " | ");
            game.processInput(c.getKeyInput());
        }
    }
}
