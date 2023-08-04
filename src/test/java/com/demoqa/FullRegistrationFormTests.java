package com.demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FullRegistrationFormTests {
    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "2560x1440";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $("#firstName").setValue("Kate");
        $("#lastName").setValue("Nebyshinets");
        $("#userEmail").setValue("kn@gmail.com");
        $("label[for='gender-radio-2']").click();
        $("#userNumber").setValue("9662020201");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOptionByValue("1996");
        $(".react-datepicker__month-select").selectOptionContainingText("March");
        $(".react-datepicker__day--019").click();
        $("#subjectsInput").setValue("Commerce").pressEnter();
        $("#subjectsInput").setValue("Physics").pressEnter();
        $("label[for='hobbies-checkbox-1']").click();
        $("label[for='hobbies-checkbox-3']").click();
        //$("#uploadPicture").uploadFile(new File("src/test/resources/image/sample.jpg")); ок вариант, но ниже - проще
        $("#uploadPicture").uploadFromClasspath("image/sample.jpg"); //подразумевает, что файл уде лежит в директории resources
        $("#currentAddress").setValue("Minsk");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("Haryana")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Panipat")).click();
        $("#submit").click();

        $(".modal-dialog").shouldBe(visible); // = should(appear)
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

        $(".table-responsive").shouldHave(
                text("Kate"),
                text("Nebyshinets"),
                text("kn@gmail.com"),
                text("Female"),
                text("9662020201"),
                text("19 March,1996"),
                text("Commerce"),
                text("Physics"),
                text("Sports"),
                text("Music"),
                text("sample.jpg"),
                text("Minsk"),
                text("Haryana"),
                text("Panipat")
        );
    }
}

