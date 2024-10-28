import java.io.File;
import java.util.ArrayList;

public class CommandParser {
    public static boolean isThereAPipe;
    public static boolean isThereARedirectOutput;
    public static boolean isThereAnAppendOutput;
    public static String redirectionTarget;
    private static String rawInput;
    private static String command;
    private static final ArrayList<Character> options = new ArrayList<>();
    private static final ArrayList<String> arguments = new ArrayList<>();
    private static String nextRawCommandAfterPipe;

    private static void reset() {
        isThereAPipe = false;
        isThereARedirectOutput = false;
        isThereAnAppendOutput = false;
        nextRawCommandAfterPipe = null;
        redirectionTarget = "Screen";
    }

    public static void setRawInput(String input) {
        rawInput = input;
    }

    public static void splitRawInput() {
        // Clear previous command data
        options.clear();
        arguments.clear();
        reset();

        String[] parts = rawInput.trim().split("\\s+");

        if (parts.length == 0) {
            return;
        }

        int indexOfFirstArgument = 1;
        int indexOfLastArgument = -1;

        command = parts[0];

        if (parts.length > 1) {
            if (parts[1].startsWith("-") && parts[1].length() > 1) {
                indexOfFirstArgument = 2;
                for (int i = 1; i < parts[1].length(); i++) {
                    options.add(parts[1].charAt(i));
                }
            }

            for (int i = indexOfFirstArgument; i < parts.length; i++) {
                if (parts[i].equals(">>") || parts[i].equals(">") || parts[i].equals("|")) {
                    if (indexOfLastArgument == -1) {
                        indexOfFirstArgument--;
                        indexOfLastArgument = indexOfFirstArgument;
                    }
                    break;
                } else {
                    arguments.add(parts[i]);
                    indexOfLastArgument = i;
                }
            }
        }

        // Check if there are more parts after the last argument
        if (indexOfLastArgument + 1 < parts.length) {
            switch (parts[indexOfLastArgument + 1]) {
                case "|":
                    isThereAPipe = true;
                    if (indexOfLastArgument + 2 < parts.length) {
                        StringBuilder nextCommandBuilder = new StringBuilder(parts[indexOfLastArgument + 2]);
                        for (int j = indexOfLastArgument + 3; j < parts.length; j++) {
                            nextCommandBuilder.append(" ").append(parts[j]);
                        }
                        nextRawCommandAfterPipe = nextCommandBuilder.toString();
                    }
                    return;

                case ">":
                    isThereARedirectOutput = true;
                    if (indexOfLastArgument + 2 < parts.length) {
                        redirectionTarget = parts[indexOfLastArgument + 2];
                    }
                    break;

                case ">>":
                    isThereAnAppendOutput = true;
                    if (indexOfLastArgument + 2 < parts.length) {
                        redirectionTarget = parts[indexOfLastArgument + 2];
                    }
                    break;
            }
        }
    }

    public static void executeCommand() {
        try {
            switch (command) {
                case "pwd":
                    DirectoryExplorer.pwd();
                    break;

                case "cd":
                    if (!arguments.isEmpty()) {
                        String NewPath = String.join(" ", arguments);
                        String CurrentPath = Meta.getCurrentDir();
                        File directory = new File(NewPath);

                        if (!directory.exists()) {
                            System.out.println("The directory " + NewPath + " does not exist: ");
                        } else {
                            if (NewPath.equals(CurrentPath)) {
                                System.out.println("You are already in the " + CurrentPath + " directory");
                            } else {
                                Meta.setCurrentDir(NewPath);
                                System.out.println("the new current path is : " + NewPath);
                            }
                        }
                    } else {
                        System.out.println("No Given Path");
                    }
                    break;

                case "ls":
                    if (!options.isEmpty()) {
                        boolean hasA = options.contains('a');
                        boolean hasR = options.contains('r');

                        if (hasA && hasR) {
                            DirectoryExplorer.lsAR();
                        } else if (hasA) {
                            DirectoryExplorer.lsA();
                        } else if (hasR) {
                            DirectoryExplorer.lsR();
                        } else {
                            System.err.println("Unsupported ls option");
                        }
                    } else {
                        if (!arguments.isEmpty()) {
                            String originalDir = Meta.getCurrentDir();
                            Meta.setCurrentDir(String.join(" ",arguments));
                            DirectoryExplorer.ls();
                            Meta.setCurrentDir(originalDir);
                        } else {
                            DirectoryExplorer.ls();
                        }
                    }
                    break;

                case "mkdir":
                    try {
                        if (arguments.isEmpty()) {
                            throw new IllegalArgumentException("missing Argument");
                        } else {
                            //DirectoryAction.mkdir(arguments);
                        }
                    } catch (IllegalArgumentException e) {
                        System.err.print(e.getMessage());
                    }
                    break;

                case "rmdir":
                    try {
                        if (arguments.isEmpty()) {
                            throw new IllegalArgumentException("missing Argument");
                        } else {
                            //DirectoryAction.rmdir(arguments);
                        }
                    } catch (IllegalArgumentException e) {
                        System.err.print(e.getMessage());
                    }
                    break;

                case "touch":
                    try {
                        if (arguments.isEmpty()) {
                            throw new IllegalArgumentException("missing Argument");
                        } else {
                            //FileAction.touch(arguments);
                        }
                    } catch (IllegalArgumentException e) {
                        System.err.print(e.getMessage());
                    }
                    break;

                case "mv":
                    try {
                        if (arguments.size() < 2) {
                            throw new IllegalArgumentException("missing Argument");
                        } else {
                            //FileAction.mv(arguments);
                        }
                    } catch (IllegalArgumentException e) {
                        System.err.print(e.getMessage());
                    }
                    break;

                case "rm":
                    try {
                        if (arguments.isEmpty()) {
                            throw new IllegalArgumentException("missing Argument");
                        } else {
                            //FileAction.rm(arguments);
                        }
                    } catch (IllegalArgumentException e) {
                        System.err.print(e.getMessage());
                    }
                    break;

                case "cat":
                    try {
                        if (arguments.isEmpty()) {
                            throw new IllegalArgumentException("missing Argument");
                        } else {
                            //FileAction.cat(arguments);
                        }
                    } catch (IllegalArgumentException e) {
                        System.err.print(e.getMessage());
                    }
                    break;

                case "exit":
                    InternalCommands.exit();
                    break;

                case "help":
                    InternalCommands.help();
                    break;

                default:
                    System.err.print(command + ": command not found");
            }
        } finally {
            // Clear options and arguments after command execution
            options.clear();
            arguments.clear();
        }
    }

    public static String getNextRawCommandAfterPipe() {
        return nextRawCommandAfterPipe;
    }

    public static void print() {
        System.out.println("Command: " + command);
        for (Character option : options) {
            System.out.println("Option: " + option);
        }
        for (String argument : arguments) {
            System.out.println("Argument: " + argument);
        }
        System.out.println("next raw command after pipe: " + nextRawCommandAfterPipe);
        System.out.println("redirection target: " + redirectionTarget);
    }
}
