
public class Main {
    public static void main(String[] args) {
        CommandParser.setRawInput("mohab -s /home/etc");
        CommandParser.splitRawInput();
        CommandParser.validateCommand();
    }
}