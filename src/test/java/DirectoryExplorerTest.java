import org.junit.jupiter.api.Test;
import org.example.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class DirectoryExplorerTest {

    @Test
    public void TestCdIfExists() {
        ArrayList<String> arguments = new ArrayList<>();
        arguments.add("C:\\cartoon movies");
        assertEquals("C:\\cartoon movies", Meta.getCurrentDir());
    }

    @Test
    public void TestCdIfNotExist() {
        ArrayList<String> arguments = new ArrayList<>();
        arguments.add("C:\\cartovies");
        DirectoryExplorer.cd(arguments);
        assertEquals("The directory \"C:\\cartovies\" does not exist", DirectoryExplorer.error);
    }

    @Test
    void testCdIfNoArguments() {
        ArrayList<String> arguments = new ArrayList<>();
        assertEquals(DirectoryExplorer.oldPath, Meta.getCurrentDir());
    }

    @Test
    public void TestPwd() {
        Meta.setCurrentDir("C:\\cartoon movies");
        assertEquals("C:\\cartoon movies", Meta.getCurrentDir());
    }

    @Test
    public void TestLS() {
        String directoryname = "C:\\TestLs";
        String testCommand = "ls C:\\TestLs";
        Controller.executeCommand(testCommand);
        File directory = new File(directoryname);
        if (!directory.exists()) {
            assertTrue(directory.mkdir());
        }
        File file1 = new File(directoryname,"test1.txt");
        File file2 = new File(directoryname,"test2.txt");
        File file3 = new File(directoryname,"test3.txt");
        DirectoryExplorer.ls(directoryname);
        String actualOutput = Meta.getLastOutput();
        String expectedOutput = "test1.txt\ntest2.txt\ntest3.txt";
        assertEquals(expectedOutput, actualOutput);
    }
}
