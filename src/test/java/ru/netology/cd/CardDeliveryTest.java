package ru.netology.cd;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.cssSelector;
import static org.slf4j.MDC.clear;

class CardDeliveryTest {

    @Test
    void shouldOrderTheCardDelivery() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        String date = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input").setValue("Казань");
//        $x("[data-test-id='date']").clear();
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "A");;
        $("[data-test-id='date'] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Иванов Петр");
        $("[data-test-id='phone'] input").setValue("+79101234567");
        $("[data-test-id='agreement']").click();
        $x("//*[contains(text(),'Забронировать')]").click();
        $("[data-test-id='notification'] .notification__content")
                .shouldBe(visible, Duration.ofSeconds(15)).should(exactText("Встреча успешно забронирована на " + date));
    }

//        $x("//*[contains(text(),'номеру счёта')]").click();
//        $$(cssSelector("[data-test-id='name'] input")).get(0).setValue("Иванов Петр");
//        $$("[name='phone']").exclude(hidden).get(0etValue("+7 999 999 99 99");
//        $$(By.className("button__content")).last().click();
//        $x("//*[contains(text(),'Успешная авторизация')]").shouldBe(visible, Duration.ofSeconds(10));
//        $(By.tagName("h2")).shouldHave(exactText("Личный кабинет"), Duration.ofMillis(8000));
//    }

}