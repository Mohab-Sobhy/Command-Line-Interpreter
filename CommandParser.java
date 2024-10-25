import CommandPart.Argument;
import CommandPart.Command;
import CommandPart.Option;

public class CommandParser {
    private static String rawInput;
    private static Command command = new Command();
    private static Option option = new Option();
    private static Argument argument = new Argument();

    public static void setRawInput(String input){
        rawInput = input; // [ls -l /home/user/Documents]
    }

    public static void splitRawInput(){
        int commandEndIndex = rawInput.indexOf(" ");
        int optionStartIndex = rawInput.indexOf("-");
        int argumentStartIndex = rawInput.indexOf(" ", commandEndIndex + 1);

        if(commandEndIndex == -1){
            command.setValue(rawInput.substring(0, rawInput.length()));
        }
        else{
            command.setValue(rawInput.substring(0, commandEndIndex));
        }

        if(optionStartIndex == -1){
            option.setValue('#');
            option.setIsAvailable(false);
        }
        else{
            option.setValue(rawInput.charAt(optionStartIndex + 1));
            option.setIsAvailable(true);
        }

        if(argumentStartIndex == -1){
            argument.setValue("#");
            argument.setIsAvailable(false);
        }
        else{
            argument.setValue(rawInput.substring(argumentStartIndex+1, rawInput.length()));
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
        System.out.println(command);
        System.out.println(option);
        System.out.println(argument);
    }
}
