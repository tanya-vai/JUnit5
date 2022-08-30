package ru.av;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class AvMainPageMethods {

    SelenideElement countryButton = $(".popup__container_content_actions");
    SelenideElement countryText = $(".header-main-city_text");
    SelenideElement searchInput = $("[placeholder='Поиск']");
    SelenideElement headerLine = $(".header-main-bottom");
    SelenideElement adulthoodConfirmButton = $(".popup__container_content_actions");
    SelenideElement textTitle = $(".catalog-header-title__text");
    ElementsCollection relevantWordsLine = $$("div.slider-tag.slider-tag--rounded.slider-tag--rounded-mobile");



    public AvMainPageMethods countryButtonClick(String value){
        countryButton.$(byText(value)).click();
        return this;
    }

    public AvMainPageMethods searchInputSetValue(String value){
        searchInput.setValue(value).pressEnter();
        return this;
    }


    public AvMainPageMethods alcoholTitleClick(){
        headerLine.$(byText("Алкоголь")).click();
        return this;
    }

    public AvMainPageMethods adulthoodButtonSelect(String value){
        adulthoodConfirmButton.$(byText(value)).click();
        return this;
    }

}
