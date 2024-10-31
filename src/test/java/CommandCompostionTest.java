import org.example.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class CommandCompostionTest{
    @Test
    public void testPipe(){
        String CommandTest = "ls";
        Controller.executeCommand(CommandTest);
        String expectedOutput = Meta.getLastOutput();
        CommandCompostion.pipe(CommandTest);
        String actualOuput = Meta.getLastOutput();
        assertEquals(expectedOutput,actualOuput);
    }
    @Test
    public void testRedirectOutput() throws IOException {
        String directoryPath = "C:\\testDir";
        String fileName = "testFile.txt";
        String fullPath = directoryPath + File.separator + fileName;
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            assertTrue(directory.mkdir());
        }
        String expectedContent = "Test content for redirectOutput function.";
        Meta.setLastOutput(expectedContent);
        File testFile = new File(fullPath);
        if (!testFile.exists()) {
            assertTrue(testFile.createNewFile(), "Failed to create test file");
        }
        System.out.println(testFile.getName());
        CommandCompostion.redirectOutput(fullPath);
        String actualContent = Files.readString(Path.of(fullPath));
        assertEquals(expectedContent, actualContent, "Content should match Meta.getLastOutput()");
        testFile.delete();
        directory.delete();
    }

@Test
public void testAppendOutput() throws IOException {
    String directoryPath = "C:\\testDir";
    String fileName = "testFile.txt";
    String fullPath = directoryPath + File.separator + fileName;

    // Ensure the directory exists
    File directory = new File(directoryPath);
    if (!directory.exists()) {
        assertTrue(directory.mkdir(), "directory cannot be created");
    }

    // Ensure the file exists and has initial content
    File testFile = new File(fullPath);
    if (!testFile.exists()) {
        assertTrue(testFile.createNewFile(), "test file cannot be created");
    }
    String initialContent = "first content in the file.\n";
    try (FileWriter writer = new FileWriter(testFile)) {
        writer.write(initialContent);
    }
    String appendContent = "content to be appended.";   // Content to append
    Meta.setLastOutput(appendContent);
    CommandCompostion.appendOutput(fullPath);
    String actualContent = Files.readString(Path.of(fullPath));
    String expectedContent = initialContent + appendContent;
    assertEquals(expectedContent, actualContent, "Content should match initial + appended content");
    testFile.delete();
    directory.delete();
}
}