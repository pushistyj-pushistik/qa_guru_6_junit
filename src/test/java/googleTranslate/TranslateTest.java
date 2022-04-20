package googleTranslate;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class TranslateTest {

    @CsvSource(value = {
            "Кот | Cat" ,
            "Бегемот | Hippo"
    },
    delimiter = '|')

    @ParameterizedTest (name = "Проверка перевода слова {0} с русского на английский")
    void translate(String testData, String results) {
        //Предусловие
        Selenide.open("https://translate.google.com/?sl=ru&tl=en&op=translate");

        //Шаги
        $(".er8xn").click();
        $(".er8xn").setValue(testData);

       // ОР
        $$(".Q4iAWc")
                .find(Condition.text(results))
                .shouldBe(visible);
    }

    @ValueSource(strings = {
            "испанский",
            "тайский"
    })

    @ParameterizedTest(name = "Проверка наличия {0} языка оригинала")
    void languageSearch(String language) {
        //Предусловие
        Selenide.open("https://translate.google.com");

        //Шаги
        $(".VfPpkd-Bz112c-RLmnJb").click();

        //ОР
        $$(".vSUSRc")
                .find(Condition.text(language))
                .shouldBe(visible);
    }

   public enum TypesOfTranslation {
       Text("Текст"), Doc("Документы"), Web("Сайты");

       public final String rusName;

       TypesOfTranslation(String rusName) {
           this.rusName = rusName;
       }
   }

    @EnumSource(TypesOfTranslation.class)

    @ParameterizedTest(name = "Проверка вида {0} перевода")
    void typesOfTranslationTest(TypesOfTranslation testData) {
        //Предусловие
        Selenide.open("https://translate.google.com");

        //Шаги
        $$(".U0xwnf")
                .find(Condition.text(String.valueOf(testData.rusName)))
                .click();

        //ОР
        Assertions.assertEquals(
                1,
                WebDriverRunner.getWebDriver().getWindowHandles().size()
        );
    }

    @AfterEach
    void close() {
        Selenide.closeWebDriver();
    }

}

