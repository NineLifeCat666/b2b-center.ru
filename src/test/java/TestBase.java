import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

public class TestBase {

    @Test
    void setUp(){
        Configuration.startMaximized = true;

    }
}
