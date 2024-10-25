import CommandPart.Argument;
import CommandPart.Command;
import CommandPart.Option;

public class CommandParser {
    private static boolean isTherePipe = false;
    private static boolean isThereRedirectOutput = false;
    private static boolean isThereAppendOutput = false;
    private static String rawInput;
    private static Command command = new Command();
    private static Option option = new Option();
    private static Argument argument = new Argument();
    private static String RedirectionTarget = "Screen";
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
            if (parts[1].startsWith("-") && parts[1].length() > 1) {
                option.setValue(parts[1].charAt(1));
                option.setIsAvailable(true);
            } else {
                argument.setValue(parts[1]);
                argument.setIsAvailable(true);
            }
        }

        if (parts.length > 2) {
            argument.setValue(parts[2]);
        }

        for (int i = 1; i < parts.length; i++) {
            switch (parts[i]) {
                case ">":
                    isThereRedirectOutput = true;
                    if (i + 1 < parts.length) {
                        RedirectionTarget = parts[i + 1];
                    }
                    return;
                case ">>":
                    isThereAppendOutput = true;
                    if (i + 1 < parts.length) {
                        RedirectionTarget = parts[i + 1];
                    }
                    return;
                case "|":
                    isTherePipe = true;
                    for (int j = i + 1; j < parts.length; j++) {
                        nextRawCommandAfterPipe += parts[j] + " ";
                    }
                    nextRawCommandAfterPipe = nextRawCommandAfterPipe.trim();
                    return;
            }
        }
    }


    public static void validateCommand() {
        String[] definedCommands = {"pwd", "ls", "mkdir", "rmdir", "touch", "mv", "rm", "cat"};
        boolean isValid = false;

        for (int i = 0; i < definedCommands.length; i++) {
            if (definedCommands[i].equals(command.getValue())) {
                isValid = true;
                break;
            }
        }

        try{
            if(!isValid){
                throw new RuntimeException(command.getValue()+": command not found");
            }
        }
        catch (RuntimeException e){
            System.err.println(e.getMessage());
        }
    }


    public static void executeCommand(){
        switch (command.getValue()){
            case "pwd" :
                //DirectoryExplorer.pwd();
                break;

            case "ls" :
                if(option.getIsAvailable()==true ){
                    if(argument.getIsAvailable()){
                        //DirectoryExplorer.ls(argument.getValue(),option.getValue());
                    }
                    else{
                        //DirectoryExplorer.ls(option.getValue());
                    }
                }
                else{
                    if(argument.getIsAvailable()){
                        //DirectoryExplorer.ls(argument.getValue());
                    }
                    else{
                        //DirectoryExplorer.ls();
                    }
                }
                break;

            case "mkdir" :
                try{
                    if(argument.getIsAvailable() == false){
                        throw new RuntimeException("missing Argument");
                    }
                    else{
                        //DirectoryAction.mkdir(argument.getValue());
                    }
                }
                catch (RuntimeException e){
                    e.printStackTrace();
                }
                break;



        }
    }

    public static void print(){
        System.out.println(command.getValue());
        System.out.println(option.getValue());
        System.out.println(argument.getValue());
        System.out.println(RedirectionTarget);
    }
}