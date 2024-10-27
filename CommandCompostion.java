
public class CommandCompostion {
    
    public static void pipe(String NextAfterPipe) {
        CommandParser.setRawInput(NextAfterPipe);
        CommandParser.splitRawInput();
        CommandParser.executeCommand();   
    }
    public static void redirectOutput(){
        
    }
    public static void appendOutput(){
        
    }
}
