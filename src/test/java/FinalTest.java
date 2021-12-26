import org.testng.annotations.*;
import utils.BookStore;
import utils.LoginPage;

public class FinalTest {
    @Test
    public void test1() {
        new LoginPage("michael", "Scranton&5");
    }

    @Test
    public void test2() {
        new BookStore("O'Reilly Media");
    }
}