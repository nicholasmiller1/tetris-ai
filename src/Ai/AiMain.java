package Ai;

import Game.GameBoard;
import processing.core.PApplet;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Map;
import java.util.TreeMap;

public class AiMain {

    private static final Map<String, Integer> keyMap = new TreeMap<>();

    private static GameBoard game;
    private static Robot gameController;
    private static int inputCounter;

    public static void main(String[] args) {
        setupEnvironment();

        pressKeys("Left", "Left", "Clockwise", "Drop");
    }

    private static void setupEnvironment() {
        String[] processingArgs = {"Game.GameBoard"};
        game = new GameBoard();
        PApplet.runSketch(processingArgs, game);

        try {
            gameController = new Robot();
        } catch(AWTException e) {
            e.printStackTrace();
        }

        createKeyMap();
        inputCounter = 0;
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

    private static void pressKeys(String... keys) {
        for (String key : keys) {
            pressKey(key);
        }
    }

    private static void pressKey(String key) {
        int keyCode = keyMap.get(key);
        System.out.println(inputCounter + " | " + game.getInputCounter() + " | " + key);
        inputCounter++;
        while (inputCounter > game.getInputCounter()) {
            gameController.keyPress(keyCode);
        }
    }
}
