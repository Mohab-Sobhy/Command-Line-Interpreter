
public class Main {
    public static void main(String[] args) {
        CommandParser.setRawInput("mkdir -aker /home/etc /mohab /ramy >> file.txt");
        CommandParser.splitRawInput();
        CommandParser.print();
        //CommandParser.executeCommand();

    }
}