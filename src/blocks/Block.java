package blocks;

import main.Main;

public class Block {

    private int[] coordinates;
    private final int blockType;

    public Block(int[] coordinates, int blockType) {
        this.coordinates = coordinates;
        this.blockType = blockType;
    }

    public void fall() {
        if (checkCollisions("Down")) {
            for (int i = 1; i < coordinates.length; i += 2) {
                coordinates[i]++;
            }
        } else {
            Main.spawnNewPiece();
        }
    }

    public void autoFall() {
        while(checkCollisions("Down")) {
            for (int i = 1; i < coordinates.length; i += 2) {
                coordinates[i]++;
            }
        }

        Main.spawnNewPiece();
    }

    public void moveRight() {
        if (checkCollisions("Right")) {
            for (int i = 0; i < coordinates.length; i += 2) {
                coordinates[i]++;
            }
        }
    }

    public void moveLeft() {
        if (checkCollisions("Left")) {
            for (int i = 0; i < coordinates.length; i += 2) {
                coordinates[i]--;
            }
        }
    }

    public void moveUp() {
        for (int i = 1; i < coordinates.length; i += 2) {
            coordinates[i]--;
        }
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    public int getBlockType() {
        return blockType;
    }

    public void rotateCounterClockwise() {
        int n = 2;
        int xOffset = coordinates[2] - 1;
        int yOffset = coordinates[3] - 1;

        for (int i = 0; i < coordinates.length; i += 2) {
            int x = coordinates[i] - xOffset;
            coordinates[i] = coordinates[i+1] - yOffset;
            coordinates[i+1] = n - x;

            coordinates[i] += xOffset;
            coordinates[i+1] += yOffset;
        }

        if ( )
    }

    public void rotateClockwise() {
        int n = 2;
        int xOffset = coordinates[2] - 1;
        int yOffset = coordinates[3] - 1;

        for (int i = 0; i < coordinates.length; i += 2) {
            int y = coordinates[i+1] - yOffset;
            coordinates[i+1] = coordinates[i] - xOffset;
            coordinates[i] = n - y;

            coordinates[i] += xOffset;
            coordinates[i+1] += yOffset;
        }
    }

    private boolean checkCollisions(String direction) {
        int xIncrement = 0;
        int yIncrement = 0;
        switch (direction) {
            case "Down" -> yIncrement = 1;
            case "Left" -> xIncrement = -1;
            case "Right" -> xIncrement = 1;
        }

        for (int i = 0; i < coordinates.length; i += 2) {
            int testX = coordinates[i] + xIncrement;
            int testY = coordinates[i+1] + yIncrement;

            if (testX >= 10 || testX < 0 || testY >= 20) {
                return false;
            } else if (testY >= 0 && Main.gameBoard[coordinates[i+1] + yIncrement][coordinates[i] + xIncrement] != 0) {
                return false;
            }
        }

        return true;
    }

    public static Block createNewBlock(int blockType) {
        return switch (blockType) {
            case 1 -> new Block(new int[]{3, 0, 4, 0, 5, 0, 6, 0}, blockType);
            case 2 -> new Block(new int[]{3, -1, 4, 0, 3, 0, 5, 0}, blockType);
            case 3 -> new Block(new int[]{3, 0, 4, 0, 5, 0, 5, -1}, blockType);
            case 4 -> new Block(new int[]{4, -1, 4, 0, 5, -1, 5, 0}, blockType);
            case 5 -> new Block(new int[]{3, 0, 4, 0, 4, -1, 5, -1}, blockType);
            case 6 -> new Block(new int[]{3, 0, 4, 0, 4, -1, 5, 0}, blockType);
            case 7 -> new Block(new int[]{3, -1, 4, 0, 4, -1, 5, 0}, blockType);
            default -> null;
        };
    }
}
