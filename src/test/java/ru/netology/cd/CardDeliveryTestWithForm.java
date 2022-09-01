package ru.netology.cd;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.text.ParseException;
import java.time.Duration;
import java.util.Objects;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.cd.Auxiliary.parse;

public class CardDeliveryTestWithForm {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void shouldOrderTheCardDeliveryThroughSevenDays() throws ParseException {
        Auxiliary data = new Auxiliary();
        int daysForPlanning = 7;
        int daysForCalculation = daysForPlanning - 3;
        $("[data-test-id='city'] input").setValue("ка");
        $x("//*[contains(text(),'Калининград')]").click();
        $(By.className("input__icon")).click();
        String day = $(By.className("calendar__day_state_current")).getText();
        String monthAndYear = $(By.className("calendar__name")).getText();

        String[] words = monthAndYear.split("\\s");
        String year = words[1];
        String month = data.figureAdjustment(data.getMonthNumber(words[0]));
        int dayToInt = Integer.parseInt(day);
        int dayToIntWithCor = dayToInt + daysForCalculation;
        String days = data.figureAdjustment(dayToIntWithCor);
        String dateToDelivery = days + "." + month + "." + year;
        String date = Objects.requireNonNull(parse(dateToDelivery)).toString();
        String[] words1 = date.split("\\s");
        String year1 = words1[5];
        String month1 = data.figureAdjustment(data.getMonthNumber(words1[1]));
        String day1 = words1[2];
        String newDate = day1 + "." + month1 + "." + year1;

        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(newDate);
        $("[data-test-id='name'] input").setValue("Иванов Петр");
        $("[data-test-id='phone'] input").setValue("+79012345678");
        $("[data-test-id='agreement']").click();
        $x("//*[contains(text(),'Забронировать')]").click();
        $("[data-test-id='notification'] .notification__content")
                .shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText("Встреча успешно забронирована на " + data.date(daysForPlanning)));
    }

    @Test
    void shouldOrderTheCardDeliveryThroughFortyFiveDays() throws ParseException {
        Configuration.holdBrowserOpen = true;
        Auxiliary data = new Auxiliary();
        int daysForPlanning = 45;
        int daysForCalculation = daysForPlanning - 3;
        $("[data-test-id='city'] input").setValue("ка");
        $x("//*[contains(text(),'Калининград')]").click();
        $(By.className("input__icon")).click();
        String day = $(By.className("calendar__day_state_current")).getText();
        String monthAndYear = $(By.className("calendar__name")).getText();

        String[] words = monthAndYear.split("\\s");
        String year = words[1];
        String month = data.figureAdjustment(data.getMonthNumber(words[0]));
        int dayToInt = Integer.parseInt(day);
        int dayToIntWithCor = dayToInt + daysForCalculation;
        String days = data.figureAdjustment(dayToIntWithCor);
        String dateToDelivery = days + "." + month + "." + year;
        String date = Objects.requireNonNull(parse(dateToDelivery)).toString();
        String[] words1 = date.split("\\s");
        String year1 = words1[5];
        String month1 = data.figureAdjustment(data.getMonthNumber(words1[1]));
        String day1 = words1[2];
        String newDate = day1 + "." + month1 + "." + year1;

        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(newDate);
        $("[data-test-id='name'] input").setValue("Иванов Петр");
        $("[data-test-id='phone'] input").setValue("+79012345678");
        $("[data-test-id='agreement']").click();
        $x("//*[contains(text(),'Забронировать')]").click();
        $("[data-test-id='notification'] .notification__content")
                .shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText("Встреча успешно забронирована на " + data.date(daysForPlanning)));
    }
}


