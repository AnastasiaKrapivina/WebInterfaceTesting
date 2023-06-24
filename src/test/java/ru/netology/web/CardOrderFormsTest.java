package ru.netology.web;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class CardOrderFormsTest {
    @BeforeEach
    void setUp() {
        Selenide.open("http://localhost:9999"); }
    @Test
    void shouldTest() {
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Анастасия");
        form.$("[data-test-id=phone] input").setValue("+79999990000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }
    @Test
    void shouldTestNegativeName() {
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Anastasia");
        form.$("[data-test-id=phone] input").setValue("+79999990000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTestNegativeEmptyName() {
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("");
        form.$("[data-test-id=phone] input").setValue("+79999990000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }
    @Test
    void shouldTestNegativePhone() {
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Анастасия");
        form.$("[data-test-id=phone] input").setValue("+7999999000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    @Test
    void shouldTestNegativeEmptyPhone() {
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Анастасия");
        form.$("[data-test-id=phone] input").setValue("");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }
    @Test
    void shouldTestNoClickAgreement() {
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Анастасия");
        form.$("[data-test-id=phone] input").setValue("+79999990000");
        form.$("[data-test-id=agreement]");
        form.$(".button").click();
        $("[data-test-id=agreement].input_invalid .checkbox__text").shouldHave(visible);

    }
}