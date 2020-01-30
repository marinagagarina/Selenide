package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import javax.xml.crypto.dsig.keyinfo.KeyName;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {
    SelenideElement form = $("form");
    SelenideElement city = form.$("[data-test-id=city] input");
    SelenideElement cityClick = $(".menu");
    SelenideElement date = form.$("[data-test-id=date] input");
    SelenideElement name = form.$("[data-test-id=name] input");
    SelenideElement phone = form.$("[data-test-id=phone] input");
    SelenideElement agreement = form.$("[data-test-id=agreement]");
    SelenideElement button = $$("button").find(exactText("Забронировать"));
    SelenideElement notification = $("[data-test-id=notification]");


    private String getFutureDate (int plusDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        LocalDate currentDate = LocalDate.now();
        LocalDate controlDate = currentDate.plusDays(plusDate);
        String formattedControlDate = controlDate.format(formatter);
        return formattedControlDate;
    }


    @BeforeEach
    void openHost() {
        open("http://localhost:9999");
    }

    @Test
    void shouldTest() {
        city.setValue("Новосибирск");
        cityClick.waitUntil(exist, 5000).click();
        date.doubleClick().sendKeys(Keys.BACK_SPACE);
        String futureDay = getFutureDate(3);
        date.setValue(futureDay);
        name.setValue("Иванов Василий");
        phone.setValue("+79270000000");
        agreement.click();
        button.click();
        notification.waitUntil(visible, 15000);
    }
}
