import java.util.Scanner;

public class CommandPrompt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to  Linux Sys");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("We hope you have a nice experience with Linux sys");
                break; 
            }

            String[] commandParts = input.split(" ");
       
            if (commandParts.length < 2) {
                System.err.println("Error: At least two separate statements are needed (command and arguments).");
                continue; 
            }

            String command = commandParts[0];
            String[] arguments = new String[commandParts.length - 1];
            System.arraycopy(commandParts, 1, arguments, 0, commandParts.length - 1);

            switch (command) {
                case "mkdir":
                    DirectoryAction.mkdir(arguments);
                    break;
                case "rmdir":
                    DirectoryAction.rmdir(arguments);
                    break;
                case "touch":
                    FileAction.touch(arguments);
                    break;
                case "rm":
                    FileAction.rm(arguments);
                    break;
                case "cat":
                    FileAction.cat(arguments);
                    break;
                case "mv":
                    if (arguments.length < 2) {
                        System.err.println("Error: At least three separate statements are needed (command and arguments(sourc , dest)).");
                    } else {
                        String destination = arguments[arguments.length - 1];
                        String[] sourceFiles = new String[arguments.length - 1];
                        System.arraycopy(arguments, 0, sourceFiles, 0, arguments.length - 1);
                        FileAction.mv(sourceFiles, destination);
                    }
                    break;
                default:
                    System.err.println("Invalid command: " + command);
                    break;
            }
        }
        scanner.close(); 
    }
}
