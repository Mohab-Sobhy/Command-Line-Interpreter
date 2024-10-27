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

    private static void reset(){
        isThereAPipe = false;
        isThereARedirectOutput = false;
        isThereAnAppendOutput = false;
        nextRawCommandAfterPipe = null;
        redirectionTarget = "Screen";
    }

    public static void setRawInput(String input){
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

        reset();

        switch (parts[indexOfLastArgument+1]) {
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

    public static void executeCommand(){
        switch (command){
            case "pwd" :
                DirectoryExplorer.pwd();
                break;

            case "ls" :
                if(!options.isEmpty()){
                    if(!arguments.isEmpty()){
                        //DirectoryExplorer.ls(arguments,options);
                    }
                    else{
                        //DirectoryExplorer.ls(options);
                    }
                }
                else{
                    if(!arguments.isEmpty()){
                        //DirectoryExplorer.ls(arguments);
                    }
                    else{
                        DirectoryExplorer.ls();
                    }
                }
                break;

            case "mkdir" :
                try{
                    if(arguments.isEmpty()){
                        throw new IllegalArgumentException("missing Argument");
                    }
                    else{
                        //DirectoryAction.mkdir(arguments);
                    }
                }
                catch (IllegalArgumentException e){
                    System.err.print(e.getMessage());
                }
                break;

            case "rmdir" :
                try{
                    if(arguments.isEmpty()){
                        throw new IllegalArgumentException("missing Argument");
                    }
                    else{
                        //DirectoryAction.rmdir(arguments);
                    }
                }
                catch (IllegalArgumentException e){
                    System.err.print(e.getMessage());
                }
                break;

            case "touch" :
                try{
                    if(arguments.isEmpty()){
                        throw new IllegalArgumentException("missing Argument");
                    }
                    else{
                        //FileAction.touch(arguments);
                    }
                }
                catch (IllegalArgumentException e){
                    System.err.print(e.getMessage());
                }
                break;

            case "mv" :
                try{
                    if(arguments.size() < 2){
                        throw new IllegalArgumentException("missing Argument");
                    }
                    else{
                        //FileAction.mv(arguments);
                    }
                }
                catch (IllegalArgumentException e){
                    System.err.print(e.getMessage());
                }
                break;

            case "rm" :
                try{
                    if(arguments.isEmpty()){
                        throw new IllegalArgumentException("missing Argument");
                    }
                    else{
                        //FileAction.rm(arguments);
                    }
                }
                catch (IllegalArgumentException e){
                    System.err.print(e.getMessage());
                }
                break;

            case "cat" :
                try{
                    if(arguments.isEmpty()){
                        throw new IllegalArgumentException("missing Argument");
                    }
                    else{
                        //FileAction.cat(arguments);
                    }
                }
                catch (IllegalArgumentException e){
                    System.err.print(e.getMessage());
                }
                break;

            case "exit" :
                InternalCommands.exit();
                break;

            case "help":
                InternalCommands.help();
                break;

            default:
                System.err.print(command + ": command not found");

        }
    }

    public static String getNextRawCommandAfterPipe(){
        return nextRawCommandAfterPipe;
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