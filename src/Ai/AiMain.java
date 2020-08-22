package Ai;

import Game.GameBoard;
import processing.core.PApplet;

import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AiMain {

    private static final Map<String, Integer> keyMap = new TreeMap<>();

    private static GameBoard game;
    private static Queue<String> inputQueue;

    public static void main(String[] args) throws InterruptedException {
        setupEnvironment();

        runProgram();
    }

    private static void runProgram() {
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(AiMain::pressNextKey, 2000, 500, TimeUnit.MILLISECONDS);
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
