import java.util.Scanner;

public class CommandLineInterpreter {
    public static void start(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.print(Meta.getCurrentDir());
            String userInput = scanner.nextLine();
            Controller.executeCommand(userInput);
//            if(CommandParser.isThereAPipe){   [Moved to Controller]
//                String cmd=CommandParser.getNextRawCommandAfterPipe();
//                CommandCompostion.pipe(cmd);
//            }
        }
    }
}