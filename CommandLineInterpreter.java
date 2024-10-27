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
            if(CommandParser.isThereAPipe){
                String cmd=CommandParser.getNextRawCommandAfterPipe();
                CommandCompostion.pipe(cmd);
            }

        }
    }
}