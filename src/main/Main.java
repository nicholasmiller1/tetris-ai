package main;

import blocks.*;
import processing.core.PApplet;

import java.util.Arrays;
import java.util.Random;

public class Main extends PApplet {

    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 700;
    private static final int SQUARE_SIZE = 20;
    private static final int[][] COLOR_VALUES = {{255, 255, 255}, {0, 255, 255}, {0, 0, 255}, {255, 128, 0}, {255, 255, 0}, {0, 255, 0}, {178, 102, 255}, {255, 0, 0}};

    public static final int[] STAGE_BORDERS = {300, 150, 500, 550};
    public static int[][] gameBoard;

    private static Block currentBlock;
    private int loopCounter;
    private int pressInterval;
    private int fallSpeed;
    private static Random rng;

    public static void main(String[] args) {
        PApplet.main("main.Main");
    }

    public void settings() {
        size(SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    public void setup() {
        loopCounter = 0;
        pressInterval = 0;
        fallSpeed = 60;

        gameBoard = new int[20][10];
        for (int r = 0; r < gameBoard.length; r++) {
            for (int c = 0; c < gameBoard[0].length; c++) {
                gameBoard[r][c] = 0;
            }
        }

        rng = new Random();
        spawnNewPiece();
    }

    public void draw() {
        drawBackground();
        loopCounter += 1;

        moveCurrentBlock(currentBlock, loopCounter);
        displayBlock(currentBlock);
    }

    public void keyPressed() {
        if (loopCounter - pressInterval > 5 && keyCode == RIGHT) {
            currentBlock.moveRight();
            pressInterval = loopCounter;
        } else if (loopCounter - pressInterval > 5 && keyCode == LEFT) {
            currentBlock.moveLeft();
            pressInterval = loopCounter;
        } else if (keyCode == DOWN) {
            fallSpeed = 5;
        } else if (keyCode == UP) {
            currentBlock.autoFall();
        } else if (key == 'z') {
            currentBlock.rotateCounterClockwise();
        } else if (key == 'x') {
            currentBlock.rotateClockwise();
        } else if (key == ' ') {
            spawnNewPiece();
        }
    }

    public void keyReleased() {
        if (keyCode == DOWN) {
            fallSpeed = 60;
        }
    }

    private void displayBlock(Block b) {
        int[] positions = b.getCoordinates();
        int blockType = b.getBlockType();

        fill(COLOR_VALUES[blockType][0], COLOR_VALUES[blockType][1], COLOR_VALUES[blockType][2]);
        strokeWeight(1);

        for (int i = 0; i < positions.length; i += 2) {
            if (positions[i] < 10 && positions[i] >= 0 && positions[i+1] < 20 && positions[i+1] >= 0) {
                square(STAGE_BORDERS[0] + (SQUARE_SIZE * positions[i]), STAGE_BORDERS[1] + (SQUARE_SIZE * positions[i + 1]), SQUARE_SIZE);
            }
        }
    }

    private void moveCurrentBlock(Block b, int loopCounter) {
        if (loopCounter % fallSpeed == 0) {
            b.fall();
        }
    }

    public static void spawnNewPiece() {
        if (currentBlock != null) {
            int[] coords = currentBlock.getCoordinates();
            for (int i = 0; i < coords.length; i += 2) {
                gameBoard[coords[i+1]][coords[i]] = currentBlock.getBlockType();
            }
        }

        int blockType = rng.nextInt(7) + 1;
        currentBlock = Block.createNewBlock(blockType);
    }

    private void drawBackground() {
        background(150);
        fill(255);
        strokeWeight(2);
        rect(STAGE_BORDERS[0], STAGE_BORDERS[1], STAGE_BORDERS[2] - STAGE_BORDERS[0], STAGE_BORDERS[3] - STAGE_BORDERS[1]);

        for (int r = 0; r < gameBoard.length; r++) {
            for (int c = 0; c < gameBoard[0].length; c++) {
                fill(COLOR_VALUES[gameBoard[r][c]][0], COLOR_VALUES[gameBoard[r][c]][1], COLOR_VALUES[gameBoard[r][c]][2]);

                switch (gameBoard[r][c]) {
                    case 0 -> strokeWeight(0);
                    default -> strokeWeight(1);
                }

                square(STAGE_BORDERS[0] + (SQUARE_SIZE * c), STAGE_BORDERS[1] + (SQUARE_SIZE * r), SQUARE_SIZE);
            }
        }
    }
}


