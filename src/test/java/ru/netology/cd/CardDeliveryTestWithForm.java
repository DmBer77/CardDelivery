package ru.netology.cd;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.text.ParseException;
import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class CardDeliveryTestWithForm {


    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void shouldOrderTheCardDeliveryEverythingOk() throws ParseException {
        Configuration.holdBrowserOpen = true;

        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $(By.className("input__icon")).click();
        String day = $(By.className("calendar__day_state_today")).getText();
        int dayToInt = Integer.parseInt(day);
        String dayToDelivery = String.valueOf((dayToInt) + 7);
        $x("//*[contains(text(), '" + dayToDelivery + "')]").click();
        String dayToInfo = $("[data-test-id='date'] input.input__control").getValue();

        $("[data-test-id='city'] input").setValue("ка");
        $x("//*[contains(text(),'Калининград')]").click();

        $("[data-test-id='name'] input").setValue("Иванов Петр");
        $("[data-test-id='phone'] input").setValue("+79012345678");
        $("[data-test-id='agreement']").click();
        $x("//*[contains(text(),'Забронировать')]").click();
        $("[data-test-id='notification'] .notification__content")
                .shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText("Встреча успешно забронирована на " + dayToInfo));
    }
}


