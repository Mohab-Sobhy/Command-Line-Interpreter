import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
public class CommandCompostionTest{
    @Test
    public void testPipe(){
        String CommandTest = "ls";
        //assertEquals(Controller.executeCommand(CommandTest),CommandCompostion.pipe(CommandTest));
    }
    @Test
    public void testRedirectOutput() throws IOException {
        String directoryPath = "testDir";
        String fileName = "testFile.txt";
        String fullPath = directoryPath + File.separator + fileName;
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
        String expectedContent = "Test content for redirectOutput function.";
        Meta.setLastOutput(expectedContent);
        File testFile = new File(fullPath);
        assertTrue(testFile.exists(), "File should be created");
        String actualContent = Files.readString(Path.of(fullPath));
        assertEquals(expectedContent, actualContent, "Content should match Meta.getLastOutput()");
        testFile.delete();
        directory.delete();
    }
}