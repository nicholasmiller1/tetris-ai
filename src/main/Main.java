package main;

import blocks.*;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Random;

public class Main extends PApplet {

    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 700;
    private static final int SQUARE_SIZE = 20;

    public static final int[] STAGE_BORDERS = {300, 150, 500, 550};

    private ArrayList<Block> blocks;
    private int loopCounter;
    private int pressInterval;
    private int fallSpeed;
    private Random rng;

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

        blocks = new ArrayList<>();
        rng = new Random();
        spawnNewPiece();
    }

    public void draw() {
        drawBackground();
        loopCounter += 1;

        displayAllBlocks(blocks);
        moveCurrentBlock(blocks.get(blocks.size() - 1), loopCounter);
    }

    public void keyPressed() {
        Block currentBlock = blocks.get(blocks.size() - 1);
        if (loopCounter - pressInterval > 10 && keyCode == RIGHT) {
            currentBlock.moveRight();
            pressInterval = loopCounter;
        } else if (loopCounter - pressInterval > 10 && keyCode == LEFT) {
            currentBlock.moveLeft();
            pressInterval = loopCounter;
        } else if (keyCode == DOWN) {
            fallSpeed = 5;
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
        int[] fillColor = b.getColor();

        fill(fillColor[0], fillColor[1], fillColor[2]);
        strokeWeight(1);

        for (int i = 0; i < positions.length; i += 2) {
            if (positions[i] < 10 && positions[i] >= 0 && positions[i+1] < 20 && positions[i+1] >= 0) {
                square(STAGE_BORDERS[0] + (SQUARE_SIZE * positions[i]), STAGE_BORDERS[1] + (SQUARE_SIZE * positions[i + 1]), SQUARE_SIZE);
            }
        }
    }

    private void displayAllBlocks(ArrayList<Block> blocks) {
        for (Block b : blocks) {
            displayBlock(b);
        }
    }

    private void moveCurrentBlock(Block b, int loopCounter) {
        if (loopCounter % fallSpeed == 0) {
            b.fall();
        }
    }

    private void spawnNewPiece() {
        int blockType = rng.nextInt(7);

        switch (blockType) {
            case 0: blocks.add(new IBlock()); break;
            case 1: blocks.add(new OBlock()); break;
            case 2: blocks.add(new JBlock()); break;
            case 3: blocks.add(new LBlock()); break;
            case 4: blocks.add(new SBlock()); break;
            case 5: blocks.add(new ZBlock()); break;
            case 6: blocks.add(new TBlock()); break;
        }
    }

    private void drawBackground() {
        background(150);
        fill(255);
        strokeWeight(2);
        rect(STAGE_BORDERS[0], STAGE_BORDERS[1], STAGE_BORDERS[2] - STAGE_BORDERS[0], STAGE_BORDERS[3] - STAGE_BORDERS[1]);

        strokeWeight(0);
        for (int i = STAGE_BORDERS[0]; i < STAGE_BORDERS[2]; i += SQUARE_SIZE) {
            line(i, STAGE_BORDERS[1], i, STAGE_BORDERS[3]);
        }

        for (int i = STAGE_BORDERS[1]; i < STAGE_BORDERS[3]; i += SQUARE_SIZE) {
            line(STAGE_BORDERS[0], i, STAGE_BORDERS[2], i);
        }
    }
}


