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
        for (int i = 1; i < coordinates.length; i += 2) {
            if (coordinates[i] >= 19) {
                Main.spawnNewPiece();
                return;
            }
        }

        for (int i = 1; i < coordinates.length; i += 2) {
            coordinates[i]++;
        }
    }

    public void moveRight() {
        for (int i = 0; i < coordinates.length; i += 2) {
            if (coordinates[i] >= 9) {
                return;
            }
        }

        for (int i = 0; i < coordinates.length; i += 2) {
            coordinates[i]++;
        }
    }

    public void moveLeft() {
        for (int i = 0; i < coordinates.length; i += 2) {
            if (coordinates[i] <= 0) {
                return;
            }
        }

        for (int i = 0; i < coordinates.length; i += 2) {
            coordinates[i]--;
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

        checkBoundaryCollisions();
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

        checkBoundaryCollisions();
    }

    private void checkBoundaryCollisions() {
        for (int i = 0; i < coordinates.length; i += 2) {
            if (coordinates[i] >= 10) {
                moveLeft();
            } else if (coordinates[i] < 0) {
                moveRight();
            } else if (coordinates[i+1] > 19) {
                moveUp();
            }
        }
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
