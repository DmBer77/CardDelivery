package ru.netology.cd;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

class CardDeliveryTest {

    @Test
    void shouldRegisterByAccountNumberDOMModification() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $x("//*[contains(text(),'номеру счёта')]").click();
        $$("[type='text']").filter(visible).first().setValue("4055 0100 0123 4613 8564");
        $$("[name='phone']").exclude(hidden).get(0).setValue("+7 999 999 99 99");
        $$(By.className("button__content")).last().click();
        $x("//*[contains(text(),'Успешная авторизация')]").shouldBe(visible, Duration.ofSeconds(10));
        $(By.tagName("h2")).shouldHave(exactText("Личный кабинет"), Duration.ofMillis(8000));
    }

}