import org.testng.annotations.*;
import utils.BookStore;
import utils.LoginPage;
import utils.RegisterUserAPI;

public class FinalTest {
    @Test
    public void test1() {
        String username = "michael", password = "Scranton&5";
        new RegisterUserAPI(username, password);
        new LoginPage(username, password);
    }

    @Test
    public void test2() {
        new BookStore("O'Reilly Media");
    }
}