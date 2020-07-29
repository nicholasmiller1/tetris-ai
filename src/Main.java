import processing.core.PApplet;

public class Main extends PApplet {

    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void draw() {
        ellipse(1,1,1,1);
    }

    public static void main(String[] args) {
        String[] processingArgs = {"Main"};
        Main main = new Main();
        PApplet.runSketch(processingArgs, main);
    }
}
