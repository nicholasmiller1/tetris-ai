package blocks;

import main.GameBoard;

public class Block {

    private int[] coordinates;
    private final int blockType;
    private int boundingBoxSize;
    private int[] boundingBoxCorner;

    public Block(int[] coordinates, int blockType) {
        this.coordinates = coordinates;
        this.blockType = blockType;

        if (blockType == 1) {
            this.boundingBoxSize = 3;
            this.boundingBoxCorner = new int[] {3, -1};
        } else if (blockType == 4) {
            this.boundingBoxSize = 1;
            this.boundingBoxCorner = new int[] {4, -1};
        } else {
            this.boundingBoxSize = 2;
            this.boundingBoxCorner = new int[] {3, -1};
        }
    }

    public void fall() {
        if (checkCollisions("Down")) {
            for (int i = 1; i < coordinates.length; i += 2) {
                coordinates[i]++;
            }
            boundingBoxCorner[1]++;
        } else {
            GameBoard.spawnNewPiece();
        }
    }

    public void autoFall() {
        while(checkCollisions("Down")) {
            for (int i = 1; i < coordinates.length; i += 2) {
                coordinates[i]++;
            }
        }

        GameBoard.spawnNewPiece();
    }

    public void moveRight() {
        if (checkCollisions("Right")) {
            for (int i = 0; i < coordinates.length; i += 2) {
                coordinates[i]++;
            }
            boundingBoxCorner[0]++;
        }
    }

    public void moveLeft() {
        if (checkCollisions("Left")) {
            for (int i = 0; i < coordinates.length; i += 2) {
                coordinates[i]--;
            }
            boundingBoxCorner[0]--;
        }
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    public int getBlockType() {
        return blockType;
    }

    public void rotateCounterClockwise() {
        for (int i = 0; i < coordinates.length; i += 2) {
            int x = coordinates[i] - boundingBoxCorner[0];
            coordinates[i] = coordinates[i+1] - boundingBoxCorner[1];
            coordinates[i+1] = boundingBoxSize - x;

            coordinates[i] += boundingBoxCorner[0];
            coordinates[i+1] += boundingBoxCorner[1];
        }
    }

    public void rotateClockwise() {
        for (int i = 0; i < coordinates.length; i += 2) {
            int y = coordinates[i+1] - boundingBoxCorner[1];
            coordinates[i+1] = coordinates[i] - boundingBoxCorner[0];
            coordinates[i] = boundingBoxSize - y;

            coordinates[i] += boundingBoxCorner[0];
            coordinates[i+1] += boundingBoxCorner[1];
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
            } else if (testY >= 0 && GameBoard.gameBoard[coordinates[i+1] + yIncrement][coordinates[i] + xIncrement] != 0) {
                return false;
            }
        }

        return true;
    }


    // 1 -> IBlock | 2 -> JBlock | 3 -> LBlock | 4 -> OBlock | 5 -> SBlock | 6 -> TBlock | 7 -> ZBlock
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
