package Ai;

import Game.GameBoard;
import processing.core.PApplet;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.security.Key;

public class AiMain {

    private static GameBoard game;
    private static Robot gameController;

    public static void main(String[] args) {
        setupEnvironment();

        gameController.keyPress(KeyEvent.VK_UP);
        System.out.println(KeyEvent.KEY_PRESSED);
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
    }
}
