import org.junit.jupiter.api.Test;
import org.example.*;
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
    public void TestPwd() {
        Meta.setCurrentDir("C:\\cartoon movies");
        assertEquals("C:\\cartoon movies", Meta.getCurrentDir());
    }
}
