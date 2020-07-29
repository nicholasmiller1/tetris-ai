public class Block {

    float x;
    float y;

    public Block(float initX, float initY) {
        this.x = initX;
        this.y = initY;
    }

    public void move() {
        y += 1;
    }

    public void display() {
//        ellipse(x, y, 20, 20);
    }
}
