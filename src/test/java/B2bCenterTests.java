import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class B2bCenterTests extends TestBase{

    @Test
    @DisplayName("Поиск компании по слову")
    void searchTest(){
        step("Открываем главную страницу b2b-center", () -> {
                open("https://www.b2b-center.ru");
        });
        step("Проверяем наличие фразы Корпоративные закупки и продажи онлайн", () -> {
                $("[class='pull-left']").shouldHave(text("Корпоративные закупки и продажи онлайн"));
        });
        step("Вводим в поисковую строку слово и нажимаем Enter", () -> {
            $("#f_keyword_above").val("качество").pressEnter();
        });
        step("Ищем в результатах поиска компанию", () -> {
            $("[class='table table-hover table-filled search-results']").shouldHave(text("ООО \"ТД ПОЛИМЕТАЛЛ\""));
        });
    }

    @Test
    @DisplayName("Заолнение критериев расширенного поиска")
    void fillExpandedSearchTest(){
        step("открываем главную страницу b2b-center", () -> {
            open("https://www.b2b-center.ru");
                });
        step("Проверям наличие фразы Торги по отрослям", () -> {
           $("[class='wrapper neutralBg']").shouldHave(text("Торги по отраслям"));
        });
        step("Вводим в поисковую строку слово и нажимаем Enter", () -> {
           $("#f_keyword_above").val("золото").pressEnter();
        });
        step("Ищем в результатах поиска компанию", () -> {
            $("[class='table table-hover table-filled search-results']").shouldHave(text("ОАО \"ЗОЛОТО СЕЛИГДАРА\""));
            System.out.println("shti");
        });
    }
}
//2. fillExpandedSearchTest
//- open main page
//- assert main page - торги по отрослям
//- setValue on navigation bar - золото
//- assert ОАО "ЗОЛОТО СЕЛИГДАРА"
//- on expanded search menu chose:
//- example - трубы
//- add name of region city Moscow
//- coast lot down - up 100_000  100_000_000
//- press submit filters
//- assert ООО "ТД "ТТК"

