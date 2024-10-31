import org.example.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.io.File;

public class DirectoryActionTest {

    @Test
    void makeFileTest() {
        CommandParser.setRawInput("mkdir /home/mohab/secondary/newDirectory");
        CommandParser.splitRawInput();
        DirectoryAction.mkdir(CommandParser.getArguments());

        File dir = new File("/home/mohab/secondary/newDirectory");
        assertTrue(dir.exists() && dir.isDirectory(), "Directory should be created.");

        dir.delete();
    }

    @Test
    void makeFileTestEmptyInput() {
        ArrayList<String> emptyInput = new ArrayList<>();
        DirectoryAction.mkdir(emptyInput);
    }

    @Test
    void makeFileAlreadyExistsTest() {
        File dir = new File("/home/mohab/secondary/02_Projects");
        dir.mkdirs();

        CommandParser.setRawInput("mkdir /home/mohab/secondary/02_Projects");
        CommandParser.splitRawInput();
        DirectoryAction.mkdir(CommandParser.getArguments());

        assertTrue(dir.exists() && dir.isDirectory(), "Directory should still exist.");

        dir.delete();
    }


    @Test
    void removeFileTest() {
        File dir = new File("/home/mohab/secondary/tempDirectory");
        dir.mkdirs();
        assertTrue(dir.exists() && dir.isDirectory(), "Directory should be created.");

        CommandParser.setRawInput("rmdir /home/mohab/secondary/tempDirectory");
        CommandParser.splitRawInput();
        DirectoryAction.rmdir(CommandParser.getArguments());

        assertFalse(dir.exists(), "Directory should be deleted.");
    }

    @Test
    void removeFileTestEmptyInput() {
        ArrayList<String> emptyInput = new ArrayList<>();
        DirectoryAction.rmdir(emptyInput);

    }

    @Test
    void removeFileNotExistTest() {
        File dir = new File("/home/mohab/secondary/nonExistentDirectory");

        assertFalse(dir.exists(), "Directory should not exist.");

        CommandParser.setRawInput("rmdir /home/mohab/secondary/nonExistentDirectory");
        CommandParser.splitRawInput();
        DirectoryAction.rmdir(CommandParser.getArguments());

    }
}
