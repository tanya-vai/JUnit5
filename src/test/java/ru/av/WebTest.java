package ru.av;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;


public class WebTest {

    AvMainPageMethods avMainPageMethods = new AvMainPageMethods();

    @BeforeEach
    void openAv() {
//       Configuration.headless = true;
        open("https://av.ru/");
        clearBrowserCookies();
        clearBrowserLocalStorage();
        refresh();
    }

    @ValueSource(strings = {"Москва", "Санкт-Петербург"})
    @ParameterizedTest(name = "Выбранная страна {0} отображается на главной странице")
    void countryTest(String country) {
        avMainPageMethods
                .countryButtonClick(country)
                .countryText.shouldHave(Condition.text(country));
    }

    @CsvSource(value ={
            "Да, Алкоголь",
            "Нет, Поиск: торт"
    })
    @ParameterizedTest(name = "Заголовщк {1} отображается при ответе на вопрос о совершеннолетии - {0}")
    void countryAndLangTest(String confirm, String title ) {
        avMainPageMethods
                .countryButtonClick("Москва")
                .searchInputSetValue("торт");
        sleep(2000);
        avMainPageMethods .alcoholTitleClick()
                .adulthoodButtonSelect(confirm)
                .textTitle.shouldHave(Condition.text(title));
    }

    public static Stream<Arguments> checkRelevantWordsTest(){
        return Stream.of(
                Arguments.of("торт", List.of("москва", "бенто" , "свечи", "три", "киевский", "наполеон", "ореховый", "прага", "мороженное")),
                Arguments.of("арбуз",  List.of("нарезка", "без косточек" , "наша", "брусочки", "гигант", "принц", "красный"))
        );
    }

    @MethodSource
    @ParameterizedTest(name = "Категории {1} отображается при поиске слова - {0}")
    void checkRelevantWordsTest(String searchWord, List<String> relevantWords) {
        avMainPageMethods
                .countryButtonClick("Москва")
                .searchInputSetValue(searchWord);
        sleep(2000);
        avMainPageMethods
                .relevantWordsLine
                .filter(Condition.visible)
                .shouldHave(CollectionCondition.texts(relevantWords));

    }

}
