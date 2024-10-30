import org.junit.jupiter.api.Test;
import org.example.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class CommandParserTest {

    @Test
    public void testBasicCommand() {
        CommandParser.setRawInput("ls         -l      /home/mohab/Documents");
        CommandParser.splitRawInput();

        assertEquals("ls", CommandParser.getCommand());
        assertEquals(new ArrayList<Character>() {{ add('l'); }}, CommandParser.getOptions());
        assertEquals(new ArrayList<String>() {{ add("/home/mohab/Documents"); }}, CommandParser.getArguments());
        assertFalse(CommandParser.isThereAPipe);
        assertFalse(CommandParser.isThereARedirectOutput);
        assertFalse(CommandParser.isThereAnAppendOutput);
        assertEquals("Screen", CommandParser.getRedirectionTarget());
        assertNull(CommandParser.getNextRawCommandAfterPipe());
    }

    @Test
    public void testCommandWithPipe() {
        CommandParser.setRawInput("pwd | ls -a");
        CommandParser.splitRawInput();

        assertEquals("pwd", CommandParser.getCommand());
        assertEquals(new ArrayList<Character>(), CommandParser.getOptions());
        assertEquals(new ArrayList<String>(), CommandParser.getArguments());
        assertTrue(CommandParser.isThereAPipe);
        assertEquals("ls -a", CommandParser.getNextRawCommandAfterPipe());
        assertEquals("nextPipe", CommandParser.getRedirectionTarget());
    }

    @Test
    public void testCommandWithRedirect() {
        CommandParser.setRawInput("ls /home/mohab > listFile.txt");
        CommandParser.splitRawInput();

        assertEquals("ls", CommandParser.getCommand());
        assertEquals(new ArrayList<Character>(), CommandParser.getOptions());
        assertEquals(new ArrayList<String>() {{ add("/home/mohab"); }}, CommandParser.getArguments());
        assertFalse(CommandParser.isThereAPipe);
        assertTrue(CommandParser.isThereARedirectOutput);
        assertEquals("listFile.txt", CommandParser.getRedirectionTarget());
    }

    @Test
    public void testCommandWithAppendRedirect() {
        CommandParser.setRawInput("ls -ar /home/mohab >> listFile.txt");
        CommandParser.splitRawInput();
        assertEquals("ls", CommandParser.getCommand());
        assertEquals(new ArrayList<Character>() {{ add('a'); add('r'); }}, CommandParser.getOptions());
        assertEquals(new ArrayList<String>() {{ add("/home/mohab"); }}, CommandParser.getArguments());
        assertEquals("listFile.txt", CommandParser.getRedirectionTarget());
        assertFalse(CommandParser.isThereAPipe);
        assertFalse(CommandParser.isThereARedirectOutput);
        assertTrue(CommandParser.isThereAnAppendOutput);
    }

    @Test
    public void testCommandWithQuotedArguments() {
        CommandParser.setRawInput("echo \"Hello World\"");
        CommandParser.splitRawInput();
        assertEquals("echo", CommandParser.getCommand());
        assertEquals(new ArrayList<Character>(), CommandParser.getOptions());
        assertEquals(new ArrayList<String>() {{ add("Hello World"); }}, CommandParser.getArguments());
    }

    @Test
    public void testCommandWithPipeAndAppendRedirect(){
        CommandParser.setRawInput("pwd | ls >> file.txt");
        CommandParser.splitRawInput();
        assertEquals("pwd" , CommandParser.getCommand());
        assertEquals("ls >> file.txt" , CommandParser.getNextRawCommandAfterPipe());
        assertTrue(CommandParser.isThereAPipe);
        assertFalse(CommandParser.isThereARedirectOutput);
        assertFalse(CommandParser.isThereAnAppendOutput);
    }

}