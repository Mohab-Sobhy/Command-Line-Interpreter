import java.util.ArrayList;

public class CommandParser {
    public static boolean isThereAPipe = false;
    public static boolean isThereARedirectOutput = false;
    public static boolean isThereAnAppendOutput = false;
    public static String redirectionTarget = "Screen";
    private static String rawInput;
    private static String command;
    private static final ArrayList<Character> options = new ArrayList<>();
    private static final ArrayList<String> arguments = new ArrayList<>();
    private static String nextRawCommandAfterPipe;


    public static void setRawInput(String input){
        rawInput = input; // [ls -l /home/user/Documents]
    }

    public static void splitRawInput() {
        String[] parts = rawInput.trim().split("\\s+");

        if (parts.length == 0) {
            return;
        }

        command = parts[0];

        if (parts.length > 1) {
            int indexOfArguments = 1;

            if (parts[1].startsWith("-") && parts[1].length() > 1) {
                indexOfArguments = 2;
                for (int i = 1; i < parts[1].length(); i++) {
                    options.add(parts[1].charAt(i));
                }
            }

            for (int i = indexOfArguments; i < parts.length; i++) {
                if (parts[i].equals(">>") || parts[i].equals(">") || parts[i].equals("|")) {
                    break;
                } else {
                    arguments.add(parts[i]);
                }
            }

        }


        for (int i = 1; i < parts.length; i++) {
            switch (parts[i]) {
                case ">":
                    isThereARedirectOutput = true;
                    if (i + 1 < parts.length) {
                        redirectionTarget = parts[i + 1];
                    }
                    return;
                case ">>":
                    isThereAnAppendOutput = true;
                    if (i + 1 < parts.length) {
                        redirectionTarget = parts[i + 1];
                    }
                    return;
                case "|":
                    isThereAPipe = true;
                    StringBuilder nextCommandBuilder = new StringBuilder(parts[i + 1]).append(" ");
                    for (int j = i + 2; j < parts.length; j++) {
                        nextCommandBuilder.append(parts[j]).append(" ");
                    }
                    nextRawCommandAfterPipe = nextCommandBuilder.toString().trim();
                    return;
            }
        }
    }


    public static void executeCommand(){
        switch (command){
            case "pwd" :
                //DirectoryExplorer.pwd();
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
                        //DirectoryExplorer.ls();
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
                //InternalCommands.exit()
                break;

            case "help":
                //InternalCommands.help()
                break;

            default:
                System.err.print(command + ": command not found");

        }
    }

    public static void reset() {
        isThereAPipe = false;
        isThereARedirectOutput = false;
        isThereAnAppendOutput = false;
        rawInput = null;
        command = null;
        options.clear();
        arguments.clear();
    }


    public static void print(){
        System.out.println(command);
        for (Character option : options) {
            System.out.println(option + " ");
        }
        for (String argument : arguments) {
            System.out.println(argument + " ");
        }
        System.out.println(nextRawCommandAfterPipe);
        System.out.println(redirectionTarget);
    }
}