import org.junit.jupiter.api.Test;
import org.example.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DirectoryExplorerTest {

    @Test
    public void TestCdIfExists() {
        ArrayList<String> arguments = new ArrayList<>();
        arguments.add("C:\\cartoon movies");
        DirectoryExplorer.cd(arguments);
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
    public void testLsNoOptions() {
        ArrayList<Character> options = new ArrayList<>(); // No options
        ArrayList<String> arguments = new ArrayList<>();
        arguments.add("path/to/directory"); // Specify an existing path here
        DirectoryExplorer.ls(options, arguments);
        // Add assertions based on expected behavior of ls method without options
    }

//    @Test
//    public void testLsA() {
//        ArrayList<Character> options = new ArrayList<>();
//        options.add('a'); // Option "a" for listing hidden files
//        ArrayList<String> arguments = new ArrayList<>();
//        arguments.add("C:\\certificates\\Fortinet NSE"); // Specify an existing path here
//        DirectoryExplorer.ls(options, arguments);
//        // Expected output
//        String expectedOutput = "NSE 1.PDF\n.hiddenfile\nNSE_1_Certification\n"; // Adjust based on actual directory contents
//
//        // Assertions
//        String actualOutput = outputStream.toString();
//        assertEquals(expectedOutput.trim(), DirectoryExplorer.lsA(arguments,options);, "The output should list all files including hidden ones.");
//        // Add assertions based on expected output of ls with "a" option
//    }
//    @Test
//    public void testLsA() {
//        ArrayList<Character> options = new ArrayList<>();
//        options.add('a'); // Option "a" for listing hidden files
//        ArrayList<String> arguments = new ArrayList<>();
//        arguments.add("C:\\certificates\\Fortinet NSE"); // Specify an existing path here
//
//        // Capture the output
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        PrintStream originalOut = System.out;
//        System.setOut(new PrintStream(outputStream));
//
//        // Call the ls method with the "a" option
//        DirectoryExplorer.ls(options, arguments);
//
//        // Restore the original System.out
//        System.setOut(originalOut);
//
//        // Expected output, adjusted to match actual directory contents
//        String expectedOutput = "NSE 1\n.hiddenfile\nNSE_1_Certification\n";
//
//        // Retrieve and trim the actual output
//        String actualOutput = outputStream.toString().trim();
//
//        // Assertion to check if output matches expected output
//        assertEquals(expectedOutput.trim(), actualOutput, "The output should list all files including hidden ones.");
//    }
//    @Test
    public void testLsR() {
        ArrayList<Character> options = new ArrayList<>();
        options.add('r'); // Option "r" for reverse order
        ArrayList<String> arguments = new ArrayList<>();
        arguments.add("path/to/directory"); // Specify an existing path here

        DirectoryExplorer.ls(options, arguments);
        // Add assertions based on expected output of ls with "r" option
    }

    @Test
    public void testLsAR() {
        ArrayList<Character> options = new ArrayList<>();
        options.add('a'); // Option "a"
        options.add('r'); // Option "r"
        ArrayList<String> arguments = new ArrayList<>();
        arguments.add("path/to/directory"); // Specify an existing path here

        DirectoryExplorer.ls(options, arguments);
        // Add assertions based on expected output of ls with both "a" and "r" options
    }

    @Test
    public void testLsWrongOption() {
        ArrayList<Character> options = new ArrayList<>();
        options.add('z'); // Unsupported option
        ArrayList<String> arguments = new ArrayList<>();
        arguments.add("path/to/directory"); // Specify an existing path here

        DirectoryExplorer.ls(options, arguments);
    }

//    @Test
//    public void testLsNoArguments() {
//        ArrayList<Character> options = new ArrayList<>();
//        ArrayList<String> arguments = new ArrayList<>();
//        DirectoryExplorer.ls(options, arguments);
//    }

    @Test
    public void TestCdIfNoPath() {
        ArrayList<String> arguments = new ArrayList<>();
        arguments.add("");
        DirectoryExplorer.cd(arguments);
        assertEquals(DirectoryExplorer.OldPath, Meta.getCurrentDir());
    }

    @Test
    public void TestPwd() {
        Meta.setCurrentDir("C:\\cartoon movies");
        assertEquals("C:\\cartoon movies", Meta.getCurrentDir());
    }

}
