import java.util.Scanner;

public class CommandLineInterpreter {
    public static void start(){
        while(true){
            System.out.print("Prompt: ");
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            CommandParser.setRawInput(userInput);
            CommandParser.splitRawInput();
            CommandParser.executeCommand();
        }
    }
}
