package ru.netology.cd;

import org.openqa.selenium.By;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Auxiliary {

    public int getMonthNumber(String month) {
        String monthInEn = "";
        String[] monthArrayInRus = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
        String[] monthArrayInEn = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String[] monthArrayInEnCut = {"Jan", "Feb", "Mar", "Apr", "May", "June", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        for (int i = 0; i < 11; i++) {
            if (monthArrayInRus[i].equals(month)) monthInEn = monthArrayInEn[i];
            if (monthArrayInEnCut[i].equals(month)) monthInEn = monthArrayInEn[i];

        }
        return Month.valueOf(monthInEn.toUpperCase()).getValue();
    }

    public String figureAdjustment(int number) {
        String answer = "";
        if (number < 10) {
            answer = "0" + number;
        } else {
            answer = String.valueOf(number);
        }
        return answer;
    }

    static Date parse(String date) {
        for (SimpleDateFormat format : formats) {
            try {
                return format.parse(date);
            } catch (ParseException ignored) {
            }
        }
        return null;
    }

    static SimpleDateFormat[] formats = new SimpleDateFormat[]{
            new SimpleDateFormat("dd.MM.yyyy"),
    };

    String date(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
