import org.example.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileActionTest {

    @Test
    void touchFileTest() {
        ArrayList<String> input = new ArrayList<>();
        input.add("/home/mohab/secondary/newFile.txt");

        FileAction.touch(input);

        File file = new File("/home/mohab/secondary/newFile.txt");
        assertTrue(file.exists() && file.isFile(), "File should be created.");

        file.delete();
    }

    @Test
    void touchFileTestEmptyInput() {
        ArrayList<String> emptyInput = new ArrayList<>();
        FileAction.touch(emptyInput);
    }

    @Test
    void removeFileTest() {
        File file = new File("/home/mohab/secondary/tempFile.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            fail("Failed to create file for test.");
        }
        assertTrue(file.exists(), "File should be created for the test.");

        ArrayList<String> input = new ArrayList<>();
        input.add("/home/mohab/secondary/tempFile.txt");
        FileAction.rm(input);

        assertFalse(file.exists(), "File should be deleted.");
    }

    @Test
    void removeFileNotExistTest() {
        ArrayList<String> input = new ArrayList<>();
        input.add("/home/mohab/secondary/nonExistentFile.txt");

        FileAction.rm(input);

    }

    @Test
    void catFileTest() {
        File file = new File("/home/mohab/secondary/readFile.txt");
        try {
            Files.write(Paths.get(file.getPath()), "Hello, world!".getBytes());
        } catch (IOException e) {
            fail("Failed to create file for test.");
        }

        ArrayList<String> input = new ArrayList<>();
        input.add("/home/mohab/secondary/readFile.txt");
        FileAction.cat(input);

        file.delete();
    }

    @Test
    void moveFileTest() {
        File sourceFile = new File("/home/mohab/secondary/sourceFile.txt");
        File destDir = new File("/home/mohab/secondary/destinationDir");
        destDir.mkdirs();
        try {
            sourceFile.createNewFile();
        } catch (IOException e) {
            fail("Failed to create file for test.");
        }

        FileAction.mv("/home/mohab/secondary/sourceFile.txt", "/home/mohab/secondary/destinationDir");

        File movedFile = new File(destDir, "sourceFile.txt");
        assertTrue(movedFile.exists(), "File should be moved to destination directory.");

        movedFile.delete();
        destDir.delete();
    }
}
