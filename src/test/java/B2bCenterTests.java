import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class B2bCenterTests extends TestBase {

    Faker faker = new Faker(new Locale("ru"));
    FakeValuesService fakeValuesService = new FakeValuesService(
            new Locale("en-GB"), new RandomService());
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String email = fakeValuesService.bothify("???##@gmail.com");
    String telephoneNumber = fakeValuesService.regexify("[0-9]{11}");
    String comment = faker.lorem().paragraph();

    String mainPageB2bCenter = "https://www.b2b-center.ru";
    String selfEducationB2bCenterPage = "https://www.b2b-center.ru/training/?t=self_education#self_education";

    @Test
    @DisplayName("Поиск компании по слову")
    void searchTest() {
        step("Открываем главную страницу b2b-center", () -> {
                open(mainPageB2bCenter);
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
    @DisplayName("Заполнение критериев расширенного поиска")
    void fillExpandedSearchTest() {
        step("открываем главную страницу b2b-center", () -> {
            open(mainPageB2bCenter);
        });

        step("Проверям наличие фразы Торги по отрослям", () -> {
           $("[class='wrapper neutralBg']").shouldHave(text("Торги по отраслям"));
        });

        step("Вводим в поисковую строку слово и нажимаем Enter", () -> {
           $("#f_keyword_above").val("золото").pressEnter();
        });

        step("Ищем в результатах поиска компанию", () -> {
            $("[class='table table-hover table-filled search-results']").shouldHave(text("ОАО \"ЗОЛОТО СЕЛИГДАРА\""));
        });

        step("Выбираем дефолтное значение в блоке 'Например'", () -> {
            $("[class='apseudo search-example']").shouldHave(text("трубы")).click();
        });

        step("Вводим название региона ", () -> {
            $("[class='selectize-ico selectize-ico-add']").click();
            $("[class='selectize-option js-active']").shouldHave(text("Россия")).click();
            $("[class='selectize-ico selectize-ico-add']").click();
        });

        step("Устанавливаем ценовой диапазон от и до", () -> {
            $(byName("price_start")).val("100000");
            $(byName("price_end")).val("100000000");
        });

         step("Нажимаем на кнопку 'Применить фильтры'", () -> {
             $("#submit_filter_button").click();
             $("[class='table table-hover table-filled search-results']").shouldHave(text("ООО \"ТД ПОЛИМЕТАЛЛ\""));
         });
    }

    @Disabled("Тест игнорируется, что-бы не спамить на проде")
    @Test
    @DisplayName("Заполнение формы 'Задайте вопрос по обучению'")
    void fillEducationQuestionFormTest() {
        step("Открываем страницу с формой", () -> {
            open(selfEducationB2bCenterPage);
            $("[class='wrapper featured-layout']").shouldHave(text("Обучение закупкам, торгам по 223 ФЗ с нуля. Повышение квалификации в сфере закупок на B2B-Center"));
        });

        step("Заолняем форму 'Задайте вопрос по обучению'", () -> {
           $(byName("request_phone")).val(telephoneNumber);
           $(byName("request_name")).val(firstName + " " + lastName);
           $(byName("request_email")).val(email);
           $(byName("request_comment")).val(comment);
           $(byText("Я даю согласие на обработку моих персональных данных в соответствии с")).click();
           $(byValue("Отправить")).click();
           $(byValue("Отправить")).shouldNotBe(visible);
        });
    }
}

