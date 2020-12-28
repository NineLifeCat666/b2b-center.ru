import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class B2bCenterTests extends TestBase{

    @Test
    @DisplayName("поиск компании по слову - качество")
    void searchTest(){
    open("https://www.b2b-center.ru");
    $("[class='pull-left']").shouldHave(text("Корпоративные закупки и продажи онлайн"));
    $("#f_keyword_above").val("качество").pressEnter();
    $("[class='table table-hover table-filled search-results']").shouldHave(text("ООО \"ТД ПОЛИМЕТАЛЛ\""));
    }
}
