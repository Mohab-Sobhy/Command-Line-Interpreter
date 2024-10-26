import java.util.Scanner;

public class CommandLineInterpreter {
    public static void start(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.print("User@ComputerName: ");
            String userInput = scanner.nextLine();
            CommandParser.setRawInput(userInput);
            CommandParser.splitRawInput();

            CommandParser.executeCommand();
            CommandParser.reset();
        }
    }
}

//            if(CommandParser.isThereAPipe){
//                //CommandComposition.pipe(command,options,arguments,nextRawCommandAfterPipe);
//            }
//            if(CommandParser.isThereARedirectOutput){
//                //CommandComposition.redirectOutput(command,options,arguments,redirectionTarget);
//            }
//            if(CommandParser.isThereAnAppendOutput){
//                //CommandComposition.appendOutput(command,options,arguments,redirectionTarget);
//            }