package googleTranslate;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
    void flightSearch(String testData, String results) {
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
}
