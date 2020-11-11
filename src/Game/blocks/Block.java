package Game.blocks;

import Game.GameBoard;

import java.util.Arrays;

public class Block {

    private int[] coordinates;
    private final int blockType;
    private final int boundingBoxSize;
    private final int[] boundingBoxCorner;
    private int rotationState;
    private final int[][][] walkKickTransformations;

    public Block(Block block) {
        this(null, block.getBlockType());

        int[] oldCoordinates = block.getCoordinates();
        int[] newCoordinates = new int[oldCoordinates.length];
        for (int i = 0; i < oldCoordinates.length; i++) {
            newCoordinates[i] = oldCoordinates[i];
        }

        this.coordinates = newCoordinates;
    }

    public Block(int[] coordinates, int blockType) {
        this.coordinates = coordinates;
        this.blockType = blockType;
        this.rotationState = 0;

        if (blockType == 1) {
            this.boundingBoxSize = 3;
            this.boundingBoxCorner = new int[] {3, -1};
            this.walkKickTransformations = new int[][][] {
                    {{0, 0}, {-2, 0}, {1, 0}, {-2, 1}, {1, -2}}, // 0 -> 1
                    {{0, 0}, {-1, 0}, {2, 0}, {-1, -2}, {2,1}}, // 1 -> 2
                    {{0, 0}, {2, 0}, {-1, 0}, {2, -1}, {-1,2}}, // 2 -> 3
                    {{0, 0}, {1, 0}, {-2, 0}, {1, 2}, {-2,-1}}, // 3 -> 0
                    {{0, 0}, {-2, 0}, {1, 0}, {-2, 1}, {1,-2}}, // 3 -> 2
                    {{0, 0}, {1, 0}, {-2, 0}, {1, 2}, {-2,-1}}, // 2 -> 1
                    {{0, 0}, {2, 0}, {-1, 0}, {2, -1}, {-1,2}}, // 1 -> 0
                    {{0, 0}, {-1, 0}, {2, 0}, {-1, -2}, {2,1}}}; // 0 -> 3
        } else if (blockType == 4) {
            this.boundingBoxSize = 1;
            this.boundingBoxCorner = new int[] {4, -1};
            this.walkKickTransformations = null;
        } else {
            this.boundingBoxSize = 2;
            this.boundingBoxCorner = new int[] {3, -1};
            this.walkKickTransformations = new int[][][] {
                    {{0, 0}, {-1, 0}, {-1, -1}, {0, 2}, {-1, 2}}, // 0 -> 1
                    {{0, 0}, {1, 0}, {1, 1}, {0, -2}, {1,-2}}, // 1 -> 2
                    {{0, 0}, {1, 0}, {1, -1}, {0, 2}, {1,2}}, // 2 -> 3
                    {{0, 0}, {-1, 0}, {-1, 1}, {0, -2}, {-1,-2}}, // 3 -> 0
                    {{0, 0}, {-1, 0}, {-1, 1}, {0, -2}, {-1,-2}}, // 3 -> 2
                    {{0, 0}, {-1, 0}, {-1, -1}, {0, 2}, {-1,2}}, // 2 -> 1
                    {{0, 0}, {1, 0}, {1, 1}, {0, -2}, {1,-2}}, // 1 -> 0
                    {{0, 0}, {1, 0}, {1, -1}, {0, 2}, {1,2}}}; // 0 -> 3
        }
    }

    public void autoFall() {
        while(checkCollisions(0, 1)) {
            for (int i = 1; i < coordinates.length; i += 2) {
                coordinates[i]++;
            }
        }
    }

    public boolean move(int xIncrement, int yIncrement) {
        if (checkCollisions(xIncrement, yIncrement)) {
            for (int i = 0; i < coordinates.length; i+=2) {
                coordinates[i] += xIncrement;
                coordinates[i+1] += yIncrement;
            }
            boundingBoxCorner[0] += xIncrement;
            boundingBoxCorner[1] += yIncrement;

            return true;
        }

        return false;
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    public int getBlockType() {
        return blockType;
    }

    public int getRotationState() {
        return rotationState;
    }

    public void rotateCounterClockwise() {
        int[] resetCoordinates = coordinates;

        for (int i = 0; i < coordinates.length; i += 2) {
            int x = coordinates[i] - boundingBoxCorner[0];
            coordinates[i] = coordinates[i+1] - boundingBoxCorner[1];
            coordinates[i+1] = boundingBoxSize - x;

            coordinates[i] += boundingBoxCorner[0];
            coordinates[i+1] += boundingBoxCorner[1];
        }

        if (walkKickTransformations != null) {
            for (int i = 0; i < walkKickTransformations[0].length; i++) {
                if(move(walkKickTransformations[7-rotationState][i][0], walkKickTransformations[7-rotationState][i][1])) {
                    if (rotationState > 0) {
                        rotationState--;
                    } else {
                        rotationState = 3;
                    }
                    return;
                }
            }


            this.coordinates = resetCoordinates;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return Arrays.equals(coordinates, block.coordinates);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(coordinates);
    }

    public void rotateClockwise() {
        int[] resetCoordinates = coordinates;

        for (int i = 0; i < coordinates.length; i += 2) {
            int y = coordinates[i+1] - boundingBoxCorner[1];
            coordinates[i+1] = coordinates[i] - boundingBoxCorner[0];
            coordinates[i] = boundingBoxSize - y;

            coordinates[i] += boundingBoxCorner[0];
            coordinates[i+1] += boundingBoxCorner[1];
        }

        if (walkKickTransformations != null) {
            for (int i = 0; i < walkKickTransformations[0].length; i++) {
                if(move(walkKickTransformations[rotationState][i][0], walkKickTransformations[rotationState][i][1])) {
                    if (rotationState < 3) {
                        rotationState++;
                    } else {
                        rotationState = 0;
                    }
                    return;
                }
            }


            this.coordinates = resetCoordinates;
        }
    }

    public boolean checkCollisions(int xIncrement, int yIncrement) {
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

    @Override
    public String toString() {
        return "Block{" +
                "coordinates=" + Arrays.toString(coordinates) +
                ", blockType=" + blockType +
                ", rotationState=" + rotationState +
                '}';
    }
}

