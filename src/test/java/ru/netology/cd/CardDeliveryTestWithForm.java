package ru.netology.cd;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static java.time.LocalDate.parse;

public class CardDeliveryTestWithForm {

    public int getMonthNumber(String month) {
        String monthInEn = "";
        String[] monthArrayInRus = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
        String[] monthArrayInEn = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        for (int i = 0; i < 11; i++) {
            if (monthArrayInRus[i].equals(month)) monthInEn = monthArrayInEn[i];
        }
        return Month.valueOf(monthInEn.toUpperCase()).getValue();
    }

    static SimpleDateFormat[] formats = new SimpleDateFormat[] {
            new SimpleDateFormat("dd.MM.yyyy"),
    };

    static Date parse(String date, DateTimeFormatter dateTimeFormatter) {
        for (SimpleDateFormat format : formats) {
            try {
                return format.parse(date);
            } catch (ParseException ignored) {}
        }
        return null;
    }

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
        String monthAndYear = $(By.className("calendar__name")).getText();
        String [] words = monthAndYear.split("\\s");
        int yearToInt = Integer.parseInt(words[1]);
        int monthToInt = getMonthNumber(words[0]);
        int dayToInt = Integer.parseInt(day);
        String dayToDelivery = String.valueOf((dayToInt) + 14);
        String dateToDelivery = dayToDelivery + "." + "0" + monthToInt + "." + yearToInt;
        String date = Objects.requireNonNull(parse(dateToDelivery, DateTimeFormatter.ofPattern("dd,MM.yyyy"))).toString();

        System.out.println("---------------");
        System.out.println(date);
        System.out.println("---------------");

        $("[data-test-id='date'] input").setValue(date);



        String dayToInfo = LocalDate.now().plusDays(14).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

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


