package org.example;
import java.util.Scanner;

public class CommandLineInterpreter {
    public static void start(){
        Scanner scanner = new Scanner(System.in);
        String promptLine;
        while(true){
            promptLine = "\u001B[32mUser@ComputerName\u001B[0m:\u001B[34m" + Meta.getCurrentDir() + "\u001B[0m$ ";
            System.out.print(promptLine);
            String userInput = scanner.nextLine();
            Controller.executeCommand(userInput);
        }
    }
}