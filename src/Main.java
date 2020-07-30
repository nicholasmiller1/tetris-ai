import blocks.*;
import processing.core.PApplet;

import java.util.ArrayList;

public class Main extends PApplet {

    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 700;

//    private static final int STAGE_WIDTH = 400;
//    private static final int STAGE_HEIGHT = 600;

    private ArrayList<Block> blocks;

    public static void main(String[] args) {
        PApplet.main("Main");
    }

    public void settings() {
        size(SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    public void setup() {
        blocks = new ArrayList<>();
        blocks.add(new OBlock(100, 100, 0));
        blocks.add(new IBlock(200, 100, 0));
        blocks.add(new LBlock(300, 100, 0));
        blocks.add(new JBlock(400, 100, 0));
        blocks.add(new TBlock(500, 100, 0));
        blocks.add(new SBlock(600, 100, 0));
        blocks.add(new ZBlock(700, 100, 0));
    }

    public void draw() {
        background(255);
//        rect(SCREEN_WIDTH/2 - STAGE_WIDTH/2, SCREEN_HEIGHT/2 - STAGE_HEIGHT/2, STAGE_WIDTH, STAGE_HEIGHT);

        displayAllBlocks(blocks);
    }

    private void displayBlock(Block b) {
        float[] positions = b.getPositions();
        int[] fillColor = b.getColor();

        fill(fillColor[0], fillColor[1], fillColor[2]);
        strokeWeight(0);
        beginShape();
        for (int i = 0; i < positions.length; i += 2) {
            vertex(positions[i], positions[i+1]);
        }
        endShape(CLOSE);
    }

    private void displayAllBlocks(ArrayList<Block> blocks) {
        for (Block b : blocks) {
            displayBlock(b);
            circle(b.getX(), b.getY(), 3);
        }
    }
}


