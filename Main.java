
public class Main {
    public static void main(String[] args) {
        CommandParser.setRawInput("mkdir /home");
        CommandParser.splitRawInput();
        CommandParser.validateCommand();
        CommandParser.executeCommand();

    }
}