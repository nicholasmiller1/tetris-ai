package Ai.pathgeneration;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Command {
    private final String stringInput;
    private final int keyInput;
    private final int[] increments;

    public Command(String stringInput) {
        this.stringInput = stringInput;

        switch (stringInput) {
            case "Left": this.keyInput = KeyEvent.VK_LEFT;
                this.increments = new int[]{-1, 0, 0};
                break;
            case "Right": this.keyInput = KeyEvent.VK_RIGHT;
                this.increments = new int[]{1, 0, 0};
                break;
            case "Down": this.keyInput = KeyEvent.VK_DOWN;
                this.increments = new int[]{0, 1, 0};
                break;
            case "Drop": this.keyInput = KeyEvent.VK_UP;
                this.increments = new int[]{}; //TODO: add values to this
                break;
            case "Hold": this.keyInput = KeyEvent.VK_SPACE;
                this.increments = new int[]{}; //TODO: add values to this
                break;
            case "Clockwise": this.keyInput = KeyEvent.VK_X;
                this.increments = new int[]{0, 0, 1};
                break;
            case "Counterclockwise": this.keyInput = KeyEvent.VK_Z;
                this.increments = new int[]{0, 0, -1};
                break;
            default: this.keyInput = 0;
                this.increments = new int[]{0, 0, 0};
        }
    }

    public Command(String stringInput, int keyInput, int[] increments) {
        this.stringInput = stringInput;
        this.keyInput = keyInput;
        this.increments = increments;
    }

    public static Set<Command> getPossibleCommands(Command lastCommand) {
        Set<Command> possibleCommands = new LinkedHashSet<>();

        if (lastCommand == null || !lastCommand.getStringInput().equals("Right")) {
            possibleCommands.add(new Command("Left"));
        }

        if (lastCommand == null || !lastCommand.getStringInput().equals("Left")) {
            possibleCommands.add(new Command("Right"));
        }

        possibleCommands.add(new Command("Down"));
        possibleCommands.add(new Command("Clockwise"));
        possibleCommands.add(new Command("Counterclockwise"));

        //TODO: Implement drop and hold capabilities
//        possibleCommands.add(new Command("Drop"));
//        possibleCommands.add(new Command("Hold"));

        return possibleCommands;
    }

    public String getStringInput() {
        return stringInput;
    }

    public int getKeyInput() {
        return keyInput;
    }

    public int[] getIncrements() {
        return increments;
    }

    @Override
    public String toString() {
        return stringInput.substring(0,1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Command command = (Command) o;
        return keyInput == command.keyInput &&
                Objects.equals(stringInput, command.stringInput) &&
                Arrays.equals(increments, command.increments);
    }
}
