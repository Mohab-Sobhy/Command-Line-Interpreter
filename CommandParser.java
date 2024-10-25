import CommandPart.Argument;
import CommandPart.Command;
import CommandPart.Option;

import java.util.ArrayList;

public class CommandParser {
    private static boolean isTherePipe = false;
    private static boolean isThereRedirectOutput = false;
    private static boolean isThereAppendOutput = false;
    private static String rawInput;
    private static Command command = new Command();
    private static ArrayList<Character> options = new ArrayList<>();
    private static ArrayList<String> arguments = new ArrayList<>();
    private static String redirectionTarget = "Screen";
    private static String nextRawCommandAfterPipe;

    public static void setRawInput(String input){
        rawInput = input; // [ls -l /home/user/Documents]
    }

    public static void splitRawInput() {
        String[] parts = rawInput.trim().split("\\s+");

        if (parts.length == 0) {
            command.setIsAvailable(true);
            return;
        }

        command.setValue(parts[0]);

        if (parts.length > 1) {
            int indexOfArguments = 1;

            if (parts[1].startsWith("-") && parts[1].length() > 1) {
                indexOfArguments = 2;
                for (int i = 1; i < parts[1].length(); i++) {
                    options.add(Character.valueOf(parts[1].charAt(i)));
                }
            }

            for (int i = indexOfArguments; i < parts.length; i++) {
                if (parts[i].equals(">>") || parts[i].equals(">") || parts[i].equals("|")) {
                    break;
                } else {
                    arguments.add(new String());
                    arguments.set(arguments.size() - 1 , parts[i]);
                }
            }

        }


        for (int i = 1; i < parts.length; i++) {
            switch (parts[i]) {
                case ">":
                    isThereRedirectOutput = true;
                    if (i + 1 < parts.length) {
                        redirectionTarget = parts[i + 1];
                    }
                    return;
                case ">>":
                    isThereAppendOutput = true;
                    if (i + 1 < parts.length) {
                        redirectionTarget = parts[i + 1];
                    }
                    return;
                case "|":
                    isTherePipe = true;
                    nextRawCommandAfterPipe = parts[i+1]+ " ";
                    for (int j = i + 2; j < parts.length; j++) {
                        nextRawCommandAfterPipe += parts[j] + " ";
                    }
                    nextRawCommandAfterPipe = nextRawCommandAfterPipe.trim();
                    return;
            }
        }
    }


    public static void executeCommand(){
        switch (command.getValue()){
            case "pwd" :
                //DirectoryExplorer.pwd();
                break;

            case "ls" :
                if(!options.isEmpty()){
                    if(!arguments.isEmpty()){
                        //DirectoryExplorer.ls(argument.getValue(),option.getValue());
                    }
                    else{
                        //DirectoryExplorer.ls(option.getValue());
                    }
                }
                else{
                    if(!arguments.isEmpty()){
                        //DirectoryExplorer.ls(argument1.getValue());
                    }
                    else{
                        //DirectoryExplorer.ls();
                    }
                }
                break;

            case "mkdir" :
                try{
                    if(arguments.isEmpty()){
                        throw new RuntimeException("missing Argument");
                    }
                    else{
                        //DirectoryAction.mkdir(argument1.getValue());
                    }
                }
                catch (RuntimeException e){
                    e.printStackTrace();
                }
                break;

            case "rmdir" :
                try{
                    if(arguments.isEmpty()){
                        throw new RuntimeException("missing Argument");
                    }
                    else{
                        //DirectoryAction.rmdir(argument1.getValue());
                    }
                }
                catch (RuntimeException e){
                    e.printStackTrace();
                }
                break;

            case "touch" :
                try{
                    if(arguments.isEmpty()){
                        throw new RuntimeException("missing Argument");
                    }
                    else{
                        //FileAction.touch(argument1.getValue());
                    }
                }
                catch (RuntimeException e){
                    e.printStackTrace();
                }
                break;

            case "mv" :
                try{
                    if(arguments.size() < 2){
                        throw new RuntimeException("missing Argument");
                    }
                    else{

                    }
                }
                catch (RuntimeException e){
                    e.printStackTrace();
                }
                break;

            case "rm" :
                try{
                    if(arguments.isEmpty()){
                        throw new RuntimeException("missing Argument");
                    }
                    else{
                        //FileAction.rm(argument1.getValue());
                    }
                }
                catch (RuntimeException e){
                    e.printStackTrace();
                }
                break;

            case "cat" :
                try{
                    if(arguments.isEmpty()){
                        throw new RuntimeException("missing Argument");
                    }
                    else{
                        //FileAction.cat(argument.getValue());
                    }
                }
                catch (RuntimeException e){
                    e.printStackTrace();
                }
                break;

            default:
                System.err.print(command.getValue()+": command not found");

        }
    }

    public static void print(){
        System.out.println(command.getValue());
        for(int i=0 ; i<options.size() ; i++){
            System.out.println(options.get(i)+" ");
        }
        for(int i=0 ; i<arguments.size() ; i++){
            System.out.println(arguments.get(i)+" ");
        }
        System.out.println(nextRawCommandAfterPipe);
        System.out.println(redirectionTarget);
    }
}