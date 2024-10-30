import org.junit.jupiter.api.Test;
import org.example.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class CommandParserTest { //from AI

    @Test
    public void testBasicCommand() {
        CommandParser.setRawInput("ls -l /home/user/Documents");
        CommandParser.splitRawInput();

        assertEquals("ls", CommandParser.getCommand());
        assertEquals(new ArrayList<Character>() {{ add('l'); }}, CommandParser.getOptions());
        assertEquals(new ArrayList<String>() {{ add("/home/user/Documents"); }}, CommandParser.getArguments());
        assertFalse(CommandParser.isThereAPipe);
        assertFalse(CommandParser.isThereARedirectOutput);
        assertFalse(CommandParser.isThereAnAppendOutput);
        assertEquals("Screen", CommandParser.getRedirectionTarget());
        assertNull(CommandParser.getNextRawCommandAfterPipe());
    }
    @Test
    public void TestCdIfExists(){
        ArrayList<String> arguments = new ArrayList<>();
        arguments.add("C:\\cartoon movies");
        DirectoryExplorer.cd(arguments);
        assertEquals("C:\\cartoon movies", Meta.getCurrentDir());
    }
    @Test
    public void TestCdIfNotExist(){
        ArrayList<String> arguments = new ArrayList<>();
        arguments.add("C:\\cartovies");
        DirectoryExplorer.cd(arguments);
        assertEquals("The directory \"C:\\cartovies\" does not exist",DirectoryExplorer.error);
    }
    @Test
    public void TestPwd(){
        Meta.setCurrentDir("C:\\cartoon movies");
        assertEquals("C:\\cartoon movies", Meta.getCurrentDir());
    }





    @Test
    public void testCommandWithPipe() {
        CommandParser.setRawInput("cat file.txt | grep 'search term'");
        CommandParser.splitRawInput();

        assertEquals("cat", CommandParser.getCommand());
        assertEquals(new ArrayList<Character>(), CommandParser.getOptions());
        assertEquals(new ArrayList<String>() {{ add("file.txt"); }}, CommandParser.getArguments());
        assertTrue(CommandParser.isThereAPipe);
        assertEquals("grep 'search term'", CommandParser.getNextRawCommandAfterPipe());
        assertEquals("nextPipe", CommandParser.getRedirectionTarget());
    }

    @Test
    public void testCommandWithRedirect() {
        CommandParser.setRawInput("echo Hello > output.txt");
        CommandParser.splitRawInput();

        assertEquals("echo", CommandParser.getCommand());
        assertEquals(new ArrayList<Character>(), CommandParser.getOptions());
        assertEquals(new ArrayList<String>() {{ add("Hello"); }}, CommandParser.getArguments());
        assertFalse(CommandParser.isThereAPipe);
        assertTrue(CommandParser.isThereARedirectOutput);
        assertEquals("output.txt", CommandParser.getRedirectionTarget());
    }

    @Test
    public void testCommandWithAppendRedirect() {
        CommandParser.setRawInput("echo Hello >> output.txt");
        CommandParser.splitRawInput();

        assertEquals("echo", CommandParser.getCommand());
        assertEquals(new ArrayList<Character>(), CommandParser.getOptions());
        assertEquals(new ArrayList<String>() {{ add("Hello"); }}, CommandParser.getArguments());
        assertFalse(CommandParser.isThereAPipe);
        assertFalse(CommandParser.isThereARedirectOutput);
        assertTrue(CommandParser.isThereAnAppendOutput);
        assertEquals("output.txt", CommandParser.getRedirectionTarget());
    }

    @Test
    public void testCommandWithQuotedArguments() {
        CommandParser.setRawInput("echo \"Hello World\"");
        CommandParser.splitRawInput();

        assertEquals("echo", CommandParser.getCommand());
        assertEquals(new ArrayList<Character>(), CommandParser.getOptions());
        assertEquals(new ArrayList<String>() {{ add("Hello World"); }}, CommandParser.getArguments());
    }
}