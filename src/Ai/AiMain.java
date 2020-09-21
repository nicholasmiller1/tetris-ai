package Ai;

import Ai.pathfinding.Pathfinder;
import Ai.pathfinding.Position;
import Ai.pathfinding.generics.Scorer;
import Game.GameBoard;
import processing.core.PApplet;

import java.awt.event.KeyEvent;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AiMain {

    private static final Map<String, Integer> keyMap = new TreeMap<>();

    private static GameBoard game;
    private static Queue<String> inputQueue;
    private static Pathfinder pathfinder;

    public static void main(String[] args) {
        setupEnvironment();

//        runProgram();

        pathfinder = new Pathfinder(GameBoard.gameBoard);
        List<Position> path = pathfinder.findRoute(new Position(5, 1, 0), new Position(1, 9, 0));
        System.out.println(path);
    }

    private static void runProgram() {
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(AiMain::pressNextKey, 1000, 300, TimeUnit.MILLISECONDS);
    }

    private static void setupEnvironment() {
        String[] processingArgs = {"Game.GameBoard"};
        game = new GameBoard();
        PApplet.runSketch(processingArgs, game);

        createKeyMap();
        inputQueue = new LinkedList<>();

        inputQueue.add("Left");
        inputQueue.add("Left");
        inputQueue.add("Clockwise");
        inputQueue.add("Drop");
    }

    private static void createKeyMap() {
        keyMap.put("Left", KeyEvent.VK_LEFT);
        keyMap.put("Right", KeyEvent.VK_RIGHT);
        keyMap.put("Fall", KeyEvent.VK_DOWN);
        keyMap.put("Drop", KeyEvent.VK_UP);
        keyMap.put("Hold", KeyEvent.VK_SPACE);
        keyMap.put("Clockwise", KeyEvent.VK_X);
        keyMap.put("CounterClockwise", KeyEvent.VK_Z);
    }

    private static void pressNextKey() {
        String input = inputQueue.poll();
        if (input != null) {
            game.processInput(keyMap.get(input));
        }
    }
}
