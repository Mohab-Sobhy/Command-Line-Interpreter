import java.util.ArrayList;

public class CommandParser {
    public static boolean isThereAPipe;
    public static boolean isThereARedirectOutput;
    public static boolean isThereAnAppendOutput;
    private static String redirectionTarget;
    private static String rawInput;
    private static String command;
    private static final ArrayList<Character> options = new ArrayList<>();
    private static final ArrayList<String> arguments = new ArrayList<>();
    private static String nextRawCommandAfterPipe;

    private static void reset(){
        isThereAPipe = false;
        isThereARedirectOutput = false;
        isThereAnAppendOutput = false;
        nextRawCommandAfterPipe = null;
        redirectionTarget = "Screen";
        options.clear();
        arguments.clear();
    }

    public static void setRawInput(String input){
        reset();
        rawInput = input; // [ls -l /home/user/Documents]
    }

    public static void splitRawInput() {
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
                    if(indexOfLastArgument == -1){
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

        switch (parts[indexOfLastArgument+1]) {
            case "|":
                isThereAPipe = true;
                if (indexOfLastArgument + 2 < parts.length) {
                    StringBuilder nextCommandBuilder = new StringBuilder(parts[indexOfLastArgument + 2]);
                    for (int j = indexOfLastArgument + 3; j < parts.length; j++) {
                        nextCommandBuilder.append(" ").append(parts[j]);
                    }
                    nextRawCommandAfterPipe = nextCommandBuilder.toString();
                    redirectionTarget = "nextPipe";
                }
                return;

            case ">" :
                isThereARedirectOutput = true;
                if (indexOfLastArgument + 2 < parts.length) {
                    redirectionTarget = parts[indexOfLastArgument + 2];
                }
                break;

            case ">>" :
                isThereAnAppendOutput = true;
                if (indexOfLastArgument + 2 < parts.length) {
                    redirectionTarget = parts[indexOfLastArgument + 2];
                }
                break;

            default :

                break;
        }

    }

    public static String getCommand() {
        return command;
    }

    public static ArrayList<Character> getOptions() {
        return options;
    }

    public static ArrayList<String> getArguments() {
        return arguments;
    }

    public static String getNextRawCommandAfterPipe(){
        return nextRawCommandAfterPipe;
    }

    public static String getRedirectionTarget(){
        return redirectionTarget;
    }

    public static void print(){
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