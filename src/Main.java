import processing.core.PApplet;

public class Main extends PApplet {

    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 800;

    private static final int STAGE_WIDTH = 400;
    private static final int STAGE_HEIGHT = 600;

    @Override
    public void settings() {
        size(SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    @Override
    public void setup() {
        background(255);
    }

    @Override
    public void draw() {
        rect(SCREEN_WIDTH/2 - STAGE_WIDTH/2, SCREEN_HEIGHT/2 - STAGE_HEIGHT/2, STAGE_WIDTH, STAGE_HEIGHT);


    }

    public static void main(String[] args) {
        String[] processingArgs = {"Main"};
        Main main = new Main();
        PApplet.runSketch(processingArgs, main);
    }
}
